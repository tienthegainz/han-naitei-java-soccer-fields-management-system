package app.service.impl;

import app.info.UserInfo;
import app.model.User;
import app.service.UserService;
import org.apache.log4j.Logger;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.security.core.context.SecurityContextHolder;

public class UserServiceImpl extends BaseServiceImpl implements UserService {
    private static final Logger logger = Logger.getLogger(UserServiceImpl.class);

    @Override
    public User getCurrentUser() {
        try {
            Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

            return principal instanceof UserAuth ? ((UserAuth) principal).getUser() : null;
        } catch (Exception e) {
            logger.error(e);
            return null;
        }
    }

    @Override
    public User findByEmail(String email) {
        try {
            return getUserDAO().findByEmail(email);
        } catch (Exception e) {
            logger.error(e);
            return null;
        }
    }

    @Override
    public Page<UserInfo> paginate(UserInfo userInfo) {
        try {
            Page<User> users = getUserDAO().paginateUser(userInfo.getPageable());
            return users.map(user -> {
                UserInfo model = new UserInfo();
                BeanUtils.copyProperties(user, model);
                return model;
            });
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return null;
        }
    }
}
