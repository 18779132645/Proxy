package proxy.main;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
/**
 * JDK 动态代理类
 * @author HP
 *
 */
public class BookFacadeProxy implements InvocationHandler {

	private Object target;
	
	public Object bind(Object target){
		this.target = target;
		return Proxy.newProxyInstance(target.getClass().getClassLoader(),
				target.getClass().getInterfaces(), this);
	}
	
	public Object invoke(Object proxy, Method method, Object[] args)
			throws Throwable {
		Object result = null;
		System.err.println("BookFacadeProxy "+method.getName()+" start ");
		result = method.invoke(target, args);
		System.err.println("BookFacadeProxy "+method.getName()+" end ");
		return result;
	}

}
