package edu.upm.midas.data.relational.repository;

import edu.upm.midas.data.relational.entities.disnetdb.EmailConfirmation;
import edu.upm.midas.data.relational.entities.disnetdb.EmailConfirmationPK;

import java.util.Date;
import java.util.List;

/**
 * Created by gerardo on 23/9/17.
 */
public interface EmailConfirmationRepository {

    EmailConfirmation findById(EmailConfirmationPK emailConfirmationPK);

    Object[] findByIdNative(String personId, String token);

    List<EmailConfirmation> findAllQuery();

    List<Object[]> findAllNative();

    void persist(EmailConfirmation emailConfirmation);

    int insertNative(String personId, String token, boolean sent, Date sentDate, Date sentDatetime, boolean enabled);

    boolean deleteById(EmailConfirmationPK emailConfirmationPK);

    void delete(EmailConfirmation emailConfirmation);

    EmailConfirmation update(EmailConfirmation emailConfirmation);

    int updateEnabledNative(String personId, String token, boolean enabled, Date date, Date datetime);

    int updateByIdQuery(EmailConfirmation emailConfirmation);
    
}
