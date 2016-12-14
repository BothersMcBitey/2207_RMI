package framework;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface NotificationSink extends Remote{
	
	public void Notify(Notification n) throws RemoteException;

}
