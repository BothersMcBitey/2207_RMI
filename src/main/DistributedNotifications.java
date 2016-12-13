package main;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

public class DistributedNotifications {

	public static void main(String[] args){
		try{
			BoopSource src = new BoopSource();
			Registry reg = LocateRegistry.createRegistry(Registry.REGISTRY_PORT);
			NotificationSource stub = (NotificationSource) UnicastRemoteObject.exportObject(src,0);
			
			reg.bind("src", stub);	
		}catch (Exception e){
			e.printStackTrace();
		}
	}

}
