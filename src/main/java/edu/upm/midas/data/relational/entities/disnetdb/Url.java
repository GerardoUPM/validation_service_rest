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
 * @className Url
 * @see
 */
@Entity
@Table(name = "url", schema = "disnetdb", catalog = "")
@XmlRootElement
@NamedQueries({
        @NamedQuery(name = "Url.findAll", query = "SELECT u FROM Url u")
        , @NamedQuery(name = "Url.findByUrlId", query = "SELECT u FROM Url u WHERE u.urlId = :urlId")
        , @NamedQuery(name = "Url.findByUrl", query = "SELECT u FROM Url u WHERE u.url = :url")
})

@SqlResultSetMappings({
        @SqlResultSetMapping(
                name = "UrlMapping",
                entities = @EntityResult(
                        entityClass = Url.class,
                        fields = {
                                @FieldResult(name = "urlId", column = "url_id"),
                                @FieldResult(name = "url", column = "url")
                        }
                )
        )
})

@JsonIdentityInfo(generator=ObjectIdGenerators.PropertyGenerator.class, property="urlId")
public class Url {
    private Integer urlId;
    private String url;
    private List<LogQueryUrl> logQueryUrlsByUrlId;
    private List<ServiceUrl> serviceUrlsByUrlId;

    @Id
    @Column(name = "url_id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Integer getUrlId() {
        return urlId;
    }

    public void setUrlId(Integer urlId) {
        this.urlId = urlId;
    }

    @Basic
    @Column(name = "url", nullable = false, length = -1)
    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Url url1 = (Url) o;
        return Objects.equals(urlId, url1.urlId) &&
                Objects.equals(url, url1.url);
    }

    @Override
    public int hashCode() {
        return Objects.hash(urlId, url);
    }

    @OneToMany(mappedBy = "urlByUrlId")
    public List<LogQueryUrl> getLogQueryUrlsByUrlId() {
        return logQueryUrlsByUrlId;
    }

    public void setLogQueryUrlsByUrlId(List<LogQueryUrl> logQueryUrlsByUrlId) {
        this.logQueryUrlsByUrlId = logQueryUrlsByUrlId;
    }

    @OneToMany(mappedBy = "urlByUrlId")
    public List<ServiceUrl> getServiceUrlsByUrlId() {
        return serviceUrlsByUrlId;
    }

    public void setServiceUrlsByUrlId(List<ServiceUrl> serviceUrlsByUrlId) {
        this.serviceUrlsByUrlId = serviceUrlsByUrlId;
    }
}
