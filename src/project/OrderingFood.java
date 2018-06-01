package project;

import java.awt.FlowLayout;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class OrderingFood {
	
	JFrame f = new JFrame("User Login");
	JLabel l = new JLabel("User name");
	JLabel l1 = new JLabel("Password");
	JTextField t = new JTextField(20);
	JTextField t1 = new JTextField(20);
	JButton b = new JButton("Login");
	DatabaseConnectionAndQueries database;
	JComboBox menuNameList;
	JLabel label;
	
	public OrderingFood() throws SQLException
	{
		database = new DatabaseConnectionAndQueries();
		f = new JFrame();
		f.setLayout(new FlowLayout());
		f.setSize(700, 600);
		f.setTitle("Booking food");
	    f.setBounds(500, 100, 450, 500);
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setVisible(true);
		String[] menuNames = database.getNameOfDishes();
		menuNameList = new JComboBox(menuNames);
		menuNameList.setBounds(90, 85, 245, 31);
		//label = new JLabel();
		f.add(menuNameList);
		//f.add(label);
		//petList.addActionListener(this);
	}
	
}
