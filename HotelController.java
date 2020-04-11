
// javac HotelController.java
// java -cp .;sqlite-jdbc-3.18.0.jar HotelController  - Windows
// java -cp .:sqlite-jdbc-3.18.0.jar HotelController  - Mac?
// SQLite skrain HotelDB.db inniheldur Hotel gagnagrunninn.

import java.sql.*;
import java.util.ArrayList;

// import java.util.Scanner;

public class HotelController {
    private Connection getDatabConnection() {
        Connection conn = null;
        try {
            Class.forName("org.sqlite.JDBC");
			conn = DriverManager.getConnection("jdbc:sqlite:HotelDB.db");
        }
        catch(Exception e ) {
            e.printStackTrace();
        }
        return conn;
    }

    Connection conn = null;
	Statement stmt = null;  

    public void addHotel(int ID, String name, String location, int numRooms) throws Exception {
        Connection conn = getDatabConnection();
        Statement statement = conn.createStatement();

        String sql = "INSERT INTO Hotel VALUES(" + ID + ",'" + name + "','"+ location +"',"+ numRooms + ");";

        statement.executeUpdate(sql);
	}

    public void removeHotel(int ID) throws Exception {
        Connection conn = getDatabConnection();
        Statement statement = conn.createStatement();

        String sql = "DELETE FROM Hotel WHERE hotelID = " + ID + ";";

        statement.executeUpdate(sql);
    }
    
    public ArrayList<Hotel> searchByLocation(String location) throws Exception {
        Connection conn = getDatabConnection();
        Statement statement = conn.createStatement();      

        String sql = "SELECT * FROM Hotel WHERE location = \"" + location +"\";"; 
        
        ResultSet rs = statement.executeQuery(sql);

        ArrayList<Hotel> hotels = new ArrayList<Hotel>();
        int hotelID;
        String hotelName; 
        String loc;
        int rooms;

        while (rs.next()){
            hotelID = rs.getInt("hotelID");
            hotelName = rs.getString("name");
            loc = rs.getString("location");
            rooms = rs.getInt("rooms");
            Hotel hotel = new Hotel(hotelID, hotelName, loc, rooms);
            hotels.add(hotel);
        }
        
        return hotels; 
    }

    public ArrayList<Hotel> searchByName(String name) throws Exception {
        Connection conn = getDatabConnection();
        Statement statement = conn.createStatement();      

        String sql = "SELECT * FROM Hotel WHERE name = \"" + name +"\";"; 

        ResultSet rs = statement.executeQuery(sql);

        ArrayList<Hotel> hotels = new ArrayList<Hotel>();
        int hotelID;
        String hotelName; 
        String loc;
        int rooms;

        while (rs.next()){
            hotelID = rs.getInt("hotelID");
            hotelName = rs.getString("name");
            loc = rs.getString("location");
            rooms = rs.getInt("rooms");
            Hotel hotel = new Hotel(hotelID, hotelName, loc, rooms);
            hotels.add(hotel);
        }
        return hotels; 
    }

    public ArrayList<Room> getRooms(int hotelID) throws Exception {
        Connection conn = getDatabConnection();
        Statement statement = conn.createStatement();      

        String sql = "SELECT * FROM Room WHERE hotelID = \"" + hotelID +"\";"; 

        ResultSet rs = statement.executeQuery(sql);

        ArrayList<Room> rooms = new ArrayList<Room>();
        // int roomID;
        // int IDhotel;
        // int price;
        // String available;

        while (rs.next()){
            // roomID = rs.getInt("roomID");
            // IDhotel = rs.getInt("hotelID");
            // price = rs.getInt("price");
            // available = rs.getString("available");
            // Room room = new Room(roomID, IDhotel, price, available);
            Room room = new Room();
            room.setRoomID(rs.getInt("roomID"));
            room.setHotelID(rs.getInt("hotelID"));
            room.setPrice(rs.getInt("price"));
            room.setAvailable(rs.getString("available"));
            rooms.add(room);
        }        
        return rooms; 
    }

    public static void main( String[] args ) throws Exception {
        
        /**
         * Test fyrir aðferðirnar hér að ofan  
         */
        HotelController test = new HotelController();       
        //test.addHotel(123, "heeeeey", "t", 1);
        //test.removeHotel(40);
        //ArrayList<Hotel> temp = test.searchByLocation("East");
        // ArrayList<Hotel> temp = test.searchByName("Hótel Örkin");
        ArrayList<Room> temp = test.getRooms(10);
        

        /**
         * Prenta út niðurstöðurnar úr leitinni
         */
        // Hotel array
        // for(int i = 0; i<temp.size(); i++) {
        //     System.out.print(temp.get(i).getHotelID()+"   ");
        //     System.out.print(temp.get(i).getHotelName()+"   ");
        //     System.out.println(temp.get(i).getLocation());
        // }

        // Room array
        for(int i = 0; i<temp.size(); i++) {
            System.out.print(temp.get(i).getRoomID()+"   ");
            System.out.print(temp.get(i).getHotelID()+"   ");
            System.out.print(temp.get(i).getPrice()+"   ");
            System.out.println(temp.get(i).getAvailable());
        }

	}
}