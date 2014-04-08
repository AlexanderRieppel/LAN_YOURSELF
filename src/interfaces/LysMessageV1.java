package interfaces;

import java.util.Properties;

public class LysMessageV1 implements LysMessage {
	private String dest;
	private String source;
	private boolean encrypted; 
	private Head head;
	private boolean remote;
	
	private String text;
	private Properties options;
	private Object load;
	
	protected LysMessageV1(String destination, String source,Properties options, String text, Object load,Head head){
		this.dest=destination;
		this.source=source;
		this.encrypted=false;
		this.head=head;
		this.remote=false;
		this.text=text;
		this.options=options;
		this.load=load;
	}

	@Override
	public String getDest() {
		// TODO Auto-generated method stub
		return dest;
	}

	@Override
	public String getSource() {
		// TODO Auto-generated method stub
		return source;
	}

	@Override
	public boolean isEncrypted() {
		// TODO Auto-generated method stub
		return encrypted;
	}

	@Override
	public Head getHead() {
		// TODO Auto-generated method stub
		return head;
	}

	@Override
	public void setHead(Head head) {
		this.head = head;

	}

	@Override
	public boolean isRemote() {
		// TODO Auto-generated method stub
		return remote;
	}

	@Override
	public void makeRemote() {
		remote=true;

	}

	@Override
	public void Encrypt(Object key) {
		// TODO Auto-generated method stub

	}

	@Override
	public void Decrypt(Object key) {
		// TODO Auto-generated method stub

	}

	@Override
	public String getText() {
		
		return text;
	}

	@Override
	public void setText(String text) {
		if(remote)
			return;
		this.text=text;
	}

	@Override
	public Properties getOptions() {
		// TODO Auto-generated method stub
		return options;
	}

	@Override
	public void setOptions(Properties options) {
		if(remote)
			return;
		this.options=options;

	}

	@Override
	public Object getLoad() {
		
		return load;
	}

	@Override
	public void setLoad(Object load) {
		if(remote)
			return;
		this.load=load;

	}

}
