package demo;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

import framework.NotificationSink;

public class NotificationClient{

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
			DemoSink c = new DemoSink(name);
			Registry reg = null;
			
			if(args.length == 3){
				reg = LocateRegistry.getRegistry(args[1], Integer.parseInt(args[2]));
			} else {
				reg = LocateRegistry.getRegistry();
			}
			
			NotificationSink stub = (NotificationSink) UnicastRemoteObject.exportObject(c,0);			
			reg.rebind(name, stub);
			
			c.register("secondSrc");
			c.register("clickSrc");
		}catch (Exception e){
			e.printStackTrace();
		}
	}
}