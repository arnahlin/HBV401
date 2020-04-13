import java.util.List;

public class Room {
    private int roomID;
    private int hotelID;
    private int price;
    private boolean booked;
    private String available; 
    private String type;

    public Room() {

    }

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

    public String getType(){
        return type;
    }

    public void setType(final String type) {
        this.type = type;
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