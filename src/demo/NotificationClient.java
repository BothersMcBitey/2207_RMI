package demo;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

import framework.NotificationSink;

public class NotificationClient{

	public static void main(String[] args){
		try{
			String name = args[0];
			DemoSink c = new DemoSink(name);
			
			Registry reg = LocateRegistry.getRegistry(); 
			NotificationSink stub = (NotificationSink) UnicastRemoteObject.exportObject(c,0);			
			reg.rebind(name, stub);
			
			c.register("secondSrc");
			c.register("clickSrc");
		}catch (Exception e){
			e.printStackTrace();
		}
	}
}