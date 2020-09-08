package app.info;

public class ApproveBookingRequestInfo {
    Integer id;

    public ApproveBookingRequestInfo() {
    }

    public ApproveBookingRequestInfo(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "ApproveBookingRequestInfo{" +
                "id=" + id +
                '}';
    }
}
