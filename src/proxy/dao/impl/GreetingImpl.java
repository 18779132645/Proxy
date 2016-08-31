package proxy.dao.impl;

import org.springframework.stereotype.Component;

import proxy.dao.Greeting;

@Component
public class GreetingImpl implements Greeting {

	public void sayHello(String name) {
		// TODO Auto-generated method stub
		System.err.println("GreetingImpl:"+name);
		
		throw new RuntimeException("ERROR");
	}

}
