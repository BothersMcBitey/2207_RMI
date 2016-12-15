package framework;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class BasicNotificationSink implements NotificationSink {

	protected String name, hostname;
	protected int port;
	
	public BasicNotificationSink(String name, String hostname, int port){
		this.name = name;
		this.hostname = hostname;
		this.port = port;
	}

	@Override
	public void Notify(Notification n) {
		System.out.println(n.toString());
	}

	public void register(String id) throws RemoteException, NotBoundException{	
		getSource(id).Register(name);
	}
	
	private NotificationSource getSource(String id) throws RemoteException, NotBoundException{
		Registry reg = LocateRegistry.getRegistry();
		return (NotificationSource) reg.lookup(id);				
	}
	
	public void unRegister(String id) throws RemoteException, NotBoundException{					
		getSource(id).UnRegister(name);		
	}
}
