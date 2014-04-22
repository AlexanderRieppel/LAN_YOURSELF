package Controller;

import gui.LogWindow;

import java.io.IOException;

import org.apache.log4j.ConsoleAppender;
import org.apache.log4j.FileAppender;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.log4j.PatternLayout;
import org.apache.log4j.jmx.LoggerDynamicMBean;

public class MyLogger {
	private static Logger log;
	private static LogWindow logwin;
	
	public static Logger getInstance() throws IOException{
		if(log != null)
			return log;
		else{
			setup();
			return log;
		}
	}
	private static void setup() throws IOException{
		PatternLayout layout = new PatternLayout( "<%d{yyyy-MM-dd HH:mm:ss}> %m%n" );
		FileAppender app = new FileAppender(layout, "LAN-Yourself.log");
		logwin = new LogWindow(layout);
		log = Logger.getLogger("MainLogger");
		log.addAppender( app );
		log.addAppender(logwin);
		log.addAppender(new ConsoleAppender(layout));
	    log.setLevel( Level.ALL );
	}
	public static void log(String m){
		try{
			if(log == null){
				setup();
			}
			log.info(m);
		}catch(IOException e){
			e.printStackTrace();
		}
	}
	
	public static LogWindow getLogW() throws IOException{
		if(log == null){
			setup();
		}
		return logwin;
		
	}
}
