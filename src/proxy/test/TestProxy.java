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
	 * ע��ʽ Spring Aop ��ǽ����
	 */
	public void ShengSpringAop(){
		ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
		Greeting greeting = (Greeting) context.getBean("greetingProxy");
		greeting.sayHello("ע��ʽ Spring Aop ��ǽ����");
	}
	@Test
	/**
	 * Spring Aop ��ǽ����
	 */
	public void SpringAop(){
		ProxyFactory proxyFactory = new ProxyFactory();
		proxyFactory.setTarget(new GreetingImpl());
//		proxyFactory.addAdvice(new GreetAfterAndBeforeAdvice());	//ǰ�úͺ�����ǿ����һ��ʵ��
//		proxyFactory.addAdvice(new GreetingBeforeAdvice());			//ǰ����ǿ
//		proxyFactory.addAdvice(new GreetingAfterAdvice());			//������ǿ
//		proxyFactory.addAdvice(new GreetingAroundAdvice()); 		//������ǿ
		proxyFactory.addAdvice(new GreetingThrowAdvice()); 		//�׳���ǿ
		
		Greeting greeting = (Greeting) proxyFactory.getProxy();
		greeting.sayHello("hello");
	}

	@Test
	/**
	 * JDK��̬����
	 */
	public void JDKProxy(){
		BookFacadeProxy proxy = new BookFacadeProxy();
		BookFacade bookproxy = (BookFacade) proxy.bind(new BookFacadeImpl());
		bookproxy.addBook();
	}
	@Test
	/**
	 * Cglib��̬����
	 */
	public void CglibProxy(){
		BookFacadeProxyCglib cglib = new BookFacadeProxyCglib();
		BookFacadeImpl bookcglib = (BookFacadeImpl) cglib.getInstance(new BookFacadeImpl());
		bookcglib.addBook();
	}
	
	@Test
	/**
	 * ��̬����,���Ƽ�ʹ��
	 */
	public void NoProxy(){
		CountImpl countImpl = new CountImpl();
		CountProxy countProxy = new CountProxy(countImpl);
		countProxy.updateCount();
		System.err.println();
		countProxy.queryCount();
	}
}
