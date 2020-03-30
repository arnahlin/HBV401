import org.junit.*;
import static org.junit.Assert.*;

import java.util.List;

public class Test {

    @Before
    public void setUp() throws Exception {
        // Code executed before each test
        Hotel locationTest = new Hotel("test", "Norðurland"); 
    }

    // Should return a list of hotels in North Iceland, if any hotel is 
    // not in north Iceland then it fails.
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

    // Should not return results, and if it does then it's a fail
    @Test
    public void testRoomSearch() {
        List hotelList = HotelController.searchByLocation(""); 
        assertEquals(0, hotelList.length); // if this returns false, then we got some results back and the test is a fail
    }

    // Invalid parameters
    @Test
    public void testHotelNameSearch() throws IllegalArgumentException {  
        List hotelList = HotelController.searchByLocation(123); 
    }

    @After
    public void tearDown() throws Exception {
        // Code executed after each test
        locationTest = null;
        roomTest = null;
        nameTest = null;
    }
}