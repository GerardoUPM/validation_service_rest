package edu.upm.midas.data.relational.entities.disnetdb;
import javax.persistence.*;
import java.util.Objects;

/**
 * Created by gerardo on 12/09/2017.
 *
 * @author Gerardo Lagunes G. ${EMAIL}
 * @version ${<VERSION>}
 * @project web_acces_control
 * @className PersonLogin
 * @see
 */
@Entity
@Table(name = "person_login", schema = "disnetdb", catalog = "")
@IdClass(PersonLoginPK.class)
public class PersonLogin {
    private String personId;
    private String loginId;
    private Byte enabled;
    private Integer attempts;
    private Person personByPersonId;
    private Login loginByLoginId;

    @Id
    @Column(name = "person_id", nullable = false, length = 150)
    public String getPersonId() {
        return personId;
    }

    public void setPersonId(String personId) {
        this.personId = personId;
    }

    @Id
    @Column(name = "login_id", nullable = false, length = 45)
    public String getLoginId() {
        return loginId;
    }

    public void setLoginId(String loginId) {
        this.loginId = loginId;
    }

    @Basic
    @Column(name = "enabled", nullable = false)
    public Byte getEnabled() {
        return enabled;
    }

    public void setEnabled(Byte enabled) {
        this.enabled = enabled;
    }

    @Basic
    @Column(name = "attempts", nullable = true)
    public Integer getAttempts() {
        return attempts;
    }

    public void setAttempts(Integer attempts) {
        this.attempts = attempts;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PersonLogin that = (PersonLogin) o;
        return Objects.equals(personId, that.personId) &&
                Objects.equals(loginId, that.loginId) &&
                Objects.equals(enabled, that.enabled) &&
                Objects.equals(attempts, that.attempts);
    }

    @Override
    public int hashCode() {
        return Objects.hash(personId, loginId, enabled, attempts);
    }

    @ManyToOne
    @JoinColumn(name = "person_id", referencedColumnName = "person_id", nullable = false, insertable = false, updatable = false)
    public Person getPersonByPersonId() {
        return personByPersonId;
    }

    public void setPersonByPersonId(Person personByPersonId) {
        this.personByPersonId = personByPersonId;
    }

    @ManyToOne
    @JoinColumn(name = "login_id", referencedColumnName = "login_id", nullable = false, insertable = false, updatable = false)
    public Login getLoginByLoginId() {
        return loginByLoginId;
    }

    public void setLoginByLoginId(Login loginByLoginId) {
        this.loginByLoginId = loginByLoginId;
    }
}
