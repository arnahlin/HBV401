
// javac HotelController.java
// java -cp .:sqlite-jdbc-3.18.0.jar HotelController
// SQLite skrain HotelDB.db inniheldur Hotel gagnagrunninn.

import java.sql.*;
import java.util.List;

public class HotelController {
    private List hotels;


    public void search() {

    }

    public void addHotel() {

    }

    public void removeHotel() {

    }

    public static void main( String[] args ) throws Exception {
		Connection conn = null;
		Statement stmt = null;  
		try
		{
			Class.forName("org.sqlite.JDBC");
			conn = DriverManager.getConnection("jdbc:sqlite:HotelDB.db");
			stmt = conn.createStatement();
		 //	stmt.executeUpdate("INSERT INTO Hotel VALUES('38','Hotel Sudurnes','South','134')");
		
        /* Ath, ég commentaði línuna hér að ofan út, því Hótel Suðurnes er komið inn núna
        ef á að keyra aftur þarf að setja inn ný gildi því annars kemur villa út af primary key :) */

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