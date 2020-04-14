public class Room {
    private int roomID;
    private int price;
    private int hotelID;
    private String type;
    private String hotelName;
    private boolean booked;
    private String available; // List of available rooms

    public Room() {

    }

    public String getType(){
        return type;
    }

    public void setType(final String type) {
        this.type = type;
    }

    public String getHotelName() {
        return hotelName;
    }

    public void setHotelName(String hotelName) {
        this.hotelName = hotelName;
    }

    public int getRoomID() {
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

	public String toString()
	{
		return "[Room id=" + this.roomID +
                ", hotel id=" + this.hotelID +
                ", hotel=" + this.hotelName + 
				", price=" + this.getPrice() +
				", available=" + this.getAvailable() + "]";
	}

}