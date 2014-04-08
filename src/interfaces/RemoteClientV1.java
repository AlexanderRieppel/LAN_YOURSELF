package interfaces;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.util.concurrent.LinkedBlockingQueue;

public class RemoteClientV1 implements RemoteClient {
	private String nodeName;
	private InetAddress ia;
	private Socket s;
	private ObjectInputStream ois;
	private ObjectOutputStream oos;
	private boolean open = false;
	private Receiver r;
	private LinkedBlockingQueue<LysMessage> messageQueue;
	
	protected RemoteClientV1 (Socket s, InetAddress ia, String nodeName, LinkedBlockingQueue<LysMessage> messageQueue){
		this.nodeName=nodeName;
		this.ia = ia;
		this.s = s;
		this.messageQueue=messageQueue;
	}

	@Override
	public void open() throws IOException {
		
		oos = new ObjectOutputStream(s.getOutputStream());
		ois = new ObjectInputStream(s.getInputStream());
		r=new Receiver(messageQueue,ois);
		open = true;
	}

	@Override
	public void close() {
		// TODO Auto-generated method stub

	}

	@Override
	public void send(LysMessage o) throws IOException {
		oos.writeObject(o);
	}

	@Override
	public LysMessage getmessage() throws InterruptedException {
		return messageQueue.take();
	}

	@Override
	public String getNodeName() {
		
		return nodeName.toString();
	}

	@Override
	public void setMessageQueue(LinkedBlockingQueue<LysMessage> messageQueue) {
		this.messageQueue= messageQueue;
	}

	
	private class Receiver implements Runnable{
		private LinkedBlockingQueue<LysMessage> messageQ;
		private ObjectInputStream is;
		private boolean stop;
		public Receiver (LinkedBlockingQueue<LysMessage> messageQ,ObjectInputStream is){
			stop=false;
			this.messageQ=messageQ;
			this.is=is;
		}
		@Override
		public void run() {
			Object o;
			while (!stop){

				try {
					o = is.readObject();
					if(o instanceof LysMessage){
						messageQueue.add((LysMessage) o);
					}
				} catch (ClassNotFoundException | IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}
			
		}
		
	}
}
