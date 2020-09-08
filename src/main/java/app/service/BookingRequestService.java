package app.service;

import app.info.BookingRequestInfo;
import app.model.BookingRequest;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

public interface BookingRequestService {

    BookingRequestInfo findBookingRequest(int id);

    boolean createBookingRequest(BookingRequestInfo bookingRequestInfo) throws InvocationTargetException, IllegalAccessException;

    boolean updateBookingRequest(BookingRequestInfo bookingRequestInfo) throws InvocationTargetException, IllegalAccessException;

    boolean deleteBookingRequest(int id);

    List<BookingRequestInfo> findByPeriod(BookingRequestInfo bookingRequestInfo);

    List<BookingRequestInfo> loadBookingRequests();

    boolean approveBookingRequest(int id);
}
