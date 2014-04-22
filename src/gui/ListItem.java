package gui;

import java.awt.BorderLayout;
import java.awt.GridLayout;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import Controller.User;

public class ListItem {
	
	public ListItem(User u,int x, int y){
		JFrame frame = new JFrame();
		frame = new JFrame();
		frame.pack();
		frame.setLayout(new BorderLayout());
		frame.setTitle("Log");
		frame.setBounds(x, y, 300, 150);
		
		JPanel p = new JPanel(new GridLayout(2,2));
		JLabel name = new JLabel("Name:");
		JLabel ip = new JLabel("IP:");
		
		JLabel namet = new JLabel(u.getName());
		JLabel ipt = new JLabel(u.getIp());
		
		p.add(name);
		p.add(namet);
		
		p.add(ip);
		p.add(ipt);
		
		frame.add(p);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	}
}
