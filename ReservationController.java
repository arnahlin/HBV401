// javac ReservationController.java
// java -cp .:sqlite-jdbc-3.18.0.jar ReservationController
// SQLite skrain HotelDB.db inniheldur Hotel gagnagrunninn.
// .read CreateTables.sql til að sjá hvort breytingin hafi komist inn.

import java.sql.*;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdIn;

public class ReservationController {
	Guest nyrGestur;

	public void newGuest(String name, String kennitala, String reservationID) throws Exception {
		Connection conn = null; 
		try
		{
			Class.forName("org.sqlite.JDBC");
			conn = DriverManager.getConnection("jdbc:sqlite:HotelDB.db");
			String newGuest = "INSERT INTO Guest VALUES('"+name +"','"+kennitala+"','" +reservationID+"')"; //Setting a new Guest.
			PreparedStatement pstmt1 = conn.prepareStatement(newGuest);
			pstmt1.executeUpdate(); 
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
	
	// Marks the room as booked
	public void bookRoom(int roomID) throws Exception {
		
		Connection conn = null; //
		PreparedStatement pstmt = null;
		//ResultSet r = null;

		try { // athuga hvort herbergi sé laust
			Class.forName("org.sqlite.JDBC");
			conn = DriverManager.getConnection("jdbc:sqlite:HotelDB.db");
			String avail = "SELECT roomID, available FROM Room WHERE available='y' AND roomID=789 AND hotelID=18";
			pstmt = conn.prepareStatement(avail);
			ResultSet rs = pstmt.executeQuery();

			if (!rs.next() ) {    
				System.out.println("This room is occupied");
			   }
			   else{ // setja í Reservation töflu 
				String setReservation = "INSERT INTO Reservation VALUES('12','John Johnsson','2020-09-09','2020-09-21','220')"; //setting room in room table to be occupied
				PreparedStatement pstmt1 = conn.prepareStatement(setReservation);
				pstmt1.executeUpdate(); 
				// updeita herbergi í Room yfir í available = n
				String setBooked = "UPDATE Room set available='y' WHERE roomID=789 AND hotelID=18"; //setting room in room table to be occupied
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

	public static void main(String[] args) throws Exception  {
		ReservationController test = new ReservationController();
		Guest nyrGestur = new Guest();
		StdOut.print("Enter full name and ID number ");
		String nafn = "";
		String IDnumber = "";

			nafn = StdIn.readLine();
			nyrGestur.setName(nafn);
			IDnumber = StdIn.readLine();
			nyrGestur.setId(IDnumber);

		System.out.println("Nafn: " +nyrGestur.name);
		System.out.println("Kennitala: " +nyrGestur.kennitala);

		String resID = "00";
		test.newGuest(nyrGestur.name, nyrGestur.kennitala, resID);

	}
}
