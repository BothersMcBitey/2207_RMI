package framework;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface NotificationSource extends Remote{
	
	public void Register(String id) throws RemoteException;

}
