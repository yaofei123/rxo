package com.refactoring.rxo.springmvc.log.interceptor;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.refactoring.rxo.springmvc.log.LoggerUtils;
import com.refactoring.rxo.springmvc.log.entity.LoggerEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 日志记录所有请求拦截器
 *
 * @author fei.yao
 * @create 2018-01-03 14:20
 **/
public class LogHandlerInterceptor implements HandlerInterceptor {
    private final static Logger LOGGER = LoggerFactory.getLogger("com.refactoring.log.request");


    /**
     * 请求开始时间标识(请求接收时间)
     */
    private static final String LOGGER_RECEIVE_TIME = "LOGGER_RECEIVE_TIME";

    /**
     * 请求日志实体标识
     */
    private static final String LOGGER_ENTITY = "LOGGER_ENTITY";

    /**
     * 进入Controller之前开始记录日志实体
     *
     * @param request  请求对象
     * @param response 响应对象
     * @param object
     * @return
     * @throws Exception
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object object) throws Exception {
        //创建日志实体
        LoggerEntity logger = new LoggerEntity();
        //获取请求sessionId
        String sessionId = request.getRequestedSessionId();
        //请求路径
        String url = request.getRequestURI();
        //获取请求参数信息
        String paramData = JSONObject.toJSONString(request.getParameterMap(), SerializerFeature.DisableCircularReferenceDetect, SerializerFeature.WriteMapNullValue);
        //设置客户端ip
        logger.setClientIp(LoggerUtils.getCliectIp(request));
        //设置请求方法
        logger.setMethod(request.getMethod());
        //设置请求类型（json|普通请求）
        logger.setType(LoggerUtils.getRequestType(request));
        //设置请求参数内容json字符串
        logger.setParamData(paramData);
        //设置请求地址
        logger.setUri(url);
        //设置sessionId
        logger.setSessionId(sessionId);
        //设置请求开始时间
        request.setAttribute(LOGGER_RECEIVE_TIME, System.currentTimeMillis());
        //设置请求实体到request内，方面afterCompletion方法调用
        request.setAttribute(LOGGER_ENTITY, logger);
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {

    }

    /**
     * 进入Controller之后
     *
     * @param request  请求对象
     * @param response 响应对象
     * @param object
     * @param e
     * @return
     * @throws Exception
     */
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object object, Exception e) throws Exception {
        //获取请求错误码
        Integer status = response.getStatus();
        //当前时间
        Long currentTime = System.currentTimeMillis();
        //请求开始时间
        Long time = Long.valueOf(request.getAttribute(LOGGER_RECEIVE_TIME).toString());
        //获取本次请求日志实体
        LoggerEntity loggerEntity = (LoggerEntity) request.getAttribute(LOGGER_ENTITY);
        //设置请求时间差
        loggerEntity.setTimeConsuming(Integer.valueOf((currentTime - time) + ""));
        //设置返回时间
        loggerEntity.setReturnTime(currentTime + "");
        //设置返回错误码
        loggerEntity.setHttpStatusCode(status + "");
        //设置返回值
        loggerEntity.setReturnData(JSONObject.toJSONString(request.getAttribute(LoggerUtils.LOGGER_RETURN),
                SerializerFeature.DisableCircularReferenceDetect,
                SerializerFeature.WriteMapNullValue));
        //执行日志
        LOGGER.info(JSONObject.toJSONString(loggerEntity), SerializerFeature.DisableCircularReferenceDetect, SerializerFeature.WriteMapNullValue);
    }
}
