package com.jcolaiacovo.armored.cars.domain.dao;

import com.jcolaiacovo.armored.cars.domain.model.User;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class SecurityDao {

    private SessionFactory sessionFactory;

    @Autowired
    public SecurityDao(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public void save(User user) {
        this.sessionFactory.getCurrentSession().save(user);
    }

    public Optional<User> getUserByUser(String user) {
        Query query = this.sessionFactory.getCurrentSession().createQuery("select distinct usr from User as usr where usr.user = :userName")
                .setString("userName", user);
        return Optional.ofNullable((User) query.uniqueResult());
    }

    public List<User> findAll() {
        return this.sessionFactory.getCurrentSession().createSQLQuery("select * from USER;")
                .addEntity(User.class)
                .list();
    }

    public List<String> getAllUserStrings() {
        return findAll().stream().map(User::getUser).collect(Collectors.toList());
    }
}
