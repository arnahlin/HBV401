// javac RoomController.java
// java -cp .:sqlite-jdbc-3.18.0.jar RoomController
// SQLite skrain HotelDB.db inniheldur Hotel gagnagrunninn.

import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class RoomController {

    private Connection dataBase() {
        Connection conn = null;
		try {
			Class.forName("org.sqlite.JDBC");
			conn = DriverManager.getConnection("jdbc:sqlite:HotelDB.db");
		}
		catch( Exception e ) {
			e.printStackTrace();
        }
        return conn;
	}

    public ArrayList<Room> search() throws SQLException {
		Connection conn = dataBase();
		Statement stmt = conn.createStatement();
		ArrayList<Room> temp = new ArrayList<Room>();
		ResultSet srs = stmt.executeQuery("SELECT * FROM Room");
		while (srs.next()) {
			Room room = new Room();
			room.setRoomID(srs.getInt("roomID"));
			room.setHotelID(srs.getInt("hotelID"));
			room.setPrice(srs.getInt("price"));
			room.setAvailable(srs.getString("available"));
			temp.add(room);
		}
		return temp;
    }

//  sé að þetta er nú þegar í reservation controller...
//	public void bookRoom(int roomID) throws SQLException {
//		Connection conn = dataBase();
//		Statement stmt = conn.createStatement();
//		String s = "UPDATE Room SET available = " + "n " + "WHERE roomID = " + roomID + ";";
//		PreparedStatement pstmt = conn.prepareStatement(s);
//		pstmt.executeUpdate();
//	}
	

    public static void main( String[] args ) throws Exception {
		Scanner s = new Scanner(System.in);
		RoomController test = new RoomController();
		
		System.out.println(Arrays.toString(test.search().toArray()));

	}
}