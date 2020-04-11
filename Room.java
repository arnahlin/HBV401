import java.util.List;

public class Room {
    private int roomID;
    private int price;
    private int hotelID;
    private boolean booked;
    private List reserved; // List of reserved rooms
    private String available; // List of available rooms

    private enum type {
        small, medium, large
    }

<<<<<<< HEAD
    // Smiður
    // public Room(int roomID, int hotelID, int price, String available) {
    //     this.roomID = roomID;
    //     this.hotelID = hotelID;
    //     this.price = price;
    //     this.available = available;
    // }
=======
    public Room() {
>>>>>>> 4b41248a12c6ea49f4ded84535b47c4e0a67a076

    public Room() {

    }

    public int getRoomID(){
        return roomID;
    }

<<<<<<< HEAD
    public void setRoomID(int roomID){
        this.roomID = roomID;
    }

    public int getHotelID() {
        return hotelID;
    }

    public void setHotelID(int hotelID) {
        this.hotelID = hotelID;
    }

    public String getAvailable() {
        return available;
    }

    public void setAvailable(final String available) {
        this.available = available;
    }

    public Room(final int roomID) {
        this.roomID = roomID;
    }

=======
    public int getRoomID(){
        return roomID;
    }

    public void setRoomID(int roomID){
        this.roomID = roomID;
    }

    public int getHotelID() {
        return hotelID;
    }

    public void setHotelID(int hotelID) {
        this.hotelID = hotelID;
    }

    public String getAvailable() {
        return available;
    }

    public void setAvailable(final String available) {
        this.available = available;
    }

    public Room(final int roomID) {
        this.roomID = roomID;
    }

>>>>>>> 4b41248a12c6ea49f4ded84535b47c4e0a67a076
    public int getPrice() {
        return price;
    }

    public void setPrice(final int price) {
        this.price = price;
    }

    public boolean getBooked() {
        return booked;
    }

    public void setBooked(final boolean booked) {
        this.booked = booked;
    }

	public String toString()
	{
		return "[Room id=" + this.roomID +
				", hotel=" + this.hotelID + 
				", price=" + this.getPrice() +
				", available=" + this.getAvailable() + "]";
	}
    /**
     * Creates the reservation for the room? Is this supposed to be here or in the
     * ReservationController?
     */
    public void makeReservation(final int roomID, final int price, final boolean booked) {
        final String s = "INSERT INTO Reservation VALUES(" + roomID + price + booked + ");";
    }

}