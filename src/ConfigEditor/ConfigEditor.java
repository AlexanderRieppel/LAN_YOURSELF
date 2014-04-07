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
			while(reader.ready())
				this.content += reader.readLine() + "\n";
			reader.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public String get(String key){
		if(this.content.contains(key)){
			int first = this.content.indexOf(key);
			int secound = this.content.indexOf(" = ",first);
			String temp = "";
			for(int i = secound+3; this.content.charAt(i) != '\n' ; i++){
				temp += this.content.charAt(i);
			}
			return temp;
		}else
			return null;
	}
	
	public void writeAll(){
		try{
			file.delete();
			file.createNewFile();
			FileOutputStream fos = new FileOutputStream(this.file);
			fos.write(this.content.getBytes());
		}catch(IOException e){
			e.printStackTrace();
		}
	}
}
