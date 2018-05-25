package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class PostgreSQLJDBC {
   public static void main(String args[]) {
	  Statement stmt = null;
      Connection c = null;
      try {
         Class.forName("org.postgresql.Driver");
         c = DriverManager
            .getConnection("jdbc:postgresql://localhost:5432/Restaurant",
            "postgres", "12345");
        /* stmt = c.createStatement();
         String sql = "CREATE TABLE Dishes " +
            "(dish_code INT PRIMARY KEY  NOT NULL," +
            " dish_name  CHAR(100) NOT NULL, " +
            " dish_price Real NOT NULL, " +
            " dish_calority Real NOT NULL," +
            " dish_weight Real NOT NULL)";
         stmt.executeUpdate(sql);
         stmt.close();*/
         c.close();
      } catch (Exception e) {
         e.printStackTrace();
         System.err.println(e.getClass().getName()+": "+e.getMessage());
         System.exit(0);
      }
      System.out.println("Opened database successfully");
   }
}
