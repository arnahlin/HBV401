import java.util.List;
import java.util.ArrayList;


public class HotelController {
    private List<Hotel> hotels;

    public void addHotel(int ID, String name, String location) {
        String sql = "INSERT INTO Hotel VALUES(" + ID + name + location + ");";

        // more code comes here
    }

    public void removeHotel(int ID) {
        String sql = "DELETE FROM Hotel WHERE hotelID = " + ID + ";";

        // more code comes here
    }
    
    public ArrayList<Hotel> searchByLocation(String location) {
        ArrayList<Hotel> temp = new ArrayList<Hotel>();

        sql = "SELECT * FROM Hotels WHERE location = " + location +";"; 

        // more code comes here
        
        return temp; 
    }

    public ArrayList<Hotel> searchByName(String name) {
        ArrayList<Hotel> temp = new ArrayList<Hotel>();

        sql = "SELECT * FROM Hotels WHERE name = " + name +";"; 

        // more code comes here

        return temp; 
    }



}