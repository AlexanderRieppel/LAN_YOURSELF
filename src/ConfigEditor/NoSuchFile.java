package ConfigEditor;

public class NoSuchFile extends Exception{
	public NoSuchFile(){
		this("Kein Solches File gefunden");
	}
	public NoSuchFile(String msg){
		super(msg);
	}

}
