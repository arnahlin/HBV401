// javac RoomController.java
// java -cp .:sqlite-jdbc-3.18.0.jar RoomController
// SQLite skrain HotelDB.db inniheldur Hotel gagnagrunninn.

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class RoomController {

	private Room room = new Room();

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

    public ArrayList<Room> search(String type) {
		ArrayList<Room> temp = new ArrayList<Room>();
		
		//todo

		return temp;
    }

    /**
	 * Marks the room as booked
	 * 
	 * @throws SQLException
	 */
	public void bookRoom(int roomID) throws SQLException {
		Connection conn = dataBase();
		Statement stmt = conn.createStatement();
		String s = "UPDATE Room SET available = " + "n " + "WHERE roomID = " + roomID + ";";
		ResultSet rs = stmt.executeQuery(s);
	}
	

    public static void main( String[] args ) throws Exception {

	}

}