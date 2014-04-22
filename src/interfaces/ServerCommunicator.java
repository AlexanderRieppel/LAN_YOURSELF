package interfaces;

import java.io.IOException;
import java.net.ServerSocket;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Properties;
import java.util.concurrent.LinkedBlockingQueue;

class ServerCommunicator extends ServerInterface  {
	private ArrayList<HashMap<String, RemoteClient>> observer;
	private LinkedBlockingQueue<LysMessage> messageQueue;
	private ServerSocket ss;
	private String localeNodeName;
	private boolean go;
	private Properties properties;
	
	protected ServerCommunicator(LinkedBlockingQueue<LysMessage> messageQueue, String localeNodeName, Properties p){
		go= false;
		this.messageQueue = messageQueue;
		observer = new ArrayList<HashMap<String, RemoteClient>>();
		this.localeNodeName=localeNodeName;
		this.properties = p;
	}

	@Override
	public void open() throws IOException {
		
		ss = new ServerSocket(Integer.parseInt((String)properties.get("javaport")));

		go=true;
	}

	@Override
	public void close() throws IOException {
		ss.close();
		go=false;

	}

	@Override
	public void run(){
		if(ss==null)
			try {
				open();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		while(go){
		try {
			newClient(CommunicationFactory.newClient(ss.accept(), messageQueue,localeNodeName));
			System.out.println("New client");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		}
	}

	@Override
	public void addObserverList(HashMap<String, RemoteClient> clientMap) {
		observer.add(clientMap);
		
	}
	private void newClient(RemoteClient rc){
		rc.setMessageQueue(messageQueue);
		Iterator<HashMap<String, RemoteClient>> i = observer.iterator();
		while(i.hasNext())
			i.next().put(rc.getNodeName(),	rc);
	}
}
