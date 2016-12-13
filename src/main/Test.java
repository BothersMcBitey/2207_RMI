package main;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class Test implements NotificationSink {


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
		}catch (Exception e){
			e.printStackTrace();
		}
	}

}
