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
	 int x, y, width, height;
	 static JFrame frame ;
	 
	 public Login_Registration(){
	   frame = new JFrame("Логін/Реєстрація");
	
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
	   heading_lbl.setBounds(x, y, width, height);
	   y+=40;
	   heading_lbl.setText("<html><font><u><b>Логін/Реєстрація</b></u></html>");
	   heading_lbl.setFont(f);
	   
	   reestr = new JButton("Реєстрація");
	   reestr.setBounds(x, y, width, height);
	   reestr.setFont(f2);
	   y+=40;
	   
	   
	   login = new JButton("Логін");
	   login.setBounds(x, y, width, height);
	   login.setFont(f2);
	   
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
		 frame.setVisible(false);
		
	 }
	 
	 public static void loginevent(ActionEvent event){
	  LoginForClient lg = new LoginForClient();
	  frame.setVisible(false);
	 }
}
