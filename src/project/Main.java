package project;

import java.sql.SQLException;

public class Main {
	public static void main(String args[]) {

		try {
			OrderingFood fr = new OrderingFood();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		/*
		 * try { ReservationOfHalls_Tables res = new ReservationOfHalls_Tables(); }
		 * catch (SQLException e) { // TODO Auto-generated catch block
		 * e.printStackTrace(); }
		 */
		//RegForm rg = new RegForm();
		//Login_Registration bd = new Login_Registration();

		// Login log = new Login();

	}
}
