package gui;

import java.awt.FlowLayout;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class MyPanel extends JPanel{
	private JTextField textField_1;
	private JTextField textField_3;
	public JTextField textField_2;
	private JPanel panel_1,panel_2,panel_3;
	
	public MyPanel(){
		setLayout(new GridLayout(4, 1, 0, 0));
		
		JPanel panel = new JPanel();
		add(panel);
		panel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		JLabel lblConnectTo = new JLabel("Connect To:");
		panel.add(lblConnectTo);
		
		textField_1 = new JTextField();
		panel.add(textField_1);
		textField_1.setColumns(10);
		
		panel_1 = new JPanel();
		add(panel_1);
		panel_1.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		JLabel lblYourName = new JLabel("Your Name:");
		panel_1.add(lblYourName);
		
		textField_3 = new JTextField();
		panel_1.add(textField_3);
		textField_3.setColumns(10);
		
		panel_2 = new JPanel();
		add(panel_2);
		
		JLabel lblYourPublicIp = new JLabel("Your Public IP:");
		panel_2.add(lblYourPublicIp);
		
		textField_2 = new JTextField();
		panel_2.add(textField_2);
		textField_2.setColumns(10);
		
		panel_3 = new JPanel();
		add(panel_3);
		panel_3.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		JButton btnConnect = new JButton("Connect");
		panel_3.add(btnConnect);
	}
}
