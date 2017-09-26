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
 * @className Country
 * @see
 */
@Entity
@XmlRootElement
@NamedQueries({
        @NamedQuery(name = "Country.findAll", query = "SELECT c FROM Country c")
        , @NamedQuery(name = "Country.findByCountryId", query = "SELECT c FROM Country c WHERE c.countryId = :countryId")
        , @NamedQuery(name = "Country.findByCode", query = "SELECT c FROM Country c WHERE c.code = :code")
        , @NamedQuery(name = "Country.findByName", query = "SELECT c FROM Country c WHERE c.name = :name")
})

@SqlResultSetMappings({
        @SqlResultSetMapping(
                name = "CountryMapping",
                entities = @EntityResult(
                        entityClass = Country.class,
                        fields = {
                                @FieldResult(name = "countryId", column = "country:id"),
                                @FieldResult(name = "code", column = "code"),
                                @FieldResult(name = "name", column = "name")
                        }
                )
        )
})

@JsonIdentityInfo(generator=ObjectIdGenerators.PropertyGenerator.class, property="countryId")
public class Country {
    private Integer countryId;
    private String code;
    private String name;
    private List<AcademicInfo> academicInfosByCountryId;

    public Country(){}

    @Id
    @Column(name = "country_id", nullable = false)
    public Integer getCountryId() {
        return countryId;
    }

    public void setCountryId(Integer countryId) {
        this.countryId = countryId;
    }

    @Basic
    @Column(name = "code", nullable = false, length = 3)
    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    @Basic
    @Column(name = "name", nullable = false, length = 120)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Country country = (Country) o;
        return Objects.equals(countryId, country.countryId) &&
                Objects.equals(code, country.code) &&
                Objects.equals(name, country.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(countryId, code, name);
    }

    @OneToMany(mappedBy = "countryByCountryId")
    public List<AcademicInfo> getAcademicInfosByCountryId() {
        return academicInfosByCountryId;
    }

    public void setAcademicInfosByCountryId(List<AcademicInfo> academicInfosByCountryId) {
        this.academicInfosByCountryId = academicInfosByCountryId;
    }
}
