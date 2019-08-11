package com.ajit.interviewbit;

public class ThreadTest {
	
	private static boolean stop = false;
	
	private static String sharedStr = "default";
	
	public static void main(String[] args) {
		
		final ThreadTest thTest = new ThreadTest();
		
		Thread evenPrinter = new Thread(new Runnable(){
			@Override
			public void run() {
				while(!stop){
					synchronized (thTest) {
						sharedStr = "odd";
						
						try {
							Thread.sleep(2000);
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						for (int i = 0; i < 10000000; i++) {
							for (int j = 0; j < 1000000; j++) {
								
							}
						}
						System.out.println(sharedStr + "=" + 1 );
					}
					
				}
			}
		});
		
		Thread oddPrinter = new Thread(new Runnable(){
			@Override
			public void run() {
				while(!stop){
					synchronized (thTest) {
						sharedStr = "even";
						/*for (int i = 0; i < 10000000; i++) {
							for (int j = 0; j < 1000000; j++) {
								
							}
						}*/
						System.out.println(sharedStr + "=" + 2 );
					}
					
				}
			}
		});
		
		
		evenPrinter.start();
		try {
			evenPrinter.wait();
		} catch (InterruptedException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		oddPrinter.start();
		
		try {
			Thread.sleep(10000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
		
	}

}
