package proxy.test;

import org.junit.Test;
import org.springframework.aop.framework.ProxyFactory;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Profile;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import proxy.advice.GreetAfterAndBeforeAdvice;
import proxy.advice.GreetingAfterAdvice;
import proxy.advice.GreetingAroundAdvice;
import proxy.advice.GreetingBeforeAdvice;
import proxy.advice.GreetingThrowAdvice;
import proxy.dao.BookFacade;
import proxy.dao.Greeting;
import proxy.dao.impl.BookFacadeImpl;
import proxy.dao.impl.CountImpl;
import proxy.dao.impl.GreetingImpl;
import proxy.main.BookFacadeProxy;
import proxy.main.BookFacadeProxyCglib;
import proxy.main.CountProxy;

public class TestProxy {
	
	@Test
	/**
	 * 注解式 Spring Aop 增墙技术
	 */
	public void ShengSpringAop(){
		ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
		Greeting greeting = (Greeting) context.getBean("greetingProxy");
		greeting.sayHello("注解式 Spring Aop 增墙技术");
	}
	@Test
	/**
	 * Spring Aop 增墙技术
	 */
	public void SpringAop(){
		ProxyFactory proxyFactory = new ProxyFactory();
		proxyFactory.setTarget(new GreetingImpl());
//		proxyFactory.addAdvice(new GreetAfterAndBeforeAdvice());	//前置和后置增强放在一起实现
//		proxyFactory.addAdvice(new GreetingBeforeAdvice());			//前置增强
//		proxyFactory.addAdvice(new GreetingAfterAdvice());			//后置增强
//		proxyFactory.addAdvice(new GreetingAroundAdvice()); 		//环绕增强
		proxyFactory.addAdvice(new GreetingThrowAdvice()); 		//抛出增强
		
		Greeting greeting = (Greeting) proxyFactory.getProxy();
		greeting.sayHello("hello");
	}

	@Test
	/**
	 * JDK动态代理
	 */
	public void JDKProxy(){
		BookFacadeProxy proxy = new BookFacadeProxy();
		BookFacade bookproxy = (BookFacade) proxy.bind(new BookFacadeImpl());
		bookproxy.addBook();
	}
	@Test
	/**
	 * Cglib动态代理
	 */
	public void CglibProxy(){
		BookFacadeProxyCglib cglib = new BookFacadeProxyCglib();
		BookFacadeImpl bookcglib = (BookFacadeImpl) cglib.getInstance(new BookFacadeImpl());
		bookcglib.addBook();
	}
	
	@Test
	/**
	 * 静态代理,不推荐使用
	 */
	public void NoProxy(){
		CountImpl countImpl = new CountImpl();
		CountProxy countProxy = new CountProxy(countImpl);
		countProxy.updateCount();
		System.err.println();
		countProxy.queryCount();
	}
}
