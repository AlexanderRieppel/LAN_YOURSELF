package gui;

import interfaces.CommunicationFactory;

import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import ConfigEditor.ConfigEditor;
import ConfigEditor.NoSuchFile;
import ConfigEditor.WrongArgument;
import Controller.CLI2;
import Controller.Controller;
import Controller.Reciever;

public class MyPanel extends JPanel{
	private JTextField textField_1;
	private JTextField textField_3;
	public JTextField textField_2;
	private JPanel panel_1,panel_2,panel_3;
	private Controller c;
	public MyPanel(Controller c){
		this.c = c;
		setLayout(new GridLayout(4, 1, 0, 0));
		
		JPanel panel = new JPanel();
		add(panel);
		panel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		JLabel lblConnectTo = new JLabel("Connect To:");
		panel.add(lblConnectTo);
		
		textField_1 = new JTextField();
		panel.add(textField_1);
		textField_1.setColumns(10);
		
		panel_1 = new JPanel();
		add(panel_1);
		panel_1.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		JLabel lblYourName = new JLabel("Your Name:");
		panel_1.add(lblYourName);
		
		textField_3 = new JTextField();
		panel_1.add(textField_3);
		textField_3.setColumns(10);
		textField_3.setText("");
		
		panel_2 = new JPanel();
		add(panel_2);
		
		JLabel lblYourPublicIp = new JLabel("Your Public IP:");
		panel_2.add(lblYourPublicIp);
		
		textField_2 = new JTextField();
		panel_2.add(textField_2);
		textField_2.setColumns(10);
		
		panel_3 = new JPanel();
		add(panel_3);
		panel_3.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		JButton btnConnect = new JButton("Connect");
		panel_3.add(btnConnect);
		btnConnect.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				System.out.println(CLI2.TINC_PATH);
				final String to = textField_1.getText();
				final String name = textField_3.getText();
				final String publicip = textField_2.getText();
				if(to != null && name != null && publicip != null){
					if(name != "" && publicip != ""){
//						CLI2.stop();
						File f = new File("tinc.conf");
						if(f.exists())
							f.delete();
						f = new File("ecdsa_key.priv");
						if(f.exists())
							f.delete();
						f = new File("rsa_key.priv");
						if(f.exists())
							f.delete();
						f = new File("hosts/"+name);
						if(f.exists())
							f.delete();
						CLI2.init(name);
						new Thread(new Runnable() {
							
							@Override
							public void run() {
								try {
									Thread.sleep(3000);
								} catch (InterruptedException e1) {
									// TODO Auto-generated catch block
									e1.printStackTrace();
								}
								System.out.println("Beginn edit!");
								try {
									ConfigEditor conf = new ConfigEditor("tinc.conf");
									conf.readAll();
									conf.set("port", CLI2.Port);
									conf.set("Interface", CLI2.Interface);
									conf.set("ExperimentalProtocol","no");
//									System.out.println("totest:"+to+"ENDTOTEST");
//									if(!to.equalsIgnoreCase("")){
////										System.out.println("TOOTO");
//										ArrayList<String> tt = new ArrayList<String>();
//										tt.add(to);
//										conf.addConections(tt);
//									}
									conf.writeAll();
								} catch (NoSuchFile e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								} catch (WrongArgument e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								}
								try {
									ConfigEditor conf = new ConfigEditor("hosts/"+name);
									conf.readAll();
									conf.set("port", CLI2.Port);
									conf.set("Subnet", CLI2.IP);
									conf.set("Address",publicip);
									conf.writeAll();
									if(!to.equalsIgnoreCase("")){
										System.out.println("Client!!!");
										MyPanel.this.c.setCommu(CommunicationFactory.makeCommunicator(name));
										Socket s = new Socket(to, 25566);
										MyPanel.this.c.getCommu().addClient(CommunicationFactory.newClient(s, null,name));
										System.out.println("Connect");
										MyPanel.this.c.getCommu().sendMessage(CommunicationFactory.newMessage(	MyPanel.this.c.getCommu().getClientByIP(s.getInetAddress()).getNodeName(), name, null, conf.getContent() ,null ));
										System.out.println("Send");
										MyPanel.this.c.setServer(false);
										new Reciever(MyPanel.this.c);
										System.out.println("Fin");
									}else{
										System.out.println("Server!!!");
										MyPanel.this.c.setCommu(CommunicationFactory.makeCommunicator(name));
										new Reciever(MyPanel.this.c);
										MyPanel.this.c.setServer(true);
										System.out.println(CLI2.start());
										System.out.println("Fin");
									}
								} catch (NoSuchFile e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								} catch (WrongArgument e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								} catch (IOException e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								}
								//VPN starten
								
								System.out.println("VPN gestartet!");
							}
						}).start();
						
					}
				}
			}
		});
	}
}
