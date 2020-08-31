package app.dao;

import app.model.User;

import java.util.List;

public interface UserDAO extends BaseDAO<Integer, User> {

    User findByEmail(String email);

}
