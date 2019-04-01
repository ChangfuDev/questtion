package cn.edu.dlnu.question.aspect;

import java.util.Arrays;
import javax.servlet.http.HttpServletRequest;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;


/**
 * @program: e3
 * @description: ${description}
 * @author: 张家豪
 * @create: 2018-10-21 21:23
 **/
@Aspect
@Component
public  class BaseAspect {

    private Logger logger = LoggerFactory.getLogger(this.getClass().getName().toString());

	@Pointcut("execution(public * cn.edu.dlnu.question.*.*.*.*(..))")
	public void addLog(){
	}

	@Before("addLog()")
	public void doBefor(JoinPoint joinPoint){
		logger.info("-------------------------------------------开始请求-------------------------------------------");
		this.logServiceMessage(joinPoint);
	}

	@AfterReturning(returning = "ret",pointcut = "addLog()")
	public void afterRunningItemController(Object ret){
		logger.info("返回数据\t"+ret);
	}

	@AfterThrowing("addLog()")
	public void throwss(JoinPoint jp){
		logger.info("-------------------------------------------请求出现异常" +
				"-------------------------------------------");
	}
	@After("addLog()")
	public void after(JoinPoint jp){
		logger.info("-------------------------------------------结束请求" +
				"-------------------------------------------");
	}

	public void logControllerMessage(JoinPoint joinPoint){
		ServletRequestAttributes requestAttributes =(ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
		HttpServletRequest request = requestAttributes.getRequest();
		String url = request.getRequestURI();
		String httpMethod = request.getMethod();
		String ip = request.getRemoteAddr();
		String classMethod = joinPoint.getSignature().getDeclaringTypeName() +"."+ joinPoint.getSignature().getName();
		String args = Arrays.toString(joinPoint.getArgs());
		logger.info("url:\t"+url + "\tmethod:\t"+httpMethod + "\t请求者的ip:\t"+ip);
		logger.info("classMethod:\t" + classMethod + "\targs:\t" +args);
		logger.info("用户的session:\t"+request.getSession().getId());
	}
	public void logServiceMessage(JoinPoint joinPoint){

		String classMethod = joinPoint.getSignature().getDeclaringTypeName() +"."+ joinPoint.getSignature().getName();
		String args = Arrays.toString(joinPoint.getArgs());
		logger.info("classMethod:\t" + classMethod + "\targs:\t" +args);

	}


}
