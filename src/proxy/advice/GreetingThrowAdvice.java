package proxy.advice;

import java.lang.reflect.Method;

import org.springframework.aop.ThrowsAdvice;

public class GreetingThrowAdvice implements ThrowsAdvice {

	public void afterThrowing(Method method, Object[] args, Object target, Exception e){
		System.err.println("-------------afterThrowing----------------");
		System.err.println("Target Class:"+target.getClass().getName());
		System.err.println("Method Name:"+method.getName());
		System.err.println("Exception Message:"+e.getMessage());
		System.err.println("-----------------------------");
	}
}
