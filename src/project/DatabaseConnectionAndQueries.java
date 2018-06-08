package project;

import java.sql.Connection;
//import java.sql.Date;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.util.Calendar;
import java.util.Date;

import javax.swing.JComboBox;
import javax.swing.JLabel;

public class DatabaseConnectionAndQueries {

	Statement stmt = null;
	Connection c = null;

	public DatabaseConnectionAndQueries() {
		connection();
	}

	public void connection() {
		try {
			Class.forName("org.postgresql.Driver");
			c = DriverManager.getConnection("jdbc:postgresql://localhost:5432/Restaurant", "postgres", "12345");
			stmt = c.createStatement();

			 
			 /* String sql = "CREATE TABLE Delivery_Dishes" +
			  "(deliv_num INT NOT NULL," + 
		      " dish_code INT NOT NULL, " + 
			  " dishes_amount INT NOT NULL)"; 
			  stmt.executeUpdate(sql);*/
		

		} catch (Exception e) {
			e.printStackTrace();
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
			System.exit(0);
		}
	}

	public String[] getNameOfMenus() throws SQLException {
		ResultSet rs = stmt.executeQuery("SELECT menu_name FROM Menu;");
		int quantity = 0;
		while (rs.next()) {
			quantity++;
		}
		String names[] = new String[quantity];
		rs = stmt.executeQuery("SELECT menu_name FROM Menu;");
		int i = 0;
		while (rs.next()) {
			String name = rs.getString("menu_name");
			names[i++] = name;
		}
		return names;
	}

	public String[] getNameOfHalls() throws SQLException {
		ResultSet rs = stmt.executeQuery("SELECT type_name FROM Halls;");
		int quantity = 0;
		while (rs.next()) {
			quantity++;
		}
		String names[] = new String[quantity];
		rs = stmt.executeQuery("SELECT type_name FROM Halls;");
		int i = 0;
		while (rs.next()) {
			String name = rs.getString("type_name");
			names[i++] = name;
		}
		return names;
	}

	public String[] getNameOfRestaurants() throws SQLException {
		ResultSet rs = stmt.executeQuery("SELECT rest_num FROM Restaurants;");
		int quantity = 0;
		while (rs.next()) {
			quantity++;
		}
		String names[] = new String[quantity];
		rs = stmt.executeQuery("SELECT rest_num FROM Restaurants;");
		int i = 0;
		while (rs.next()) {
			int num = rs.getInt("rest_num");
			names[i++] = " " + num;
		}
		return names;
	}

	public String[] getNameOfDishesWithCalorityAndPrice() throws SQLException {
		ResultSet rs = stmt.executeQuery("SELECT dish_name, dish_calority, dish_price FROM Dishes;");
		int quantity = 0;
		while (rs.next()) {
			quantity++;
		}
		String names[] = new String[quantity];
		rs = stmt.executeQuery("SELECT dish_name, dish_calority, dish_price FROM Dishes;");
		int i = 0;
		while (rs.next()) {
			String name = rs.getString("dish_name");
			double calority = rs.getDouble("dish_calority");
			String calor = "  " + calority;
			int price = rs.getInt("dish_price");
			String pr = "       " + price;
			String all = name + calor + pr;
			names[i++] = all;
		}
		return names;
	}

	public String[] getNameOfCitiesOfRestaurants() throws SQLException {
		ResultSet rs = stmt.executeQuery("SELECT city FROM Restaurants;");
		String names[] = new String[200];
		int i = 0;
		while (rs.next()) {
			String name = rs.getString("city");
			names[i++] = name;
		}
		return names;
	}

	public int isUserExists(String user, String pass) throws SQLException {
		ResultSet rs = stmt
				.executeQuery("select client_name, password from Clients where client_name='" + user + "' and password='" + pass + "'");
		int count = 0;
		while (rs.next()) {
			count = count + 1;
		}
		return count;
	}

	public String[] getCitiesOfRestaurant() throws SQLException {
		ResultSet rs = stmt.executeQuery("SELECT city FROM Menu;");
		String names[] = new String[200];
		int i = 0;
		while (rs.next()) {
			String name = rs.getString("city");
			names[i++] = name;
		}
		return names;
	}

	public void insertIntoClients(String name, String surname, String patronymic, String email, Date birth_date, String password)
			throws SQLException {
		ResultSet rs = stmt.executeQuery("SELECT client_num FROM Clients;");
		int id = 0;
		while (rs.next()) {
			id = rs.getInt("client_num");
		}
		System.out.println(id);
		id = id + 2 ;
		String sql = "INSERT INTO Clients(client_num, client_name, surname, patronymic, email, birth_date, password) "
				+ "VALUES ('" + id + "', '" + name + "', '" + surname + "', '" + patronymic + "', '" + email
				+ "', '" + birth_date + "', '"+password+"');";
		stmt.executeUpdate(sql);

	}
	
	public void insertIntoDelivery(Date on_when, String address, String city, int deliv_sum, int deliv_cost)
			throws SQLException {
		ResultSet rs = stmt.executeQuery("SELECT deliv_num FROM Delivery;");
		int id = 0;
		while (rs.next()) {
			id = rs.getInt("deliv_num");
		}
		Date date = null;
		
		//Date dt = new Date();
		Calendar cal = Calendar.getInstance();
		LocalDateTime now = LocalDateTime.now();
		/*cal.setTime(date);
		int year = cal.get(Calendar.YEAR);
		int month = cal.get(Calendar.MONTH);
		int day = cal.get(Calendar.DAY_OF_MONTH);*/
		Date dt = new Date(2018, 12, 6);
		id = id + 1;
		int client_num = 703;

		String sql = "INSERT INTO Delivery(deliv_num, deliv_date, date_on_when_deliv, deliv_adress, is_deliv, is_paid, client_num, city, deliv_sum, deliv_cost, discount) "
				+ "VALUES ('" + id + "', '"+dt+"', '" + on_when + "', '" + address + "', 'ні', 'ні', '"+client_num+"', '"+city+"', '"+deliv_sum+"', '"+deliv_cost+"', 5);";
		stmt.executeUpdate(sql);
	
	}
	/*
	 * while(rs.next()) { int id = rs.getInt("menu_code"); String name =
	 * rs.getString("menu_name"); String type = rs.getString("menu_type"); int time
	 * = rs.getInt("valid_time"); // float salary = rs.getFloat("salary");
	 * System.out.println("ID = " + id); System.out.println("NAME = " + name);
	 * System.out.println("TYPE = " + type); System.out.println("AGE = " + time); //
	 * System.out.println("ADDRESS = " + address); // System.out.println("SALARY = "
	 * + salary); System.out.println(); }
	 */

	/*
	 * String sql = "CREATE TABLE Clients " +
	 * "(client_num INT PRIMARY KEY  NOT NULL," + " client_name CHAR(100) NOT NULL,
	 * " + " surname CHAR(100) NOT NULL, " + " dish_calority Real NOT NULL," +
	 * " dish_weight Real NOT NULL)"; stmt.executeUpdate(sql);
	 */

	// rs.close();stmt.close();c.close();
}
