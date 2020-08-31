package app.dao.impl;

import app.dao.GenericDAO;
import app.dao.UserDAO;
import app.model.User;


public class UserDAOImpl extends GenericDAO<Integer, User> implements UserDAO {
    public UserDAOImpl() {
        super(User.class);
    }

    @Override
    public User findByEmail(String email) {
        return (User) getSession().createQuery("FROM User where email = :email")
                .setParameter("email", email).getResultList().stream().findFirst()
                .orElse(null);
    }

}
