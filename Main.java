// javac Main.java
// java -cp .;sqlite-jdbc-3.18.0.jar Main  - Windows
// java -cp .:sqlite-jdbc-3.18.0.jar Main  - Mac?

import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws Exception {
        System.out.println("Welcome to the Hotel search engine.");
        boolean search = true;
        Scanner input = new Scanner(System.in);
        while(search) {
            System.out.println("Please select if you want to search by hotels or rooms.");
            System.out.println("Type (h) for hotels or (r) for rooms.");
            boolean validInput = false;
            boolean hotelSearch = false;
    
            while (!validInput) {
                String searchType = input.nextLine();
    
                if (searchType.equals("h")) {
                    validInput = true;
                    hotelSearch = true;
                    HotelController.main(null);
                } else if (searchType.equals("r")) {
                    validInput = true;
                    RoomController.main(null);
                } else {
                    System.out.println("Please press (h) or (r).");
                }
            }
    
            System.out.println("If you want to book a room please press (b), press (s) to search again or press (q) to quit.");
            String book = input.nextLine();
            if( book.equals("b") ) {
                search = false;
                ReservationController.main(null);            
            } 
            else if ( book.equals("s")) {
                search = true;
            }
            else if( book.equals("q") ) {
                System.out.println("Quitting");
                search = false;
                input.close();
                return;
            } 
            else { System.out.println("Please enter (b), (s) or (q)."); }
        }
        input.close();
    }
}