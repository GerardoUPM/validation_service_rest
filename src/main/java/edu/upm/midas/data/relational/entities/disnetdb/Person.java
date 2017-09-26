package edu.upm.midas.data.relational.entities.disnetdb;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.List;
import java.util.Objects;

/**
 * Created by gerardo on 12/09/2017.
 *
 * @author Gerardo Lagunes G. ${EMAIL}
 * @version ${<VERSION>}
 * @project web_acces_control
 * @className Person
 * @see
 */
@Entity
@Table(name = "person", schema = "disnetdb", catalog = "")
@XmlRootElement
@NamedQueries({
        @NamedQuery(name = "Person.findAll", query = "SELECT p FROM Person p")
        , @NamedQuery(name = "Person.findByPersonId", query = "SELECT p FROM Person p WHERE p.personId = :personId")
        , @NamedQuery(name = "Person.findByStatus", query = "SELECT p FROM Person p WHERE p.status = :status")
        , @NamedQuery(name = "Person.findByEnabled", query = "SELECT p FROM Person p WHERE p.enabled = :enabled")
        , @NamedQuery(name = "Person.findByFirstName", query = "SELECT p FROM Person p WHERE p.firstName = :firstName")
        , @NamedQuery(name = "Person.findByLastName", query = "SELECT p FROM Person p WHERE p.lastName = :lastName")
        , @NamedQuery(name = "Person.findByPassword", query = "SELECT p FROM Person p WHERE p.password = :password")
        , @NamedQuery(name = "Person.findByDate", query = "SELECT p FROM Person p WHERE p.date = :date")
        , @NamedQuery(name = "Person.findByDatetime", query = "SELECT p FROM Person p WHERE p.datetime = :datetime")
        , @NamedQuery(name = "Person.findByLastUpdate", query = "SELECT p FROM Person p WHERE p.lastUpdate = :lastUpdate")
})

@NamedNativeQueries({
        @NamedNativeQuery(
                name = "Person.findByIdAndStatusNative",
                query = "SELECT p.person_id, p.status, p.enabled, p.first_name, p.last_name, p.password, pp.profile_id, pp.name profile_name, pp.authority, pp.enabled enabled_profile, a.institution_name, a.occupation, a.interest, c.name country_name, p.date, p.datetime, p.last_update " +
                        "FROM person p " +
                        "INNER JOIN academic_info a ON a.academic_info_id = p.academic_info_id " +
                        "INNER JOIN profile pp ON pp.profile_id = p.profile_id " +
                        "INNER JOIN country c ON c.country_id = a.country_id " +
                        "WHERE p.person_id = :email " +
                        "AND p.status = :status "
        ),

        @NamedNativeQuery(
                name = "Person.findByIdAndStatusOKNative",
                query = "SELECT p.person_id username, p.password, p.status enabled " +
                        "FROM person p " +
                        "WHERE p.person_id = :email " +
                        "AND p.status = 'OK' "
        ),
        @NamedNativeQuery(
                name = "Person.findRolesByIdAndStatusOKNative",
                query = "SELECT p.person_id username, pp.authority role " +
                        "FROM person p " +
                        "INNER JOIN profile pp ON pp.profile_id = p.profile_id " +
                        "WHERE p.person_id = :email " +
                        "AND p.status = 'OK' "
        ),


        @NamedNativeQuery(
                name = "Person.findAllCountriesNative",
                query = "SELECT c.country_id, c.code, c.name " +
                        "FROM country c "
        ),

        @NamedNativeQuery(
                name = "Person.insertAcademicInfoNative",
                query = "INSERT INTO academic_info (institution_name, country_id, occupation, interest) " +
                        "VALUES (:institution, :countryId, :occupation, :interest) "
        ),

        @NamedNativeQuery(
                name = "Person.insertNative",
                query = "INSERT INTO person (person_id, status, first_name, last_name, pwd, profile_id, academic_info_id, date) " +
                        "VALUES (:personId, :status, :firstName, :lastName, :pwd, :profileId, :academicId, :date) "
        )
})



@SqlResultSetMappings({
        @SqlResultSetMapping(
                name = "PersonMapping",
                entities = @EntityResult(
                        entityClass = Person.class,
                        fields = {
                                @FieldResult(name = "personId", column = "interest"),
                                @FieldResult(name = "status", column = "status"),
                                @FieldResult(name = "enabled", column = "enabled"),
                                @FieldResult(name = "firstName", column = "first_name"),
                                @FieldResult(name = "lastName", column = "last_name"),
                                @FieldResult(name = "password", column = "password"),
                                @FieldResult(name = "profileId", column = "profile_id"),
                                @FieldResult(name = "academicInfoId", column = "academic_info_id"),
                                @FieldResult(name = "date", column = "date"),
                                @FieldResult(name = "datetime", column = "datetime"),
                                @FieldResult(name = "lastUpdate", column = "last_update")
                        }
                )
        )
})

@JsonIdentityInfo(generator=ObjectIdGenerators.PropertyGenerator.class, property="personId")
public class Person {
    private String personId;
    private Status status;
    private boolean enabled;
    private String firstName;
    private String lastName;
    private String password;
    private String profileId;
    private Integer academicInfoId;
    private Date date;
    private Timestamp datetime;
    private Timestamp lastUpdate;

    private Profile profileByProfileId;
    private AcademicInfo academicInfoByAcademicInfoId;
    private List<PersonBlock> personBlocksByPersonId;
    private List<EmailConfirmation> emailConfirmationsByPersonId;
    private List<PersonLogin> personLoginsByPersonId;
    private List<PersonToken> personTokensByPersonId;

