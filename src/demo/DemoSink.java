package demo;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

import framework.Notification;
import framework.NotificationSink;
import framework.NotificationSource;

public class DemoSink implements NotificationSink {


	@Override
	public void Notify(Notification n) {
		System.out.println("notified");
		System.out.println(n.toString());
	}

	public void register(){	
		try{
			Registry reg = LocateRegistry.getRegistry();
			NotificationSource src = (NotificationSource) reg.lookup("src");	
			
			src.Register("sink");
			
			NotificationSource src1 = (NotificationSource) reg.lookup("src1");	
			
			src1.Register("sink");
		}catch (Exception e){
			e.printStackTrace();
		}
	}

}
