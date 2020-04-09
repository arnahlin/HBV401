// javac ReservationController.java
// java -cp .:sqlite-jdbc-3.18.0.jar ReservationController
// SQLite skrain HotelDB.db inniheldur Hotel gagnagrunninn.
// .read CreateTables.sql til að sjá hvort breytingin hafi komist inn.

import java.sql.*;

public class ReservationController {

	// Marks the room as booked
	public void bookRoom(int roomId) {

	}

	public static void main(String[] args) throws Exception {
		Connection conn = null; //
		PreparedStatement pstmt = null;
		//ResultSet r = null;

		try {
			Class.forName("org.sqlite.JDBC");
			conn = DriverManager.getConnection("jdbc:sqlite:HotelDB.db");
			String avail = "SELECT roomID, hotelID FROM Room WHERE roomID=789 AND hotelID=18";
			pstmt = conn.prepareStatement(avail);
			ResultSet rs = pstmt.executeQuery();

			if (!rs.next() ) {    
				System.out.println("This room is occupied");
			   }
			   else{
				//setting room in room table to be occupied
				String setBooked = "UPDATE Room set available='n' WHERE roomID=789 AND hotelID=18";
				PreparedStatement pstmt2 = conn.prepareStatement(setBooked);
				pstmt2.executeUpdate();

			}    


		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (conn != null)
				conn.close();
		}
	}
}
