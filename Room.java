import java.util.List;

public class Room {
    private int roomID;
    private int price;
    private boolean booked; 
    private List reserved; // List of reserved rooms
    private List available; // List of available rooms

    private enum type {
        small,
        medium, 
        large
    }

    public Room()
    {

    }

    public Room(int roomID)
    {
        this.roomID = roomID;
    }

    public int getPrice()
    {
        return price;
    }

    public void setPrice(int price)
    {
        this.price = price;
    }
    
    public boolean getBooked()
    {
        return booked;
    }

    public void setBooked(boolean booked)
    {
        this.booked = booked;
    }

    /** 
     * Creates the reservation for the room? Is this supposed to be here or in the ReservationController?
     */
    public void makeReservation(int roomID, int price, boolean booked) {
        String s = "INSERT INTO Reservation VALUES(" + roomID + price + booked + ");";
    }

}