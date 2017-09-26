package edu.upm.midas.data.relational.repository;
import edu.upm.midas.data.relational.entities.disnetdb.Country;
import edu.upm.midas.data.relational.entities.disnetdb.Person;

import java.util.Date;
import java.util.List;

/**
 * Created by gerardo on 18/09/2017.
 *
 * @author Gerardo Lagunes G. ${EMAIL}
 * @version ${<VERSION>}
 * @project web_acces_control
 * @className PersonRepository
 * @see
 */
public interface PersonRepository {

    Person findById(String personId);

    Person findByStatusNative(String status);

    Person findByNameNative(String nameAndLastName);

    Person findByCreateDate(Date create_date);

    Person findByProfileIdNative(int resourceId);
    
    Object[] findByIdNative(String personId);//por email

    Object[] findByIdAndStatusNative(String personId, String status);//por email

    List<Person> findAllQuery();

    List<Country> findAllCountriesNative();

    List<Object[]> findAllNative();

    void persist(Person person);

    int insertNative(String personId, String status, String firstName, String lastName, String pwd, String profileId, int academicId, Date createDate);

    int insertAcademicInfoNative(String institution, int country, String occupation, String interest);

    int insertBlockNative(String blockId, Date createDate, int seconds, int hour);

    int insertPersonBlockNative(String personId, String blockId, boolean enabled, int attempts);

    int insertLoginNative(String loginId, Date createDate, int seconds, int hour);

    int insertPersonLoginNative(String personId, String loginId, boolean enabled, int attempts);

    boolean deleteById(String personId);

    void delete(Person person);

    Person update(Person person);

    int updateByIdQuery(Person person);

}
