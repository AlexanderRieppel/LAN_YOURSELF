package gui;

import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JPanel;

public class MyFrame extends JFrame {
	private static int def_h = 208*2, def_w = 250*2;
	public MyFrame(JPanel p,JMenuBar menu, String text, boolean haup) {   
		this(p,menu, text,(int)((p.getToolkit().getScreenSize().getWidth() - def_w) / 2), (int)((p.getToolkit().getScreenSize().getHeight() - def_h) / 2),def_h, def_w, haup);
	}

	public MyFrame(JPanel p,JMenuBar menu, String text, int x, int y, int h, int w,boolean haup) {
		this.pack();
		this.setLayout(new BorderLayout());
		this.setBounds(x, y, w, h);
		this.setTitle(text);
		this.add(p);
		this.setVisible(true);
		//this.setJMenuBar(m);
		if (haup)
			this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		else
			this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		this.setJMenuBar(menu);
		
	}
}
