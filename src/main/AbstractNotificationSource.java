package main;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;

public abstract class AbstractNotificationSource extends Thread implements NotificationSource {

	protected List<NotificationSink> register;
	protected Map<NotificationSink, Queue<Notification>> messageQueues;
	
	public AbstractNotificationSource() {
		super();
		register = new ArrayList<NotificationSink>();
		messageQueues = new HashMap<NotificationSink, Queue<Notification>>();
	}
	
	public abstract void run();
	
	@Override
	public void Register(String id) throws RemoteException {
		Registry reg = LocateRegistry.getRegistry();
		NotificationSink sink;
		
		try {
			sink = (NotificationSink) reg.lookup(id);
		} catch (NotBoundException e) {
			throw new RemoteException("", e);
		}
		
		synchronized(register){
			if(register.contains(sink)){
				throw new RemoteException("Already registered with this source.");
			} else {
				register.add(sink);
				messageQueues.put(sink, new LinkedList<Notification>());
				System.out.println("New sink registered");
			}
		}
	}
	
	protected void QueueNotification(Notification n){
		synchronized(register){
			for(NotificationSink sink : register){
				messageQueues.get(sink).add(n);				
			}
		}
	}
	
	protected void SendNotifications(){
		NotificationSink[] it = null;
		synchronized(register){
			 it = register.toArray(it);
		}
		for(NotificationSink sink : it){
			Queue<Notification> q = messageQueues.get(sink);
			boolean connected = true;
			
			while(connected && !q.isEmpty()){
				try {
					sink.Notify(q.peek());
				} catch (RemoteException e) {
					connected = false;
				}
				q.remove();
			}
		}		
	}

	protected void NotifyAll(Notification n){
		System.out.println("notifying");
		synchronized(register){
			for(NotificationSink sink : register){
				try {								
					sink.Notify(n);
				} catch (RemoteException e) {
					System.err.println(e.getMessage());
				}
			}
		}
	}
}
