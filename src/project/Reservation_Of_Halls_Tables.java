package project;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Date;
import java.sql.SQLException;
import java.sql.Time;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import org.jdesktop.swingx.JXDatePicker;

public class Reservation_Of_Halls_Tables {
	JFrame frame;
	JButton button;
	JComboBox citiesNameList;
	JPanel panel;
	static JCheckBox[] choicesOfmenu;
	static JCheckBox[] choicesOfdishes;
	static JComboBox[] quantites;
	JComboBox restaurantNameList;
	JComboBox hallOrTable;
	JComboBox hallsNameList;
	JComboBox quantityOfTables;
	static JTextField address;
	JLabel text1, text2, text3, text4, text5, text6, text7, text8, text9, text10;
	static JButton submit_btn;
	static Date dateOnWhenBooked;
	String namesOfRestaurant[];
	String tables_arr[] ;
	// JSpinner
	JTextArea event;
	JXDatePicker picker;
	JScrollPane scrollpanel;
	Font f1;
	int x, y, width, height;
	int lastY;
	// static JComboBox year;
	static DatabaseConnectionAndQueries database;

	public Reservation_Of_Halls_Tables() throws SQLException {
		database = new DatabaseConnectionAndQueries();
		frame = new JFrame();
		frame.setSize(1300, 1000);
		frame.setTitle("Бронювання столиків/залів");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
		panel = new JPanel();
		f1 = new Font("Arial", Font.ITALIC, 18);
		panel.setLayout(null);

		x = 30;
		y = 20;
		width = 200;
		height = 30;
		text1 = new JLabel("Виберіть місто: ");
		text1.setFont(f1);
		text1.setBounds(x, y, width, height);
		y += 40;
		panel.add(text1);

		initializeCitiesOfRestaurants();
		citiesNameList.setFont(f1);
		citiesNameList.setBounds(x, y, width, height);
		y += 40;
		panel.add(citiesNameList);

		text5 = new JLabel("Виберіть pecторан: ");
		text5.setFont(f1);
		text5.setBounds(x, y, width, height);
		y += 40;
		panel.add(text5);

		initializeRestaurants();
		restaurantNameList.setBounds(x, y, width, height);
		y += 40;
		restaurantNameList.setFont(f1);
		panel.add(restaurantNameList);

		text10 = new JLabel("Що Ви хочете забронювати?");
		text10.setFont(f1);
		text10.setBounds(x, y, width + 60, height);
		y += 40;
		panel.add(text10);

		String val[] = { "Стіл", "Зал" };
		hallOrTable = new JComboBox(val);
		hallOrTable.setFont(f1);
		hallOrTable.setBounds(x, y, width, height);
		y += 40;
		lastY = y;
		panel.add(hallOrTable);

		text3 = new JLabel("Виберіть тип залу: ");
		text3.setFont(f1);
		text3.setBounds(x, y, width, height);
		y += 40;
		panel.add(text3);

		initializeHalls();
		hallsNameList.setFont(f1);
		hallsNameList.setBounds(x, y, width, height);
		y += 40;
		panel.add(hallsNameList);

		text4 = new JLabel("Виберіть столик на кількість людей: ");
		text4.setFont(f1);
		text4.setBounds(x, y, width + 130, height);
		y += 40;
		panel.add(text4);

		tables_arr = new String[10];
		for (int i = 1; i <= 10; i++)
			tables_arr[i - 1] = Integer.toString(i);
		quantityOfTables = new JComboBox(tables_arr);
		quantityOfTables.setFont(f1);
		quantityOfTables.setBounds(x, y, width, height);
		y += 40;	
		panel.add(quantityOfTables);

		text2 = new JLabel("Дата на коли бронювання: ");
		text2.setFont(f1);
		text2.setBounds(x, y, width + 40, height);
		y += 40;
		panel.add(text2);

		picker = new JXDatePicker();
		picker.setDate(Calendar.getInstance().getTime());
		picker.setFormats(new SimpleDateFormat("dd.MM.yyyy"));
		picker.setFont(f1);
		picker.setBounds(x, y, width, height);
		y += 40;
		panel.add(picker);

		text6 = new JLabel("На яку годину бронювання: ");
		text6.setFont(f1);
		text6.setBounds(x, y, width + 50, height);
		y += 40;
		panel.add(text6);

		text7 = new JLabel("Назва події: ");
		text7.setFont(f1);
		text7.setBounds(x, y, width, height);
		y += 40;
		panel.add(text7);

		event = new JTextArea();
		event.setFont(f1);
		event.setSize(150, 50);
		event.setBounds(x, y, width, height);
		y += 40;
		panel.add(event);

		text8 = new JLabel("Знижка");
		text8.setFont(f1);
		text8.setBounds(x, y, width + 30, height);
		y += 40;
		panel.add(text8);

		submit_btn = new JButton("Забронювати");
		submit_btn.setBounds(x, y, width + 30, height);
		y += 40;
		submit_btn.setFont(f1);
		panel.add(submit_btn);

		frame.add(panel);

		citiesNameList.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				String selectedBook = (String) citiesNameList.getSelectedItem();
				restaurantNameList.removeAllItems();
				try {
					namesOfRestaurant = setNamesOfRestaurantsByCity(selectedBook);
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				int length = namesOfRestaurant.length;
				for (int i = 0; i < length; ++i)
					restaurantNameList.addItem(namesOfRestaurant[i]);

			}
		});

		hallOrTable.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				String selectedItem = (String) hallOrTable.getSelectedItem();
				if (selectedItem.equals("Зал")) {
					text4.setVisible(false);
					panel.remove(text4);
					quantityOfTables.setVisible(false);
					panel.remove(quantityOfTables);
					panel.updateUI();
				
				} else if (selectedItem.equals("Стіл")) {
					text3.setVisible(false);
					panel.remove(text3);
				    hallsNameList.setVisible(false);
				    panel.remove(hallsNameList);		    
					panel.updateUI();
				}
				
			    text4.setBounds(x, lastY, width + 130, height);
			    lastY +=40;
			    quantityOfTables.setBounds(x, lastY, width, height);
			    lastY +=40;
			    text2.setBounds(x, lastY, width + 40, height);
			    lastY +=40;
			    picker.setBounds(x, lastY, width, height);
			    lastY +=40;
			    text6.setBounds(x, lastY, width + 130, height);
			    lastY +=40;
			    text7.setBounds(x, lastY, width, height);
			    lastY +=40;
			    event.setBounds(x, lastY, width , height);
			    lastY +=40;
			    text8.setBounds(x, lastY, width, height);
			    lastY +=40;
			    submit_btn.setBounds(x, lastY, width, height);
			    lastY +=40;

			}
		});

		SimpleDateFormat formater = new SimpleDateFormat("yyyy-MM-dd");
		picker.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				java.util.Date date = picker.getDate();
				String str = formater.format(date);
				dateOnWhenBooked = Date.valueOf(str);
			}
		});

		submit_btn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				try {
					try {
						submit_action(event);
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				} finally {

				}
			}
		});

	}

	public void initializeCitiesOfRestaurants() throws SQLException {
		String[] citiesNames = database.getNameOfCitiesOfRestaurants();
		citiesNameList = new JComboBox(citiesNames);
	}

	public void initializeRestaurants() throws SQLException {
		String[] restaurantNames = database.getNameOfRestaurants();
		restaurantNameList = new JComboBox(restaurantNames);
	}

	public void initializeHalls() throws SQLException {
		String[] hallsNames = database.getNameOfHalls();
		hallsNameList = new JComboBox(hallsNames);
	}

	public String[] setNamesOfRestaurantsByCity(String nameOfTheCity) throws SQLException {
		return database.getRestaurantsByCity(nameOfTheCity);
	}

	public static void submit_action(ActionEvent event) throws SQLException {
		String time = "19:00:00";
		Time timeZone = Time.valueOf(time);
		database.insertIntoBooking(dateOnWhenBooked, timeZone, 100, 3, "День народження");
		JOptionPane.showMessageDialog(null, "Ваше бронювання зареєстроване!");
		// database.insertIntoDelivery(date, address.getText(), "Київ", 400, 0);
		// insertIntoClients(name_txt.getText(),
		// surname_txt.getText(), // fathers_txt.getText(), // email_txt.getText(),
		// date);

	}

}
