package main;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

public class NotificationServer {

	public static void main(String[] args){
		try{
			SecondNotificationSource src = new SecondNotificationSource();
			Registry reg = LocateRegistry.createRegistry(Registry.REGISTRY_PORT);
			NotificationSource stub = (NotificationSource) UnicastRemoteObject.exportObject(src,0);			
			reg.bind("src", stub);	
			src.start();
		}catch (Exception e){
			e.printStackTrace();
		}
	}

}