    @Id
    @Column(name = "person_id", nullable = false, length = 150)
    public String getPersonId() {
        return personId;
    }

    public void setPersonId(String personId) {
        this.personId = personId;
    }

    @Basic
    @Column(name = "status", nullable = false)
    @Enumerated(EnumType.STRING)
    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    @Basic
    @Column(name = "enabled", nullable = false)
    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    @Basic
    @Column(name = "first_name", nullable = false, length = 55)
    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    @Basic
    @Column(name = "last_name", nullable = false, length = 100)
    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @Basic
    @Column(name = "password", nullable = false, length = 256)
    public String getPassword() {
        return password;
    }

    public void setPassword(String pwd) {
        this.password = pwd;
    }

    @Basic
    @Column(name = "profile_id", nullable = false, length = 5)
    public String getProfileId() {
        return profileId;
    }

    public void setProfileId(String profileId) {
        this.profileId = profileId;
    }

    @Basic
    @Column(name = "academic_info_id", nullable = false)
    public Integer getAcademicInfoId() {
        return academicInfoId;
    }

    public void setAcademicInfoId(Integer academicInfoId) {
        this.academicInfoId = academicInfoId;
    }

    @Basic
    @Column(name = "date", nullable = false)
    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @Basic
    @Column(name = "datetime", nullable = false)
    public Timestamp getDatetime() {
        return datetime;
    }

    public void setDatetime(Timestamp datetime) {
        this.datetime = datetime;
    }

    @Basic
    @Column(name = "last_update", nullable = false)
    public Timestamp getLastUpdate() {
        return lastUpdate;
    }

    public void setLastUpdate(Timestamp update) {
        this.lastUpdate = update;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Person person = (Person) o;
        return Objects.equals(personId, person.personId) &&
                Objects.equals(status, person.status) &&
                Objects.equals(enabled, person.enabled) &&
                Objects.equals(firstName, person.firstName) &&
                Objects.equals(lastName, person.lastName) &&
                Objects.equals(password, person.password) &&
                Objects.equals(profileId, person.profileId) &&
                Objects.equals(academicInfoId, person.academicInfoId) &&
                Objects.equals(date, person.date) &&
                Objects.equals(datetime, person.datetime) &&
                Objects.equals(lastUpdate, person.lastUpdate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(personId, status, enabled, firstName, lastName, password, profileId, academicInfoId, date, datetime, lastUpdate);
    }

    @ManyToOne
    @JoinColumn(name = "profile_id", referencedColumnName = "profile_id", nullable = false, insertable = false, updatable = false)
    public Profile getProfileByProfileId() {
        return profileByProfileId;
    }

    public void setProfileByProfileId(Profile profileByProfileId) {
        this.profileByProfileId = profileByProfileId;
    }

    @ManyToOne
    @JoinColumn(name = "academic_info_id", referencedColumnName = "academic_info_id", nullable = false, insertable = false, updatable = false)
    public AcademicInfo getAcademicInfoByAcademicInfoId() {
        return academicInfoByAcademicInfoId;
    }

    public void setAcademicInfoByAcademicInfoId(AcademicInfo academicInfoByAcademicInfoId) {
        this.academicInfoByAcademicInfoId = academicInfoByAcademicInfoId;
    }

    @OneToMany(mappedBy = "personByPersonId")
    public List<PersonBlock> getPersonBlocksByPersonId() {
        return personBlocksByPersonId;
    }

    public void setPersonBlocksByPersonId(List<PersonBlock> personBlocksByPersonId) {
        this.personBlocksByPersonId = personBlocksByPersonId;
    }

    @OneToMany(mappedBy = "personByPersonId")
    public List<EmailConfirmation> getEmailConfirmationsByPersonId() {
        return emailConfirmationsByPersonId;
    }

    public void setEmailConfirmationsByPersonId(List<EmailConfirmation> personConfirmationsByPersonId) {
        this.emailConfirmationsByPersonId = personConfirmationsByPersonId;
    }

    @OneToMany(mappedBy = "personByPersonId")
    public List<PersonLogin> getPersonLoginsByPersonId() {
        return personLoginsByPersonId;
    }

    public void setPersonLoginsByPersonId(List<PersonLogin> personLoginsByPersonId) {
        this.personLoginsByPersonId = personLoginsByPersonId;
    }

    @OneToMany(mappedBy = "personByPersonId")
    public List<PersonToken> getPersonTokensByPersonId() {
        return personTokensByPersonId;
    }

    public void setPersonTokensByPersonId(List<PersonToken> personTokensByPersonId) {
        this.personTokensByPersonId = personTokensByPersonId;
    }

    @Override
    public String toString() {
        return "Person{" +
                "personId='" + personId + '\'' +
                ", status=" + status +
                ", enabled=" + enabled +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", password='" + password + '\'' +
                ", profileId='" + profileId + '\'' +
                ", academicInfoId=" + academicInfoId +
                ", date=" + date +
                ", datetime=" + datetime +
                ", lastUpdate=" + lastUpdate +
                ", profileByProfileId=" + profileByProfileId +
                ", academicInfoByAcademicInfoId=" + academicInfoByAcademicInfoId +
                ", personBlocksByPersonId=" + personBlocksByPersonId +
                ", emailConfirmationsByPersonId=" + emailConfirmationsByPersonId +
                ", personLoginsByPersonId=" + personLoginsByPersonId +
                ", personTokensByPersonId=" + personTokensByPersonId +
                '}';
    }
}
