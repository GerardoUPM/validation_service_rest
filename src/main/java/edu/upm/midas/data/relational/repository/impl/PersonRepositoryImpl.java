package edu.upm.midas.data.relational.repository.impl;
import edu.upm.midas.data.relational.entities.disnetdb.Country;
import edu.upm.midas.data.relational.entities.disnetdb.Person;
import edu.upm.midas.data.relational.repository.AbstractDao;
import edu.upm.midas.data.relational.repository.PersonRepository;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

/**
 * Created by gerardo on 18/09/2017.
 *
 * @author Gerardo Lagunes G. ${EMAIL}
 * @version ${<VERSION>}
 * @project web_acces_control
 * @className PersonRepositoryImpl
 * @see
 */
@Repository("PersonRepositoryDao")
public class PersonRepositoryImpl extends AbstractDao<String, Person>
                                    implements PersonRepository{

    @Override
    public Person findById(String personId) {
        Person person = getByKey(personId);
        return person;
    }

    @SuppressWarnings("unchecked")
    @Override
    public Person findByStatusNative(String status) {
        return null;
    }

    @SuppressWarnings("unchecked")
    @Override
    public Person findByNameNative(String nameAndLastName) {
        return null;
    }

    @SuppressWarnings("unchecked")
    @Override
    public Person findByCreateDate(Date create_date) {
        return null;
    }

    @SuppressWarnings("unchecked")
    @Override
    public Person findByProfileIdNative(int resourceId) {
        return null;
    }

    @SuppressWarnings("unchecked")
    @Override
    public Object[] findByIdNative(String personId) {
        return new Object[0];
    }

    @SuppressWarnings("unchecked")
    @Override
    public Object[] findByIdAndStatusNative(String personId, String status) {
        Object[] user = null;
        List<Object[]> userList = (List<Object[]>) getEntityManager()
                .createNamedQuery("Person.findByIdAndStatusNative")
                .setParameter("email", personId)
                .setParameter("status", status)
                .setMaxResults(1)
                .getResultList();
        if (CollectionUtils.isNotEmpty(userList))
            user = userList.get(0);

        return user;
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<Person> findAllQuery() {
        return null;
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<Country> findAllCountriesNative() {
        return (List<Country>) getEntityManager()
                .createNamedQuery("Person.findAllCountriesNative")
                .getResultList();
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<Object[]> findAllNative() {
        return null;
    }

    @Override
    public void persist(Person person) {
        super.persist(person);
    }

    @Override
    public int insertNative(String personId, String status, String firstName, String lastName, String pwd, String profileId, int academicId, Date createDate) {
        return 0;
    }

    @Override
    public int insertAcademicInfoNative(String institution, int country, String occupation, String interest) {
        return getEntityManager()
                .createNamedQuery("Person.insertAcademicInfoNative")
                .setParameter("institution", institution)
                .setParameter("countryId", country)
                .setParameter("occupation", occupation)
                .setParameter("interest", interest)
                .executeUpdate();
    }

    @Override
    public int insertBlockNative(String blockId, Date createDate, int seconds, int hour) {
        return 0;
    }

    @Override
    public int insertPersonBlockNative(String personId, String blockId, boolean enabled, int attempts) {
        return 0;
    }

    @Override
    public int insertLoginNative(String loginId, Date createDate, int seconds, int hour) {
        return 0;
    }

    @Override
    public int insertPersonLoginNative(String personId, String loginId, boolean enabled, int attempts) {
        return 0;
    }

    @Override
    public boolean deleteById(String personId) {
        return false;
    }

    @Override
    public void delete(Person person) {

    }

    @Override
    public Person update(Person person) {
        return super.update(person);
    }

    @Override
    public int updateByIdQuery(Person person) {
        return 0;
    }
}
