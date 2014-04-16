package Controller;

import gui.MainPanel;
import gui.MyFrame;
import gui.MyMenu;
import interfaces.CommunicationFactory;
import interfaces.Communicator;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.net.URL;
import java.util.ArrayList;
import java.util.Properties;
import java.util.Vector;

import sun.misc.Cleaner;
import ConfigEditor.ConfigEditor;
import ConfigEditor.NoSuchFile;
import ConfigEditor.WrongArgument;

public class Controller{
	private Communicator commu;
	private boolean server;
	private Vector<User> v = new Vector<User>();
	
	public static void main(String[] args) {
		new Controller();

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
	
	public Controller(){
		MainPanel mg = new MainPanel(this);
		mg.setListData(v);
		
		MyMenu m = new MyMenu(this);
		
		new MyFrame(mg,m, "Test GUI", true);
//		try {
//			FileInputStream fis = new FileInputStream("config.properties");
//			Properties prop = new Properties();
//			prop.load(fis);
//			fis.close();
//			CLI2.TINC_PATH = (String)prop.get("path");
//			CLI2.IP = (String)prop.get("ip");
//			CLI2.Interface = (String)prop.get("Interface");
//			CLI2.Port = (String)prop.get("Port");
//		} catch (FileNotFoundException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		String url = "http://checkip.amazonaws.com";
//		String ip = getPublicIP(url);
//		if(ip != null){
//			System.out.println(ip);
//			MyPanel mg = new MyPanel(this);
//			mg.textField_2.setText(ip);
//			new MyFrame(mg, "Test GUI", true);
//			
//		}
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
}
