package demo;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

import framework.NotificationSink;

public class NotificationClient{

	public static void main(String[] args){
		try{
			DemoSink c = new DemoSink();
			
			Registry reg = LocateRegistry.getRegistry(); 
			NotificationSink stub = (NotificationSink) UnicastRemoteObject.exportObject(c,0);			
			reg.rebind("sink", stub);
			
			c.register();
		}catch (Exception e){
			e.printStackTrace();
		}
	}
	
	public NotificationClient() {
		
	}
}
