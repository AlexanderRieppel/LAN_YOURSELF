package Controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;

import ConfigEditor.ConfigEditor;
import ConfigEditor.NoSuchFile;
import interfaces.CommunicationFactory;
import interfaces.LysMessage;

public class Reciever implements Runnable{
	private Controller c;
	private boolean run;
	public Reciever(Controller c){
		this.c = c;
		run = true;
		new Thread(this).start();
	}
	@Override
	public void run() {
		while(run){
			try {
				LysMessage msg = c.getCommu().getMessage();
				String name = msg.getSource();
				MyLogger.log("Nachricht von: " + name);
				
				String text = msg.getText();
				
				if(text.equalsIgnoreCase("Ich bin neu")){
					User u = (User)msg.getLoad();
					File f = new File("hosts" + File.separator + name);
					if(f.exists())
						f.delete();
					f.createNewFile();
					FileOutputStream fos = new FileOutputStream(f);
					fos.write(u.getHost());
					fos.close();
					this.c.addUser(u);
					sendHosts(name);
				}
				if(text.equalsIgnoreCase("Ich lebe")){
					
				}
				if(text.equalsIgnoreCase("Nachricht")){
					String txt = (String)msg.getLoad();
					this.c.resNachricht(msg.getSource() + ": " + txt);
				}
				if(text.equalsIgnoreCase("Den gibts")){
					User u = (User)msg.getLoad();
					File f = new File("hosts" + File.separator + name);
					if(f.exists())
						f.delete();
					f.createNewFile();
					FileOutputStream fos = new FileOutputStream(f);
					fos.write(u.getHost());
					fos.close();
					this.c.addUser(u);
					ConfigEditor conf = new ConfigEditor("tinc.conf");
					conf.readAll();
					conf.addConection(u.getName());
					conf.writeAll();CLI2.restart();
				}
				
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (NoSuchFile e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	public void stop(){
		this.run = false;
	}
	
	public void sendHosts(String to){
		try{
		File hosts = new File("hosts");
		String[] hh = hosts.list();
		for(int i = 0; i < hh.length;i++){
			if(!hh[i].equalsIgnoreCase(to)){
				MyLogger.log("Senden von: " + hh[i]);
				ConfigEditor conf = new ConfigEditor("hosts" + File.separator + hh[i]);
				User u = new User(hh[i], conf.get("Subnet").split("/")[0],conf.getContent().getBytes());
				this.c.getCommu().sendMessage(CommunicationFactory.newMessage(	hh[i], (String)this.c.properties.get("Name"), null,"Den gibts", u));
			}
		}
		}catch(NoSuchFile e){
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
//try {
//	LysMessage msg = c.getCommu().getMessage();
//	MyLogger.log("Empfangen von:" + msg.getSource());
//	String name = msg.getSource();
//	File f = new File("hosts/"+name);
//	if(f.exists())
//		f.delete();
//	f.createNewFile();
//	FileOutputStream fos = new FileOutputStream(f);
//	fos.write(msg.getText().getBytes());
//	fos.close();
//	if(c.getServer()){
//		File ff = new File("hosts/"+msg.getDest());
//		if(ff.exists()){
//			FileInputStream fis = new FileInputStream(ff);
//			byte[] tempb = new byte[(int)ff.length()];
//			fis.read(tempb);
//			fis.close();
//			String s = new String(tempb);
//			this.c.getCommu().sendMessage(CommunicationFactory.newMessage(name, msg.getDest(), null, s, null));
//			CLI2.restart();
//		}
//	}else{
//		ConfigEditor conf = new ConfigEditor("tinc.conf");
//		conf.readAll();
//		ArrayList<String> cont = new ArrayList<String>();
//		cont.add(name);
//		conf.addConections(cont);
//		conf.writeAll();
//		CLI2.start();
//	}
//} catch (InterruptedException e) {
//	// TODO Auto-generated catch block
//	e.printStackTrace();
//} catch (IOException e) {
//	// TODO Auto-generated catch block
//	e.printStackTrace();
//} catch (NoSuchFile e) {
//	// TODO Auto-generated catch block
//	e.printStackTrace();
//}