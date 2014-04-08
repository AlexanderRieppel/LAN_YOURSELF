package interfaces;

import java.io.IOException;
import java.net.ServerSocket;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.concurrent.LinkedBlockingQueue;

class ServerCommunicator extends ServerInterface  {
	private ArrayList<HashMap<String, RemoteClient>> observer;
	private LinkedBlockingQueue<LysMessage> messageQueue;
	private ServerSocket ss;
	private boolean go;
	
	protected ServerCommunicator(LinkedBlockingQueue<LysMessage> messageQueue){
		go= false;
		this.messageQueue = messageQueue;
		observer = new ArrayList<HashMap<String, RemoteClient>>();
		
	}

	@Override
	public void open() throws IOException {
		
		ss = new ServerSocket(25566);//TODO Make dynamic

		go=true;
	}

	@Override
	public void close() {
		// TODO Auto-generated method stub

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
			newClient(CommunicationFactory.newClient(ss.accept(), "test"+(int)(Math.random()*1000), messageQueue));
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
