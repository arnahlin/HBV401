import java.util.List;

public class Reservation {
    private List rooms; // List of rooms that the reservation contains
    // private Room room; // ??

    private int ReservationID;
    private Hotel hotel;
    private Guest guest;
    private Date checkinDate;
    private Date checkoutDate;
    
    //smiður
    public Booking(Hotel hotel, Guest guest, int daysFromNow, int nights) {
        this.hotel = hotel;
        this.guest = guest;
        this.beds = 1;
        setReservationDates(daysFromNow, nights);
    }

	// Returns reservationId
	public int getReservationID() {
		return ReservationID;
	}

	public void setID(int id) {
		this.id = ReservationID;
	}

	public Date getCheckinDate() {
        return checkinDate;
	}
	
	public void setCheckinDate(Date checkIn) {
        this.checkinDate = checkIn;
	}
	
	public Hotel getHotel() {
        return hotel;
	}
	
	public void setHotel(Hotel hotel) {
        this.hotel = hotel;
    }

    public final void setReservationDates(int daysFromNow, int nights) {  // Format YY MM DD
        Calendar rightNow = Calendar.getInstance(); // getInstance method returns a Calendar object whose calendar fields have been initialized with the current date and time:
        rightNow.set(rightNow.get(Calendar.YEAR), rightNow.get(Calendar.MONTH), rightNow.get(Calendar.DAY_OF_MONTH)
                + daysFromNow, 0, 0, 0);
        this.checkinDate = rightNow.getTime();
        rightNow.add(Calendar.DAY_OF_MONTH, nights);
        this.checkoutDate = rightNow.getTime();
    }

    
}