// javac RoomController.java
// java -cp .:sqlite-jdbc-3.18.0.jar RoomController
// SQLite skrain HotelDB.db inniheldur Hotel gagnagrunninn.

import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

enum Type {
    small, medium, large
}
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

    public ArrayList<Room> searchByPrice(int priceBot, int priceTop) throws SQLException {
		Connection conn = dataBase();
		Statement stmt = conn.createStatement();
		ArrayList<Room> temp = new ArrayList<Room>();
		String sql = "SELECT r.roomID, r.hotelID, r.price, r.available, r.roomType , h.name from hotel h, room r where r.hotelID = h.hotelID AND available = \"y\" AND price BETWEEN " + priceBot + " AND " + priceTop + ";";		
		ResultSet srs = stmt.executeQuery(sql);
		while (srs.next()) {
			Room room = new Room();
			room.setRoomID(srs.getInt("roomID"));
			room.setHotelID(srs.getInt("hotelID"));
			room.setHotelName(srs.getString("name"));
			room.setPrice(srs.getInt("price"));
			room.setAvailable(srs.getString("available"));
			room.setType(srs.getString("roomType"));
			temp.add(room);
		}
		return temp;
	}
	
	public ArrayList<Room> searchByType(String type) throws SQLException {
		Connection conn = dataBase();
		Statement stmt = conn.createStatement();
		ArrayList<Room> temp = new ArrayList<Room>();
		String sql = "SELECT r.roomID, r.hotelID, r.price, r.available, r.roomType, h.name from hotel h, room r where r.hotelID = h.hotelID AND available = \"y\" AND roomType = \"" + type + "\";";		
		ResultSet srs = stmt.executeQuery(sql);
		while (srs.next()) {
			Room room = new Room();
			room.setRoomID(srs.getInt("roomID"));
			room.setHotelID(srs.getInt("hotelID"));
			room.setHotelName(srs.getString("name"));
			room.setPrice(srs.getInt("price"));
			room.setAvailable(srs.getString("available"));
			room.setType(srs.getString("roomType"));
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
		Scanner input = new Scanner(System.in);
		RoomController test = new RoomController();
		
		// System.out.println(Arrays.toString(test.searchByPrice(15000, 25001).toArray()));


		boolean validInput = false;

        System.out.println("Would you like to search by price (p) or size (s):");
        while (!validInput) {
            String searchType = input.nextLine();

            if ( searchType.equals("p") ) { // leit eftir verði
                validInput = true;
                boolean validPrice = false;
                System.out.println("Please enter the price range. Lowest and higest price.");
                while(!validPrice) {
					int priceBot = input.nextInt();
					int priceTop = input.nextInt();
					boolean price = true;
                    if(price) {
                        ArrayList<Room> rooms = test.searchByPrice(priceBot, priceTop);
						
						// Prenta headers
						System.out.printf("%-6s","ID"); System.out.printf("%-8s","Price");
						// System.out.printf("%-12s","Available"); 
						System.out.printf("%-9s","Type"); System.out.printf("%-35s","Hotel");
						System.out.println(); System.out.println("-".repeat(60));

						// Prenta niðurstöður úr leitinni
						for(int i = 0; i<rooms.size(); i++) {
						    System.out.printf("%-6s",rooms.get(i).getRoomID());
						    //System.out.printf("%-6s",rooms.get(i).getHotelID());
						    System.out.printf("%-8s",rooms.get(i).getPrice());
							// System.out.printf("%-12s",rooms.get(i).getAvailable());
							System.out.printf("%-9s",rooms.get(i).getType());
							System.out.printf("%-35s",rooms.get(i).getHotelName());
							System.out.println();
						}			
                        return;
                    } else {
						System.out.println("Something went wrong.");
                        System.out.println("Please enter the price range:");
                    }
                }

            } 
            else if ( searchType.equals("s")) { // leit eftir stærð
                validInput = true;
                boolean validSize = false;
                System.out.println("Please select the size: small, medium or large.");
                while(!validSize) {
                    String size = input.nextLine();
                    if(size.equals("small")||size.equals("medium")||size.equals("large")) {
						validSize = true;
						ArrayList<Room> rooms = test.searchByType(size);
													
						// Prenta headers 
						
						System.out.printf("%-6s","ID"); System.out.printf("%-8s","Price");
						// System.out.printf("%-12s","Available"); 
						System.out.printf("%-9s","Type");System.out.printf("%-35s","Hotel");
						System.out.println(); System.out.println("-".repeat(60));

						// Prenta niðurstöður úr leitinni
						for(int i = 0; i<rooms.size(); i++) {
						    System.out.printf("%-6s",rooms.get(i).getRoomID());
						    //System.out.printf("%-6s",rooms.get(i).getHotelID());
						    System.out.printf("%-8s",rooms.get(i).getPrice());
							// System.out.printf("%-12s",rooms.get(i).getAvailable());
							System.out.printf("%-9s",rooms.get(i).getType());
							System.out.printf("%-35s",rooms.get(i).getHotelName());
							System.out.println();
						}
							return;
					} else { System.out.println("Enter the size: (small), (medium), (large)"); }
                }
            } else {
                System.out.println("Please enter (p) for price or (s) for size.");
            }
        }
        input.close();
	}
}