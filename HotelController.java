
// javac HotelController.java
// java -cp .;sqlite-jdbc-3.18.0.jar HotelController  - Windows
// java -cp .:sqlite-jdbc-3.18.0.jar HotelController  - Mac?
// SQLite skrain HotelDB.db inniheldur Hotel gagnagrunninn.

import java.sql.*;
import java.util.ArrayList;

import java.util.Scanner;

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

    // Kannski meira viðeigandi inni í RoomController?
    public ArrayList<Room> getRooms(int hotelID) throws Exception {
        Connection conn = getDatabConnection();
        Statement statement = conn.createStatement();      

        String sql = "SELECT * FROM Room WHERE hotelID = \"" + hotelID +"\";"; 

        ResultSet rs = statement.executeQuery(sql);

        ArrayList<Room> rooms = new ArrayList<Room>();
        while (rs.next()){
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
        HotelController test = new HotelController();
        Scanner input = new Scanner(System.in);
        boolean validInput = false;

        System.out.println("Veldu leit eftir stadsetningu (s) eda leit eftir nafni (n):");
        while (!validInput) {
            String searchType = input.nextLine();

            if (searchType.equals("s")){
                validInput = true;
                boolean validLoc = false;
                System.out.println("Veldu staðsetningu: South, North, West eda East");
                while(!validLoc) {
                    String loc = input.nextLine();
                    if(loc.equals("South")||loc.equals("North")||loc.equals("East") || loc.equals("West")) {
                        ArrayList<Hotel> hotels = test.searchByLocation(loc);
                        
                        // Prenta niðurstöður úr leitinni
                        System.out.printf("%-5s","ID"); System.out.printf("%-35s","Name");
                        System.out.printf("%-10s","Location"); System.out.printf("%-4s","No.rooms");
                        System.out.println(); System.out.println("-".repeat(58));
                        for(int i = 0; i<hotels.size(); i++) {
                            System.out.printf("%-5s",hotels.get(i).getHotelID());
                            System.out.printf("%-35s",hotels.get(i).getHotelName());
                            System.out.printf("%-10s",hotels.get(i).getLocation());
                            System.out.printf("%-4s",hotels.get(i).getRoomCount());
                            System.out.println();
                        }
                        return;
                    } else {
                        System.out.println("Sládu inn 'South', 'North', 'West' eda 'East'");
                    }
                }

            } 
            else if ( searchType.equals("n")) {
                validInput = true;
                boolean validName = false;
                System.out.println("Sládu inn nafn hótelsins:");
                while(!validName) {
                    String name = input.nextLine();
                    if(name instanceof String) {
                        ArrayList<Hotel> hotels = test.searchByName(name);
                        if(!hotels.isEmpty()){
                            validName = true;
                            // Prenta niðurstöður úr leitinni
                            System.out.printf("%-5s","ID"); System.out.printf("%-35s","Name");
                            System.out.printf("%-10s","Location"); System.out.printf("%-4s","No.rooms");
                            System.out.println(); System.out.println("-".repeat(58));
                            for(int i = 0; i<hotels.size(); i++) {
                                System.out.printf("%-5s",hotels.get(i).getHotelID());
                                System.out.printf("%-35s",hotels.get(i).getHotelName());
                                System.out.printf("%-10s",hotels.get(i).getLocation());
                                System.out.printf("%-4s",hotels.get(i).getRoomCount());
                                System.out.println();
                            }
                        } else {
                            System.out.println("Leitin skiladi engri nidurstödu, viltu leita aftur? (y/n)");
                            String ans = input.nextLine();
                            if(ans.equals("y")){
                                validName = false;
                            } else { return;}   
                        }
                    } else { System.out.println("Sládu inn nafn:"); }
                }
                return;
            } else {
                System.out.println("Vinsamlegast sládu inn s fyrir stadsetningu eda n fyrir nafn.");
            }
            
        }

        input.close();

        /**
         * Test fyrir aðferðirnar hér að ofan  
         */
        // HotelController test = new HotelController();       
        //test.addHotel(123, "tester", "t", 1);
        //test.removeHotel(40);
        // ArrayList<Room> temp = test.getRooms(10);
        

        /**
         * Prenta út niðurstöðurnar úr leitinni
         */

        // Room array
        // for(int i = 0; i<temp.size(); i++) {
        //     System.out.print(temp.get(i).getRoomID()+"   ");
        //     System.out.print(temp.get(i).getHotelID()+"   ");
        //     System.out.print(temp.get(i).getPrice()+"   ");
        //     System.out.println(temp.get(i).getAvailable());
        // }

	}
}