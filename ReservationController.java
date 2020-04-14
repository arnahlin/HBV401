// javac ReservationController.java
// java -cp .:sqlite-jdbc-3.18.0.jar ReservationController
// SQLite skrain HotelDB.db inniheldur Hotel gagnagrunninn.

import java.sql.*;
import java.util.Date;
import java.util.Scanner;
import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;
import java.text.SimpleDateFormat;



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
			conn.close(); // sleppa?
	}

	/** 
	 * Insert new reservation into Reservation table.
	 * **/
	public void makeNewReservation(int resID, String name, String checkIn, String checkOut, int roomID) throws SQLException {
			Connection conn = this.connect();
			String avail = "SELECT roomID, available FROM Room WHERE available='y' AND roomID=" + roomID +";"; // ég tók hotelID burt - Arna
			PreparedStatement pstmt1 = conn.prepareStatement(avail);
			ResultSet rs = pstmt1.executeQuery();
			if (!rs.next()) {
				System.out.println("This room is occupied");
			} else { // setja í Reservation töflu
				String setReservation = "INSERT INTO Reservation VALUES('" +String.valueOf(resID)+ "','"+name+"','"+String.valueOf(checkIn)+"','"+String.valueOf(checkOut)+"','"+String.valueOf(roomID)+ "')"; // setting in Room table to be occupied.
				PreparedStatement pstmt = conn.prepareStatement(setReservation);
				pstmt.executeUpdate();
				String setBooked = "UPDATE Room set available='y' WHERE roomID=" + roomID +";"; // Tók HotelID í burt líka hér. - Þurí.
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

	public boolean searchAfterDates(int roomID, String chkIn) throws Exception {
		Connection conn = this.connect();		
		String In = "SELECT checkIn from Reservation WHERE roomID=" +roomID +";";
				PreparedStatement pstmt  = conn.prepareStatement(In);
				ResultSet rst = pstmt.executeQuery();
				if(rst.isClosed())
    			return true;
				String Out = "SELECT checkOut from Reservation WHERE roomID=" +roomID +";";
				PreparedStatement pstmt2  = conn.prepareStatement(Out);
				ResultSet r = pstmt2.executeQuery();
				if(r.isClosed())
    			return true;
				
				String pattern = "yyyy-MM-dd";
                SimpleDateFormat format = new SimpleDateFormat(pattern);
				Date resChkIn = format.parse(rst.getString(1));
				Date request = format.parse(chkIn);
				Date resChkOut = format.parse(r.getString(1));
				
				if(request.compareTo(resChkIn) > 0 || request.compareTo(resChkIn)== 0){
					if(request.compareTo(resChkOut) < 0 || request.compareTo(resChkOut)== 0 )
					conn.close();
					return false;
				} else {
					conn.close();
					return true;
				}
				
				
	}


	public static void main(String[] args) throws Exception {
		Scanner input = new Scanner(System.in);
		System.out.println("Please enter the ID of the room you want to book.");
		int roomID = input.nextInt();
		input.nextLine();
		ReservationController test = new ReservationController();
		Guest guest = new Guest();
		System.out.println("Please enter your full name:");
		String name = input.nextLine();
		guest.setName(name);
		System.out.println("Please enter your kennitala:");
		String kt = input.nextLine();
		guest.setId(kt);
		System.out.println("Please enter check in date:"); // Biðja um ákveðið format? YYYY-MM-DD
		int checkIn = input.nextInt(); // int svo forritið keyri, á að verða string eins og í línunni fyrir neðan
		// String checkIn = input.nextLine();
		System.out.println("Please enter check out date:"); // Biðja um ákveðið format?
		int checkOut = input.nextInt(); // int svo forritið keyri, á að verða string eins og í línunni fyrir neðan
		// String checkOut = input.nextLine();
		
		Reservation newRes = new Reservation(name, checkIn, checkOut);
		boolean avail = test.searchAfterDates(roomID, newRes.checkinDate);
		if(!avail){
		System.out.println("the room is booked at those dates, please choose other dates or another room.");
		} else { 
		newRes.ReservationID = test.makeNewReservationID(); //gera nýtt reservationID (Max af dálkinum +1)
		test.insertNewGuest(guest.name, guest.kennitala, newRes.ReservationID);
		test.makeNewReservation(newRes.ReservationID, guest.name, newRes.checkinDate, newRes.checkoutDate, roomID); 

		System.out.println();
		System.out.println("Your reservation of room "+ roomID + " was successful.");
		System.out.println("Your reservation number is: "+ newRes.ReservationID);
		System.out.println("The name on the reservation is: "+ guest.name); 
		System.out.println("Check in is on: "+ newRes.checkinDate);
		System.out.println("Check out is on: " + newRes.checkoutDate);
		System.out.println();
		System.out.println("Thank you for using the Hotel search engine.");
	
	
		
		}
		input.close();
	}
}