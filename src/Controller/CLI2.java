package Controller;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
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
public class CLI2 {
	private static String TINC_PATH = "D:\\Other\\tinc\\tinc.exe";
	private static String WIN_PATH = System.getenv("windir")+"/System32/cmd.exe";
	public static boolean init(String name){
		ProcessBuilder builder = new ProcessBuilder(WIN_PATH);
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
			p_stdin.write(TINC_PATH);
			p_stdin.newLine();
			p_stdin.flush();
			p_stdin.write("init "+name);
			p_stdin.newLine();
			p_stdin.flush();
			p_stdin.write("exit");
			p_stdin.newLine();
			p_stdin.flush();
			return true;
		} catch (IOException e) {
			System.out.println(e);
			return false;
		}
		
	}
	public static boolean start(){
		ProcessBuilder builder = new ProcessBuilder(WIN_PATH);
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
			p_stdin.write(TINC_PATH);
			p_stdin.newLine();
			p_stdin.flush();
			p_stdin.write("start");
			p_stdin.newLine();
			p_stdin.flush();
			return true;
		} catch (IOException e) {
			System.out.println(e);
			return false;
		}
		
	}
	public static boolean restart(){
		ProcessBuilder builder = new ProcessBuilder(WIN_PATH);
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
			p_stdin.write(TINC_PATH);
			p_stdin.newLine();
			p_stdin.flush();
			p_stdin.write("restart");
			p_stdin.newLine();
			p_stdin.flush();
			return true;
		} catch (IOException e) {
			System.out.println(e);
			return false;
		}
		
	}
	public static boolean stop(){
		ProcessBuilder builder = new ProcessBuilder(WIN_PATH);
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
			p_stdin.write(TINC_PATH);
			p_stdin.newLine();
			p_stdin.flush();
			p_stdin.write("stop");
			p_stdin.newLine();
			p_stdin.flush();
			return true;
		} catch (IOException e) {
			System.out.println(e);
			return false;
		}
		
	}
//	public static void main(String[] args) {
//		// init shell
//		ProcessBuilder builder = new ProcessBuilder("C:/Windows/System32/cmd.exe");
//		Process p = null;
//		try {
//			p = builder.start();
//		} catch (IOException e) {
//			System.out.println(e);
//		}
//		// get stdin of shell
//		BufferedWriter p_stdin = new BufferedWriter(new OutputStreamWriter(p.getOutputStream()));
//
//		// execute the desired command (here: ls) n times
//		// int n=10;
//		// for (int i=0; i<n; i++) {
//		try {
//			// single execution
//			p_stdin.write("D:\\Other\\tinc\\tinc.exe");
//			p_stdin.newLine();
//			p_stdin.flush();
//			p_stdin.write("init Alex");
//			p_stdin.newLine();
//			p_stdin.flush();
//		} catch (IOException e) {
//			System.out.println(e);
//		}
//		// }
//		try {
//			p_stdin.write("exit");
//			p_stdin.newLine();
//			p_stdin.flush();
//			// finally close the shell by execution exit command
//		} catch (IOException e) {
//			System.out.println(e);
//		}
//		
//		// write stdout of shell (=output of all commands)
//		String s;
//        System.err.println("Standard output: ");
//		BufferedReader stdInput = new BufferedReader(new InputStreamReader(p.getInputStream()));
//		try {
//			while ((s = stdInput.readLine()) != null) {
//				System.out.println(s);
//			}
//		} catch (IOException e) {
//			System.out.println(e);
//		}
//	}
}
