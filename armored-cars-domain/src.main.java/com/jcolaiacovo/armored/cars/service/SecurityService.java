package com.jcolaiacovo.armored.cars.service;

import com.jcolaiacovo.armored.cars.domain.dao.SecurityDao;
import com.jcolaiacovo.armored.cars.domain.login.SecurityToken;
import com.jcolaiacovo.armored.cars.domain.model.User;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import static org.joda.time.DateTime.now;

/**
 * Created by ffeller on 11/2/16.
 */
@Service
@Transactional
public class SecurityService {

    @Autowired
    private SecurityDao securityDao;


    public SecurityToken login(String user, String password) {

        if (!securityDao.getAllUserStrings().contains(user)) {
            throw new RuntimeException("invalid user");
        }
        User currentUser = securityDao.getUserByUser(user);
        String str = getMD5(password);

        if (!str.equals(currentUser.getPass())) {
            throw new RuntimeException("invalid pass");
        }

        String tokenHash = new HashCodeBuilder().append(user).append(password).append(now().getMillis()).toHashCode() + "";
        SecurityToken token = new SecurityToken(tokenHash, user);
        return token;
    }

    private String getMD5(String md5) {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] array = md.digest(md5.getBytes());
            StringBuffer sb = new StringBuffer();
            for (int i = 0; i < array.length; ++i) {
                sb.append(Integer.toHexString((array[i] & 0xFF) | 0x100).substring(1, 3));
            }
            return sb.toString();
        } catch (NoSuchAlgorithmException e) {
        }
        return null;
    }

    public void logout(String tokenHash) {

    }
}
