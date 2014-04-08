package Verbindung;

import java.net.Socket;

public interface Reciever {
	public void handle(Object o);
	public void addClient(Socket s);
}
