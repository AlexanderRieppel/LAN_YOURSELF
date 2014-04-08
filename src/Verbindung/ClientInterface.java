package Verbindung;

public interface ClientInterface {
	public void open();
	public void close();
	public void send(Object o);
	//Weiterleiten an handleInput
}
