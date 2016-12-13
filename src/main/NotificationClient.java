package main;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

public class NotificationClient{

	public static void main(String[] args){
		try{
			Test c = new Test();
			
			Registry reg = LocateRegistry.getRegistry();
			NotificationSink stub = (NotificationSink) UnicastRemoteObject.exportObject(c,0);			
			reg.bind("sink", stub);
			
			c.register();
		}catch (Exception e){
			e.printStackTrace();
		}
	}
	
	public NotificationClient() {
		
	}
}
