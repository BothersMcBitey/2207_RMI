package demo;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

import framework.Notification;
import framework.NotificationSink;
import framework.NotificationSource;

public class DemoSink implements NotificationSink {

	private String name;
	
	public DemoSink(String name) {
		this.name = name;
	}

	@Override
	public void Notify(Notification n) {
		System.out.println("notified");
		System.out.println(n.toString());
	}

	public void register(String id){	
		try{
			Registry reg = LocateRegistry.getRegistry();
			NotificationSource src = (NotificationSource) reg.lookup(id);				
			src.Register(name);
		}catch (Exception e){
			e.printStackTrace();
		}
	}

}
