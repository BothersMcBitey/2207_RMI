package demo;

import java.text.SimpleDateFormat;
import java.util.Date;

import framework.Notification;

public class SecondNotification implements Notification{

	private static final long serialVersionUID = 1L;
	private Date time;
	
	public SecondNotification(){
		time = new Date();
	}
	
	@Override
	public String toString() {
		return "A second has passed, the time is now: " + System.getProperty("line.separator") + 
				new SimpleDateFormat("mm:ss").format(time);
	}
	
}