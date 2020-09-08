package app.dao.impl;

import app.dao.BookingRequestDAO;
import app.dao.GenericDAO;
import app.info.BookingRequestInfo;
import app.model.BookingRequest;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

import java.util.List;

public class BookingRequestDAOImpl extends GenericDAO<Integer, BookingRequest> implements BookingRequestDAO {

    public BookingRequestDAOImpl() {
        super(BookingRequest.class);
    }

    public BookingRequestDAOImpl(SessionFactory sessionFactory) {
        setSessionFactory(sessionFactory);
    }

    @Override
    public List<BookingRequest> loadBookingRequests() {
        return getSession().createQuery("FROM BookingRequest ORDER BY updatedAt DESC").getResultList();
    }

    @Override
    public List<BookingRequest> loadBookingRequestsByStatus(BookingRequest.Status status) {
        Query query = getSession().createQuery("FROM BookingRequest WHERE status = :status ORDER BY updatedAt DESC");
        query.setParameter("status", status);
        return query.getResultList();
    }

    @Override
    public List<BookingRequest> loadBookingRequestsOfUserByStatus(BookingRequest.Status status, int user_id) {
        logger.info("USER ID:" + user_id);
        Query query = getSession().createQuery("FROM BookingRequest WHERE status = :status AND user.id = :user_id ORDER BY updatedAt DESC");
        query.setParameter("status", status);
        query.setParameter("user_id", user_id);
        return query.getResultList();
    }

    @Override
    public List<BookingRequest> findByPeriod(BookingRequestInfo bookingRequestInfo) {
        Query query = getSession().createQuery("FROM BookingRequest br WHERE br.field.id = :field_id" +
                " AND status != 'CANCELED' " +
                "AND ((br.fromDate BETWEEN :fromDate AND :toDate) OR" +
                "(br.toDate BETWEEN :fromDate AND :toDate))");
        query.setParameter("field_id", bookingRequestInfo.getField().getId());
        query.setParameter("fromDate", bookingRequestInfo.getFromDate());
        query.setParameter("toDate", bookingRequestInfo.getToDate());
        return query.getResultList();
    }
}
