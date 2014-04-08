package Controller;

import gui.MainGUI;
import gui.MyFrame;
import gui.MyPanel;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.net.URL;
import java.util.ArrayList;
import java.util.Properties;

import Verbindung.ClientInterface;
import Verbindung.Reciever;
import Verbindung.ServerInterface;
import sun.misc.Cleaner;
import ConfigEditor.ConfigEditor;
import ConfigEditor.NoSuchFile;
import ConfigEditor.WrongArgument;

public class Controller implements Reciever{
	private ArrayList<ClientInterface> clients = new ArrayList<ClientInterface>();
	private ServerInterface server;
	
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
		try {
			FileInputStream fis = new FileInputStream("config.properties");
			Properties prop = new Properties();
			prop.load(fis);
			CLI2.TINC_PATH = (String)prop.get("path");
			CLI2.IP = (String)prop.get("ip");
			CLI2.Interface = (String)prop.get("Interface");
			CLI2.Port = (String)prop.get("Port");
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String url = "http://checkip.amazonaws.com";
		String ip = getPublicIP(url);
		if(ip != null){
			System.out.println(ip);
			MyPanel mg = new MyPanel();
			mg.textField_2.setText(ip);
			new MyFrame(mg, "Test GUI", true);
			//Server Starten
			server = null;
			if(server != null)
				server.open();
		}
		
	}
	
	
	@Override
	public void handle(Object o) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void addClient(Socket s) {
		// TODO Auto-generated method stub
		
	}

}
