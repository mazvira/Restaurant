package project;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class LoginForStaff {
	JFrame f = new JFrame("Staff Login");
	JLabel l = new JLabel("Staff`s id");
	JLabel l1 = new JLabel("Password");
	JTextField t = new JTextField(20);
	JTextField t1 = new JTextField(20);
	JButton b = new JButton("Login");
	Reservation_Ordering_for_Staff bd;
	DatabaseConnectionAndQueries database;
	int x, y, width, height;

	public LoginForStaff() {
		database = new DatabaseConnectionAndQueries();
		frame();
	}

	public void frame() {

		f.setSize(1300, 1000);
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setVisible(true);
		Font f1 = new Font("Arial", Font.ITALIC, 18);
		x = 30;
		y = 20;
		width = 200;
		height = 30;

		JPanel p = new JPanel();

		l.setFont(f1);
		l.setBounds(x, y, width, height);
		y+=40;
		p.add(l);
		
		t.setFont(f1);
		t.setBounds(x, y, width, height);
		y+=40;
		p.add(t);
			
		l1.setFont(f1);
		l1.setBounds(x, y, width, height);
		y+=40;
		p.add(l1);
		
		t1.setFont(f1);
		t1.setBounds(x, y, width, height);
		y+=40;
		p.add(t1);
		
		b.setFont(f1);
		b.setBounds(x, y, width, height);
		y+=40;
		p.add(b);

		f.add(p);

		b.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				int id = Integer.parseInt(t.getText());
				String pass = t1.getText().trim();
				int count = 0;
				try {
					count = database.isStaffExists(id, pass);
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				if (count == 1)
				{
					bd = new Reservation_Ordering_for_Staff();
				    f.setVisible(false);
				}
				
				else if (count > 1)
					JOptionPane.showMessageDialog(null, "Duplicate User, Access denied");
				else
					JOptionPane.showMessageDialog(null, "User not Found!");
			}

		});
	}
}
