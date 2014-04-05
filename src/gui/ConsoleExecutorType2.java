package gui;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.Scanner;

/**
 * Also works with tinc.exe CLI xD
 * 
 * Don't forget to set the path to your tinc.exe
 * 
 * Only thing is that you need to shut down the program manually since i 
 * tried but failed to send a CTRL-C Keystroke
 * @author Alex
 *
 */
public class ConsoleExecutorType2 {
	public static void main(String[] args) {
		// init shell
		ProcessBuilder builder = new ProcessBuilder("C:/Windows/System32/cmd.exe");
		Process p = null;
		try {
			p = builder.start();
		} catch (IOException e) {
			System.out.println(e);
		}
		// get stdin of shell
		BufferedWriter p_stdin = new BufferedWriter(new OutputStreamWriter(p.getOutputStream()));

		// execute the desired command (here: ls) n times
		// int n=10;
		// for (int i=0; i<n; i++) {
		try {
			// single execution
			p_stdin.write("D:\\Other\\tinc\\tinc.exe");
			p_stdin.newLine();
			p_stdin.flush();
			p_stdin.write("init Alex");
			p_stdin.newLine();
			p_stdin.flush();
		} catch (IOException e) {
			System.out.println(e);
		}
		// }
		try {
			p_stdin.write("exit");
			p_stdin.newLine();
			p_stdin.flush();
			// finally close the shell by execution exit command
		} catch (IOException e) {
			System.out.println(e);
		}

		// write stdout of shell (=output of all commands)
		Scanner s = new Scanner(p.getInputStream());
		while (s.hasNext()) {
				System.out.println(s.next());
			
		}
		s.close();
	}
}
