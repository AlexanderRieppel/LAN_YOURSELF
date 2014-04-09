package interfaces;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
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
	private InternReceiver r;
	private Thread receiverThread;
	private LinkedBlockingQueue<LysMessage> messageQueue;
	
	protected RemoteClientV1 (Socket s, InetAddress ia, String nodeName, LinkedBlockingQueue<LysMessage> messageQueue, InputStream is, OutputStream os) throws IOException{
		System.out.println("Creatin new client");
		this.nodeName=nodeName;
		this.ia = ia;
		this.s = s;
		this.messageQueue=messageQueue;
		oos = new ObjectOutputStream(os);
		ois = new ObjectInputStream(is);
	}

	@Override
	public void open() throws IOException {
		if(open)
			return;
		System.out.println("Reciever creat");
		r=new InternReceiver(messageQueue,ois);
		receiverThread = new Thread(r);
		receiverThread.start();
		System.out.println("Reciever created");
		open = true;
	}

	@Override
	public void close() {
		// TODO Auto-generated method stub

	}

	@Override
	public void send(LysMessage o) throws IOException {
		System.out.println("Senden vom " + o.getText() + " an "+ o.getDest());
		if(!open)
			open();
		oos.writeObject(o);
		oos.flush();
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

	
	private class InternReceiver implements Runnable{
		private LinkedBlockingQueue<LysMessage> messageQ;
		private ObjectInputStream is;
		private boolean stop;
		public InternReceiver (LinkedBlockingQueue<LysMessage> messageQ,ObjectInputStream is){
			stop=false;
			this.messageQ=messageQ;
			this.is=is;
		}
		@Override
		public void run() {
			
			Object o;
			while (!stop){
				System.out.println("Intern receiver started");
				try {
					o = is.readObject();
					if(o instanceof LysMessage){
						if(nodeName==null)
							nodeName=((LysMessage) o).getDest();
						messageQueue.add((LysMessage) o);
						System.out.println(""+o.toString());
					}
				} catch (ClassNotFoundException | IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}
			
		}
		
	}


	@Override
	public InetAddress getInetAddress() {
		// TODO Auto-generated method stub
		return ia;
	}
}
