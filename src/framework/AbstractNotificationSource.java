package framework;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;

public abstract class AbstractNotificationSource extends Thread implements NotificationSource {
	
	private class MessageBroker extends Thread{
		private AbstractNotificationSource src;
		
		public MessageBroker(AbstractNotificationSource src) {
			this.src = src;
		}
		
		@Override
		public void run(){
			while(!isInterrupted()){
				src.SendNotifications();
			}
		}
	}

	protected Map<String, NotificationSink> register;
	protected Map<String, Queue<Notification>> messageQueues;
	
	protected MessageBroker messenger;
	
	public AbstractNotificationSource() {
		super();
		register = new HashMap<String, NotificationSink>();
		messageQueues = new HashMap<String, Queue<Notification>>();
		messenger = new MessageBroker(this);
		messenger.start();
	}
	
	//run is used to generate notifications
	@Override
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
			if(register.containsKey(id)){
				register.replace(id, sink);
			} else {
				register.put(id, sink);
				messageQueues.put(id, new LinkedList<Notification>());;
				System.out.println("New sink registered");
			}
		}
	}
	
	protected void QueueNotification(Notification n){
		Collection<String> keys;
		synchronized(register){
			keys = register.keySet();
		}
		for(String key: keys){
			messageQueues.get(key).add(n);				
		}		
	}
	
	protected void SendNotifications(){
		Collection<String> keys;
		synchronized(register){
			keys = register.keySet();			
		}
		for(String key: keys){
			Queue<Notification> q = messageQueues.get(key);
			NotificationSink sink;
			synchronized(register){
				sink = register.get(key);
			}			
			boolean connected = true;
			
			while(connected && !q.isEmpty()){
				try {
					sink.Notify(q.peek());
//					System.out.println("notified");
					q.remove();
				} catch (RemoteException e) {
					connected = false;
//					System.err.println("sink dropped");
				}				
			}
		}		
	}
}
