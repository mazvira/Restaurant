package project;

import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class Client_Staff {

	static JButton order;
	static JButton dostavka;
	static JFrame frame ;
	int x, y, width, height;

	public Client_Staff() {
		frame = new JFrame("Клієнт чи Співробітник");
		//frame.setExtendedState(JFrame.MAXIMIZED_BOTH); 
		//frame.setUndecorated(true);
		frame.setVisible(true);
		//frame.setBounds(200, 100, 700, 600);
		frame.setSize(1300, 1000);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		x = 30;
		y = 20;
		width = 200;
		height = 30;

		Container c = frame.getContentPane();
		c.setLayout(null);
		Font f = new Font("Arial", Font.BOLD, 20);
		Font f2 = new Font("Arial", Font.ITALIC, 18);

		JLabel heading_lbl = new JLabel();
		heading_lbl.setBounds(x, y, width, height + 40);
		y+=70;
		heading_lbl.setText("<html><font><u><b>Оберіть свою категорію.</b></u></html>");
		heading_lbl.setFont(f);

		order = new JButton("Клієнт");
		order.setBounds(x, y, width, height );
		y+=40;
		order.setFont(f2);

		dostavka = new JButton("Співробітник");
		dostavka.setBounds(x, y, width, height);
		y+=40;
		dostavka.setFont(f2);

		order.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				try {
					order(event);
				} finally {

				}
			}
		});

		dostavka.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				try {
					dostavka(event);
				} finally {

				}
			}
		});

		c.add(heading_lbl);
		c.add(order);
		c.add(dostavka);
	}

	public static void order(ActionEvent event) {	
		Login_Registration log = new Login_Registration();
		frame.setVisible(false);
		
	}

	public static void dostavka(ActionEvent event) {
		LoginForStaff logFor = new LoginForStaff();
		frame.setVisible(false);
	}
}