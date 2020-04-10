
// javac HotelController.java
// java -cp .:sqlite-jdbc-3.18.0.jar HotelController
// SQLite skrain HotelDB.db inniheldur Hotel gagnagrunninn.

import java.sql.*;
import java.util.List;
import java.util.ArrayList;


public class HotelController {
    private List<Hotel> hotels;


    private Connection connectToDatab() {
        Connection conn = null;
		// Statement stmt = null;  
		try {
			Class.forName("org.sqlite.JDBC");
			conn = DriverManager.getConnection("jdbc:sqlite:HotelDB.db");
			//stmt = conn.createStatement();
		 //	stmt.executeUpdate("INSERT INTO Hotel VALUES('38','Hotel Sudurnes','South','134')");
		
        /* Ath, ég commentaði línuna hér að ofan út, því Hótel Suðurnes er komið inn núna
        ef á að keyra aftur þarf að setja inn ný gildi því annars kemur villa út af primary key :) */

        System.out.println("Connection successful");

		}
		catch( Exception e ) {
			e.printStackTrace();
        }
        return conn;
	}

    public void addHotel(int ID, String name, String location) throws Exception {
        Connection conn = connectToDatab();
        Statement stmt = conn.createStatement();

        String sql = "INSERT INTO Hotel VALUES(" + ID + name + location + ");";

        ResultSet rs =  stmt.executeQuery(sql);
        
        // return rs;
    }

    public void removeHotel(int ID) {
        String sql = "DELETE FROM Hotel WHERE hotelID = " + ID + ";";

        // more code comes here
    }
    
    public ArrayList<Hotel> searchByLocation(String location) {
        ArrayList<Hotel> temp = new ArrayList<Hotel>();

        String sql = "SELECT * FROM Hotels WHERE location = " + location +";"; 

        // more code comes here
        
        return temp; 
    }

    public ArrayList<Hotel> searchByName(String name) {
        ArrayList<Hotel> temp = new ArrayList<Hotel>();

        String sql = "SELECT * FROM Hotels WHERE name = " + name +";"; 

        // more code comes here

        return temp; 
    }

    public static void main( String[] args ) throws Exception {
        addHotel(999, "TestHotel", "tester");
    }

    // Þetta var í Hotel.java, held þetta ætti að vera hérna frekar
    // jafnvel enn frekar í roomController
    /*
    public void addRoom(int roomID, int hotelID, int price, char available) {
        String sql = "INSERT INTO Room VALUES(" + roomID + hotelID + price + available + ");";
    }
  
    public void removeRoom(int roomID) {
        String sql = "DELETE FROM Room WHERE roomID = " + roomID + ";";
    }

    public void updateAvailability(int roomID, char a) {
        String sql = "UPDATE Room SET available = "+ a + "WHERE roomID = " + roomID + ";";
    }
    */


}