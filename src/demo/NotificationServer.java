package demo;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

import framework.NotificationSource;

public class NotificationServer {

	public static void main(String[] args){
		try{
			ClickNotificationSource clickSrc = new ClickNotificationSource();			
			SecondNotificationSource secondSrc = new SecondNotificationSource();
			
			Registry reg = LocateRegistry.createRegistry(Registry.REGISTRY_PORT);
			
			NotificationSource secondStub = (NotificationSource) UnicastRemoteObject.exportObject(secondSrc,0);			
			reg.bind("secondSrc", secondStub);			
			NotificationSource clickStub = (NotificationSource) UnicastRemoteObject.exportObject(clickSrc,0);			
			reg.bind("clickSrc", clickStub);	
			
			secondSrc.start();
			clickSrc.start();
		}catch (Exception e){
			e.printStackTrace();
		}
	}

}
