package interfaces;

import java.io.IOException;
import java.util.concurrent.LinkedBlockingQueue;

/**
 *  Clientrolle der Kommunikation
 * @author Thomas Traxler <ttraxler@student.tgm.ac.at>
 * @version 08.04.2014
 */
public interface RemoteClient{
	public void open() throws IOException;
	public void close();
	public void send(LysMessage o) throws IOException;
	public void setMessageQueue(LinkedBlockingQueue<LysMessage> messageQueue);
	public LysMessage getmessage() throws InterruptedException;
	public String getNodeName();
	//Weiterleiten an handleInput
}
