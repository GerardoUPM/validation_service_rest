package edu.upm.midas.data.relational.entities.disnetdb;
import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.Objects;

/**
 * Created by gerardo on 15/09/2017.
 *
 * @author Gerardo Lagunes G. ${EMAIL}
 * @version ${<VERSION>}
 * @project web_acces_control
 * @className EmailConfirmationPK
 * @see
 */
public class EmailConfirmationPK implements Serializable {
    private String personId;
    private String token;

    @Column(name = "person_id", nullable = false, length = 150)
    @Id
    public String getPersonId() {
        return personId;
    }

    public void setPersonId(String personId) {
        this.personId = personId;
    }

    @Column(name = "token", nullable = false, length = 500)
    @Id
    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        EmailConfirmationPK that = (EmailConfirmationPK) o;
        return Objects.equals(personId, that.personId) &&
                Objects.equals(token, that.token);
    }

    @Override
    public int hashCode() {
        return Objects.hash(personId, token);
    }
}
