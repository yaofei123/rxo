package com.refactoring.rxo.datasource.mybatis;

import com.baomidou.mybatisplus.exceptions.MybatisPlusException;
import com.baomidou.mybatisplus.toolkit.PluginUtils;
import com.baomidou.mybatisplus.toolkit.SqlUtils;
import com.baomidou.mybatisplus.toolkit.StringUtils;
import com.baomidou.mybatisplus.toolkit.SystemClock;
import org.apache.ibatis.executor.statement.StatementHandler;
import org.apache.ibatis.logging.Log;
import org.apache.ibatis.logging.LogFactory;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.plugin.*;
import org.apache.ibatis.reflection.MetaObject;
import org.apache.ibatis.reflection.SystemMetaObject;
import org.apache.ibatis.session.ResultHandler;

import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.sql.Statement;
import java.util.Properties;

/**
 * <p>
 * 性能分析拦截器，用于输出每条 SQL 语句及其执行时间(包含参数)
 * </p>
 *
 * @author xiao eight
 * @Date 2018-05-02
 */
@Intercepts({@Signature(type = StatementHandler.class, method = "query", args = {Statement.class, ResultHandler.class}),
        @Signature(type = StatementHandler.class, method = "update", args = {Statement.class}),
        @Signature(type = StatementHandler.class, method = "batch", args = {Statement.class})})
public class YvanPerformanceInterceptor implements Interceptor {

    private static final Log logger = LogFactory.getLog(YvanPerformanceInterceptor.class);
    private static final String DruidPooledPreparedStatement = "com.alibaba.druid.pool.DruidPooledPreparedStatement";
    private static final String T4CPreparedStatement = "oracle.jdbc.driver.T4CPreparedStatement";
    private static final String OraclePreparedStatementWrapper = "oracle.jdbc.driver.OraclePreparedStatementWrapper";
    private static final String HikariPreparedStatementWrapper = "com.zaxxer.hikari.pool.HikariProxyPreparedStatement";
    /**
     * SQL 执行最大时长，超过自动停止运行，有助于发现问题。
     */
    private long maxTime = 0;
    /**
     * SQL 是否格式化
     */
    private boolean format = false;
    /**
     * 是否写入日志文件<br>
     * true 写入日志文件，不阻断程序执行！<br>
     * 超过设定的最大执行时长异常提示！
     */
    private boolean writeInLog = false;
    private Method oracleGetOriginalSqlMethod;
    private Method druidGetSQLMethod;

