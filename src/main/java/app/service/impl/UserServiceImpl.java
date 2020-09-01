package app.service.impl;

import app.model.User;
import app.service.UserService;
import org.apache.log4j.Logger;

import java.io.Serializable;

public class UserServiceImpl extends BaseServiceImpl implements UserService {
    private static final Logger logger = Logger.getLogger(UserServiceImpl.class);

    @Override
    public User findByEmail(String email) {
        try {
            return getUserDAO().findByEmail(email);
        } catch (Exception e) {
            logger.error(e);
            return null;
        }
    }

}
