package com.p.v2iserver.account.annotation.aspect;

import com.alibaba.fastjson.JSON;

import com.p.v2iserver.account.utils.NetUtil;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;

/**
 *
 * TODO
 * @author Pactera
 * @date 2020-11-19 09:53:36
 **/
@Component
@Aspect
public class LogAspect {

	private Logger logger = LoggerFactory.getLogger(getClass());

	private static String PROMETHEUS = "/actuator/prometheus";
	private static String HEALTH = "/actuator/health";

	@Pointcut("execution(* com.p.v2iserver.account.controller.*.*(..))")
	public void logPointcut() {

	}

	@Around("logPointcut()")
	public Object log(ProceedingJoinPoint jp) throws Throwable {
		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes())
				.getRequest();
		Object result = null;
		String path = request.getRequestURI();
		if(PROMETHEUS.equals(path) || HEALTH.equals(path)){
			result = jp.proceed();
		} else {
			// 打印请求信息
			String queryString = JSON.toJSONString(request.getParameterMap());

			// 请求报文为 json 时
			if ("{}".equals(queryString)) {
				try{
					queryString = JSON.toJSONString(jp.getArgs());
				}catch (Exception e) {
					queryString = Arrays.toString(jp.getArgs());
				}
			}
			// 打印耗时及返回结果
			long startTime = System.currentTimeMillis();
			result = jp.proceed();
			long duration = System.currentTimeMillis() - startTime;
			logger.info(String.format("来自%s的%s方法请求 %s,请求参数:%s,响应用时:%sms,返回结果:%s", NetUtil.getIpAddr(request),
					request.getMethod(), request.getRequestURI(), queryString, duration, result));
		}
		return result;
	}
}
