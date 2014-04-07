package Controller;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;

public class Controller {

	public static void main(String[] args) {
		new Controller();

	}
	public static String getPublicIP(String url){
		try{
			URL whatismyip = new URL(url);
			BufferedReader in = new BufferedReader(new InputStreamReader(
			                whatismyip.openStream()));
	
			String ip = in.readLine(); //you get the IP as a String
			in.close();
			return ip;
		}catch(Exception e){
			e.printStackTrace();
		}
		return null;
	}
	
	public Controller(){
		String url = "http://checkip.amazonaws.com";
		String ip = getPublicIP(url);
		if(ip != null){
			System.out.println(ip);
		}
	}

}
