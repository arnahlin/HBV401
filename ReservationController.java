// javac reservationController.java
// java -cp .:sqlite-jdbc-3.18.0.jar ReservationController
// SQLite skrain HotelDB.db inniheldur Hotel gagnagrunninn.

import java.sql.*;

public class ReservationController {

	Connection conn = null; //
	Statement stmt = null; // Statement: The object used for executing a static SQL statement and returning
							// the results it produces.

	/**
	 * Marks the room as booked
	 */
	public void bookRoom(int roomId) {

	}

	public static void main(String[] args) throws Exception {

		try {
			Class.forName("org.sqlite.JDBC");
			conn = DriverManager.getConnection("jdbc:sqlite:HotelDB.db");
			stmt = conn.createStatement();
			// stmt.executeUpdate("INSERT INTO Guest VALUES('Jón
			// Gunnarsson','2309841329','38'),('Sigurður Jónasson','0109725469','43')");
			// stmt.executeUpdate("INSERT INTO Reservation VALUES('12','Sigurður
			// Jónasson','2020-06-20','057');");
			// stmt.executeUpdate("INSERT INTO Occupancy
			// VALUES('2020-06-30','2020-07-04','022');");

			/*
			 * Ath, ég commentaði út þessar 3 línur því ég er búin að setja þær inn í
			 * gagnagrunnana til að sjá hvort að tengingin virki. Ef maðu keyrir skjalið
			 * aftur með þessum gildum þá þá kemur villa út af primary key. Þ.e maður þarf
			 * að setja inn ný gildi.
			 */

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (conn != null)
				conn.close();
		}
	}
}
