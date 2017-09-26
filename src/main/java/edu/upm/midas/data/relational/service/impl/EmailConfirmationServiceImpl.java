package edu.upm.midas.data.relational.service.impl;

import edu.upm.midas.data.relational.entities.disnetdb.EmailConfirmation;
import edu.upm.midas.data.relational.entities.disnetdb.EmailConfirmationPK;
import edu.upm.midas.data.relational.repository.EmailConfirmationRepository;
import edu.upm.midas.data.relational.service.EmailConfirmationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

/**
 * Created by gerardo on 24/9/17.
 */
@Service("emailConfirmationService")
public class EmailConfirmationServiceImpl implements EmailConfirmationService {

    @Autowired
    private EmailConfirmationRepository daoEmailConfirmation;

    @Transactional(propagation= Propagation.REQUIRED,readOnly=true)
    public EmailConfirmation findById(EmailConfirmationPK emailConfirmationPK) {
        EmailConfirmation emailConfirmation = daoEmailConfirmation.findById(emailConfirmationPK);
        return emailConfirmation;
    }

    @Transactional(propagation= Propagation.REQUIRED,readOnly=true)
    public Object[] findByIdNative(String personId, String token) {
        return daoEmailConfirmation.findByIdNative(personId, token);
    }

    @Transactional(propagation= Propagation.REQUIRED,readOnly=true)
    public List<EmailConfirmation> findAllQuery() {
        return null;
    }

    @Transactional(propagation= Propagation.REQUIRED,readOnly=true)
    public List<Object[]> findAllNative() {
        return null;
    }

    @Transactional(propagation= Propagation.REQUIRED)
    public void save(EmailConfirmation emailConfirmation) {
        daoEmailConfirmation.persist(emailConfirmation);
    }

    @Transactional(propagation=Propagation.REQUIRED)
    public int insertNative(String personId, String token, boolean sent, Date sentDate, Date sentDatetime, boolean enabled) {
        return daoEmailConfirmation.insertNative(personId, token, sent, sentDate, sentDatetime, enabled);
    }

    @Transactional(propagation=Propagation.REQUIRED)
    public boolean deleteById(EmailConfirmationPK emailConfirmationPK) {
        return false;
    }

    @Transactional(propagation=Propagation.REQUIRED)
    public void delete(EmailConfirmation emailConfirmation) {

    }

    @Transactional(propagation=Propagation.REQUIRED)
    public EmailConfirmation update(EmailConfirmation emailConfirmation) {
        return daoEmailConfirmation.update(emailConfirmation);
    }

    @Transactional(propagation=Propagation.REQUIRED)
    public int updateEnabledNative(String personId, String token, boolean enabled, Date date, Date datetime) {
        return daoEmailConfirmation.updateEnabledNative(personId, token, enabled, date, datetime);
    }

    @Transactional(propagation=Propagation.REQUIRED)
    public int updateByIdQuery(EmailConfirmation emailConfirmation) {
        return 0;
    }
}
