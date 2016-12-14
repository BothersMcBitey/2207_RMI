package demo;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.WindowConstants;

import framework.AbstractNotificationSource;

public class ClickNotificationSource extends AbstractNotificationSource {

	
	
	public ClickNotificationSource() {
		JFrame frame = new JFrame("ClickNotificationWindow");
		
		frame.setBounds(0, 0, 1000, 1000);
		
		JPanel pane = new JPanel(null);
		frame.setContentPane(pane);
		
		pane.addMouseListener(new MouseListener() {
			
			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseClicked(MouseEvent e) {
				QueueNotification(new ClickNotification(e.getPoint()));
			}
		});
		
		frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		
		frame.setVisible(true);
	}
	
	@Override
	public void run() {
		System.out.println("Waiting for clicks...");
	}

}
