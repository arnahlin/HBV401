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
	public void insertNewGuest(String name, String kennitala, int reservationID) {
		try {
			Connection conn = this.connect();
			String newGuest = "INSERT INTO Guest VALUES('" + name + "','" + kennitala + "','" + String.valueOf(reservationID) + "')"; // Setting a new Guest into table Guest in HotelDB
			PreparedStatement pstmt1 = conn.prepareStatement(newGuest);
			pstmt1.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
	}

	/** 
	 * Insert new reservation into Reservation table.
	 * **/
	public void makeNewReservation(int resID, String name, Date checkIn, Date checkOut, int roomID) {
		try {
			Connection conn = this.connect();
			String avail = "SELECT roomID, available FROM Room WHERE available='y' AND roomID=789 AND hotelID=18"; // ATH! RoomID og hotelID kemur úr search.
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
			} catch (SQLException e) {
            System.out.println(e.getMessage());
        }
	}

	/** 
	 * Make new Reservation ID. Find max ReservationID and increment by 1.
	 * **/
	public int makeNewReservationID() {
		String sql = "SELECT MAX(reservationID) FROM Reservation";
        int max = 0;
        try (Connection conn = this.connect();
             Statement stmt  = conn.createStatement();
             ResultSet rs    = stmt.executeQuery(sql)){
            
            while (rs.next()) { 
				max = rs.getInt("MAX(reservationID)");
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
	return max+1;
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
		System.out.println("Nýtt res ID " +test.makeNewReservationID());
		
		newRes.ReservationID = test.makeNewReservationID(); //gera nýtt reservationID (Max af dálkinum +1)
		test.insertNewGuest(nyrGestur.name, nyrGestur.kennitala, newRes.ReservationID);
		test.makeNewReservation(newRes.ReservationID,nyrGestur.name,newRes.checkinDate,newRes.checkoutDate,555);
	}
}
