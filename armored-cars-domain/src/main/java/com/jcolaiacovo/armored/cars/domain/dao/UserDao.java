package com.jcolaiacovo.armored.cars.domain.dao;

import com.jcolaiacovo.armored.cars.domain.model.User;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class UserDao extends AbstractDao<User> {

    @Autowired
    public UserDao(@Qualifier(value = "sessionFactory") SessionFactory sessionFactory) {
        super(User.class, sessionFactory);
    }

    public Optional<User> getUserByUsername(String userName) {
        Query query = this.getSessionFactory().getCurrentSession().createQuery("select distinct usr from User as usr where usr.user = :userName")
                .setString("userName", userName);
        return Optional.ofNullable((User) query.uniqueResult());
    }

}
