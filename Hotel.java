import java.util.List;
import java.util.ArrayList;

public class Hotel {
    private int hotelID;
    private String hotelName;
    private String location;
    private List<Integer> rooms; // maybe a list of roomId's?
   

    // Variable rooms is missing from this method, not sure how to do this
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

    public void setHotelID(int hotelID) {
        this.hotelID = hotelID;
    } 

    public void setHotelName(String hotelName) {
        this.hotelName = hotelName;
    }

    public void setLocation(String location) {
        this.location = location;
    }


}