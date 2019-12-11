package com.team404.util.aop;

import java.util.Arrays;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.team404.controller.HomeController;

@Aspect
@Component
public class LogAdvice {

	//이 프로젝트의 모든 서비스에 대해서 , target, args, ignature의 실행 정보를 출력하는 aop
	
	//log4j.xml에 선언
//	<logger name="com.team404.util.aop">
//		<level value="info" />
//	</logger>
	//로그를 찍을수있게 처리하는 기능
	private static final Logger log = LoggerFactory.getLogger(LogAdvice.class);
	
	@Around("execution(* com.team404.*.service.*ServiceImpl.*(..))")
	public Object service(ProceedingJoinPoint jp) {

		
		log.info("==적용 클래스==: "+jp.getTarget());
		log.info("==적용 파라미터==: "+Arrays.toString(jp.getArgs()));
		log.info("==적용 메서드==: "+jp.getSignature());
		
		long start = System.currentTimeMillis();
		Object result = null;
		
		System.out.println("=======실행전(before)=======");
		try {
			result = jp.proceed();
		} catch (Throwable e) {
			e.printStackTrace();
		}
		
		System.out.println("=======실행후(after)=======");
		long end = System.currentTimeMillis();
		log.info("===메서드 수행 시간===: "+(end - start)*0.001+"초");
		
		return result;
	}
	
	
}
