package ConfigEditor;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class ConfigEditor {
	private String path;
	private File file;
	private String content;
	
	public ConfigEditor(String path) throws NoSuchFile{
		this.path = path;
		file = new File(this.path);
		if(!file.exists())
			throw new NoSuchFile();
	}
	
	public void readAll(){
		try {
			BufferedReader reader = new BufferedReader(new FileReader(this.file));
			this.content = "";
			while(reader.ready()){
				String line = reader.readLine();
//				System.out.println(line);
				if(line != null)
					this.content += line + "\n";
			}
			reader.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public String get(String key){
		if(key.equalsIgnoreCase("ConnectTo"))
			return null;
		if(this.content.contains(key)){
			int eins = this.content.indexOf(key);
			int zwei = this.content.indexOf(" = ",eins);
			String temp = "";
			for(int i = zwei+3; this.content.charAt(i) != '\n' ; i++){
				temp += this.content.charAt(i);
			}
			return temp;
		}else
			return null;
	}
	public void remove(String key){
		if(this.content.contains(key)){
			int eins = this.content.indexOf(key);
			String temp = "";
			for(int i = eins; this.content.charAt(i) != '\n' ; i++){
				temp += this.content.charAt(i);
			}
			this.content = this.content.replaceFirst(temp + "\n", "");
		}
	}
	public ArrayList<String> getConnections(){
		String key = "ConnectTo";
		ArrayList<String> returns = new ArrayList<String>();
		if(this.content.contains(key)){
			int eins = 0;
			int zwei = 0;
			while(eins >= 0){
				eins = this.content.indexOf(key,zwei);
				zwei = this.content.indexOf(" = ",eins);
				if(eins != -1){
					String temp = "";
					for(int i = zwei+3; this.content.charAt(i) != '\n' ; i++){
						temp += this.content.charAt(i);
					}
					returns.add(temp);
				}
//				System.out.println(eins);
			}
			return returns;
		}else
			return null;
	}
	public void addConections(ArrayList<String> neu){
		for(String temp : neu){
			this.content = "ConnectTo = " + temp + "\n" + this.content; 
		}
	}
	public void addConection(String neu){
		this.content = "ConnectTo = " + neu + "\n" + this.content; 
	}
	public void removeConections(ArrayList<String> rem){
		for(String temp : rem){
			if(this.content.contains(temp)){
				String[] args = this.content.split("\n");
				for(int i = 0; i < args.length;i++)
					if(args[i].contains(temp))
						args[i] = "";
				this.content = "";
				for(String t :  args)
					if(t != "")
						this.content += t + "\n";
			}
		}
	}
	public void set(String key, String value) throws WrongArgument{
		if(key.equalsIgnoreCase("ConnectTo"))
			throw new WrongArgument();
		String[] args = this.content.split("\n");
		boolean found = false;
		for(int i = 0; i < args.length;i++){
			if(args[i].contains(key)){
				args[i] = args[i].replaceAll(this.get(key), value);
				found = true;
				break;
			}
		}
		String temp = "";
		for(int i = 0; i < args.length;i++)
			if(args[i] != null)
				temp += args[i] + "\n";
		
		this.content = temp;
		if(!found)
			this.content = key + " = " + value + "\n\n" + this.content;
	}
	public void writeAll(){
		try{
			file.delete();
			file.createNewFile();
			FileOutputStream fos = new FileOutputStream(this.file);
			fos.write(this.content.getBytes());
			fos.close();
		}catch(IOException e){
			e.printStackTrace();
		}
	}
	public String getContent(){
		return this.content;
	}
}
