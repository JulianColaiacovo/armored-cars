package com.jcolaiacovo.armored.cars.domain.dao;

import com.jcolaiacovo.armored.cars.domain.model.Armored;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

/**
 * Created by Julian on 21/02/2017.
 */
@Repository
public class ArmoredDao extends AbstractDao<Armored> {

    @Autowired
    public ArmoredDao(@Qualifier(value = "sessionFactory") SessionFactory sessionFactory) {
        super(Armored.class, sessionFactory);
    }

    @Transactional
    public List<Armored> getAll() {
        return (List<Armored>) this.getSessionFactory().getCurrentSession().createSQLQuery("select * from ARMORED;")
                .addEntity(Armored.class)
                .list();
    }

    @Transactional
    public Optional<Armored> findByCode(int code) {
        Armored armored = (Armored) this.getSessionFactory().getCurrentSession()
                .createQuery("select distinct armored from Armored as armored where armored.code = :code")
                .setInteger("code", code)
                .uniqueResult();
        return Optional.ofNullable(armored);
    }

    public List<Armored> search(String code, String brand, String clientName) {
        String likeCode = Optional.ofNullable(code).map(s -> '%' + s + '%').orElse("%");
        String likeBrand = Optional.ofNullable(brand).map(s -> '%' + s + '%').orElse("%");
        String likeClientName = Optional.ofNullable(clientName).map(s -> '%' + s + '%').orElse("%");
        return (List<Armored>) this.getSessionFactory().getCurrentSession()
                .createQuery("select distinct armored from Armored as armored " +
                        " where armored.code like :code " +
                        "       and armored.car.brand like :brand" +
                        "       and armored.billingAndReference.billToClient.name like :clientName")
                .setString("code", likeCode)
                .setString("brand", likeBrand)
                .setString("clientName", likeClientName)
                .list();
    }

}
