package demo;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

import framework.BasicNotificationSink;
import framework.NotificationSink;

public class DemoNotificationClient{

	public static void main(String[] args){
		try{
			if(args.length != 1 && args.length != 3){							
				System.err.println("Incorrect number of arguments, correct usage is:" + System.getProperty("line.separator") + 
						"java NotificationClient [client_id] [host] [port]" + System.getProperty("line.separator") +
						"OR:" + System.getProperty("line.separator") +
						"java NotificationClient [client_id]");
				System.exit(1);
			}
			
			String name = args[0];
			String hostname = "localhost";
			int port = Registry.REGISTRY_PORT;
			Registry reg = null;
			
			if(args.length == 3){
				reg = LocateRegistry.getRegistry(args[1], Integer.parseInt(args[2]));
				hostname = args[1];
				port = Integer.parseInt(args[2]);
			} else {
				reg = LocateRegistry.getRegistry();
			}
			
			BasicNotificationSink c = new BasicNotificationSink(name, hostname, port);
			
			NotificationSink stub = (NotificationSink) UnicastRemoteObject.exportObject(c,0);			
			reg.rebind(name, stub);
			
			c.register("secondSrc");
//			c.register("clickSrc");
			
			Thread.sleep(10000);
			c.unRegister("secondSrc");
		}catch (Exception e){
			e.printStackTrace();
		}
	}
}