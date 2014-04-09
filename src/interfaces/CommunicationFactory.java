package interfaces;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
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
	public static Communicator makeCommunicator (String localeNodeName, Properties p){
		//if(comm == null){
		HashMap<String,RemoteClient> clientMap = new  HashMap<String,RemoteClient> ();
		LinkedBlockingQueue<LysMessage> messageQueue = new LinkedBlockingQueue<LysMessage>();
		ServerCommunicator sc = new ServerCommunicator(messageQueue,localeNodeName,p);
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
	 * @throws IOException 
	 */
	public static RemoteClient newClient(Socket s,  LinkedBlockingQueue<LysMessage> messageQueue, String localeNodeName) throws IOException{
		OutputStream os = s.getOutputStream();
		InputStream is = s.getInputStream();
		PrintWriter pw = new PrintWriter(os);
		pw.write(localeNodeName+"\n");
		pw.flush();
		System.out.println("NAME SENT");
		BufferedReader br = new BufferedReader(new InputStreamReader(is));
		RemoteClientV1 rc = new RemoteClientV1(s, s.getInetAddress(),br.readLine(), messageQueue,is,os);
		rc.open();
		System.out.println("client created");
		return rc;
	}

}
