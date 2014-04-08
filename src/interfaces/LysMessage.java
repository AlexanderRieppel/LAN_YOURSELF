package interfaces;

import java.util.Properties;

public interface LysMessage {
	public String getDest();
	public String getSource();
	public boolean isEncrypted();
	public Head getHead();
	public void setHead(Head head);
	public boolean isRemote();
	public void makeRemote();//Remote Messages can't be further modified except for De- and Encryption
	public void Encrypt (Object key);
	public void Decrypt (Object key);
	
	public String getText();
	public void setText(String text);
	public Properties getOptions();
	public void setOptions(Properties options);
	public Object getLoad();
	public void setLoad(Object load);
}
