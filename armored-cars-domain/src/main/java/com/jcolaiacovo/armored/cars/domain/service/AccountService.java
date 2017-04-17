package com.jcolaiacovo.armored.cars.domain.service;

import com.jcolaiacovo.armored.cars.domain.dao.UserDao;
import com.jcolaiacovo.armored.cars.domain.login.SecurityToken;
import com.jcolaiacovo.armored.cars.domain.model.User;
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
public class AccountService {

    private static final Logger LOGGER = LoggerFactory.getLogger(AccountService.class);

    private UserDao userDao;

    @Autowired
    public AccountService(UserDao userDao) {
        this.userDao = userDao;
    }

    public void changePassword(String userName, String oldPassword, String newPassword, String repeatNewPassword) {
        if (!StringUtils.equals(newPassword, repeatNewPassword)) {
            throw new RuntimeException("New passwords not equals");
        }

        Optional<User> optionalUser = this.userDao.getUserByUsername(userName);

        if (!optionalUser.isPresent()) {
            throw new RuntimeException("Invalid username or password");
        }
        User user = optionalUser.get();

        if (!user.getPassword().equals(oldPassword)) {
            throw new RuntimeException("Invalid username or password");
        }

        user.setPassword(newPassword);
        this.userDao.save(user);
    }

}
