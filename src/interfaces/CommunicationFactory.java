package interfaces;

import java.net.Socket;
import java.util.HashMap;
import java.util.Properties;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * Factory welche Communicator Objects returned
 * @author Thomas Traxler <ttraxler@student.tgm.ac.at>
 * @version 08.04.2014
 */
public class CommunicationFactory {
	//private static Communicator comm = null;
	
	/**
	 * Returns a new Communicator who is in Charge for the whole communication between the nodes
	 * @return
	 */
	public static Communicator makeCommunicator (){
		//if(comm == null){
		HashMap<String,RemoteClient> clientMap = new  HashMap<String,RemoteClient> ();
		LinkedBlockingQueue<LysMessage> messageQueue = new LinkedBlockingQueue<LysMessage>();
		ServerCommunicator sc = new ServerCommunicator(messageQueue);
		sc.addObserverList(clientMap);
		sc.start();
		 CommunicatorV1 comm = new CommunicatorV1(sc,clientMap, messageQueue);
		//}
		
		
		return comm;
	}
	/**
	 * Creates a new Message to be sent
	 * @return
	 */
	public static LysMessage newMessage(String destination, String source, Properties options, String text, Object load){
		return new LysMessageV1(destination, source, options, text, load, null);
	}
	/**
	 * Creates a new remtoeClient from a Socket
	 * @param s
	 * @param nodeName
	 * @param messageQueue
	 * @return
	 */
	public static RemoteClient newClient(Socket s, String nodeName,  LinkedBlockingQueue<LysMessage> messageQueue){
		return new RemoteClientV1(s, s.getInetAddress(), nodeName, messageQueue);
	}

}
