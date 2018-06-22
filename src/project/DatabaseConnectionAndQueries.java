package project;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Time;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Calendar;
//import java.util.Date;

import javax.swing.JComboBox;
import javax.swing.JLabel;

public class DatabaseConnectionAndQueries {

	Statement stmt = null;
	Connection c = null;
	int idOfClient = 0;

	public DatabaseConnectionAndQueries() {
		connection();
	}

	public void connection() {
		try {
			Class.forName("org.postgresql.Driver");
			c = DriverManager.getConnection("jdbc:postgresql://localhost:5432/Restaurant", "postgres", "12345");
			stmt = c.createStatement();

/*			 String sql = "CREATE TABLE Halls_Restaurants " +
					"(hall_num INT NOT NULL," + 
					" rest_num INT NOT NULL)"; 
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
		rs = stmt.executeQuery("SELECT type_name, quantity_of_places FROM Halls;");
		int i = 0;
		while (rs.next()) {
			String name = rs.getString("type_name");
			int places = rs.getInt("quantity_of_places");
			names[i++] = name + " " + places;
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
		rs = stmt.executeQuery("SELECT street, build_num FROM Restaurants;");
		int i = 0;
		while (rs.next()) {
			String street = rs.getString("street");
			int build_num = rs.getInt("build_num");
			names[i++] = street + "  " + build_num;
		}
		return names;
	}
	
	public String[] getNumbersOfRestaurants() throws SQLException {
		ResultSet rs = stmt.executeQuery("SELECT rest_num FROM Restaurants;");
		int quantity = 0;
		while (rs.next()) {
			quantity++;
		}
		String names[] = new String[quantity];
		rs = stmt.executeQuery("SELECT rest_num FROM Restaurants;");
		int i = 0;
		while (rs.next()) {
			int build_num = rs.getInt("rest_num");
			names[i++] =  build_num + "  ";
		}
		return names;
	}
	
	/*public String[] getNumbersOfRestaurantsByRes(String rest) throws SQLException {
		int num = Integer.parseInt(rest);
		ResultSet rs = stmt.executeQuery("SELECT rest_num FROM Restaurants WHERE rest_num = "+num+";");
		int quantity = 0;
		while (rs.next()) {
			quantity++;
		}
		String names[] = new String[quantity];
		rs = stmt.executeQuery("SELECT rest_num FROM Restaurants WHERE rest_num = "+num+";");
		int i = 0;
		while (rs.next()) {
			int build_num = rs.getInt("rest_num");
			names[i++] =  build_num + "  ";
		}
		return names;
	}*/

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
			String pr = "      " + price;
			String all = name + calor + pr;
			names[i++] = all;
		}
		return names;
	}

	public String[] getNameOfDishes() throws SQLException {
		ResultSet rs = stmt.executeQuery("SELECT dish_name FROM Dishes;");
		int quantity = 0;

		while (rs.next()) {
			quantity++;
		}
		String names[] = new String[quantity];
		rs = stmt.executeQuery("SELECT dish_name FROM Dishes;");
		int i = 0;
		while (rs.next()) {
			String name = rs.getString("dish_name");
			names[i++] = name;
		}
		return names;
	}

	public String[] getNameOfDishesBySpecificMenu(String menu) throws SQLException {
		ResultSet rs = stmt.executeQuery(
				"SELECT dish_name FROM (Dishes INNER JOIN Menu_Dishes ON Dishes.dish_code = Menu_Dishes.dish_code) INNER JOIN Menu ON Menu_Dishes.menu_code = Menu.menu_code WHERE menu_name = '"+ menu + "';");
		int quantity = 0;

		while (rs.next()) {
			quantity++;
		}
		String names[] = new String[quantity];
		rs = stmt.executeQuery(
				"SELECT dish_name FROM (Dishes INNER JOIN Menu_Dishes ON Dishes.dish_code = Menu_Dishes.dish_code) INNER JOIN Menu ON Menu_Dishes.menu_code = Menu.menu_code WHERE menu_name = '"
						+ menu + "';");
		int i = 0;
		while (rs.next()) {
			String name = rs.getString("dish_name");
			names[i++] = name;
		}
		return names;
	}

