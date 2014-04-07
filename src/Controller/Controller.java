package Controller;

import gui.MainGUI;
import gui.MyFrame;
import gui.MyPanel;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;

import ConfigEditor.ConfigEditor;
import ConfigEditor.NoSuchFile;

public class Controller {

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
			ConfigEditor e = new ConfigEditor("tinc.conf");
			e.readAll();
			System.out.println(e.get("Interface"));
		} catch (NoSuchFile e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
//		String url = "http://checkip.amazonaws.com";
//		String ip = getPublicIP(url);
//		if(ip != null){
//			System.out.println(ip);
//			MyPanel mg = new MyPanel();
//			mg.textField_2.setText(ip);
//			new MyFrame(mg, "Test GUI", true);
//		}
	}

}
