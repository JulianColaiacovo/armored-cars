package com.jcolaiacovo.armored.cars.service;

import com.jcolaiacovo.armored.cars.domain.dao.SecurityDao;
import com.jcolaiacovo.armored.cars.domain.login.SecurityToken;
import com.jcolaiacovo.armored.cars.domain.model.User;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import static org.joda.time.DateTime.now;

@Service
@Transactional
public class SecurityService {

    private static final Logger LOGGER = LoggerFactory.getLogger(SecurityService.class);

    private SecurityDao securityDao;

    @Autowired
    public SecurityService(SecurityDao securityDao) {
        this.securityDao = securityDao;
    }

    public SecurityToken login(String user, String password) {

        if (!securityDao.getAllUserStrings().contains(user)) {
            throw new RuntimeException("invalid user");
        }
        User currentUser = securityDao.getUserByUser(user);
        String str = getMD5(password);

        if (!str.equals(currentUser.getPassword())) {
            throw new RuntimeException("invalid pass");
        }

        int tokenHash = new HashCodeBuilder().append(user).append(password).append(now().getMillis()).toHashCode();
        return new SecurityToken(Integer.toString(tokenHash), user);
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
            return null;
        }
    }

    public void logout(String tokenHash) {

    }
}
