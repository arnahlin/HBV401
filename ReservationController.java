// javac ReservationController.java
// java -cp .:sqlite-jdbc-3.18.0.jar ReservationController
// SQLite skrain HotelDB.db inniheldur Hotel gagnagrunninn.
// .read CreateTables.sql til að sjá hvort breytingin hafi komist inn.

import java.sql.*;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdIn;
import java.util.Date;

public class ReservationController {

	Connection conn = null;


	public void newGuest(String name, String kennitala, int reservationID) throws Exception {
		try {
			Class.forName("org.sqlite.JDBC");
			conn = DriverManager.getConnection("jdbc:sqlite:HotelDB.db");
			String newGuest = "INSERT INTO Guest VALUES('" + name + "','" + kennitala + "','" + String.valueOf(reservationID) + "')"; // Setting a new Guest into table Guest in HotelDB
			PreparedStatement pstmt1 = conn.prepareStatement(newGuest);
			pstmt1.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (conn != null)
				conn.close();
		}
	}

	public void bookRoom(int resID, String name, Date checkIn, Date checkOut, int roomID) throws Exception {
		PreparedStatement pstmt = null;
		

		try { // athuga hvort herbergi sé laust
			Class.forName("org.sqlite.JDBC");
			conn = DriverManager.getConnection("jdbc:sqlite:HotelDB.db");
			String avail = "SELECT roomID, available FROM Room WHERE available='y' AND roomID=789 AND hotelID=18";
			pstmt = conn.prepareStatement(avail);
			ResultSet rs = pstmt.executeQuery();

			if (!rs.next()) {
				System.out.println("This room is occupied");
			} else { // setja í Reservation töflu
				String setReservation = "INSERT INTO Reservation VALUES('" +String.valueOf(resID)+ "','"+name+"','"+String.valueOf(checkIn)+"','"+String.valueOf(checkOut)+"','"+String.valueOf(roomID)+ "')"; // setting in Room table to be occupied.
				PreparedStatement pstmt1 = conn.prepareStatement(setReservation);
				pstmt1.executeUpdate();
				String setBooked = "UPDATE Room set available='y' WHERE roomID=789 AND hotelID=18"; // updeita herbergi í Room yfir í available = n, eftir að klára þessa.
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

	public static void main(String[] args) throws Exception {
		ReservationController test = new ReservationController();
		Guest nyrGestur = new Guest();
		StdOut.print("Enter full name and ID number ");

		String nafn = StdIn.readLine();
		nyrGestur.setName(nafn);
		String IDnumber = StdIn.readLine();
		nyrGestur.setId(IDnumber);

		System.out.println("Nafn: " + nyrGestur.name);
		System.out.println("Kennitala: " + nyrGestur.kennitala);

		Reservation newRes = new Reservation(nyrGestur.name, 20, 15);
		newRes.ReservationID = 15;  // ath, þarf að ná í max ReservationID og hækka um 1. Á það eftir.
		test.newGuest(nyrGestur.name, nyrGestur.kennitala, newRes.ReservationID);
		test.bookRoom(newRes.ReservationID,nyrGestur.name,newRes.checkinDate,newRes.checkoutDate,333);

	}
}
