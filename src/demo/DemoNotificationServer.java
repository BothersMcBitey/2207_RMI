package demo;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

import framework.NotificationSource;

public class DemoNotificationServer {

	public static void main(String[] args){
		try{
			ClickNotificationSource clickSrc = new ClickNotificationSource();			
			SecondNotificationSource secondSrc = new SecondNotificationSource();
			
			Registry reg = null;
			if(args.length == 1){
				reg = LocateRegistry.createRegistry(Integer.parseInt(args[0]));
			} else if(args.length == 0){
				reg = LocateRegistry.createRegistry(Registry.REGISTRY_PORT);
			} else {
				System.err.println("Incorrect number of arguments, correct usage is:" + System.getProperty("line.separator") + 
						"java NotificationServer [port]");
				System.exit(1);
			}
			
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
