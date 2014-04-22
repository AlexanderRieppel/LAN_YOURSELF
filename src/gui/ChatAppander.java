package gui;

import java.util.ArrayList;

import javax.swing.JTextArea;

import org.apache.log4j.AppenderSkeleton;
import org.apache.log4j.spi.LoggingEvent;


public class ChatAppander extends AppenderSkeleton{
	ArrayList<LoggingEvent> eventsList = new ArrayList();
	private JTextArea text;
	
	public ChatAppander(JTextArea a){
		this.text = a;
	}
	
	@Override
	public void close() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean requiresLayout() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	protected void append(LoggingEvent arg0) {
		this.text.setText(this.text.getText() + this.getLayout().format(arg0));
		eventsList.add(arg0);
		
	}

}
