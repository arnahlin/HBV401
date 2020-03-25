import org.junit.*;
import static org.junit.Assert.*;

import java.util.List;

public class Test {
    @BeforeClass
    public static void setUpClass() throws Exception {
        // Code executed before the first test method
    }

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
        // Code that tests if the room searching works
        // tests the search method with some 

        List roomList = RoomController.searchByPrice();
    }

    @Test
    public void testHotelNameSearch() {
        // Code that tests something else
        assertEquals(nameTest.hotelName, HotelController.)
    }

    @After
    public void tearDown() throws Exception {
        // Code executed after each test 
    }
 
    @AfterClass
    public static void tearDownClass() throws Exception {
        // Code executed after the last test method 
    }
}