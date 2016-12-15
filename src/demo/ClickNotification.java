package demo;

import java.awt.Point;

import framework.Notification;

public class ClickNotification implements Notification {

	private static final long serialVersionUID = 1L;
	
	private Point p;

	public ClickNotification(Point p) {
		this.p = p;
	}
	
	public String toString(){
		return "Click at " + p.toString();
	}
}
