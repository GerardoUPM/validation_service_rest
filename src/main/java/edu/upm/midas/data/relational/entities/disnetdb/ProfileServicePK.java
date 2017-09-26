package edu.upm.midas.data.relational.entities.disnetdb;
import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.Objects;

/**
 * Created by gerardo on 12/09/2017.
 *
 * @author Gerardo Lagunes G. ${EMAIL}
 * @version ${<VERSION>}
 * @project web_acces_control
 * @className ProfileServicePK
 * @see
 */
public class ProfileServicePK implements Serializable {
    private String profileId;
    private String serviceId;

    @Column(name = "profile_id", nullable = false, length = 5)
    @Id
    public String getProfileId() {
        return profileId;
    }

    public void setProfileId(String profileId) {
        this.profileId = profileId;
    }

    @Column(name = "service_id", nullable = false, length = 5)
    @Id
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
        ProfileServicePK that = (ProfileServicePK) o;
        return Objects.equals(profileId, that.profileId) &&
                Objects.equals(serviceId, that.serviceId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(profileId, serviceId);
    }
}
