package vedant.olahackathon.network;

/**
 * Created by exort on 8/2/15.
 */
public class BookingCreateResponse {
    private Integer error;
    private Booking booking;

    public Integer getError() {
        return error;
    }

    public void setError(Integer error) {
        this.error = error;
    }

    public Booking getBooking() {
        return booking;
    }

    public void setBooking(Booking booking) {
        this.booking = booking;
    }
}
