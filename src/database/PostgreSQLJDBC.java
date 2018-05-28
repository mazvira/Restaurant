package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.swing.JComboBox;
import javax.swing.JLabel;

public class PostgreSQLJDBC {
	public static void main(String args[]) {
		Statement stmt = null;
		Connection c = null;
		myFrame fr = new myFrame();
		JComboBox nameList;
		JComboBox typeList;
		fr.setVisible(true);

		try {
			Class.forName("org.postgresql.Driver");
			c = DriverManager.getConnection("jdbc:postgresql://localhost:5432/Restaurant", "postgres", "12345");
			stmt = c.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM Menu;");
			String[] names = new String[100];
			
			int i = 0;

			while (rs.next()) {
				int id = rs.getInt("menu_code");
				String name = rs.getString("menu_name");
				names[i] = name;
				String type = rs.getString("menu_type");
				int time = rs.getInt("valid_time");
				i++;
				// float salary = rs.getFloat("salary");
				System.out.println("ID = " + id);
				System.out.println("NAME = " + name);
				System.out.println("TYPE = " + type);
				System.out.println("AGE = " + time);
				// System.out.println("ADDRESS = " + address);
				// System.out.println("SALARY = " + salary);
				System.out.println();
			}
			/*
			 * String sql = "CREATE TABLE Clients " + "(client_num INT PRIMARY KEY  NOT NULL,"
			 * + " client_name CHAR(100) NOT NULL, 
			 * " + " surname CHAR(100) NOT NULL, " +
			 * " dish_calority Real NOT NULL," + " dish_weight Real NOT NULL)";
			 * stmt.executeUpdate(sql);
			 */
			nameList = new JComboBox(names);
			nameList.setSelectedIndex(0);
			nameList.setBounds(200, 200, 245, 200);
			fr.add(nameList);
			new Login();
		
			rs.close();
			stmt.close();
			c.close();
		}

		catch (Exception e) {
			e.printStackTrace();
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
			System.exit(0);
		}
		System.out.println("Opened database successfully");
	}
}
