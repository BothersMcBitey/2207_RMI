package demo;

import framework.AbstractNotificationSource;

public class SecondNotificationSource extends AbstractNotificationSource {
	
	@Override
	public void run(){
		try {
			while(!Thread.interrupted()){
				Thread.sleep(1000);
				QueueNotification(new SecondNotification());
				System.out.println("S");
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
}
