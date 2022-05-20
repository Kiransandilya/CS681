package edu.umb.cs681.hw009;

import java.util.concurrent.atomic.AtomicReference;

public class ConcurrentSingleton implements Runnable{
	private ConcurrentSingleton(){};
	private static AtomicReference<ConcurrentSingleton> aCS = new AtomicReference<>();
	public static ConcurrentSingleton getInstance(){
		
		ConcurrentSingleton cs = aCS.get();
		
		if(cs == null) {
			cs = new ConcurrentSingleton();
			
			if(!aCS.compareAndSet(null, cs)) {
				cs = aCS.get();
			}
		}
		return cs;
	}
	@Override
	public void run() {
		System.out.println(ConcurrentSingleton.getInstance());
	}
	
	public static void main(String[] args){
		for(int i=0; i<10; i++){
			Thread t = new Thread(new ConcurrentSingleton());
			t.start();
		}
		
	}

	
}