	public String[] getDishesCalorityAndPrice() throws SQLException {
		ResultSet rs = stmt.executeQuery("SELECT dish_calority, dish_price FROM Dishes;");
		int quantity = 0;

		while (rs.next()) {
			quantity++;
		}
		String names[] = new String[quantity];
		rs = stmt.executeQuery("SELECT dish_calority, dish_price FROM Dishes;");
		int i = 0;
		while (rs.next()) {
			double calority = rs.getDouble("dish_calority");
			String calor = calority + "  ";
			int price = rs.getInt("dish_price");
			String pr = "      " + price;
			String all = calor + pr;
			names[i++] = all;
		}
		return names;
	}

	public String[] getDishesCalorityAndPrice(String menu) throws SQLException {
		ResultSet rs = stmt.executeQuery(
				"SELECT dish_calority, dish_price FROM (Dishes INNER JOIN Menu_Dishes ON Dishes.dish_code = Menu_Dishes.dish_code) INNER JOIN Menu ON Menu_Dishes.menu_code = Menu.menu_code WHERE menu_name = '"+ menu + "';");
		int quantity = 0;

		while (rs.next()) {
			quantity++;
		}
		String names[] = new String[quantity];
		rs = stmt.executeQuery("SELECT dish_calority, dish_price FROM (Dishes INNER JOIN Menu_Dishes ON Dishes.dish_code = Menu_Dishes.dish_code) INNER JOIN Menu ON Menu_Dishes.menu_code = Menu.menu_code WHERE menu_name = '"+ menu + "';");
		int i = 0;
		while (rs.next()) {
			double calority = rs.getDouble("dish_calority");
			String calor = calority + "  ";
			int price = rs.getInt("dish_price");
			String pr = "      " + price;
			String all = calor + pr;
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

	public String[] getNumberOfReservation() throws SQLException {
		ResultSet rs = stmt.executeQuery("SELECT book_num FROM Booking;");
		int quantity = 0;

		while (rs.next()) {
			quantity++;
		}
		String names[] = new String[quantity];
		rs = stmt.executeQuery("SELECT book_num FROM Booking;");
		int i = 0;
		while (rs.next()) {
			int number = rs.getInt("book_num");
			names[i++] = " " + number;
		}
		return names;
	}
	
	public String[] getNumberOfReservation(String rest) throws SQLException {
		ResultSet rs = stmt.executeQuery("SELECT book_num FROM Booking INNER JOIN Booking_Tables ON Booking.book_num = Booking_Tables.book_num;");
		int quantity = 0;

		while (rs.next()) {
			quantity++;
		}
		String names[] = new String[quantity];
		rs = stmt.executeQuery("SELECT book_num FROM Booking;");
		int i = 0;
		while (rs.next()) {
			int number = rs.getInt("book_num");
			names[i++] = " " + number;
		}
		return names;
	}

	public String[] getNamesOfClientsInReservation() throws SQLException {
		ResultSet rs = stmt.executeQuery("SELECT client_num FROM Booking;");
		int quantity = 0;

		while (rs.next()) {
			quantity++;
		}
		String names[] = new String[quantity];
		rs = stmt.executeQuery(
				"SELECT client_name, surname FROM Booking INNER JOIN Clients ON Booking.client_num = Clients.client_num;");
		int i = 0;
		while (rs.next()) {
			String name = rs.getString("client_name");
			String surname = rs.getString("surname");
			names[i++] = name + " " + surname;
		}
		return names;
	}

	public String[] getDatesOfReservation() throws SQLException {
		ResultSet rs = stmt.executeQuery("SELECT date_on_when_booked FROM Booking;");
		int quantity = 0;

		while (rs.next()) {
			quantity++;
		}
		String names[] = new String[quantity];
		rs = stmt.executeQuery("SELECT date_on_when_booked FROM Booking;");
		int i = 0;
		while (rs.next()) {
			Date date = rs.getDate("date_on_when_booked");
			DateFormat df = new SimpleDateFormat("yyyy/MM/dd");
			names[i++] = df.format(date);
		}
		return names;
	}

	public String[] getTimesOfReservation() throws SQLException {
		ResultSet rs = stmt.executeQuery("SELECT reserv_time FROM Booking;");
		int quantity = 0;

		while (rs.next()) {
			quantity++;
		}
		String names[] = new String[quantity];
		rs = stmt.executeQuery("SELECT reserv_time FROM Booking;");
		int i = 0;
		while (rs.next()) {
			Time time = rs.getTime("reserv_time");
			names[i++] = time.toString();
		}
		return names;
	}

	public String[] getQuantityOfPeopleInReservation() throws SQLException {
		ResultSet rs = stmt.executeQuery("SELECT quantity_of_people FROM Booking;");
		int quantity = 0;

		while (rs.next()) {
			quantity++;
		}
		String names[] = new String[quantity];
		rs = stmt.executeQuery("SELECT quantity_of_people FROM Booking;");
		int i = 0;
		while (rs.next()) {
			int quantity_of = rs.getInt("quantity_of_people");
			names[i++] = quantity_of + " ";
		}
		return names;
	}

	public String[] getNumbersOfDelivery() throws SQLException {
		ResultSet rs = stmt.executeQuery("SELECT deliv_num FROM Delivery;");
		int quantity = 0;

		while (rs.next()) {
			quantity++;
		}
		String names[] = new String[quantity + 1];
		rs = stmt.executeQuery("SELECT deliv_num FROM Delivery;");
		int i = 0;
		names[0] = "Номер замовлення";
		i++;
		while (rs.next()) {
			int number = rs.getInt("deliv_num");
			names[i++] = number + " ";
		}
		return names;
	}

	public String[] getNamesOfClientsInDelivery() throws SQLException {
		ResultSet rs = stmt.executeQuery("SELECT client_num FROM Delivery;");
		int quantity = 0;

		while (rs.next()) {
			quantity++;
		}
		String names[] = new String[quantity + 1];
		rs = stmt.executeQuery(
				"SELECT client_name, surname FROM Delivery INNER JOIN Clients ON Delivery.client_num = Clients.client_num;");
		int i = 0;
		names[0] = "Ім'я клієнтy";
		i++;
		while (rs.next()) {
			String name = rs.getString("client_name");
			String surname = rs.getString("surname");
			names[i++] = name + " " + surname;
		}
		return names;
	}

	public String[] getDatesOfDelivery() throws SQLException {
		ResultSet rs = stmt.executeQuery("SELECT date_on_when_deliv FROM Delivery;");
		int quantity = 0;
		while (rs.next()) {
			quantity++;
		}
		String dates[] = new String[quantity + 1];
		rs = stmt.executeQuery("SELECT date_on_when_deliv FROM Delivery;");
		int i = 0;
		dates[0] = "Дата доставки";
		i++;
		while (rs.next()) {
			Date date = rs.getDate("date_on_when_deliv");
			DateFormat df = new SimpleDateFormat("yyyy/MM/dd");
			dates[i++] = df.format(date);
		}
		return dates;
	}

	public String[] getAddressOfDeliv() throws SQLException {
		ResultSet rs = stmt.executeQuery("SELECT deliv_adress FROM Delivery;");
		int quantity = 0;

		while (rs.next()) {
			quantity++;
		}
		String names[] = new String[quantity + 1];
		rs = stmt.executeQuery("SELECT deliv_adress FROM Delivery;");
		int i = 0;
		names[0] = "Адреса доставки";
		i++;
		while (rs.next()) {
			String address = rs.getString("deliv_adress");
			names[i++] = address;
		}
		return names;
	}
	
	public String[] getCitiesOfDeliv() throws SQLException {
		ResultSet rs = stmt.executeQuery("SELECT city FROM Delivery;");
		int quantity = 0;

		while (rs.next()) {
			quantity++;
		}
		String names[] = new String[quantity + 1];
		rs = stmt.executeQuery("SELECT city FROM Delivery;");
		int i = 0;
		names[0] = "Місто доставки";
		i++;
		while (rs.next()) {
			String address = rs.getString("city");
			names[i++] = address;
		}
		return names;
	}

	public String[] getSumOfDelivery() throws SQLException {
		ResultSet rs = stmt.executeQuery("SELECT deliv_sum FROM Delivery;");
		int quantity = 0;

		while (rs.next()) {
			quantity++;
		}
		String names[] = new String[quantity + 1];
		rs = stmt.executeQuery("SELECT deliv_sum FROM Delivery;");
		int i = 0;
		names[0] = "Сума замовлення";
		i++;
		while (rs.next()) {
			int sum = rs.getInt("deliv_sum");
			names[i++] = sum + " ";
		}
		return names;
	}

	public String[] getCostOfDelivery() throws SQLException {
		ResultSet rs = stmt.executeQuery("SELECT deliv_cost FROM Delivery;");
		int quantity = 0;

		while (rs.next()) {
			quantity++;
		}
		String names[] = new String[quantity + 1];
		rs = stmt.executeQuery("SELECT deliv_cost FROM Delivery;");
		int i = 0;
		names[0] = "Сума за доставку";
		i++;
		while (rs.next()) {
			int sum = rs.getInt("deliv_cost");
			names[i++] = sum + " ";
		}
		return names;
	}

	public String[] getIsDeliv() throws SQLException {
		ResultSet rs = stmt.executeQuery("SELECT is_deliv FROM Delivery;");
		int quantity = 0;

		while (rs.next()) {
			quantity++;
		}
		String names[] = new String[quantity + 1];
		rs = stmt.executeQuery("SELECT is_deliv FROM Delivery;");
		int i = 0;
		names[0] = "Чи доставлено";
		i++;
		while (rs.next()) {
			String sum = rs.getString("is_deliv");
			names[i++] = sum;
		}
		return names;
	}

	public String[] getIsPaid() throws SQLException {
		ResultSet rs = stmt.executeQuery("SELECT is_paid FROM Delivery;");
		int quantity = 0;

		while (rs.next()) {
			quantity++;
		}
		String names[] = new String[quantity + 1];
		rs = stmt.executeQuery("SELECT is_paid FROM Delivery;");
		int i = 0;		
		names[0] = "Чи проплачено";
		i++;
		while (rs.next()) {
			String sum = rs.getString("is_paid");
			names[i++] = sum;
		}
		return names;
	}

	public String[] getNumbersOfDelivByCity(String city) throws SQLException {
		ResultSet rs = stmt.executeQuery("SELECT deliv_num FROM Delivery WHERE city = '" + city + "';");
		int quantity = 0;
		while (rs.next()) {
			quantity++;
		}
		String names[] = new String[quantity + 1];
		rs = stmt.executeQuery(
				"SELECT deliv_num FROM Delivery WHERE city = '" + city + "';");
		int i = 0;
		names[0] = "Номер замовлення";
		i++;
		while (rs.next()) {
			int num = rs.getInt("deliv_num");
			names[i++] = num + "  ";
		}
		return names;
		
	}
	
	public String[] getNamesOfClientsOfDelivByCity(String city) throws SQLException {
		ResultSet rs = stmt.executeQuery("SELECT client_num FROM Delivery WHERE city = '" + city + "';");
		int quantity = 0;

		while (rs.next()) {
			quantity++;
		}
		String names[] = new String[quantity + 1];
		rs = stmt.executeQuery(
				"SELECT client_name, surname FROM Delivery INNER JOIN Clients ON Delivery.client_num = Clients.client_num WHERE city = '" + city + "';");
		int i = 0;
		names[0] = "Ім'я клієнтy";
		i++;
		while (rs.next()) {
			String name = rs.getString("client_name");
			String surname = rs.getString("surname");
			names[i++] = name + " " + surname;
		}
		return names;
	}
	
	public String[] getDatesOfDeliveryByCity(String city) throws SQLException {
		ResultSet rs = stmt.executeQuery("SELECT date_on_when_deliv FROM Delivery WHERE city = '" + city + "';");
		int quantity = 0;
		while (rs.next()) {
			quantity++;
		}
		String dates[] = new String[quantity + 1];
		rs = stmt.executeQuery("SELECT date_on_when_deliv FROM Delivery WHERE city = '" + city + "';");
		int i = 0;
		dates[0] = "Дата доставки";
		i++;
		while (rs.next()) {
			Date date = rs.getDate("date_on_when_deliv");
			DateFormat df = new SimpleDateFormat("yyyy/MM/dd");
			dates[i++] = df.format(date);
		}
		return dates;
	}
	
	public String[] getAddressOfDeliveryByCity(String city) throws SQLException {
		ResultSet rs = stmt.executeQuery("SELECT deliv_adress FROM Delivery WHERE city = '" + city + "';");
		int quantity = 0;

		while (rs.next()) {
			quantity++;
		}
		String names[] = new String[quantity + 1];
		rs = stmt.executeQuery("SELECT deliv_adress FROM Delivery WHERE city = '" + city + "';");
		int i = 0;
		names[0] = "Адреса доставки";
		i++;
		while (rs.next()) {
			String address = rs.getString("deliv_adress");
			names[i++] = address;
		}
		return names;
	}
	
	public String[] getCitiesOfDeliveryByCity(String city) throws SQLException {
		ResultSet rs = stmt.executeQuery("SELECT city FROM Delivery WHERE city = '" + city + "';");
		int quantity = 0;

		while (rs.next()) {
			quantity++;
		}
		String names[] = new String[quantity + 1];
		rs = stmt.executeQuery("SELECT city FROM Delivery WHERE city = '" + city + "';");
		int i = 0;
		names[0] = "Місто доставки";
		i++;
		while (rs.next()) {
			String address = rs.getString("city");
			names[i++] = address;
		}
		return names;
	}
	
	public String[] getSumOfDeliveryByCity(String city) throws SQLException {
		ResultSet rs = stmt.executeQuery("SELECT deliv_sum FROM Delivery WHERE city = '" + city + "';");
		int quantity = 0;

		while (rs.next()) {
			quantity++;
		}
		String names[] = new String[quantity + 1];
		rs = stmt.executeQuery("SELECT deliv_sum FROM Delivery WHERE city = '" + city + "';");
		int i = 0;
		names[0] = "Сума замовлення";
		i++;
		while (rs.next()) {
			int sum = rs.getInt("deliv_sum");
			names[i++] = sum + " ";
		}
		return names;
	}

	public String[] getCostOfDeliveryByCity(String city) throws SQLException {
		ResultSet rs = stmt.executeQuery("SELECT deliv_cost FROM Delivery WHERE city = '" + city + "' ;");
		int quantity = 0;

		while (rs.next()) {
			quantity++;
		}
		String names[] = new String[quantity + 1];
		rs = stmt.executeQuery("SELECT deliv_cost FROM Delivery WHERE city = '" + city + "';");
		int i = 0;
		names[0] = "Сума за доставку";
		i++;
		while (rs.next()) {
			int sum = rs.getInt("deliv_cost");
			names[i++] = sum + " ";
		}
		return names;
	}

	public String[] getIsDelivByCity(String city) throws SQLException {
		ResultSet rs = stmt.executeQuery("SELECT is_deliv FROM Delivery WHERE city = '" + city + "';");
		int quantity = 0;

		while (rs.next()) {
			quantity++;
		}
		String names[] = new String[quantity + 1];
		rs = stmt.executeQuery("SELECT is_deliv FROM Delivery WHERE city = '" + city + "';");
		int i = 0;
		names[0] = "Чи доставлено";
		i++;
		while (rs.next()) {
			String sum = rs.getString("is_deliv");
			names[i++] = sum;
		}
		return names;
	}

	public String[] getIsPaidByCity(String city) throws SQLException {
		ResultSet rs = stmt.executeQuery("SELECT is_paid FROM Delivery WHERE city = '" + city + "';");
		int quantity = 0;

		while (rs.next()) {
			quantity++;
		}
		String names[] = new String[quantity + 1];
		rs = stmt.executeQuery("SELECT is_paid FROM Delivery WHERE city = '" + city + "';");
		int i = 0;		
		names[0] = "Чи проплачено";
		i++;
		while (rs.next()) {
			String sum = rs.getString("is_paid");
			names[i++] = sum;
		}
		return names;
	}

	public int isUserExists(String user, String pass) throws SQLException {
		ResultSet rs = stmt.executeQuery(
				"SELECT login, password from Clients where login='" + user + "' and password='" + pass + "'");
		int count = 0;
		while (rs.next()) {
			count = count + 1;
		}
		if (count == 1) {
			rs = stmt.executeQuery(
					"SELECT client_num from Clients where login='" + user + "' and password='" + pass + "'");
			while (rs.next()) {
				int num = rs.getInt("client_num");
				idOfClient = num;
			}
		}

		return count;
	}

	public int isStaffExists(int id, String pass) throws SQLException {
		ResultSet rs = stmt.executeQuery(
				"SELECT id_staff, password from Staff where id_staff='" + id + "' and password='" + pass + "'");
		int count = 0;
		while (rs.next()) {
			count = count + 1;
		}
		return count;
	}

	public String[] getRestaurantsByCity(String nameOfTheCity) throws SQLException {
		ResultSet rs = stmt.executeQuery("SELECT rest_num FROM Restaurants WHERE city = '" + nameOfTheCity + "';");
		int quantity = 0;
		while (rs.next()) {
			quantity++;
		}
		String names[] = new String[quantity];
		rs = stmt.executeQuery(
				"SELECT rest_num, street, build_num FROM Restaurants WHERE city = '" + nameOfTheCity + "';");
		int i = 0;
		while (rs.next()) {
			int num = rs.getInt("rest_num");
			String street = rs.getString("street");
			int build_num = rs.getInt("build_num");
			names[i++] = num + "  " + street + "  " + build_num;
		}
		return names;
	}

	public void insertIntoClients(String name, String surname, String patronymic, String email, Date birth_date,
			String password) throws SQLException {
		ResultSet rs = stmt.executeQuery("SELECT client_num FROM Clients;");
		int id = 0;
		while (rs.next()) {
			id = rs.getInt("client_num");
		}
		System.out.println(id);
		id = id + 1;
		String sql = "INSERT INTO Clients(client_num, client_name, surname, patronymic, email, birth_date, password) "
				+ "VALUES ('" + id + "', '" + name + "', '" + surname + "', '" + patronymic + "', '" + email + "', '"
				+ birth_date + "', '" + password + "');";
		stmt.executeUpdate(sql);

	}

	public void insertIntoDelivery(Date on_when, String address, String city, int deliv_sum, int deliv_cost)
			throws SQLException {
		ResultSet rs = stmt.executeQuery("SELECT deliv_num FROM Delivery;");
		int id = 0;
		while (rs.next()) {
			id = rs.getInt("deliv_num");
		}
		id = id + 1;
		/*
		 * Date date = null;
		 * 
		 * // Date dt = new Date(); Calendar cal = Calendar.getInstance(); LocalDateTime
		 * now = LocalDateTime.now();
		 */
		/*
		 * cal.setTime(date); int year = cal.get(Calendar.YEAR); int month =
		 * cal.get(Calendar.MONTH); int day = cal.get(Calendar.DAY_OF_MONTH);
		 */
		// Date dt = new Date(2018, 12, 6);
		String str = "2018-06-2";
		Date dt = Date.valueOf(str);
		Date currentDate = new Date(Calendar.getInstance().getTime().getTime());
		int client_num = 703;

		String sql = "INSERT INTO Delivery(deliv_num, deliv_date, date_on_when_deliv, deliv_adress, is_deliv, is_paid, client_num, city, deliv_sum, deliv_cost, discount) "
				+ "VALUES ('" + id + "', '" + currentDate + "', '" + dt + "', '" + address + "', 'ні', 'ні', '"
				+ client_num + "', '" + city + "', '" + deliv_sum + "', '" + deliv_cost + "', 5);";
		stmt.executeUpdate(sql);

		int dishcode = 402;
		String sql2 = "INSERT INTO Delivery_Dishes(deliv_num, dish_code, dishes_amount) " + "VALUES ('" + id + "', '"
				+ dishcode + "', '" + 5 + "');";
		stmt.executeUpdate(sql2);

	}

	public void insertIntoBooking(Date on_when, Time time, int price, int quantity_of_people, String event_name)
			throws SQLException {
		ResultSet rs = stmt.executeQuery("SELECT book_num FROM Booking;");
		int id = 0;
		while (rs.next()) {
			id = rs.getInt("book_num");
		}

		// Date dt = new Date();
		Calendar cal = Calendar.getInstance();
		LocalDateTime now = LocalDateTime.now();
		/*
		 * cal.setTime(date); int year = cal.get(Calendar.YEAR); int month =
		 * cal.get(Calendar.MONTH); int day = cal.get(Calendar.DAY_OF_MONTH);
		 */
		String str = "2018-06-2";
		Date dt = Date.valueOf(str);
		id = id + 2;
		int client_num = 703;

		String sql = "INSERT INTO Booking(book_num, date_when_booked, date_on_when_booked, reserv_duration, book_price, quantity_of_people, event_name, discount, client_num) "
				+ "VALUES ('" + id + "', '" + dt + "', '" + dt + "', '" + time + "', '" + price + "', '"
				+ quantity_of_people + "', '" + event_name + "' ,  5 , '" + client_num + "');";
		stmt.executeUpdate(sql);

		int table_num = 1007;
		String sql2 = "INSERT INTO Booking_Tables(table_num, book_num) " + "VALUES ('" + table_num + "', '" + id
				+ "');";
		stmt.executeUpdate(sql2);

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
