package proxy.main;

import proxy.dao.Count;
import proxy.dao.impl.CountImpl;


public class CountProxy implements Count{

	private CountImpl countImpl;
	
	public CountProxy(CountImpl countImpl){
		this.countImpl = countImpl;
	}
	
	public void queryCount() {
		// TODO Auto-generated method stub
		System.err.println(" 事务处理 before");
		countImpl.queryCount();
		System.err.println(" 事务处理 after");
	}

	public void updateCount() {
		// TODO Auto-generated method stub
		System.err.println(" 事务处理 before");
		countImpl.updateCount();
		System.err.println(" 事务处理 after");
	}

}
