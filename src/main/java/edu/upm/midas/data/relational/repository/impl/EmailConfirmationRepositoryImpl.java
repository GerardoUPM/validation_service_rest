package edu.upm.midas.data.relational.repository.impl;

import edu.upm.midas.data.relational.entities.disnetdb.EmailConfirmation;
import edu.upm.midas.data.relational.entities.disnetdb.EmailConfirmationPK;
import edu.upm.midas.data.relational.repository.AbstractDao;
import edu.upm.midas.data.relational.repository.EmailConfirmationRepository;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

/**
 * Created by gerardo on 23/9/17.
 */
@Repository("EmailConfirmationRepositoryDao")
public class EmailConfirmationRepositoryImpl extends AbstractDao<EmailConfirmationPK, EmailConfirmation>
                                            implements EmailConfirmationRepository{
    @Override
    public EmailConfirmation findById(EmailConfirmationPK emailConfirmationPK) {
        EmailConfirmation emailConfirmation = getByKey(emailConfirmationPK);
        return emailConfirmation;
    }

    @SuppressWarnings("unchecked")
    public Object[] findByIdNative(String personId, String token) {
        Object[] oEmailConfirmation = null;
        List<Object[]> emailConfirmationList = (List<Object[]>) getEntityManager()
                .createNamedQuery("EmailConfirmation.findByIdNative")
                .setParameter("personId", personId)
                .setParameter("token", token)
                .setMaxResults(1)
                .getResultList();
        if (CollectionUtils.isNotEmpty(emailConfirmationList))
            oEmailConfirmation = emailConfirmationList.get(0);

        return oEmailConfirmation;
    }

    @SuppressWarnings("unchecked")
    public List<EmailConfirmation> findAllQuery() {
        return null;
    }

    @SuppressWarnings("unchecked")
    public List<Object[]> findAllNative() {
        return null;
    }

    @Override
    public void persist(EmailConfirmation emailConfirmation) {
        super.persist(emailConfirmation);
    }

    @Override
    public int insertNative(String personId, String token, boolean sent, Date sentDate, Date sentDatetime, boolean enabled) {
        return getEntityManager()
                .createNamedQuery("EmailConfirmation.insertNative")
                .setParameter("personId", personId)
                .setParameter("token", token)
                .setParameter("sent", sent)
                .setParameter("sentDate", sentDate)
                .setParameter("sentDatetime", sentDatetime)
                .setParameter("enabled", enabled)
                .executeUpdate();
    }

    @Override
    public boolean deleteById(EmailConfirmationPK emailConfirmationPK) {
        return false;
    }

    @Override
    public void delete(EmailConfirmation emailConfirmation) {

    }

    @Override
    public EmailConfirmation update(EmailConfirmation emailConfirmation) {
        return super.update(emailConfirmation);
    }

    @Override
    public int updateEnabledNative(String personId, String token, boolean enabled, Date date, Date datetime) {
        return getEntityManager()
                .createNamedQuery("PersonToken.updateEnabledNative")
                .setParameter("personId", personId)
                .setParameter("token", token)
                .setParameter("enabled", enabled)
                .setParameter("date", date)
                .setParameter("datetime", datetime)
                .executeUpdate();
    }

    @Override
    public int updateByIdQuery(EmailConfirmation emailConfirmation) {
        return 0;
    }
}
