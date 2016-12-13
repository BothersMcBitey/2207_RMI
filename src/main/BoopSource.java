package main;

import java.io.Serializable;
import java.rmi.RemoteException;

public class BoopSource implements NotificationSource, Serializable {

	private static final long serialVersionUID = 1L;

	@Override
	public int boop(int bop) throws RemoteException {
		int p = bop % 10;
		int bo = bop - p;
		
		return (bo * 10) + p;
	}

}
