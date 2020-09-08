package app.service;

import app.info.BookingRequestInfo;
import app.model.BookingRequest;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

public interface BookingRequestService {

    BookingRequestInfo findBookingRequest(int id);

    boolean createBookingRequest(BookingRequestInfo bookingRequestInfo) throws InvocationTargetException, IllegalAccessException;

    boolean deleteBookingRequest(int id);

    List<BookingRequestInfo> findByPeriod(BookingRequestInfo bookingRequestInfo);

    List<BookingRequestInfo> loadBookingRequests();

    List<BookingRequestInfo> loadBookingRequestsByStatus(BookingRequest.Status status);

    List<BookingRequestInfo> loadBookingRequestsOfUserByStatus(BookingRequest.Status status, int user_id);

    boolean updateBookingRequestStatus(int id, BookingRequest.Status status);
}
