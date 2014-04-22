package gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.JCheckBoxMenuItem;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

import Controller.CLI2;
import Controller.Controller;
import Controller.MyLogger;

public class MyMenu extends JMenuBar{
	public static JCheckBoxMenuItem logItem = new JCheckBoxMenuItem();
	private Controller controller;
	
	public MyMenu(Controller con){
		this.controller = con;
		
		JMenu verbindung = new JMenu();
		verbindung.setText("Verbindung");
		
		JMenuItem cone = new JMenuItem();
		cone.setText("Connect to...");
		cone.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				MyLogger.log("Aktion bei " + e.getSource().getClass().getSimpleName() + " \"Connect to...\"");
				
			}
		});
		
		JMenuItem verbinden = new JMenuItem();
		verbinden.setText("Verbinden");
		verbinden.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				MyLogger.log("Aktion bei " + e.getSource().getClass().getSimpleName() + " Verbinden");
				MyMenu.this.controller.verbinden();
			}
		});
		JMenuItem trennen = new JMenuItem();
		trennen.setText("Trennen");
		trennen.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				MyLogger.log("Aktion bei " + e.getSource().getClass().getSimpleName() + " Trennen");
				CLI2.stop();
			}
		});
		
		
		verbindung.add(cone);
		verbindung.add(verbinden);
		verbindung.add(trennen);
		
		JMenu datei = new JMenu();
		datei.setText("Datei");
		
		 
		logItem.setText("Log");
		logItem.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
					try {
						MyLogger.log("Aktion bei " + arg0.getSource().getClass().getSimpleName() + " Log");
						MyLogger.getLogW().setVisible(logItem.isSelected());
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
			}
		});
		
		JMenuItem exit = new JMenuItem();
		exit.setText("Beenden");
		exit.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				MyLogger.log("Aktion bei " + e.getSource().getClass().getSimpleName() + " Beenden");
				MyMenu.this.controller.shutdown();
			}
		});
		
		datei.add(logItem);
		datei.add(exit);
		
		add(datei);
		add(verbindung);
	}
}
