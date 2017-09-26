package edu.upm.midas.data.relational.service.impl;

import edu.upm.midas.data.relational.entities.disnetdb.PersonToken;
import edu.upm.midas.data.relational.entities.disnetdb.PersonTokenPK;
import edu.upm.midas.data.relational.repository.PersonTokenRepository;
import edu.upm.midas.data.relational.service.PersonTokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

/**
 * Created by gerardo on 24/9/17.
 */
@Service("personTokenService")
public class PersonTokenServiceImpl implements PersonTokenService {

    @Autowired
    private PersonTokenRepository daoPersonToken;

    @Transactional(propagation= Propagation.REQUIRED,readOnly=true)
    public PersonToken findById(PersonTokenPK personTokenPK) {
        PersonToken personToken = daoPersonToken.findById(personTokenPK);
        return personToken;
    }

    @Transactional(propagation= Propagation.REQUIRED,readOnly=true)
    public Object[] findByIdNative(String personId, String token) {
        return daoPersonToken.findByIdNative(personId, token);
    }

    @Transactional(propagation= Propagation.REQUIRED,readOnly=true)
    public List<PersonToken> findAllQuery() {
        return null;
    }

    @Transactional(propagation= Propagation.REQUIRED,readOnly=true)
    public List<Object[]> findAllNative() {
        return null;
    }

    @Transactional(propagation= Propagation.REQUIRED)
    public void save(PersonToken personToken) {
        daoPersonToken.persist(personToken);
    }

    @Transactional(propagation=Propagation.REQUIRED)
    public int insertNative(String personId, String token, boolean enabled, Date date) {
        return daoPersonToken.insertNative(personId, token, enabled, date);
    }

    @Transactional(propagation=Propagation.REQUIRED)
    public boolean deleteById(PersonTokenPK personTokenPK) {
        return false;
    }

    @Transactional(propagation=Propagation.REQUIRED)
    public void delete(PersonToken personToken) {

    }

    @Transactional(propagation=Propagation.REQUIRED)
    public PersonToken update(PersonToken personToken) {
        return null;
    }

    @Transactional(propagation=Propagation.REQUIRED)
    public int updateEnabledNative(String personId, String token, boolean enabled, Date date, Date datetime) {
        return daoPersonToken.updateEnabledNative(personId, token, enabled, date, datetime);
    }

    @Transactional(propagation=Propagation.REQUIRED)
    public int updateByIdQuery(PersonToken personToken) {
        return 0;
    }
}