    @Override
    public Object intercept(Invocation invocation) throws Throwable {
        Statement statement;
        Object firstArg = invocation.getArgs()[0];
        if (Proxy.isProxyClass(firstArg.getClass())) {
            statement = (Statement) SystemMetaObject.forObject(firstArg).getValue("h.statement");
        } else {
            statement = (Statement) firstArg;
        }
        try {
            statement = (Statement) SystemMetaObject.forObject(statement).getValue("stmt.statement");
        } catch (Exception e) {
            // do nothing
        }

        String originalSql = null;
        String stmtClassName = statement.getClass().getName();
        if (DruidPooledPreparedStatement.equals(stmtClassName)) {
//            try {
//                if (druidGetSQLMethod == null) {
//                    Class<?> clazz = Class.forName(DruidPooledPreparedStatement);
//                    druidGetSQLMethod = clazz.getMethod("getSql");
//                }
//                Object stmtSql = druidGetSQLMethod.invoke(statement);
//                if (stmtSql != null && stmtSql instanceof String) {
//                    originalSql = (String) stmtSql;
//                }
//            } catch (Exception ignored) {
//            }
        } else if (T4CPreparedStatement.equals(stmtClassName)
                || OraclePreparedStatementWrapper.equals(stmtClassName)) {
            try {
                if (oracleGetOriginalSqlMethod != null) {
                    Object stmtSql = oracleGetOriginalSqlMethod.invoke(statement);
                    if (stmtSql != null && stmtSql instanceof String) {
                        originalSql = (String) stmtSql;
                    }
                } else {
                    Class<?> clazz = Class.forName(stmtClassName);
                    oracleGetOriginalSqlMethod = getMethodRegular(clazz, "getOriginalSql");
                    if (oracleGetOriginalSqlMethod != null) {
                        oracleGetOriginalSqlMethod.setAccessible(true);//OraclePreparedStatementWrapper is not a public class, need set this.
                        if (oracleGetOriginalSqlMethod != null) {
                            Object stmtSql = oracleGetOriginalSqlMethod.invoke(statement);
                            if (stmtSql != null && stmtSql instanceof String) {
                                originalSql = (String) stmtSql;
                            }
                        }
                    }
                }
            } catch (Exception e) {
                //ignore
            }
        } else if (HikariPreparedStatementWrapper.equals(stmtClassName)) {
            try {
                Object sqlStatement = SystemMetaObject.forObject(statement).getValue("delegate.sqlStatement");
                if (sqlStatement != null) {
                    originalSql = sqlStatement.toString();
                }
            } catch (Exception e) {
                //ignore
            }
        }
        if (originalSql == null) {
            originalSql = statement.toString();
        }

        int index = originalSql.indexOf(':');
        if (index > 0) {
            originalSql = originalSql.substring(index + 1, originalSql.length());
        }



        // 计算执行 SQL 耗时
        long start = SystemClock.now();
        Object result;
        try{
            result = invocation.proceed();
        }catch (Exception ex){
            //when error happen
            if (this.isWriteInLog()) {
                logger.error("ERROR SQL:" + SqlUtils.sqlFormat(originalSql, format));
            } else {
                System.err.println("ERROR SQL:"+SqlUtils.sqlFormat(originalSql, format));
            }
            throw ex;
        }

        long timing = SystemClock.now() - start;

        // 格式化 SQL 打印执行结果
        Object target = PluginUtils.realTarget(invocation.getTarget());
        MetaObject metaObject = SystemMetaObject.forObject(target);
        MappedStatement ms = (MappedStatement) metaObject.getValue("delegate.mappedStatement");
        StringBuilder formatSql = new StringBuilder();
        formatSql.append(" Time：").append(timing);
        formatSql.append(" ms - ID：").append(ms.getId());
        formatSql.append("\n Execute SQL：").append(SqlUtils.sqlFormat(originalSql, format)).append("\n");
        if (this.isWriteInLog()) {
            if (this.getMaxTime() >= 1 && timing > this.getMaxTime()) {
                logger.error(formatSql.toString());
            } else {
                logger.debug(formatSql.toString());
            }
        } else {
            System.err.println(formatSql.toString());
            if (this.getMaxTime() >= 1 && timing > this.getMaxTime()) {
                throw new MybatisPlusException(" The SQL execution time is too large, please optimize ! ");
            }
        }
        return result;
    }

    @Override
    public Object plugin(Object target) {
        if (target instanceof StatementHandler) {
            return Plugin.wrap(target, this);
        }
        return target;
    }

    @Override
    public void setProperties(Properties prop) {
        String maxTime = prop.getProperty("maxTime");
        String format = prop.getProperty("format");
        if (StringUtils.isNotEmpty(maxTime)) {
            this.maxTime = Long.parseLong(maxTime);
        }
        if (StringUtils.isNotEmpty(format)) {
            this.format = Boolean.valueOf(format);
        }
    }

    public long getMaxTime() {
        return maxTime;
    }

    public YvanPerformanceInterceptor setMaxTime(long maxTime) {
        this.maxTime = maxTime;
        return this;
    }

    public boolean isFormat() {
        return format;
    }

    public YvanPerformanceInterceptor setFormat(boolean format) {
        this.format = format;
        return this;
    }

    public boolean isWriteInLog() {
        return writeInLog;
    }

    public YvanPerformanceInterceptor setWriteInLog(boolean writeInLog) {
        this.writeInLog = writeInLog;
        return this;
    }

    public Method getMethodRegular(Class<?> clazz, String methodName) {
        if (Object.class.equals(clazz)) {
            return null;
        }
        for (Method method : clazz.getDeclaredMethods()) {
            if (method.getName().equals(methodName)) {
                return method;
            }
        }
        return getMethodRegular(clazz.getSuperclass(), methodName);
    }
}
