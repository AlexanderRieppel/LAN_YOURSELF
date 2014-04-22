package gui;

import java.awt.BorderLayout;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import org.apache.log4j.AppenderSkeleton;
import org.apache.log4j.Layout;
import org.apache.log4j.spi.LoggingEvent;

public class LogWindow extends AppenderSkeleton{
	private JFrame frame;
	private JPanel mp;
	private JTextArea log;
	
	public LogWindow(Layout l){
		this.setLayout(l);
		frame = new JFrame();
		frame.pack();
		frame.setLayout(new BorderLayout());
		frame.setTitle("Log");
		frame.setBounds(50, 50, 500, 250);
		mp = new JPanel(new BorderLayout());
		log = new JTextArea();
		log.setEditable(false);
		mp.add(new JScrollPane(log));
		
		frame.add(mp);
		frame.setVisible(false);
		//this.setJMenuBar(m);
		frame.addWindowListener(new WindowListener() {
			
			@Override
			public void windowOpened(WindowEvent arg0) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void windowIconified(WindowEvent arg0) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void windowDeiconified(WindowEvent arg0) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void windowDeactivated(WindowEvent arg0) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void windowClosing(WindowEvent arg0) {
				MyMenu.logItem.doClick();
			}
			
			@Override
			public void windowClosed(WindowEvent arg0) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void windowActivated(WindowEvent arg0) {
				// TODO Auto-generated method stub
				
			}
		});
	}
	@Override
	public void close() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean requiresLayout() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	protected void append(LoggingEvent arg0) {
		log.setText(log.getText() + this.getLayout().format(arg0));
	}
	
	public void setVisible(boolean b){
		frame.setVisible(b);
	}
	public boolean getVisible(){
		return frame.isVisible();
	}

}
