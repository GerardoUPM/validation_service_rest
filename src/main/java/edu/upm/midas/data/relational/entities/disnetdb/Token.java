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
 * @className Token
 * @see
 */
@Entity
@Table(name = "token", schema = "disnetdb", catalog = "")
@XmlRootElement
@NamedQueries({
        @NamedQuery(name = "Token.findAll", query = "SELECT t FROM Token t")
        , @NamedQuery(name = "Token.findById", query = "SELECT t FROM Token t WHERE t.token = :token")
        , @NamedQuery(name = "Token.findByToken", query = "SELECT t FROM Token t WHERE t.token = :token")
        , @NamedQuery(name = "Token.findByType", query = "SELECT t FROM Token t WHERE t.type = :type")
        , @NamedQuery(name = "Token.findByEnabled", query = "SELECT t FROM Token t WHERE t.enabled = :enabled")
        , @NamedQuery(name = "Token.findByScope", query = "SELECT t FROM Token t WHERE t.scope = :scope")
        , @NamedQuery(name = "Token.findByDate", query = "SELECT t FROM Token t WHERE t.date = :date")
        , @NamedQuery(name = "Token.findByDatetime", query = "SELECT t FROM Token t WHERE t.datetime = :datetime")
        , @NamedQuery(name = "Token.findByLastUpdate", query = "SELECT t FROM Token t WHERE t.lastUpdate = :lastUpdate")})

@NamedNativeQueries({
        @NamedNativeQuery(
                name = "Token.findByIdNative",
                query = "SELECT t.token, t.type, t.enabled, t.expiration, t.scope, t.date, t.datetime, t.last_update " +
                        "FROM token t " +
                        "WHERE t.token = :token "
        ),

        @NamedNativeQuery(
                name = "Token.insertNative",
                query = "INSERT INTO token (token, type, enabled, expiration, scope, date) " +
                        "VALUES (:token, :type, :enabled, :expiration, :scope, :date) "
        ),

        @NamedNativeQuery(
                name = "Token.updateEnabledNative",
                query = "UPDATE token t " +
                        "SET t.enabled = :enabled " +
                        "WHERE t.token = :token "
        )
})



@SqlResultSetMappings({
        @SqlResultSetMapping(
                name = "TokenMapping",
                entities = @EntityResult(
                        entityClass = Token.class,
                        fields = {
                                @FieldResult(name = "token", column = "token"),
                                @FieldResult(name = "type", column = "type"),
                                @FieldResult(name = "enabled", column = "enabled"),
                                @FieldResult(name = "expiration", column = "expiration"),
                                @FieldResult(name = "scope", column = "scope"),
                                @FieldResult(name = "date", column = "date"),
                                @FieldResult(name = "datetime", column = "datetime"),
                                @FieldResult(name = "last_update", column = "last_update")
                        }
                )
        )
})

@JsonIdentityInfo(generator=ObjectIdGenerators.PropertyGenerator.class, property="token")
public class Token {
    private String token;
    private String type;
    private boolean enabled;
    private long expiration;
    private String scope;
    private Date date;
    private Timestamp datetime;
    private Timestamp lastUpdate;
    private List<EmailConfirmation> emailConfirmationsByToken;
    private List<PersonToken> personTokensByToken;
    private List<TokenQuery> tokenQueriesByToken;

    @Id
    @Column(name = "token", nullable = false, length = 500)
    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    @Basic
    @Column(name = "type", nullable = false, length = 10)
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
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
    @Column(name = "expiration", nullable = true, length = -1)
    public long getExpiration() {
        return expiration;
    }

    public void setExpiration(long expiration) {
        this.expiration = expiration;
    }

    @Basic
    @Column(name = "scope", nullable = true, length = 45)
    public String getScope() {
        return scope;
    }

    public void setScope(String scope) {
        this.scope = scope;
    }

    @Basic
    @Column(name = "date", nullable = false)
    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @Basic
    @Column(name = "datetime", nullable = false)
    public Timestamp getDatetime() {
        return datetime;
    }

    public void setDatetime(Timestamp datetime) {
        this.datetime = datetime;
    }

    @Basic
    @Column(name = "last_update", nullable = false)
    public Timestamp getLastUpdate() {
        return lastUpdate;
    }

    public void setLastUpdate(Timestamp update) {
        this.lastUpdate = update;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Token token1 = (Token) o;
        return Objects.equals(token, token1.token) &&
                Objects.equals(type, token1.type) &&
                Objects.equals(enabled, token1.enabled) &&
                Objects.equals(expiration, token1.expiration) &&
                Objects.equals(scope, token1.scope) &&
                Objects.equals(date, token1.date) &&
                Objects.equals(datetime, token1.datetime) &&
                Objects.equals(lastUpdate, token1.lastUpdate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(token, type, enabled, expiration, scope, date, datetime, lastUpdate);
    }

    @OneToMany(mappedBy = "tokenByToken")
    public List<EmailConfirmation> getEmailConfirmationsByToken() {
        return emailConfirmationsByToken;
    }

    public void setEmailConfirmationsByToken(List<EmailConfirmation> emailConfirmationsByToken) {
        this.emailConfirmationsByToken = emailConfirmationsByToken;
    }

    @OneToMany(mappedBy = "tokenByToken")
    public List<PersonToken> getPersonTokensByToken() {
        return personTokensByToken;
    }

    public void setPersonTokensByToken(List<PersonToken> personTokensByToken) {
        this.personTokensByToken = personTokensByToken;
    }

    @OneToMany(mappedBy = "tokenByToken")
    public List<TokenQuery> getTokenQueriesByToken() {
        return tokenQueriesByToken;
    }

    public void setTokenQueriesByToken(List<TokenQuery> tokenQueriesByToken) {
        this.tokenQueriesByToken = tokenQueriesByToken;
    }
}
