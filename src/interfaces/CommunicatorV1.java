package interfaces;

import java.io.IOException;
import java.net.InetAddress;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map.Entry;
import java.util.Queue;
import java.util.concurrent.LinkedBlockingQueue;

public class CommunicatorV1 implements Communicator {
	private LinkedBlockingQueue<LysMessage> messageQueue;
	private HashMap<String,RemoteClient> clientMap;
	private ServerInterface si;
	
	protected CommunicatorV1 (ServerInterface si, HashMap<String,RemoteClient> clientMap,LinkedBlockingQueue<LysMessage> messageQueue){
	
		this.si = si;
		this.messageQueue = messageQueue;
		this.clientMap = clientMap;
		
	}

	@Override
	public boolean hasMessage() {
		return !messageQueue.isEmpty();
	}

	@Override
	public LysMessage getMessage() throws InterruptedException {
		return messageQueue.take();
	}
	
	/**
	 * Adds an Client to the intern Clientmap
	 */
	@Override
	public boolean addClient(RemoteClient rc) {
		addClientWithoutMessageQueue(rc);
		return true;
	}

	/**
	 * Adds an Client but sets the MessageQueue of the Cleint to the MessageQueue of this Communicator
	 * Use this if you have no clue how this package works
	 * addClientWithoutMessageQueue
	 * @param rc RemoteClient
	 * @return Successstate
	 *
	 * @author Thomas Traxler
	 * @version 09.04.2014
	 */
	public boolean addClientWithoutMessageQueue(RemoteClient rc) {
		rc.setMessageQueue(messageQueue);
		try {
			rc.open();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		clientMap.put(rc.getNodeName(),rc);
		return true;
	}
	@Override
	public HashMap<String,RemoteClient> getClientMap() {
		
		return clientMap;
	}

	@Override
	public RemoteClient getClient(String client) {
		return clientMap.get(client);
	}

	@Override
	public void sendMessage(LysMessage message) throws IOException {
		clientMap.get(message.getDest()).send(message);
		
	}
	/**
	 * Closes all connections nice
	 */
	
	@Override
	public boolean shutdown() {
		si.close();
		Iterator<RemoteClient> i = clientMap.values().iterator();
		while (i.hasNext()){
			i.next().close();
		}
		return true;
	}

	@Override
	public RemoteClient getClientByIP(InetAddress ia) {
		Iterator<RemoteClient> i = clientMap.values().iterator();
		while(i.hasNext()){
			RemoteClient rc = i.next();
			if(rc.getInetAddress()==ia)
				return rc;
		}
		return null;
	}

}
