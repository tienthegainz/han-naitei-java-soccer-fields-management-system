package app.dao;

import app.info.BookingRequestInfo;
import app.model.BookingRequest;

import java.util.List;

public interface BookingRequestDAO extends BaseDAO<Integer, BookingRequest> {

    List<BookingRequest> loadBookingRequests();

    List<BookingRequest> loadBookingRequestsByStatus(BookingRequest.Status status);

    List<BookingRequest> loadBookingRequestsOfUserByStatus(BookingRequest.Status status, int user_id);

    List<BookingRequest> findByPeriod(BookingRequestInfo bookingRequestInfo);
}
