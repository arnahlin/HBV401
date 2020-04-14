import java.util.List;
import java.util.Calendar;
import java.util.Date;
import java.text.SimpleDateFormat;

public class Reservation {
        public int ReservationID;
        public String guest;
        public String checkinDate;
        public String checkoutDate;

        // smiður
        public Reservation(String guest, int daysFromNow, int nights) throws Exception {
                this.guest = guest;
        setDates(daysFromNow, nights);
        }

        public int getReservationID() {
                return ReservationID;
        }

        public void setID(int id) {
                this.ReservationID = id;
        }

        public String getCheckinDate() {
                return checkinDate;
        }

        public void setCheckinDate(String checkIn) {
                this.checkinDate = checkIn;
        }

        public void setCheckOutDate(String checkOut) {
                this.checkoutDate = checkOut;
        }
        
        public String getCheckOutDate() {
                return checkoutDate;
        }
        

        public void setDates(int daysFromNow, int nights) throws Exception {
                try{
                String pattern = "yyyy-MM-dd";
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
                Calendar c = Calendar.getInstance();
                // CheckIn
                c.add(Calendar.DATE, daysFromNow);
                String checkIn = simpleDateFormat.format(c.getTime());
                setCheckinDate(checkIn);
                // checkOut
                c.add(Calendar.DATE, nights);
                String checkOut = simpleDateFormat.format(c.getTime());
                setCheckOutDate(checkOut);
                }
                catch(Exception e){
                        System.out.println(e);
                }
        }


}