package app.service.impl;

import app.info.BookingRequestInfo;
import app.model.BookingRequest;
import app.service.BookingRequestService;
import org.apache.log4j.Logger;
import org.springframework.beans.BeanUtils;
import org.springframework.transaction.annotation.Transactional;

import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.stream.Collectors;

public class BookingRequestServiceImpl extends BaseServiceImpl implements BookingRequestService {
    private static final Logger logger = Logger.getLogger(BookingRequestServiceImpl.class);

    @Override
    public BookingRequestInfo findBookingRequest(int id) {
        try {
            return new BookingRequestInfo(getBookingRequestDAO().findById(id, false));
        } catch (Exception e) {
            logger.error(e);
            return null;
        }
    }

    @Override
    public boolean createBookingRequest(BookingRequestInfo bookingRequestInfo) throws InvocationTargetException, IllegalAccessException {
        try {
            logger.info("Creating new booking request");
            getBookingRequestDAO().saveOrUpdate(bookingRequestInfo.toBookingRequest());
            return true;
        } catch (Exception e) {
            logger.error(e.getMessage());
            throw e;
        }
    }

    @Override
    public boolean deleteBookingRequest(int id) {
        try {
            BookingRequest bookingRequest = getBookingRequestDAO().findById(id, true);
            bookingRequest.setStatus(BookingRequest.Status.CANCELED);
            getBookingRequestDAO().saveOrUpdate(bookingRequest);
            logger.info(String.format("Delete Field Type having id: %d", id));
            return true;
        } catch (Exception e) {
            logger.error(e);
            throw e;
        }
    }

    @Override
    public List<BookingRequestInfo> loadBookingRequests() {
        try {
            logger.info("Get Booking Requests list");
            return getBookingRequestDAO().loadBookingRequests().stream().map(BookingRequestInfo::new).collect(Collectors.toList());
        } catch (Exception e) {
            logger.error(e);
            return null;
        }
    }

    @Override
    public List<BookingRequestInfo> loadBookingRequestsByStatus(BookingRequest.Status status) {
        try {
            logger.info("Get Booking Requests list");
            return getBookingRequestDAO().loadBookingRequestsByStatus(status).stream().map(BookingRequestInfo::new).collect(Collectors.toList());
        } catch (Exception e) {
            logger.error(e);
            return null;
        }
    }

    @Override
    public List<BookingRequestInfo> loadBookingRequestsOfUserByStatus(BookingRequest.Status status, int user_id) {
        try {
            logger.info("Get Booking Requests list");
            return getBookingRequestDAO().loadBookingRequestsOfUserByStatus(status, user_id).stream().map(BookingRequestInfo::new).collect(Collectors.toList());
        } catch (Exception e) {
            logger.error(e);
            return null;
        }
    }

    @Override
    public List<BookingRequestInfo> findByPeriod(BookingRequestInfo bookingRequestInfo) {
        try {
            logger.info("Get Booking Request In Period");
            return getBookingRequestDAO().findByPeriod(bookingRequestInfo).stream().map(BookingRequestInfo::new).collect(Collectors.toList());
        } catch (Exception e) {
            logger.error(e);
            return null;
        }
    }

    @Override
    @Transactional
    public boolean updateBookingRequestStatus(int id, BookingRequest.Status status) {
        try {
            logger.info("Approve");
            BookingRequest bookingRequest = getBookingRequestDAO().findById(id, true);
            logger.info("BOOKING REQUEST TO BE APPROVED:" + bookingRequest.toString());
            bookingRequest.setStatus(status);
            logger.info("Set approved");
            getBookingRequestDAO().saveOrUpdate(bookingRequest);
            return true;
        } catch (Exception e) {
            logger.error(e.getMessage());
            return false;
        }
    }
}
