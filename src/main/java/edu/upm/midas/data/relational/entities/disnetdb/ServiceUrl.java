package edu.upm.midas.data.relational.entities.disnetdb;
import javax.persistence.*;
import java.util.Objects;

/**
 * Created by gerardo on 12/09/2017.
 *
 * @author Gerardo Lagunes G. ${EMAIL}
 * @version ${<VERSION>}
 * @project web_acces_control
 * @className ServiceUrl
 * @see
 */
@Entity
@Table(name = "service_url", schema = "disnetdb", catalog = "")
@IdClass(ServiceUrlPK.class)
public class ServiceUrl {
    private String serviceId;
    private Integer urlId;
    private SystemService systemServiceByServiceId;
    private Url urlByUrlId;

    @Id
    @Column(name = "service_id", nullable = false, length = 5)
    public String getServiceId() {
        return serviceId;
    }

    public void setServiceId(String serviceId) {
        this.serviceId = serviceId;
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
        ServiceUrl that = (ServiceUrl) o;
        return Objects.equals(serviceId, that.serviceId) &&
                Objects.equals(urlId, that.urlId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(serviceId, urlId);
    }

    @ManyToOne
    @JoinColumn(name = "service_id", referencedColumnName = "service_id", nullable = false, insertable = false, updatable = false)
    public SystemService getSystemServiceByServiceId() {
        return systemServiceByServiceId;
    }

    public void setSystemServiceByServiceId(SystemService systemServiceByServiceId) {
        this.systemServiceByServiceId = systemServiceByServiceId;
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
