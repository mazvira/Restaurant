package project;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

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
			c = DriverManager.getConnection("jdbc:postgresql://172.18.96.1:5432/Restaurant", "postgres", "12345");
			stmt = c.createStatement();
			
			  String sql = "CREATE TABLE Restaurants" +
			            "(rest_num INT PRIMARY KEY   NOT NULL," +
			            " city      TEXT    NOT NULL, " +
			            " street    TEXT     NOT NULL, " +
			            " build_num  INT NOT NULL, " +
			            " open_date Date NOT NULL, " + 
			            " total_area INT NOT NULL)";
			 stmt.executeUpdate(sql);
			 
		} catch (Exception e) {
			e.printStackTrace();
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
			System.exit(0);
		}
	}

	public String[] getNameOfDishes() throws SQLException {
		ResultSet rs = stmt.executeQuery("SELECT menu_name FROM Menu;");
		String names[] = new String[200];
		int i = 0;
		while (rs.next()) {
			String name = rs.getString("menu_name");
			names[i++] = name;
		}
		return names;
	}

	public int isUserExists(String user, String pass) throws SQLException {
		ResultSet rs = stmt
				.executeQuery("select user, pass from Client where user='" + user + "' and pass='" + pass + "'");
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
