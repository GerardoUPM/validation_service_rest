package edu.upm.midas.data.relational.entities.disnetdb;
import javax.persistence.*;
import java.util.Objects;

/**
 * Created by gerardo on 12/09/2017.
 *
 * @author Gerardo Lagunes G. ${EMAIL}
 * @version ${<VERSION>}
 * @project web_acces_control
 * @className ProfileService
 * @see
 */
@Entity
@Table(name = "profile_service", schema = "disnetdb", catalog = "")
@IdClass(ProfileServicePK.class)
public class ProfileService {
    private String profileId;
    private String serviceId;
    private boolean enabled;
    private Profile profileByProfileId;
    private SystemService systemServiceByServiceId;

    @Id
    @Column(name = "profile_id", nullable = false, length = 5)
    public String getProfileId() {
        return profileId;
    }

    public void setProfileId(String profileId) {
        this.profileId = profileId;
    }

    @Id
    @Column(name = "service_id", nullable = false, length = 5)
    public String getServiceId() {
        return serviceId;
    }

    public void setServiceId(String serviceId) {
        this.serviceId = serviceId;
    }

    @Basic
    @Column(name = "enabled", nullable = false)
    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProfileService that = (ProfileService) o;
        return Objects.equals(profileId, that.profileId) &&
                Objects.equals(serviceId, that.serviceId) &&
                Objects.equals(enabled, that.enabled);
    }

    @Override
    public int hashCode() {
        return Objects.hash(profileId, serviceId, enabled);
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
    @JoinColumn(name = "service_id", referencedColumnName = "service_id", nullable = false, insertable = false, updatable = false)
    public SystemService getSystemServiceByServiceId() {
        return systemServiceByServiceId;
    }

    public void setSystemServiceByServiceId(SystemService systemServiceByServiceId) {
        this.systemServiceByServiceId = systemServiceByServiceId;
    }
}
