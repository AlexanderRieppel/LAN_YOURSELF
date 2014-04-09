package interfaces;

import java.io.IOException;
import java.net.InetAddress;
import java.util.ArrayList;
import java.util.HashMap;
/**
 * Programmschnittstelle der Kommunikation
 * @author Thomas Traxler <ttraxler@student.tgm.ac.at>
 * @version 08.04.2014
 */
public interface Communicator {
	
	public boolean hasMessage();
	public LysMessage getMessage() throws InterruptedException;
	public void sendMessage(LysMessage message) throws IOException;
	public boolean addClient(RemoteClient rc);
	public RemoteClient getClient (String client);
	HashMap<String, RemoteClient> getClientMap();
	public boolean shutdown();
	public RemoteClient getClientByIP(InetAddress ia);
	
}
