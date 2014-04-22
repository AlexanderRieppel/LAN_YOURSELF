package gui;

import javax.swing.JPanel;

import Controller.Controller;
import Controller.User;

import java.awt.BorderLayout;
import java.awt.List;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JList;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import org.apache.log4j.Logger;
import org.apache.log4j.PatternLayout;

public class MainPanel extends JPanel{
	private Controller contoroller;
	private JList<User> userlist;
	private JTextField userinput;
	private JButton send;
	private JTextArea messages;
	private Logger chat = Logger.getLogger("Chat");
	
	public MainPanel(Controller con){
		this.contoroller = con;
		setLayout(new BorderLayout(0, 0));
		
		userlist = new JList<User>();
		userinput = new JTextField();
		send = new JButton();
		messages = new JTextArea();
		
		ChatAppander app = new ChatAppander(messages);
		app.setLayout(new PatternLayout("%d{yyyy-MM-dd HH:mm:ss} => %m%n" ));
		chat.addAppender(app);
		
		send.setText("Senden");
		
		userlist.addMouseListener(new MouseAdapter() {
		    public void mouseClicked(MouseEvent evt) {
		        JList list = (JList)evt.getSource();
		        if (evt.getClickCount() >= 2) {
		        	new ListItem((User)list.getSelectedValue(),Controller.frame.getX() + Controller.frame.getWidth()+10, Controller.frame.getY());
		        }
		    }
		});
		
		
		JPanel p1 = new JPanel();
		p1.setLayout(new BorderLayout(0,0));
		p1.add(send,BorderLayout.EAST);
		p1.add(userinput,BorderLayout.CENTER);
		
		add(new JScrollPane(messages),BorderLayout.CENTER);
		add(p1,BorderLayout.SOUTH);
		add(new JScrollPane(userlist),BorderLayout.EAST);
		
		send.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				buttonklick();
			}
		});
		
		userinput.addKeyListener(new KeyListener() {
			
			@Override
			public void keyTyped(KeyEvent arg0) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void keyReleased(KeyEvent arg0) {
				if(arg0.getKeyCode() == KeyEvent.VK_ENTER)
					buttonklick();
			}
			
			@Override
			public void keyPressed(KeyEvent arg0) {
				// TODO Auto-generated method stub
				
			}
		});
		
		messages.setEditable(false);
		
	}
	
	public void addMessage(String nmsg){
		chat.info(nmsg);
	}
	
	public void setListData(Vector<User> v){
		userlist.setListData(v);
	}
	private void buttonklick(){
		String msg = userinput.getText();
		userinput.setText("");
		this.contoroller.sendNachricht(msg);
	}
}
