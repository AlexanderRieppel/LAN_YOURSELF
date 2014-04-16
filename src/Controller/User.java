package Controller;

public class User {
	private String name;
	private String ip;
	private byte[] host;
	
	public User(){
		name= "";
		ip = "";
		host = null;
	}

	public User(String name, String ip, byte[] host) {
		super();
		this.name = name;
		this.ip = ip;
		this.host = host;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public byte[] getHost() {
		return host;
	}

	public void setHost(byte[] host) {
		this.host = host;
	}
	
	@Override
	public String toString(){
		return name;
	}
	
}
