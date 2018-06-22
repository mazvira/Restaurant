package project;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class Information_About_Ordering_Food {
	JTable table;
	JFrame frame;
	JPanel panel;
	JLabel text1, text2;
	JComboBox rest;
	String numbersOfDeliv[];
	String namesOfClient[];
	String datesOfDeliv[];
	String addressOfDeliv[];
	String citiesOfDeliv[];
	String sumOfDeliv[];
	String costOfDeliv[];
	String isDeliv[];
	String isPaid[];
	
	String numbersOfDeliv2[];
	String namesOfClient2[];
	String datesOfDeliv2[];
	String addressOfDeliv2[];
	String citiesOfDeliv2[];
	String sumOfDeliv2[];
	String costOfDeliv2[];
	String isDeliv2[];
	String isPaid2[];
	Font f1;
	String cities[];
	int x, y, width, height;
	int lastY ;
	DatabaseConnectionAndQueries database;

	public Information_About_Ordering_Food() throws SQLException {
		database = new DatabaseConnectionAndQueries();
		frame = new JFrame();
		frame.setVisible(true);
		frame.setSize(1300, 1000);
		frame.setTitle("Інформація про замовлення їжі");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		panel = new JPanel();
		panel.setLayout(null);
		x = 30;
		y = 20;
		width = 200;
		height = 30;
		f1 = new Font("Arial", Font.ITALIC, 18);
		text1 = new JLabel("Місто:");
		text1.setBounds(x, y, width, height);
		text1.setFont(f1);
		y += 40;
		panel.add(text1);

		cities = database.getNameOfCitiesOfRestaurants();
		rest = new JComboBox(cities);
		rest.setBounds(x, y, width, height);
		rest.setFont(f1);
		y += 40;
		panel.add(rest);

		numbersOfDeliv = initNumbersOfDelivery();
		int quantity = numbersOfDeliv.length;
		namesOfClient = initNamesOfClient();
		datesOfDeliv = initDatesOfDelivery();
		addressOfDeliv = initAddressOfDeliv();
		citiesOfDeliv = initCitiesOfDeliv();
		sumOfDeliv = initSumOfDelivery();
		costOfDeliv = initCostOfDeliv();
		isDeliv = initIsDeliv();
		isPaid = initIsPaid();
		String[] header = { "Номер", "Ім'я клієнта", "Дата Доставки", "Місто", "Адреса доставки", "Сума замовлення",
				"Сума за доставку", "Чи доставлено", "Чи проплачено" };

		Object data[][] = new Object[quantity + 1][9];
		for (int i = 0; i < quantity; ++i)
			data[i][0] = numbersOfDeliv[i];
		for (int i = 0; i < quantity; ++i)
			data[i][1] = namesOfClient[i];
		for (int i = 0; i < quantity; ++i)
			data[i][2] = datesOfDeliv[i];
		for (int i = 0; i < quantity; ++i)
			data[i][3] = citiesOfDeliv[i];
		for (int i = 0; i < quantity; ++i)
			data[i][4] = addressOfDeliv[i];
		for (int i = 0; i < quantity; ++i)
			data[i][5] = sumOfDeliv[i];
		for (int i = 0; i < quantity; ++i)
			data[i][6] = costOfDeliv[i];
		for (int i = 0; i < quantity; ++i)
			data[i][7] = isDeliv[i];
		for (int i = 0; i < quantity; ++i)
			data[i][8] = isPaid[i];

		// Object data[][] = { { 1, "20.05.2018", "18:00", 6 }, { 2, "20.05.2018",
		// "18:00", 6 } };
		// table = new JTable(data, header); //
		// table.setPreferredScrollableViewportSize(new Dimension(500, 50));
		// JScrollPane scrollPane = new JScrollPane(table);
		// table.setFillsViewportHeight(true);

		// String data[][] = { { "101", "Amit", "670000" }, { "102", "Jai", "780000" },
		// { "101", "Sachin", "700000" } };

		String column[] = { "ID", "NAME", "SALARY" };
		table = new JTable();
		table.setModel(new DefaultTableModel(data, header));
		// table = new JTable(data, column);
		table.setEnabled(false);
		table.setBounds(x, y, 1600, 300);
		lastY = y;
		y += 40;
		table.setFont(f1);
		panel.add(table);

		rest.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				String selectedItem = (String) rest.getSelectedItem();
				table.setVisible(false);
				panel.remove(table);
				panel.updateUI();
				try {
					numbersOfDeliv2 = setNumbersOfDeliveryByCity(selectedItem);
					System.out.println(numbersOfDeliv2.length);
					//for(int i = 0; i<numbersOfDeliv2.length; ++i)
					  //System.out.println(numbersOfDeliv2[i]);
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				int quantity2 = numbersOfDeliv2.length;
				try {
					namesOfClient2 = setNamesOfClientsDeliveryByCity(selectedItem);
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				try {
					datesOfDeliv2 = setDatesOfDeliveryByCity(selectedItem);
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			
				try {
					addressOfDeliv2 = setAddressOfDelivByCity(selectedItem);
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				try {
					citiesOfDeliv2 = setCitiesOfDelivByCity(selectedItem);
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				try {
					sumOfDeliv2 = setSumOfDeliveryByCity(selectedItem);
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				try {
					costOfDeliv2 = setCostOfDelivByCity(selectedItem);
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				try {
					isDeliv2 = setIsDelivByCity(selectedItem);
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				try {
					isPaid2 = setIsPaidByCity(selectedItem);
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				JTable tab = new JTable();
				Object data2[][] = new Object[quantity2  + 1][9];
				
				for (int i = 0; i < quantity2 ; ++i)
					data2[i][0] = numbersOfDeliv2[i];
				for (int i = 0; i < quantity2; ++i)
					data2[i][1] = namesOfClient2[i];
				for (int i = 0; i < quantity2; ++i)
					data2[i][2] = datesOfDeliv2[i];
				for (int i = 0; i < quantity2; ++i)
					data2[i][3] = citiesOfDeliv2[i];
				for (int i = 0; i < quantity2; ++i)
					data2[i][4] = addressOfDeliv2[i];
				for (int i = 0; i < quantity2; ++i)
					data2[i][5] = sumOfDeliv2[i];
				for (int i = 0; i < quantity2; ++i)
					data2[i][6] = costOfDeliv2[i];
				for (int i = 0; i < quantity2; ++i)
					data2[i][7] = isDeliv2[i];
				for (int i = 0; i < quantity2; ++i)
					data2[i][8] = isPaid2[i];

			//	tab.setModel(new DefaultTableModel(data2, header));
				table = new JTable();
				table.setModel(new DefaultTableModel(data2, header));
				
				table.setBounds(x, lastY, 1600, 300);
				table.setEnabled(false);
				table.setFont(f1);
				panel.add(table);
				
				
				
			}
		});

		frame.add(panel);

	}

	public String[] initNumbersOfDelivery() throws SQLException {

		return database.getNumbersOfDelivery();

	}

	private String[] initNamesOfClient() throws SQLException {

		return database.getNamesOfClientsInDelivery();
	}

	public String[] initDatesOfDelivery() throws SQLException {

		return database.getDatesOfDelivery();

	}

	public String[] initAddressOfDeliv() throws SQLException {

		return database.getAddressOfDeliv();

	}

	public String[] initCitiesOfDeliv() throws SQLException {

		return database.getCitiesOfDeliv();

	}

	public String[] initSumOfDelivery() throws SQLException {

		return database.getSumOfDelivery();

	}

	private String[] initCostOfDeliv() throws SQLException {
		return database.getCostOfDelivery();
	}

	public String[] initIsDeliv() throws SQLException {
		return database.getIsDeliv();
	}

	public String[] initIsPaid() throws SQLException {
		return database.getIsPaid();
	}
	
	public String[] setNumbersOfDeliveryByCity(String city) throws SQLException {

		return database.getNumbersOfDelivByCity(city);

	}

	private String[] setNamesOfClientsDeliveryByCity(String city) throws SQLException {

		return database.getNamesOfClientsOfDelivByCity(city);
	}

	public String[] setDatesOfDeliveryByCity(String city) throws SQLException {

		return database.getDatesOfDeliveryByCity(city);

	}

	public String[] setAddressOfDelivByCity(String city) throws SQLException {

		return database.getAddressOfDeliv();

	}

	public String[] setCitiesOfDelivByCity(String city) throws SQLException {

		return database.getCitiesOfDeliveryByCity(city);
	}

	public String[] setSumOfDeliveryByCity(String city) throws SQLException {
		return database.getSumOfDeliveryByCity(city);
	}

	private String[] setCostOfDelivByCity(String city) throws SQLException {
		return database.getCostOfDeliveryByCity(city);
	}

	public String[] setIsDelivByCity(String city) throws SQLException {
		return database.getIsDelivByCity(city);
	}

	public String[] setIsPaidByCity(String city) throws SQLException {
		return database.getIsPaidByCity(city);
	}


}
