package com.jcolaiacovo.armored.cars.domain.service;

import com.jcolaiacovo.armored.cars.domain.dao.UserDao;
import com.jcolaiacovo.armored.cars.domain.login.SecurityToken;
import com.jcolaiacovo.armored.cars.domain.model.User;
import com.jcolaiacovo.armored.cars.domain.model.UserLevel;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

import static org.joda.time.DateTime.now;

@Service
@Transactional
public class SecurityService {

    private static final Logger LOGGER = LoggerFactory.getLogger(SecurityService.class);

    private UserDao userDao;

    @Autowired
    public SecurityService(UserDao userDao) {
        this.userDao = userDao;
    }

    public SecurityToken login(String userName, String password) {
        User user = this.validateUser(userName, password);

        int tokenHash = new HashCodeBuilder().append(user).append(password).append(now().getMillis()).toHashCode();
        return new SecurityToken(tokenHash, userName);
    }

    public void logout(String tokenHash) {

    }

    private User validateUser(String userName, String password) {
        Optional<User> optionalUser = this.userDao.getUserByUsername(userName);

        if (!optionalUser.isPresent()) {
            throw new RuntimeException("Invalid username or password");
        }
        User user = optionalUser.get();

        if (!user.getPassword().equals(password)) {
            throw new RuntimeException("Invalid username or password");
        }

        return optionalUser.get();
    }

}
