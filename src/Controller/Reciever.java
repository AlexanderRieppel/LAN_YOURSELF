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
				System.out.println(msg.getText()+"Empfangen! von:" + msg.getSource());
				String name = msg.getSource();
				File f = new File("hosts/"+name);
				if(f.exists())
					f.delete();
				f.createNewFile();
				FileOutputStream fos = new FileOutputStream(f);
				fos.write(msg.getText().getBytes());
				fos.close();
				if(c.getServer()){
					File ff = new File("hosts/"+msg.getDest());
					if(ff.exists()){
						FileInputStream fis = new FileInputStream(ff);
						byte[] tempb = new byte[(int)ff.length()];
						fis.read(tempb);
						fis.close();
						String s = new String(tempb);
						this.c.getCommu().sendMessage(CommunicationFactory.newMessage(name, msg.getDest(), null, s, null));
						CLI2.restart();
					}
				}else{
					ConfigEditor conf = new ConfigEditor("tinc.conf");
					conf.readAll();
					ArrayList<String> cont = new ArrayList<String>();
					cont.add(name);
					conf.addConections(cont);
					conf.writeAll();
					CLI2.start();
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
}
