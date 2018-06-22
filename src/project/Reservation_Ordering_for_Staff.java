package project;

import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.Frame;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.*;

public class Reservation_Ordering_for_Staff {

	static JButton order;
	static JButton dostavka;
	int x, y, width, height;
	static JFrame frame;

	public Reservation_Ordering_for_Staff() {
		frame = new JFrame("Інформація про Бронювання столика/Замовлення їжі");
		frame.setVisible(true);
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
		heading_lbl.setBounds(x, y, width + 40, height + 30);
		y += 70;
		heading_lbl.setText("<html><font><u><b>Бронювання столика/Замовлення їжі</b></u></html>");
		heading_lbl.setFont(f);

		order = new JButton("Бронювання");
		order.setBounds(x, y, width, height);
		y += 40;
		order.setFont(f2);

		dostavka = new JButton("Доставка");
		dostavka.setBounds(x, y, width, height);
		dostavka.setFont(f2);
		/*
		 * JLabel heading_lbl = new JLabel(); heading_lbl.setBounds(250, 5, 250, 40);
		 * heading_lbl.setText("<html><font><u><b>Бровання/Доставка</b></u></html>");
		 * heading_lbl.setFont(f);
		 * 
		 * order = new JButton("Бронювання"); order.setBounds(180, 200, 120, 40);
		 * 
		 * dostavka = new JButton("Доставка"); dostavka.setBounds(180, 300, 120, 40);
		 */

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
		try {
			Information_About_Reservation_Of_Halls_Tables info = new Information_About_Reservation_Of_Halls_Tables();
			frame.setVisible(false);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static void dostavka(ActionEvent event) {
		try {
		Information_About_Ordering_Food info2 = new Information_About_Ordering_Food();
		frame.setVisible(false);

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}