package vedant.olahackathon.network;

/**
 * Created by exort on 8/2/15.
 */
public class BookingCreateRequest {
    private Long userId;
    private Long flightPNR;
    private String callbackId;
    private Double destinationLatitude;
    private Double destinationLongitude;

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getFlightPNR() {
        return flightPNR;
    }

    public void setFlightPNR(Long flightPNR) {
        this.flightPNR = flightPNR;
    }

    public String getCallbackId() {
        return callbackId;
    }

    public void setCallbackId(String callbackId) {
        this.callbackId = callbackId;
    }

    public Double getDestinationLatitude() {
        return destinationLatitude;
    }

    public void setDestinationLatitude(Double destinationLatitude) {
        this.destinationLatitude = destinationLatitude;
    }

    public Double getDestinationLongitude() {
        return destinationLongitude;
    }

    public void setDestinationLongitude(Double destinationLongitude) {
        this.destinationLongitude = destinationLongitude;
    }
}
