package interfaces;

import java.io.IOException;
import java.util.HashMap;

/**
 * Serverrolle der Kommunikation
 * @author Traxl
 *
 */
public abstract class ServerInterface extends Thread{
	public abstract void open() throws IOException;
	public abstract void close() throws IOException;
	public abstract void addObserverList(HashMap<String, RemoteClient> clientMap);
	//"Serversocket"
}
