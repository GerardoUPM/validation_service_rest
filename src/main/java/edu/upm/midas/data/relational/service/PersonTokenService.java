package edu.upm.midas.data.relational.service;

import edu.upm.midas.data.relational.entities.disnetdb.PersonToken;
import edu.upm.midas.data.relational.entities.disnetdb.PersonTokenPK;

import java.util.Date;
import java.util.List;

/**
 * Created by gerardo on 23/9/17.
 */
public interface PersonTokenService {

    PersonToken findById(PersonTokenPK personTokenPK);

    Object[] findByIdNative(String personId, String token);

    List<PersonToken> findAllQuery();

    List<Object[]> findAllNative();

    void save(PersonToken personToken);

    int insertNative(String personId, String token, boolean enabled, Date date);

    boolean deleteById(PersonTokenPK personTokenPK);

    void delete(PersonToken personToken);

    PersonToken update(PersonToken personToken);

    int updateEnabledNative(String personId, String token, boolean enabled, Date date, Date datetime);

    int updateByIdQuery(PersonToken personToken);

}
