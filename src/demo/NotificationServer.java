package demo;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

import framework.NotificationSource;

public class NotificationServer {

	public static void main(String[] args){
		try{
			SecondNotificationSource src = new SecondNotificationSource();
			SecondNotificationSource src1 = new SecondNotificationSource();
			
			Registry reg = LocateRegistry.createRegistry(Registry.REGISTRY_PORT);
			NotificationSource stub = (NotificationSource) UnicastRemoteObject.exportObject(src,0);			
			reg.bind("src", stub);	
			src.start();
			NotificationSource stub1 = (NotificationSource) UnicastRemoteObject.exportObject(src1,0);			
			reg.bind("src1", stub1);	
			src1.start();
		}catch (Exception e){
			e.printStackTrace();
		}
	}

}
