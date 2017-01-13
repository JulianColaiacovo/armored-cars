package com.jcolaiacovo.armored.cars.domain.dao;

import com.jcolaiacovo.armored.cars.domain.model.User;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by gabrieldyck on 22/11/16.
 */
@Component
public class SecurityDao {

    private SessionFactory sessionFactory;

    @Autowired
    public SecurityDao(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    private SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public void save(User user) {
        this.getSessionFactory().getCurrentSession().save(user);
    }

    public User getUserByUser(String user){
        Query query = this.getSessionFactory().getCurrentSession().createQuery("select DISTINCT userSearch from User as userSearch where userSearch.user = :userId").setParameter("userId",user);
        return (User) query.uniqueResult();
    }

    public User getUser(Long id) {
        User user = (User) this.getSessionFactory().getCurrentSession().get(User.class, id);
        return user;
    }

    public List<User> findAll() {
        Query query = this.getSessionFactory().getCurrentSession().createQuery("select DISTINCT user from User as user");
        return query.list();
    }

    public List<String> getAllUserStrings(){
        return findAll().stream().map(User::getUser).collect(Collectors.toList());
    }
}
