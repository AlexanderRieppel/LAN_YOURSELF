package ConfigEditor;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;

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
	public void set(String key, String value){
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
			this.content = key + " = " + value + "\n" + this.content;
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
}
