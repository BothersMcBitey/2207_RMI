package main;

public class SecondNotificationSource extends AbstractNotificationSource {
	
	public void run(){
		try {
			while(!Thread.interrupted()){
				Thread.sleep(1000);
				NotifyAll(new SecondNotification());
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
}
