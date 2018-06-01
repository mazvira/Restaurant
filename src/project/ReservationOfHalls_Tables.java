package project;

import javax.swing.*;

public class ReservationOfHalls_Tables {
	
	JFrame f = new JFrame("User Login");
	JLabel l = new JLabel("User name");
	JLabel l1 = new JLabel("Password");
	JTextField t = new JTextField(20);
	JTextField t1 = new JTextField(20);
	JButton b = new JButton("Login");
	DatabaseConnectionAndQueries database;

	public ReservationOfHalls_Tables() {
		frame();
	}

	
	public void frame()
	{
		f.setSize(600, 400);
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setVisible(true);
		
		JPanel p = new JPanel();
		p.add(l);
		p.add(t);
		p.add(l1);
		p.add(t1);
		p.add(b);
		
		f.add(p);
		
	
	}
}
