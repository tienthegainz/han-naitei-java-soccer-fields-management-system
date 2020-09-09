package app.service.impl;

import app.info.UserInfo;
import app.model.User;
import app.service.UserService;
import org.apache.log4j.Logger;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class UserServiceImpl extends BaseServiceImpl implements UserService {
    private static final Logger logger = Logger.getLogger(UserServiceImpl.class);

    @Autowired
    @Qualifier(value = "encoder")
    BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public boolean checkNewEmail(String email) {
        return getUserDAO().checkNewEmail(email);
    }

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
    public boolean create(UserInfo userInfo) {
        try {
                userInfo.setPassword(bCryptPasswordEncoder.encode(userInfo.getPassword()));
                userInfo.setAvatar("https://i4.sndcdn.com/avatars-Pjqz7m3i6m0ey3dt-C1i2vg-t200x200.jpg");
                User user = userInfo.toUser();
                user.setRole(User.Role.USER);
                getUserDAO().saveOrUpdate(user);
                logger.info("Created new User");
                return true;
        } catch (Exception e) {
            e.printStackTrace();
            logger.error(e);
            throw e;
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
