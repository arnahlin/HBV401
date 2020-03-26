import org.junit.*;
import static org.junit.Assert.*;

import java.util.List;

public class Test {

    @Before
    public void setUp() throws Exception {
        // Code executed before each test
        Hotel locationTest = new Hotel("test", "Norðurland"); 
        Room roomTest = new Room(99, 123, False, medium); // don't know if the enum is right
        Hotel nameTest = new Hotel("Fosshótel", "test");
    }

    @Test
    public void testHotelSearch() {
        // Get the output from the search method.
        // returns a list of hotel objects.
        List hotelList = HotelController.searchByLocation("Norðurland"); 

        // assertEquals returns true if the objects are equal
        for(int i = 0; i < hotelList.length; i++) {
            assertEquals(locationTest.location, hotelList[i].location);
        }
    }

    @Test
    public void testRoomSearch() {
        // Tests if searching for a room by price works
        List roomList = RoomController.searchByPrice("less", 20000);

        for(int i = 0; i < hotelList.length; i++) {
            if(roomList[i].price < 20000) {
                return True; // not sure if we are supposed to return true?? 
            } else {return False;}
        }
    }

    @Test
    public void testHotelNameSearch() {    
        // Tests if searching for a hotel by name works   
        assertEquals(nameTest.hotelName, HotelController.searchByName("Fosshótel"));
    }


    // Maybe add one more test here? Ideas?

    @After
    public void tearDown() throws Exception {
        // Code executed after each test
        locationTest = null;
        roomTest = null;
        nameTest = null;
    }

}