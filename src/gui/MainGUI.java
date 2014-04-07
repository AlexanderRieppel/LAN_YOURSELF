package gui;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

@SuppressWarnings("serial")
public class MainGUI extends JFrame {
	private JTextField textField_1;
	private JTextField textField_3;
	public JTextField textField_2;
	private JPanel panel_1,panel_2,panel_3;
	public MainGUI() {
		getContentPane().setLayout(new GridLayout(4, 1, 0, 0));
		
		JPanel panel = new JPanel();
		getContentPane().add(panel);
		panel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		JLabel lblConnectTo = new JLabel("Connect To:");
		panel.add(lblConnectTo);
		
		textField_1 = new JTextField();
		panel.add(textField_1);
		textField_1.setColumns(10);
		
		panel_1 = new JPanel();
		getContentPane().add(panel_1);
		panel_1.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		JLabel lblYourName = new JLabel("Your Name:");
		panel_1.add(lblYourName);
		
		textField_3 = new JTextField();
		panel_1.add(textField_3);
		textField_3.setColumns(10);
		
		panel_2 = new JPanel();
		getContentPane().add(panel_2);
		
		JLabel lblYourPublicIp = new JLabel("Your Public IP:");
		panel_2.add(lblYourPublicIp);
		
		textField_2 = new JTextField();
		panel_2.add(textField_2);
		textField_2.setColumns(10);
		
		panel_3 = new JPanel();
		getContentPane().add(panel_3);
		panel_3.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		JButton btnConnect = new JButton("Connect");
		panel_3.add(btnConnect);
	}
//	public void MyFrame(JPanel p, String text, int x, int y, int h, int w, boolean haup){
//		this.p = p;
//		this.setLayout(new BorderLayout());
//		this.setBounds(x, y, h, w);
//		this.setTitle(text);
//		this.add(p);
//		this.setVisible(true);
//		this.setJMenuBar(m);
//		if(haup)
//			this.setDefaultCloseOperation(EXIT_ON_CLOSE);
//		else
//			this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
//	}
	public static void main(String[] args){
		MainGUI mg = new MainGUI();
		// JFrame zentriert zur Bildschirmmitte, also JFrame in der Mitte des Bildschirms positionieren: 
		mg.pack();
		mg.setAutoRequestFocus(true);
		mg.setTitle("LAN Yourself");
		mg.setVisible(true);
		mg.setDefaultCloseOperation(EXIT_ON_CLOSE);
		int h = mg.getPanel_1().getHeight()+mg.getPanel_2().getHeight()+mg.getPanel_3().getHeight();
		int w = mg.getPanel_1().getWidth();
		System.out.println(h+","+w);
		final Dimension d = mg.getToolkit().getScreenSize(); 
	    mg.setLocation((int) ((d.getWidth() - w) / 2), (int) ((d.getHeight() - h) / 2));
	}
	/**
	 * @return the panel_1
	 */
	public JPanel getPanel_1() {
		return panel_1;
	}
	/**
	 * @return the panel_2
	 */
	public JPanel getPanel_2() {
		return panel_2;
	}
	/**
	 * @return the panel_3
	 */
	public JPanel getPanel_3() {
		return panel_3;
	}
	
}
