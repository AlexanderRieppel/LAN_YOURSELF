package gui;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

import Controller.Controller;

public class MyMenu extends JMenuBar{
	private Controller controller;
	
	public MyMenu(Controller con){
		this.controller = con;
		
		JMenu menu = new JMenu();
		menu.setText("Verbindung");
		
		JMenuItem cone = new JMenuItem();
		cone.setText("Connect to...");
		
		JMenuItem vv = new JMenuItem();
		vv.setText("Verbinden");
		
		menu.add(cone);
		menu.add(vv);
		
		add(menu);
	}
}
