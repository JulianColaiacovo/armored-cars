package com.jcolaiacovo.armored.cars.domain.service;

import com.jcolaiacovo.armored.cars.domain.dao.AbstractDao;
import com.jcolaiacovo.armored.cars.domain.dao.UserDao;
import com.jcolaiacovo.armored.cars.domain.login.SecurityToken;
import com.jcolaiacovo.armored.cars.domain.model.Client;
import com.jcolaiacovo.armored.cars.domain.model.User;
import com.jcolaiacovo.armored.cars.domain.model.UserLevel;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

import static org.joda.time.DateTime.now;

@Service
@Transactional
public class UserService extends AbstractDaoService<User> {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserService.class);

    private UserDao userDao;

    @Autowired
    public UserService(UserDao userDao) {
        this.userDao = userDao;
    }

    public List<User> getAll() {
        return this.userDao.getAll();
    }

    public User getByName(String name) {
        return this.userDao.getUserByUsername(name)
                .orElseThrow(() -> new RuntimeException("Can not find user"));
    }

    public void changePassword(String userName, String oldPassword, String newPassword) {
        User user = this.validateUser(userName, oldPassword);

        user.setPassword(newPassword);
        this.userDao.save(user);
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

    @Override
    protected AbstractDao<User> getDao() {
        return this.userDao;
    }

}
