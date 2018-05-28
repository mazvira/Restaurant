package database;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.swing.*;

public class Login {
	Statement stmt = null;
	Connection c = null;
	ResultSet rs = null;
	
	JFrame f = new JFrame("User Login");
	JLabel l = new JLabel("User name");
	JLabel l1 = new JLabel("Password");
	JTextField t = new JTextField(20);
	JTextField t1 = new JTextField(20);
	JButton b = new JButton("Login");

	public Login() {
	    connect();
		frame();
	}
	public void connect()
	{
		try
		{
		
			Class.forName("org.postgresql.Driver");
			c = DriverManager.getConnection("jdbc:postgresql://localhost:5432/Restaurant", "postgres", "12345");
			stmt = c.createStatement();
		}
		catch(Exception e)
		{
			e.printStackTrace();
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
			System.exit(0);
		}
		
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
				try 
				{
					String user = t.getText().trim();
					String pass = t1.getText().trim();
					
					String sql  = "select user, pass from Client where user='"+user+"' and pass='" +pass+"'";
					rs = stmt.executeQuery(sql);
					int count = 0;
					while(rs.next())
					{
						count = count + 1;
					}
					if(count == 1)
						JOptionPane.showMessageDialog(null, "User Found, Access Granted");
					else if(count > 1)
						JOptionPane.showMessageDialog(null, "Duplicate User, Access denied");
					else 
						JOptionPane.showMessageDialog(null, "User not Found!");
				}
				catch(Exception ex)
				{
					
				}
				
				
			}
		});
	}
}
