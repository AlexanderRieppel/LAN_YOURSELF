package ConfigEditor;

public class WrongArgument extends Exception{
	public WrongArgument(){
		this("Falsches Argument Übergeben");
	}
	public WrongArgument(String msg){
		super(msg);
	}

}
