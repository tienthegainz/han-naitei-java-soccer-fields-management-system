package app.service;

import app.model.User;

public interface UserService {

    User findByEmail(String email);

}

