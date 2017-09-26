package edu.upm.midas.data.relational.entities.disnetdb;
import javax.persistence.*;
import java.util.List;
import java.util.Objects;

/**
 * Created by gerardo on 12/09/2017.
 *
 * @author Gerardo Lagunes G. ${EMAIL}
 * @version ${<VERSION>}
 * @project web_acces_control
 * @className Profile
 * @see
 */
@Entity
public class Profile {
    private String profileId;
    private boolean enabled;
    private String name;
    private AuthorityName authority;
    private List<Person> peopleByProfileId;
    private List<ProfileService> profileServicesByProfileId;

    @Id
    @Column(name = "profile_id", nullable = false, length = 5)
    public String getProfileId() {
        return profileId;
    }

    public void setProfileId(String profileId) {
        this.profileId = profileId;
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
    @Column(name = "name", nullable = false, length = 45)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Basic
    @Column(name = "authority", nullable = false)
    @Enumerated(EnumType.STRING)
    public AuthorityName getAuthority() {
        return authority;
    }

    public void setAuthority(AuthorityName authority) {
        this.authority = authority;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Profile profile = (Profile) o;
        return Objects.equals(profileId, profile.profileId) &&
                Objects.equals(enabled, profile.enabled) &&
                Objects.equals(name, profile.name) &&
                Objects.equals(authority, profile.authority);
    }

    @Override
    public int hashCode() {
        return Objects.hash(profileId, enabled, name, authority);
    }

    @OneToMany(mappedBy = "profileByProfileId")
    public List<Person> getPeopleByProfileId() {
        return peopleByProfileId;
    }

    public void setPeopleByProfileId(List<Person> peopleByProfileId) {
        this.peopleByProfileId = peopleByProfileId;
    }

    @OneToMany(mappedBy = "profileByProfileId")
    public List<ProfileService> getProfileServicesByProfileId() {
        return profileServicesByProfileId;
    }

    public void setProfileServicesByProfileId(List<ProfileService> profileServicesByProfileId) {
        this.profileServicesByProfileId = profileServicesByProfileId;
    }
}
