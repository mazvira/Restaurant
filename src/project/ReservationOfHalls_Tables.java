package project;

import java.awt.Toolkit;
import java.sql.SQLException;

import javax.swing.*;

public class ReservationOfHalls_Tables {
	
	JFrame f;
	JPanel contentPane;
	JComboBox citiesNameList;
	JComboBox restaurantNameList;
	JComboBox hallsNameList;
	JCheckBox[] choicesOfmenu;
	JCheckBox[] choicesOfdishes;
	JLabel text1, text2, text3, text4,text5 ;
	JRadioButton rad1 = new JRadioButton("");
	JRadioButton rad2 = new JRadioButton("");

	DatabaseConnectionAndQueries database;

	public ReservationOfHalls_Tables() throws SQLException {
		database = new DatabaseConnectionAndQueries();
		f = new JFrame();
		//f.setLayout(new FlowLayout());
		f.setSize(800, 700);
		f.setTitle("Reservation of Halls/Tables");
		//f.setBounds(200, 100, 700, 600);
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setVisible(true);
		
		contentPane = new JPanel();
		//contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(null);
		text1 = new JLabel("Виберіть місто: ");
		text1.setBounds(30, 50, 100, 30);
		contentPane.add(text1);
		initializeCitiesOfRestaurants();
		citiesNameList.setBounds(160,50, 180, 30);
		contentPane.add(citiesNameList);
		text2 = new JLabel("Виберіть pecторан: ");
		text2.setBounds(30, 90, 150, 30);
		contentPane.add(text2);
		initializeRestaurants();
		restaurantNameList.setBounds(160,90, 230, 30);
		contentPane.add(restaurantNameList);
		text3 = new JLabel("Дата бронювання? ");
		text3.setBounds(30, 130, 200, 30);
		contentPane.add(text3);
		
		text4 = new JLabel("Виберіть тип залу: ");
		text4.setBounds(30, 250, 350, 30);
		contentPane.add(text4);
		initializeHalls();
		hallsNameList.setBounds(160, 250, 350, 30);
		contentPane.add(hallsNameList);
		text5 = new JLabel("Виберіть кількість столиків: ");
		text5.setBounds(30, 290, 400, 30);
		contentPane.add(text5);
		//initializeDishesOfRestaurants();	
		f.add(contentPane);
		//f.add(label);
		//petList.addActionListener(this);
	}
	
	public void initializeCitiesOfRestaurants() throws SQLException
	{
		String[]  citiesNames = database.getNameOfCitiesOfRestaurants();
		citiesNameList = new JComboBox(citiesNames);
	}
	
	public void initializeRestaurants() throws SQLException
	{
		String[] restaurantNames = database.getNameOfRestaurants();
		restaurantNameList = new JComboBox(restaurantNames);
	}
	
	public void initializeHalls() throws SQLException
	{
		String[] hallsNames = database.getNameOfHalls();
		hallsNameList = new JComboBox(hallsNames);
	}

	/*public void frame()
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
	}*/
}
