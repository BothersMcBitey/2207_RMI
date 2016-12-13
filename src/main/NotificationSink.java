package main;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Random;

public class NotificationSink {

	public static void main(String[] args) {
		try{
			Registry reg = LocateRegistry.getRegistry();
			NotificationSource src = (NotificationSource) reg.lookup("src");
			for(int i = 11; i < 1000; i += 3*(new Random().nextInt(7)))
			System.out.println(src.boop(i));
		}catch (Exception e){
			e.printStackTrace();
		}
	}

}
