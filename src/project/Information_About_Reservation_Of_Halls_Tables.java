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

public class Information_About_Reservation_Of_Halls_Tables {
	JTable table;
	JFrame frame;
	JPanel panel;
	JLabel text1, text2;
	JComboBox rest;
	String numbersOfReserv[];
	String namesOfClients[];
	String datesOfReserv[];
	String timeOfReserv[]; 
	String quantityOfPeople[];
	
	String numbersOfReserv2[];
	String namesOfClients2[];
	String datesOfReserv2[];
	String timeOfReserv2[]; 
	String quantityOfPeople2[];
	
	int x, y, width, height;
	Font f1;
	DatabaseConnectionAndQueries database;

	public Information_About_Reservation_Of_Halls_Tables() throws SQLException {
		database = new DatabaseConnectionAndQueries();
		x = 30;
		y = 20;
		width = 200;
		height = 30;
		f1 = new Font("Arial", Font.ITALIC, 18);
		frame = new JFrame();
		frame.setVisible(true);
		frame.setSize(1300, 1000);
		frame.setTitle("Бронювання столиків.");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		panel = new JPanel();

		panel.setLayout(null);
		/*text1 = new JLabel("Бронювання столиків.");
		text1.setBounds(x, y, width, height);
		text1.setFont(f1);
		y += 40;
		panel.add(text1);*/

		text2 = new JLabel("Ресторан: ");
		text2.setBounds(x, y, width, height);
		text2.setFont(f1);
		y += 40;
		panel.add(text2);

		String rest_arr[] = initNumbersOfRestaurants();
		rest = new JComboBox(rest_arr);
		rest.setBounds(x, y, width, height);
		rest.setFont(f1);
		y += 40;
		panel.add(rest);

		numbersOfReserv = initNumbersOfReservation();
		int quantity = numbersOfReserv.length;
		namesOfClients = initNamesOfClients();
		datesOfReserv = initDatesOfReservation();
		timeOfReserv = initTimeOfReservation();
		quantityOfPeople = initQuantityOfPeopleInReservation();
		String[] header = { "Номер", "Ім'я клієнта", "Дата", "Час", "Кількість людей" };
		
		Object data[][] = new Object[quantity][5];
		for(int i = 0; i<quantity;++i)
			data[i][0] = numbersOfReserv[i];
		for(int i = 0; i<quantity;++i)
			data[i][1] = namesOfClients[i];	
		for(int i = 0; i<quantity;++i)
			data[i][2] = datesOfReserv[i];
		for(int i = 0; i<quantity;++i)
			data[i][3] = timeOfReserv[i];
		for(int i = 0;i<quantity; ++i)
			data[i][4] = quantityOfPeople[i];
	
		//Object data[][] = { { 1, "20.05.2018", "18:00", 6 }, { 2, "20.05.2018", "18:00", 6 } };
		// table = new JTable(data, header); //
		// table.setPreferredScrollableViewportSize(new Dimension(500, 50));
		// JScrollPane scrollPane = new JScrollPane(table);
		// table.setFillsViewportHeight(true);

		// String data[][] = { { "101", "Amit", "670000" }, { "102", "Jai", "780000" },
		// { "101", "Sachin", "700000" } };
		
		//String column[] = { "ID", "NAME", "SALARY" };
		table = new JTable();
		table.setModel(new DefaultTableModel(data, header));
		// table = new JTable(data, column);
		table.setBounds(x, y, 1600, 150);
		table.setFont(f1);
		JScrollPane sp = new JScrollPane(table);

		frame.add(sp);
		panel.add(table);

		frame.add(panel);
		
		rest.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				String selectedItem = (String) rest.getSelectedItem();
				table.setVisible(false);
				panel.remove(table);
				panel.updateUI();
				
			}
		});
		

	}
	
	public String[] initNumbersOfRestaurants() throws SQLException {

		return database.getNumbersOfRestaurants();

	}
	
	public String[] initNumbersOfReservation() throws SQLException {

		return database.getNumberOfReservation();

	}
	
	private String[] initNamesOfClients() throws SQLException {
		return database.getNamesOfClientsInReservation();
	}
	
	public String[] initDatesOfReservation() throws SQLException {

		return database.getDatesOfReservation();

	}

	public String[] initTimeOfReservation() throws SQLException {

		return database.getTimesOfReservation();

	}
	
	public String[] initQuantityOfPeopleInReservation() throws SQLException {

		return database.getQuantityOfPeopleInReservation();

	}
		
	/*public String[] setNumbersOfReservationByRes(String rest) throws SQLException {

		//return database.getNumberOfReservationByRes(rest);

	}*/
	
	private String[] setNamesOfClientsByRes(String rest) throws SQLException {
		return database.getNamesOfClientsInReservation();
	}
	
	public String[] setDatesOfReservationByRes(String rest) throws SQLException {

		return database.getDatesOfReservation();
	}

	public String[] setTimeOfReservationByRes(String rest) throws SQLException {

		return database.getTimesOfReservation();

	}
	
	public String[] setQuantityOfPeopleInReservationByRes(String rest) throws SQLException {

		return database.getQuantityOfPeopleInReservation();

	}
}
