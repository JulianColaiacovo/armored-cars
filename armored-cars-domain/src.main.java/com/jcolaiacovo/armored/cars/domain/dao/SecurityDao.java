package com.jcolaiacovo.armored.cars.domain.dao;

import com.jcolaiacovo.armored.cars.domain.model.User;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
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

    public User getUserByUser(String user) {
        Query query = this.sessionFactory.getCurrentSession().createQuery("select DISTINCT userSearch from User as userSearch where userSearch.user = :userId").setParameter("userId", user);
        return (User) query.uniqueResult();
    }

    public List<User> findAll() {
        Query query = this.sessionFactory.getCurrentSession().createQuery("select DISTINCT user from User as user");
        return query.list();
    }

    public List<String> getAllUserStrings() {
        return findAll().stream().map(User::getUser).collect(Collectors.toList());
    }
}
