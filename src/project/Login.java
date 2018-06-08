package project;

import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.*;

public class Login {
	
	JFrame f = new JFrame("User Login");
	JLabel l = new JLabel("User name");
	JLabel l1 = new JLabel("Password");
	JTextField t = new JTextField(20);
	JTextField t1 = new JTextField(20);
	JButton b = new JButton("Login");
	Reservation_Ordering bd;
	DatabaseConnectionAndQueries database;

	public Login() {
		database = new DatabaseConnectionAndQueries();
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
		
		b.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
					String user = t.getText().trim();
					String pass = t1.getText().trim();			
					int count = 0;
					try {
						count = database.isUserExists(user, pass);
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}				
					if(count == 1)
						 bd = new Reservation_Ordering();
					else if(count > 1)
						JOptionPane.showMessageDialog(null, "Duplicate User, Access denied");
					else 
						JOptionPane.showMessageDialog(null, "User not Found!");
				}
			
		});
	}
}
