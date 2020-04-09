// javac RoomController.java
// java -cp .:sqlite-jdbc-3.18.0.jar RoomController
// SQLite skrain HotelDB.db inniheldur Hotel gagnagrunninn.

import java.sql.*;
import java.util.List;

public class RoomController {
    private List<Room> rooms; // ætti að vera tengt við Room eh veginn
    

    /**
     * Searhces the database for a room that meets
     * the search criteria.
     * @return Room object
     */
    // public Room search() {
        
    // }

    /**
     * Marks the room as booked 
     */
    public void bookRoom(int roomId) {

    }

    public static void main( String[] args ) throws Exception {
		Connection conn = null;
		Statement stmt = null;  
		try
		{
			Class.forName("org.sqlite.JDBC");
			conn = DriverManager.getConnection("jdbc:sqlite:HotelDB.db");
			stmt = conn.createStatement();
		// 	stmt.executeUpdate("INSERT INTO Room VALUES('220','12','13900','y','n')");
		
        /* Ath, ég commentaði línuna hér að ofan út, því ef á að keyra aftur þarf að setja inn ný gildi því annars kemur villa út af primary key :) */

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

}