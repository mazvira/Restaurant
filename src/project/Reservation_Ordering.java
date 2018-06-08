package project;

import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.*;

public class Reservation_Ordering {
	 
	 static JButton order;
	 static JButton dostavka;
	 
	 public Reservation_Ordering(){
	   JFrame frame = new JFrame("Початок роботи");
	   Toolkit tk = Toolkit.getDefaultToolkit();
	   int xSize = ((int) tk.getScreenSize().getWidth());
	   int ySize = ((int) tk.getScreenSize().getHeight());
	   frame.setSize(xSize,ySize);
	   frame.setVisible(true);
	  // frame.setBounds(200, 100, 700, 600);
	   frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	   
	   Container c = frame.getContentPane();
	   c.setLayout(null);
	   Font f = new Font("Arial", Font.BOLD, 20);
	   
	   JLabel heading_lbl = new JLabel();
	   heading_lbl.setBounds(250, 5, 250, 40);
	   heading_lbl.setText("<html><font><u><b>Бровання/Доставка</b></u></html>");
	   heading_lbl.setFont(f);
	   
	   order = new JButton("Бронювання");
	   order.setBounds(180, 200, 120, 40);
	   
	   dostavka = new JButton("Доставка");
	   dostavka.setBounds(180, 300, 120, 40);
	   
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
	 public static void order(ActionEvent event){
	 try {
			ReservationOfHalls_Tables rese = new ReservationOfHalls_Tables();
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	 }
	 
	 public static void dostavka(ActionEvent event){
	  try {
			OrderingFood order = new OrderingFood();
	
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	 }
	}