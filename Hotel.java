import java.util.List;
import java.util.ArrayList;

public class Hotel {
    private int hotelID;
    private String hotelName;
    private String location;
    private List<Integer> rooms; // maybe a list of roomId's?
   

    // variable rooms is missing from this method, not sure how to do this
    // rooms is set as an int in the hotel table in database
    public Hotel(String hotelName, String location) { 
        this.hotelName = hotelName;
        this.location = location;
    }

    public int getHotelID() {
        return hotelID;
    }

    public String getHotelName() {
        return hotelName;
    }

    public String getLocation() {
        return location;
    }

    public void setHotelID(int id) {
        this.id = hotelID;
    } 

    public void setHotelName(String name) {
        this.name = hotelName;
    }

    public void setLocation(String loc) {
        this.loc = location;
    }

    public void addRoom(int roomID, int type, int price) { // enum type
        String sql = "INSERT INTO Room VALUES(" + roomID + hotelID + price + available + ");";
    }
  
    public void removeRoom(int roomID) {
        String sql = "DELETE FROM Room WHERE roomID = " + roomID + ";";
    }

    public void updateAvailability(int roomID, char a) {
        String sql = "UPDATE Room SET available = "+ a + "WHERE roomID = " + roomID + ";";
    }

}