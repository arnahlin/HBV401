import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws Exception {
        System.out.println("Velkomin/n í hótel-leitarvélina. Vinsamlegast veldu hvort tú vilt leita eftir hótelum eda herbergjum.");
        System.out.println("Sládu inn 'ht' fyrir hótel eda 'he' fyrir herbergi.");
        Scanner input = new Scanner(System.in);
        boolean validInput = false;

        while (!validInput) {
            String searchType = input.nextLine();

            if (searchType.equals("ht")) {
                validInput = true;
                HotelController.main(null);
            } else if (searchType.equals("he")) {
                validInput = true;
                // RoomController.main(null);
            } else {
                System.out.println("Vinsamlegast sláid inn ht eda he");
            }
        }
        //System.out.println("nedst main í Main");
        input.close();

    }
}