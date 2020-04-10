import java.util.List;
import java.util.Calendar;
import java.util.Date;

public class Reservation {
        public List rooms; // List of rooms that the reservation contains
        // private Room room; // ??

        public int ReservationID;
     //   private Hotel hotel;
        public String guest;
        public Date checkinDate;
        public Date checkoutDate;

        // smiður
        public Reservation(String guest, int daysFromNow, int nights) {
           //     this.hotel = hotel;
                this.guest = guest;
        setReservationDates(daysFromNow, nights);
        }

        // Returns reservationId
        public int getReservationID() {
                return ReservationID;
        }

        public void setID(int id) {
                this.ReservationID = id;
        }

        public Date getCheckinDate() {
                return checkinDate;
        }

        public void setCheckinDate(Date checkIn) {
                this.checkinDate = checkIn;
        }
        /*
        public Hotel getHotel() {
                return hotel;
        }

        public void setHotel(Hotel hotel) {
                this.hotel = hotel;
        }
        */
        // ath! þarf að laga format á dagsetningu.
        public final void setReservationDates(int daysFromNow, int nights) {
                Calendar rightNow = Calendar.getInstance(); // getInstance method returns a Calendar object whose calendar fields have been initialized with the current date and time:

                rightNow.set(rightNow.get(Calendar.YEAR), rightNow.get(Calendar.MONTH), rightNow.get(Calendar.DAY_OF_MONTH) + daysFromNow, 0, 0, 0);

                this.checkinDate = rightNow.getTime();
                rightNow.add(Calendar.DAY_OF_MONTH, nights);
                this.checkoutDate = rightNow.getTime();
        }

}