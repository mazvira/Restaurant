package project;

import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class Login_Registration {
	 static JButton reestr;
	 static JButton login;
	 
	 public Login_Registration(){
	   JFrame frame = new JFrame("Автентифікація");
	
	   frame.setVisible(true);
	   //frame.setBounds(200, 100, 700, 600);
	   frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	   
	   Container c = frame.getContentPane();
	   c.setLayout(null);
	   Font f = new Font("Arial", Font.BOLD, 20);
	   
	   JLabel heading_lbl = new JLabel();
	   heading_lbl.setBounds(250, 5, 250, 40);
	   heading_lbl.setText("<html><font><u><b>Логін/Реєстрація</b></u></html>");
	   heading_lbl.setFont(f);
	   
	   reestr = new JButton("Реєстрація");
	   reestr.setBounds(200, 220, 140, 60);
	   
	   
	   login = new JButton("Логін");
	   login.setBounds(200, 320, 140, 60);
	   
	   reestr.addActionListener(new ActionListener() {
	    public void actionPerformed(ActionEvent event) {
	     try {
	      reestrevent(event);
	      } finally {
	       
	      }
	     }
	    });
	   
	   login.addActionListener(new ActionListener() {
	      public void actionPerformed(ActionEvent event) {
	        try {
	          loginevent(event);
	        } finally {
	         
	        }
	      }
	    });
	   
	   c.add(heading_lbl);
	   c.add(reestr);
	   c.add(login);
	   //frame.add(c);
	 }
	 public static void reestrevent(ActionEvent event){
		 RegForm rg = new RegForm();
		
	 }
	 
	 public static void loginevent(ActionEvent event){
	  Login lg = new Login();
	  //Runtime.getRuntime().exit(1);
	 }
}
