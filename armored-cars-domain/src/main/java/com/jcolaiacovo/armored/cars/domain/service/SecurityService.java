package com.jcolaiacovo.armored.cars.domain.service;

import com.jcolaiacovo.armored.cars.domain.dao.UserDao;
import com.jcolaiacovo.armored.cars.domain.login.SecurityToken;
import com.jcolaiacovo.armored.cars.domain.model.User;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
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

        Optional<User> optionalUser = userDao.getUserByUsername(userName);

        if (!optionalUser.isPresent()) {
            throw new RuntimeException("Invalid username or password");
        }

        String str = getMD5(password);

        User user = optionalUser.get();
        if (!str.equals(user.getPassword())) {
            throw new RuntimeException("Invalid username or password");
        }

        int tokenHash = new HashCodeBuilder().append(user).append(password).append(now().getMillis()).toHashCode();
        return new SecurityToken(tokenHash, userName);
    }

    private String getMD5(String password) {
        try {
            MessageDigest messageDigest = MessageDigest.getInstance("MD5");
            byte[] array = messageDigest.digest(password.getBytes());
            StringBuilder sb = new StringBuilder();
            for (byte anArray : array) {
                sb.append(Integer.toHexString((anArray & 0xFF) | 0x100).substring(1, 3));
            }
            return sb.toString();
        } catch (NoSuchAlgorithmException e) {
            LOGGER.error("Invalid algorithm", e);
            throw new RuntimeException(e);
        }
    }

    public void logout(String tokenHash) {

    }
}
