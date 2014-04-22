package Controller;

import gui.MainPanel;
import gui.MyFrame;
import gui.MyMenu;
import gui.old.MyPanel;
import interfaces.CommunicationFactory;
import interfaces.Communicator;

import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.net.URL;
import java.util.ArrayList;
import java.util.Properties;
import java.util.Vector;

import javax.swing.JOptionPane;

import org.apache.log4j.Logger;
import org.teleal.cling.UpnpService;
import org.teleal.cling.UpnpServiceImpl;
import org.teleal.cling.model.message.header.STAllHeader;
import org.teleal.cling.support.igd.PortMappingListener;
import org.teleal.cling.support.model.PortMapping;

import sun.misc.Cleaner;
import ConfigEditor.ConfigEditor;
import ConfigEditor.NoSuchFile;
import ConfigEditor.WrongArgument;

public class Controller{
	private Communicator commu;
	private boolean server;
	private Vector<User> v = new Vector<User>();
	private String propertiespath;
	private int port = -1;
	private MainPanel mg;
	
	public String publicIP;
	public Properties properties;
	
	
	public static MyFrame frame;
	
	public static void main(String[] args) {
		try {
			new Controller("config.properties");
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	public static String getPublicIP(String url){
		try{
			URL whatismyip = new URL(url);
			BufferedReader in = new BufferedReader(new InputStreamReader(whatismyip.openStream()));
			String ip = in.readLine();
			in.close();
			return ip;
		}catch(Exception e){
			e.printStackTrace();
		}
		return null;
	}
	
	public Controller(String propp) throws InterruptedException, IOException{
		Logger l = MyLogger.getInstance();
		
		l.info("Lade Einstellungen...");
		propertiespath = propp;
		FileInputStream fis = new FileInputStream(propp);
		properties = new Properties();
		properties.load(fis);
		l.info("Einstellungen geladen!");
		
		l.info("Nach namen fragen?");
		String oldname = (String) properties.get("Name");
		l.info("Alter Name: " + oldname);
		String name= (String) JOptionPane.showInputDialog(null, "Bitte deinen Namen eingeben:\nLeer für: " + oldname,"Name?", 1);
		if(name == null)
			System.exit(0);
		if(name.equals(""))
			name =oldname;
		name = name.replaceAll(" ", "_");
		properties.put("Name", name);
		l.info("Name lautet: " + name);
		
		l.info("Starte GUI...");
		mg = new MainPanel(this);
		mg.setListData(v);
		
		MyMenu m = new MyMenu(this);
		//v.add(new User("Dominik","Meine IP is geheim :D",null));
		frame = new MyFrame(mg,m, "Test GUI", true);
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
				Controller.this.shutdown();
			}
			
			@Override
			public void windowClosed(WindowEvent arg0) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void windowActivated(WindowEvent arg0) {
				// TODO Auto-generated method stub
				
			}
		});;
		l.info("GUI gestartet!");
		
		l.info("Server starten...");
		commu = CommunicationFactory.makeCommunicator((String)properties.get("Name"),properties);
		new Reciever(this);
		l.info("Server erstellt!");
	}
	
	
	public Communicator getCommu(){
		return commu;
	}
	public void setCommu(Communicator commu){
		this.commu = commu;
	}
	public void setServer(boolean nn){
		this.server = nn;
	}
	public boolean getServer(){
		return this.server;
	}
	public void verbinden(){
		MyLogger.log("Verbinden..");
		try {
			ConfigEditor conf = new ConfigEditor("tinc.conf");
			conf.readAll();
			ArrayList<String> cont = conf.getConnections();
			//File f = new File("hosts"+ File.separator + cont.get(0));
			File f2 = new File("hosts"+ File.separator + (String)properties.get("Name"));
			if(f2.exists()){
				CLI2.start();
				for(int i = 0 ; i  < cont.size(); i++){
					if(!cont.get(i).equalsIgnoreCase((String)properties.get("Name"))){
						ConfigEditor temp = new ConfigEditor("hosts"+ File.separator + cont.get(i));
						String sub = temp.get("Subnet").split("/")[0];
						if(port == -1)
							port = Integer.parseInt((String)properties.get("Port"))+1;
						Socket s = new Socket(sub, port++);
						this.commu.addClient(CommunicationFactory.newClient(s, null,properties.getProperty("Name")));
						User u = new User((String)properties.get("Name"),s.getLocalAddress().getHostAddress(),conf.getContent().getBytes());
						this.commu.sendMessage(CommunicationFactory.newMessage(	commu.getClientByIP(s.getInetAddress()).getNodeName(), (String)properties.get("Name"), this.properties,"Ich bin neu", u));
					}
				}
			}else{
				MyLogger.log("File Nicht gefunden!");
				return;
			}
		} catch (NoSuchFile e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		MyLogger.log("Verbunden!");
	}
	public void addUser(User u){
		v.add(u);
	}
	public void resNachricht(String txt){
		mg.addMessage(txt);
	}
	public void sendNachricht(String txt){
		try {
			if(commu != null)
				this.commu.sendMessage(CommunicationFactory.newMessage(	null, (String)properties.get("Name"), null,"Nachricht", txt));
			mg.addMessage("Ich: " + txt);
		} catch (IOException e) {
			//TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void shutdown(){
		try {
			MyLogger.log("Speichere Einstellungen");
			properties.store(new FileOutputStream(propertiespath), null);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.exit(0);
	}
}
