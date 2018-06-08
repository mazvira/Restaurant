package project;

import java.awt.Container;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Date;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class OrderingFood {

	JFrame f;
	Container contentPane;
	static JComboBox citiesNameList;
	// JComboBox restaurantNameList;
	static JComboBox menuNameList;
	static JCheckBox[] choicesOfmenu;
	static JCheckBox[] choicesOfdishes;
	static JTextField address;
	JLabel text1, text2, text3, text4, text5, text6, text7, text8, text9;
	static JButton submit_btn;
	static JComboBox day;
	static JComboBox month;
	// static JComboBox year;
	static DatabaseConnectionAndQueries database;

	public OrderingFood() throws SQLException {
		database = new DatabaseConnectionAndQueries();
		f = new JFrame();
		f.setVisible(true);
		// f.setLayout(new FlowLayout());
		f.setBounds(200, 100, 700, 600);
		// f.setSize(800, 800);
		f.setTitle("Booking food");
		// f.setBounds(500, 100, 450, 500);
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		contentPane = f.getContentPane();
		// contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(null);
		text1 = new JLabel("Виберіть своє місто: ");
		text1.setBounds(30, 50, 130, 30);
		contentPane.add(text1);
		initializeCitiesOfRestaurants();
		citiesNameList.setBounds(160, 50, 180, 30);
		contentPane.add(citiesNameList);
		text2 = new JLabel("Виберіть потрібні меню: ");
		text2.setBounds(30, 90, 150, 30);
		contentPane.add(text2);
		initializeMenusOfRestaurants();
		text3 = new JLabel("Виберіть страви для замовлення: ");
		text3.setBounds(30, 370, 500, 30);
		text4 = new JLabel("Назва страви             Калорійність    Ціна");
		text4.setBounds(30, 410, 550, 30);
		contentPane.add(text3);
		contentPane.add(text4);
		initializeDishesOfRestaurants();
		text5 = new JLabel("Введіть адресу доставки: ");
		text5.setBounds(30, 890, 400, 30);
		contentPane.add(text5);
		address = new JTextField();
		address.setBounds(30, 930, 500, 30);
		contentPane.add(address);
		text6 = new JLabel("Виберіть дату доставки: ");
		text6.setBounds(480, 50, 180, 30);
		contentPane.add(text6);
		String day_arr[] = new String[31];
		for (int i = 1; i <= 31; i++)
			day_arr[i - 1] = Integer.toString(i);
		day = new JComboBox(day_arr);
		day.setBounds(480, 90, 40, 30);
		contentPane.add(day);

		String month_arr[] = { "Січ", "Лют", "Бер", "Квіт", "Трав", "Черв", "Лип", "Серп", "Вер", "Жовт", "Лист",
				"Груд" };
		month = new JComboBox(month_arr);
		month.setBounds(530, 90, 60, 30);
		contentPane.add(month);
		
		text7 = new JLabel("Сума до сплати: ");
		text7.setBounds(480, 140, 130, 30);
		contentPane.add(text7);
		text8 = new JLabel("Сума за доставку: ");
		text8.setBounds(480, 180, 130, 30);
		contentPane.add(text8);
		text9 = new JLabel("Знижка: ");
		text9.setBounds(480, 220, 130, 30);
		contentPane.add(text9);
		
		submit_btn = new JButton("Замовити");
		submit_btn.setBounds(480, 265, 300, 40);
		f.add(submit_btn);
		// Step 18 : Adding ActionListener on submit button
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

		// f.add(contentPane);
		// f.add(label);
		// petList.addActionListener(this);
	}

	public static void submit_action(ActionEvent event) throws SQLException {

		Date date = new Date(2018, 6, 5);
		database.insertIntoDelivery(date, address.getText(), "Київ", 400, 0);
		// insertIntoClients(name_txt.getText(), surname_txt.getText(),
		// fathers_txt.getText(),
		// email_txt.getText(), date);

	}

	public void initializeCitiesOfRestaurants() throws SQLException {
		String[] citiesNames = database.getNameOfCitiesOfRestaurants();
		citiesNameList = new JComboBox(citiesNames);
	}

	/*
	 * public void initializeRestaurants() throws SQLException { String[]
	 * restaurantNames = database.getNameOfRestaurants(); restaurantNameList = new
	 * JComboBox(restaurantNames); }
	 */

	public void initializeMenusOfRestaurants() throws SQLException {
		String[] menuNames = database.getNameOfMenus();
		int i = 0;
		int y = 0;
		int width = 0;
		int len = menuNames.length;
		choicesOfmenu = new JCheckBox[len];
		while (i < len) {
			choicesOfmenu[i] = new JCheckBox(menuNames[i]);
			choicesOfmenu[i].setBounds(30, 130 + y, 200 + width, 30);
			contentPane.add(choicesOfmenu[i]);
			i++;
			y = y + 40;
			width = width + 50;
		}
	}

	public void initializeDishesOfRestaurants() throws SQLException {
		String[] dishesNames = database.getNameOfDishesWithCalorityAndPrice();
		int i = 0;
		int y = 0;
		int width = 0;
		int len = dishesNames.length;
		choicesOfdishes = new JCheckBox[len];
		while (i < len) {
			choicesOfdishes[i] = new JCheckBox(dishesNames[i]);
			choicesOfdishes[i].setBounds(30, 450 + y, 600 + width, 30);
			contentPane.add(choicesOfdishes[i]);
			i++;
			y = y + 40;
			width = width + 50;
		}
	}
}
