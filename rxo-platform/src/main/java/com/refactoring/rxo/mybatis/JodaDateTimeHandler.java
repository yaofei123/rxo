package com.refactoring.rxo.mybatis;

import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.MappedJdbcTypes;
import org.apache.ibatis.type.MappedTypes;
import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.springframework.util.StringUtils;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@MappedJdbcTypes(JdbcType.TIMESTAMP)
@MappedTypes(DateTime.class)
public class JodaDateTimeHandler extends BaseTypeHandler<DateTime> {

    private static final String PATTERN = "yyyy-MM-dd HH:mm:ss";
    private static final DateTimeFormatter FORMATTER = DateTimeFormat.forPattern(PATTERN);

    @Override
    public void setNonNullParameter(PreparedStatement ps, int i, DateTime parameter, JdbcType jdbcType) throws SQLException {
        if (parameter != null) {
            if (parameter.getZone().getOffset(parameter) != 28800000) {
                //不是中国时间
                parameter = parameter.withZone(DateTimeZone.forOffsetHours(8));
            }
            ps.setString(i, parameter.toString(PATTERN));
        } else {
            ps.setString(i, null);
        }
    }

    @Override
    public DateTime getNullableResult(ResultSet rs, String columnName) throws SQLException {
        String sqlTimestamp = rs.getString(columnName);
        if (!StringUtils.isEmpty(sqlTimestamp)) {
            if (sqlTimestamp.endsWith(".0")) {
                sqlTimestamp = sqlTimestamp.substring(0, sqlTimestamp.length() - 2);
            }
            return FORMATTER.parseDateTime(sqlTimestamp);
        }
        return null;
    }

    @Override
    public DateTime getNullableResult(ResultSet rs, int columnIndex) throws SQLException {
        String sqlTimestamp = rs.getString(columnIndex);
        if (!StringUtils.isEmpty(sqlTimestamp)) {
            if (sqlTimestamp.endsWith(".0")) {
                sqlTimestamp = sqlTimestamp.substring(0, sqlTimestamp.length() - 2);
            }
            return FORMATTER.parseDateTime(sqlTimestamp);
        }
        return null;
    }

    @Override
    public DateTime getNullableResult(CallableStatement cs, int columnIndex) throws SQLException {
        String sqlTimestamp = cs.getString(columnIndex);
        if (!StringUtils.isEmpty(sqlTimestamp)) {
            if (sqlTimestamp.endsWith(".0")) {
                sqlTimestamp = sqlTimestamp.substring(0, sqlTimestamp.length() - 2);
            }
            return FORMATTER.parseDateTime(sqlTimestamp);
        }
        return null;
    }

    public static void main(String[] args) {
        System.out.println(FORMATTER.parseDateTime(new DateTime().toString(PATTERN)));

    }
}
