
// Höfundur: Þurí
//
// Notkun: java -cp .;sqlite-jdbc-....jar Guest
// Fyrir:  SQLite skrain HotelDB.db inniheldur Hotel gagnagrunninn.
// Eftir:  

import java.sql.*;

public class Guest
{
	public static void main( String[] args )
		throws Exception
	{
		Connection conn = null;
		Statement stmt = null;
		try
		{
			Class.forName("org.sqlite.JDBC");
			conn = DriverManager.getConnection("jdbc:sqlite:HotelDB.db");
			stmt = conn.createStatement();
			stmt.executeUpdate("INSERT INTO Guest VALUES('Gunnar Atlason','2207896679')");
		}
		catch( Exception e )
		{
			e.printStackTrace();
		}
		finally
		{
			//if( stmt!=null ) stmt.close();
			if( conn!=null ) conn.close();
		}
	}
}
