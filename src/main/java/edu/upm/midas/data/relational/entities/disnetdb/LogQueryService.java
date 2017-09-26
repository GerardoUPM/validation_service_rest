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
 * @className LogQuery_Service
 * @see
 */
@Entity
@Table(name = "log_query_service", schema = "disnetdb", catalog = "")
@XmlRootElement
@NamedQueries({
        @NamedQuery(name = "LogQueryService.findAll", query = "SELECT l FROM LogQueryService l")
        , @NamedQuery(name = "LogQueryService.findByQueryId", query = "SELECT l FROM LogQueryService l WHERE l.queryId = :queryId")
        , @NamedQuery(name = "LogQueryService.findByServiceId", query = "SELECT l FROM LogQueryService l WHERE l.serviceId = :serviceId")
})

@SqlResultSetMappings({
        @SqlResultSetMapping(
                name = "LogQueryServiceMapping",
                entities = @EntityResult(
                        entityClass = LogQueryService.class,
                        fields = {
                                @FieldResult(name = "queryId", column = "query_id"),
                                @FieldResult(name = "serviceId", column = "service_id")
                        }
                )
        )
})

@IdClass(LogQueryServicePK.class)
public class LogQueryService {
    private String queryId;
    private String serviceId;
    private LogQuery logQueryByQueryId;
    private SystemService systemServiceByServiceId;

    @Id
    @Column(name = "query_id", nullable = false, length = 55)
    public String getQueryId() {
        return queryId;
    }

    public void setQueryId(String queryId) {
        this.queryId = queryId;
    }

    @Id
    @Column(name = "service_id", nullable = false, length = 5)
    public String getServiceId() {
        return serviceId;
    }

    public void setServiceId(String serviceId) {
        this.serviceId = serviceId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LogQueryService that = (LogQueryService) o;
        return Objects.equals(queryId, that.queryId) &&
                Objects.equals(serviceId, that.serviceId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(queryId, serviceId);
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
    @JoinColumn(name = "service_id", referencedColumnName = "service_id", nullable = false, insertable = false, updatable = false)
    public SystemService getSystemServiceByServiceId() {
        return systemServiceByServiceId;
    }

    public void setSystemServiceByServiceId(SystemService systemServiceByServiceId) {
        this.systemServiceByServiceId = systemServiceByServiceId;
    }
}
