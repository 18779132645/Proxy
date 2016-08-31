package proxy.advice;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;

public class GreetingAroundAdvice implements MethodInterceptor {

	public Object invoke(MethodInvocation invocation) throws Throwable {
		// TODO Auto-generated method stub
		before();
		Object result = invocation.proceed();
		after();
		return result;
	}
	
	public void before(){
		System.err.println("before");
	}
	
	public void after(){
		System.err.println("after");
	}
}
