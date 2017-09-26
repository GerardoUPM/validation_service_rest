package edu.upm.midas.data.relational.service.impl;

import edu.upm.midas.data.relational.entities.disnetdb.Country;
import edu.upm.midas.data.relational.entities.disnetdb.Person;
import edu.upm.midas.data.relational.repository.PersonRepository;
import edu.upm.midas.data.relational.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

/**
 * Created by gerardo on 18/09/2017.
 *
 * @author Gerardo Lagunes G. ${EMAIL}
 * @version ${<VERSION>}
 * @project web_acces_control
 * @className PersonServiceImpl
 * @see
 */
@Service("personService")
public class PersonServiceImpl implements PersonService {

    @Autowired
    private PersonRepository daoPerson;

    @Transactional(propagation= Propagation.REQUIRED,readOnly=true)
    public Person findById(String personId) {
        Person person = daoPerson.findById(personId);
        return person;
    }

    @Transactional(propagation= Propagation.REQUIRED,readOnly=true)
    public Person findByStatusNative(String status) {
        return null;
    }

    @Transactional(propagation= Propagation.REQUIRED,readOnly=true)
    public Person findByNameNative(String nameAndLastName) {
        return null;
    }

    @Transactional(propagation= Propagation.REQUIRED,readOnly=true)
    public Person findByCreateDate(Date create_date) {
        return null;
    }

    @Transactional(propagation= Propagation.REQUIRED,readOnly=true)
    public Person findByProfileIdNative(int resourceId) {
        return null;
    }

    @Transactional(propagation= Propagation.REQUIRED,readOnly=true)
    public Object[] findByIdNative(String personId) {
        return new Object[0];
    }

    @Transactional(propagation= Propagation.REQUIRED,readOnly=true)
    public Object[] findByIdAndStatusNative(String personId, String status) {
        return daoPerson.findByIdAndStatusNative(personId, status);
    }

    @Transactional(propagation= Propagation.REQUIRED,readOnly=true)
    public List<Person> findAllQuery() {
        return null;
    }

    @Transactional(propagation= Propagation.REQUIRED,readOnly=true)
    public List<Object[]> findAllNative() {
        return null;
    }

    @Override
    public List<Country> findAllCountriesNative() {
        return daoPerson.findAllCountriesNative();
    }

    @Transactional(propagation= Propagation.REQUIRED)
    public void save(Person person) {
        daoPerson.persist(person);
    }

    @Transactional(propagation=Propagation.REQUIRED)
    public int insertNative(String personId, String status, String firstName, String lastName, String pwd, String profileId, int academicId, Date createDate) {
        return 0;
    }

    @Transactional(propagation=Propagation.REQUIRED)
    public int insertAcademicInfoNative(String institution, int country, String occupation, String interest) {
        return 0;
    }

    @Transactional(propagation=Propagation.REQUIRED)
    public int insertBlockNative(String blockId, Date createDate, int seconds, int hour) {
        return 0;
    }

    @Transactional(propagation=Propagation.REQUIRED)
    public int insertPersonBlockNative(String personId, String blockId, boolean enabled, int attempts) {
        return 0;
    }

    @Transactional(propagation=Propagation.REQUIRED)
    public int insertLoginNative(String loginId, Date createDate, int seconds, int hour) {
        return 0;
    }

    @Transactional(propagation=Propagation.REQUIRED)
    public int insertPersonLoginNative(String personId, String loginId, boolean enabled, int attempts) {
        return 0;
    }

    @Transactional(propagation=Propagation.REQUIRED)
    public boolean deleteById(String personId) {
        return false;
    }

    @Transactional(propagation=Propagation.REQUIRED)
    public void delete(Person person) {

    }

    @Transactional(propagation=Propagation.REQUIRED)
    public Person update(Person person) {
        return daoPerson.update(person);
    }

    @Transactional(propagation=Propagation.REQUIRED)
    public int updateByIdQuery(Person person) {
        return 0;
    }
}
