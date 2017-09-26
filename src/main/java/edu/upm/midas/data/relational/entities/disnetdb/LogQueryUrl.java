package edu.upm.midas.data.relational.entities.disnetdb;
import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.Objects;

/**
 * Created by gerardo on 12/09/2017.
 *
 * @author Gerardo Lagunes G. ${EMAIL}
 * @version ${<VERSION>}
 * @project web_acces_control
 * @className LogQueryUrl
 * @see
 */
@Entity
@Table(name = "log_query_url", schema = "disnetdb", catalog = "")
@XmlRootElement
@NamedQueries({
        @NamedQuery(name = "LogQueryUrl.findAll", query = "SELECT l FROM LogQueryUrl l")
        , @NamedQuery(name = "LogQueryUrl.findByQueryId", query = "SELECT l FROM LogQueryUrl l WHERE l.queryId = :queryId")
        , @NamedQuery(name = "LogQueryUrl.findByUrlId", query = "SELECT l FROM LogQueryUrl l WHERE l.urlId = :urlId")
})

@SqlResultSetMappings({
        @SqlResultSetMapping(
                name = "LogQueryUrlMapping",
                entities = @EntityResult(
                        entityClass = LogQueryUrl.class,
                        fields = {
                                @FieldResult(name = "queryId", column = "query_id"),
                                @FieldResult(name = "urlId", column = "url_id")
                        }
                )
        )
})

@IdClass(LogQueryUrlPK.class)
public class LogQueryUrl {
    private String queryId;
    private Integer urlId;
    private LogQuery logQueryByQueryId;
    private Url urlByUrlId;

    @Id
    @Column(name = "query_id", nullable = false, length = 55)
    public String getQueryId() {
        return queryId;
    }

    public void setQueryId(String queryId) {
        this.queryId = queryId;
    }

    @Id
    @Column(name = "url_id", nullable = false)
    public Integer getUrlId() {
        return urlId;
    }

    public void setUrlId(Integer urlId) {
        this.urlId = urlId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LogQueryUrl that = (LogQueryUrl) o;
        return Objects.equals(queryId, that.queryId) &&
                Objects.equals(urlId, that.urlId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(queryId, urlId);
    }

    @ManyToOne
    @JoinColumn(name = "query_id", referencedColumnName = "query_id", nullable = false, insertable = false, updatable = false)
    public LogQuery getLogQueryByQueryId() {
        return logQueryByQueryId;
    }

    public void setLogQueryByQueryId(LogQuery logQueryByQueryId) {
        this.logQueryByQueryId = logQueryByQueryId;
    }

    @ManyToOne
    @JoinColumn(name = "url_id", referencedColumnName = "url_id", nullable = false, insertable = false, updatable = false)
    public Url getUrlByUrlId() {
        return urlByUrlId;
    }

    public void setUrlByUrlId(Url urlByUrlId) {
        this.urlByUrlId = urlByUrlId;
    }
}
