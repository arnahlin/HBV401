// javac ReservationController.java
// java -cp .:sqlite-jdbc-3.18.0.jar ReservationController
// SQLite skrain HotelDB.db inniheldur Hotel gagnagrunninn.

import java.sql.*;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdIn;
import java.util.Date;
import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ReservationController {

	/**
     * Connect to the HotelDB.db database
     * @return the Connection object
     */
    private Connection connect() {
        String url = "jdbc:sqlite:HotelDB.db";
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(url);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return conn;
    }

	/** 
	 * Insert new Guest into Guest table
	 * **/
	public void insertNewGuest(String name, String kennitala, int reservationID) throws SQLException {
			Connection conn = this.connect();
			String newGuest = "INSERT INTO Guest VALUES('" + name + "','" + kennitala + "','" + String.valueOf(reservationID) + "')"; // Setting a new Guest into table Guest in HotelDB
			PreparedStatement pstmt1 = conn.prepareStatement(newGuest);
			pstmt1.executeUpdate();
	}

	/** 
	 * Insert new reservation into Reservation table.
	 * **/
	public void makeNewReservation(int resID, String name, String checkIn, String checkOut, int roomID) throws SQLException {
			Connection conn = this.connect();
			String avail = "SELECT roomID, available FROM Room WHERE available='y' AND roomID=101 AND hotelID=18"; // ATH! RoomID og hotelID kemur úr search.
			PreparedStatement pstmt1 = conn.prepareStatement(avail);
			ResultSet rs = pstmt1.executeQuery();
			if (!rs.next()) {
				System.out.println("This room is occupied");
			} else { // setja í Reservation töflu
				String setReservation = "INSERT INTO Reservation VALUES('" +String.valueOf(resID)+ "','"+name+"','"+String.valueOf(checkIn)+"','"+String.valueOf(checkOut)+"','"+String.valueOf(roomID)+ "')"; // setting in Room table to be occupied.
				PreparedStatement pstmt = conn.prepareStatement(setReservation);
				pstmt.executeUpdate();
				String setBooked = "UPDATE Room set available='y' WHERE roomID=789 AND hotelID=18"; // updeita herbergi í Room yfir í available = n, eftir að klára þessa þegar search er tilbúið.
				PreparedStatement pstmt2 = conn.prepareStatement(setBooked);
				pstmt2.executeUpdate();
				} 
	}

		/** 
	 * Make new Reservation ID. Find max ReservationID and increment by 1.
	 * **/
	public int makeNewReservationID() throws SQLException {
		String sql = "SELECT MAX(reservationID) FROM Reservation";
        int max = 0;
             Connection conn = this.connect();
             Statement stmt  = conn.createStatement();
             ResultSet rs    = stmt.executeQuery(sql);
            
            while (rs.next()) { 
				max = rs.getInt("MAX(reservationID)");
            }
	return max+1;
	}

	public int searchAfterDates() throws SQLException {
		String sql = "SELECT roomID from Reservation WHERE checkIn='2020-06-01' AND checkOut='2020-06-21'";
        int roomID = 0;
             Connection conn = this.connect();
             Statement stmt  = conn.createStatement();
             ResultSet rs    = stmt.executeQuery(sql);
            
            while (rs.next()) { 
				roomID = rs.getInt("roomID");
            }
	return roomID;
	}


	public static void main(String[] args) throws Exception {
		ReservationController test = new ReservationController();
		Guest nyrGestur = new Guest();
		StdOut.print("Enter full name, ID number ");

		String nafn = StdIn.readLine();
		nyrGestur.setName(nafn);
		String IDnumber = StdIn.readLine();
		nyrGestur.setId(IDnumber);

		System.out.println("Nafn: " + nyrGestur.name);
		System.out.println("Kennitala: " + nyrGestur.kennitala);

		Reservation newRes = new Reservation(nyrGestur.name, 2, 10);
		System.out.println("Nýtt res ID " +test.makeNewReservationID());
		
		newRes.ReservationID = test.makeNewReservationID(); //gera nýtt reservationID (Max af dálkinum +1)
		test.insertNewGuest(nyrGestur.name, nyrGestur.kennitala, newRes.ReservationID); // ok
		test.makeNewReservation(newRes.ReservationID, nyrGestur.name, newRes.checkinDate, newRes.checkoutDate, 555);
		int roomID = test.searchAfterDates();
		System.out.println("roomID after dates: " +roomID);
	}
}
