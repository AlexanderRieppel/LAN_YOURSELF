package ConfigEditor;

public class WrongArgument extends Exception{
	public WrongArgument(){
		this("Falsches Argument �bergeben");
	}
	public WrongArgument(String msg){
		super(msg);
	}

}
