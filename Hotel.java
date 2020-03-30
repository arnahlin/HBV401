import java.util.List;

public class Hotel {
    private String hotelName;
    private String location;
    private List rooms; // maybe a list of roomId's?

    public Hotel(String hotelName, String location) { // variable rooms is missing
        this.hotelName = hotelName;
        this.location = location;
    }

    public void addRoom(int roomID, int type, int price) { // enum type

    }

    public void removeRoom(int roomID) {

    }

    public List searchByLocation(String location) {
        
    }
}