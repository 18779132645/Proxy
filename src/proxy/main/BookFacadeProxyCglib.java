package proxy.main;

import java.lang.reflect.Method;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

public class BookFacadeProxyCglib implements MethodInterceptor{

	private Object target;
	
	public Object getInstance(Object target){
		this.target = target;
		Enhancer enhancer = new Enhancer();
		enhancer.setSuperclass(this.target.getClass());
		enhancer.setCallback(this);
		return enhancer.create();
	}
	
	public Object intercept(Object obj, Method method, Object[] arg2,
			MethodProxy proxy) throws Throwable {
		System.err.println("BookFacadeProxyCglib start ");
		proxy.invokeSuper(obj, arg2);
		System.err.println("BookFacadeProxyCglib end");
		return null;
	}

}
