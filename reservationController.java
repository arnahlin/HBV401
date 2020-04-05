// javac reservationController.java
// java -cp .:sqlite-jdbc-3.18.0.jar ReservationController
// SQLite skrain HotelDB.db inniheldur Hotel gagnagrunninn.

import java.sql.*;

public class ReservationController
{
	public static void main( String[] args ) throws Exception {
		Connection conn = null;  // 
		Statement stmt = null;  //Statement: The object used for executing a static SQL statement and returning the results it produces.
		try
		{
			Class.forName("org.sqlite.JDBC");
			conn = DriverManager.getConnection("jdbc:sqlite:HotelDB.db");
			stmt = conn.createStatement();
			stmt.executeUpdate("INSERT INTO Guest VALUES('Jón Gunnarsson','2309841329','38'),('Sigurður Jónasson','0109725469','43')");
		}
		catch( Exception e )
		{
			e.printStackTrace();
		}
		finally
		{
			if( conn!=null ) conn.close();
		}
	}
}