package main;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface NotificationSource extends Remote{
	
	public int boop(int bop) throws RemoteException;

}
