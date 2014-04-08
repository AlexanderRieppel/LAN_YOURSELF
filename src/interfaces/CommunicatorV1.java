package interfaces;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
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

	@Override
	public boolean addClient(RemoteClient rc) {
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
	 * Closes all connection nice
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

}
