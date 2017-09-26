package edu.upm.midas.data.relational.entities.disnetdb;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;
import java.util.Objects;

/**
 * Created by gerardo on 12/09/2017.
 *
 * @author Gerardo Lagunes G. ${EMAIL}
 * @version ${<VERSION>}
 * @project web_acces_control
 * @className AcademicInfo
 * @see
 */
@Entity
@Table(name = "academic_info", schema = "disnetdb", catalog = "")
@XmlRootElement
@NamedQueries({
        @NamedQuery(name = "AcademicInfo.findAll", query = "SELECT a FROM AcademicInfo a")
        , @NamedQuery(name = "AcademicInfo.findByAcademicInfoId", query = "SELECT a FROM AcademicInfo a WHERE a.academicInfoId = :academicInfoId")
        , @NamedQuery(name = "AcademicInfo.findByInstitutionName", query = "SELECT a FROM AcademicInfo a WHERE a.institutionName = :institutionName")
        , @NamedQuery(name = "AcademicInfo.findByPosition", query = "SELECT a FROM AcademicInfo a WHERE a.occupation = :position")})

@NamedNativeQueries({
        @NamedNativeQuery(
                name = "AcademicInfo.findByIdNative",
                query = "SELECT a.academic_info_id, a.institution_name, c.name, a.occupation, a.interest "
                        + "FROM academic_info a " +
                        "INNER JOIN country c ON c.country_id = a.country_id " +
                        "WHERE a.academic_info_id = :academicId "
        )
})

@SqlResultSetMappings({
        @SqlResultSetMapping(
                name = "AcademicInfoMapping",
                entities = @EntityResult(
                        entityClass = AcademicInfo.class,
                        fields = {
                                @FieldResult(name = "academicInfoId", column = "academic_info_id"),
                                @FieldResult(name = "institutionName", column = "institution_name"),
                                @FieldResult(name = "countryId", column = "country_id"),
                                @FieldResult(name = "occupation", column = "occupation"),
                                @FieldResult(name = "interest", column = "interest")
                        }
                )
        )
})

@JsonIdentityInfo(generator=ObjectIdGenerators.PropertyGenerator.class, property="academicInfoId")
public class AcademicInfo {
    private Integer academicInfoId;
    private String institutionName;
    private Integer countryId;
    private String occupation;
    private String interest;
    private Country countryByCountryId;
    private List<Person> peopleByAcademicInfoId;

    @Id
    @Column(name = "academic_info_id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Integer getAcademicInfoId() {
        return academicInfoId;
    }

    public void setAcademicInfoId(Integer academicInfoId) {
        this.academicInfoId = academicInfoId;
    }

    @Basic
    @Column(name = "institution_name", nullable = false, length = 200)
    public String getInstitutionName() {
        return institutionName;
    }

    public void setInstitutionName(String institutionName) {
        this.institutionName = institutionName;
    }

    @Basic
    @Column(name = "country_id", nullable = false)
    public Integer getCountryId() {
        return countryId;
    }

    public void setCountryId(Integer countryId) {
        this.countryId = countryId;
    }

    @Basic
    @Column(name = "occupation", nullable = false, length = 55)
    public String getOccupation() {
        return occupation;
    }

    public void setOccupation(String occupation) {
        this.occupation = occupation;
    }

    @Basic
    @Column(name = "interest", nullable = false, length = -1)
    public String getInterest() {
        return interest;
    }

    public void setInterest(String interest) {
        this.interest = interest;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AcademicInfo that = (AcademicInfo) o;
        return Objects.equals(academicInfoId, that.academicInfoId) &&
                Objects.equals(institutionName, that.institutionName) &&
                Objects.equals(countryId, that.countryId) &&
                Objects.equals(occupation, that.occupation) &&
                Objects.equals(interest, that.interest);
    }

    @Override
    public int hashCode() {
        return Objects.hash(academicInfoId, institutionName, countryId, occupation, interest);
    }

    @ManyToOne
    @JoinColumn(name = "country_id", referencedColumnName = "country_id", nullable = false, insertable = false, updatable = false)
    public Country getCountryByCountryId() {
        return countryByCountryId;
    }

    public void setCountryByCountryId(Country countryByCountryId) {
        this.countryByCountryId = countryByCountryId;
    }

    @OneToMany(mappedBy = "academicInfoByAcademicInfoId")
    public List<Person> getPeopleByAcademicInfoId() {
        return peopleByAcademicInfoId;
    }

    public void setPeopleByAcademicInfoId(List<Person> peopleByAcademicInfoId) {
        this.peopleByAcademicInfoId = peopleByAcademicInfoId;
    }
}
