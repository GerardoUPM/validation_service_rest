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
 * @className LogQuery
 * @see
 */
@Entity
@Table(name = "log_query", schema = "disnetdb", catalog = "")
@XmlRootElement
@NamedQueries({
        @NamedQuery(name = "LogQuery.findAll", query = "SELECT l FROM LogQuery l")
        , @NamedQuery(name = "LogQuery.findByQueryId", query = "SELECT l FROM LogQuery l WHERE l.queryId = :queryId")
        , @NamedQuery(name = "LogQuery.findByAuthorized", query = "SELECT l FROM LogQuery l WHERE l.authorized = :authorized")
        , @NamedQuery(name = "LogQuery.findByDate", query = "SELECT l FROM LogQuery l WHERE l.date = :date")
        , @NamedQuery(name = "LogQuery.findByDatetime", query = "SELECT l FROM LogQuery l WHERE l.datetime = :datetime")
})

@SqlResultSetMappings({
        @SqlResultSetMapping(
                name = "LogQueryMapping",
                entities = @EntityResult(
                        entityClass = LogQuery.class,
                        fields = {
                                @FieldResult(name = "queryId", column = "query_id"),
                                @FieldResult(name = "authorized", column = "authorized"),
                                @FieldResult(name = "request", column = "request"),
                                @FieldResult(name = "date", column = "date"),
                                @FieldResult(name = "datetime", column = "datetime")
                        }
                )
        )
})

@JsonIdentityInfo(generator=ObjectIdGenerators.PropertyGenerator.class, property="queryId")
public class LogQuery {
    private String queryId;
    private boolean authorized;
    private String request;
    private Date date;
    private Timestamp datetime;
    private List<LogQueryService> logQueryServicesByQueryId;
    private List<LogQueryUrl> logQueryUrlsByQueryId;
    private List<TokenQuery> tokenQueriesByQueryId;

    @Id
    @Column(name = "query_id", nullable = false, length = 55)
    public String getQueryId() {
        return queryId;
    }

    public void setQueryId(String queryId) {
        this.queryId = queryId;
    }

    @Basic
    @Column(name = "authorized", nullable = false)
    public boolean isAuthorized() {
        return authorized;
    }

    public void setAuthorized(boolean authorized) {
        this.authorized = authorized;
    }

    @Basic
    @Column(name = "request", nullable = true, length = -1)
    public String getRequest() {
        return request;
    }

    public void setRequest(String request) {
        this.request = request;
    }

    @Basic
    @Column(name = "date", nullable = true)
    public Date getDate() {
        return date;
    }

    public void setDate(Date startDate) {
        this.date = startDate;
    }

    @Basic
    @Column(name = "datetime", nullable = true)
    public Timestamp getDatetime() {
        return datetime;
    }

    public void setDatetime(Timestamp endDate) {
        this.datetime = endDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LogQuery logQuery = (LogQuery) o;
        return Objects.equals(queryId, logQuery.queryId) &&
                Objects.equals(authorized, logQuery.authorized) &&
                Objects.equals(request, logQuery.request) &&
                Objects.equals(date, logQuery.date) &&
                Objects.equals(datetime, logQuery.datetime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(queryId, authorized, request, date, datetime);
    }

    @OneToMany(mappedBy = "logQueryByQueryId")
    public List<LogQueryService> getLogQueryServicesByQueryId() {
        return logQueryServicesByQueryId;
    }

    public void setLogQueryServicesByQueryId(List<LogQueryService> logQueryServicesByQueryId) {
        this.logQueryServicesByQueryId = logQueryServicesByQueryId;
    }

    @OneToMany(mappedBy = "logQueryByQueryId")
    public List<LogQueryUrl> getLogQueryUrlsByQueryId() {
        return logQueryUrlsByQueryId;
    }

    public void setLogQueryUrlsByQueryId(List<LogQueryUrl> logQueryUrlsByQueryId) {
        this.logQueryUrlsByQueryId = logQueryUrlsByQueryId;
    }

    @OneToMany(mappedBy = "logQueryByQueryId")
    public List<TokenQuery> getTokenQueriesByQueryId() {
        return tokenQueriesByQueryId;
    }

    public void setTokenQueriesByQueryId(List<TokenQuery> tokenQueriesByQueryId) {
        this.tokenQueriesByQueryId = tokenQueriesByQueryId;
    }
}
