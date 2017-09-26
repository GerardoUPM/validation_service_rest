package edu.upm.midas.data.relational.entities.disnetdb;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.Objects;

/**
 * Created by gerardo on 15/09/2017.
 *
 * @author Gerardo Lagunes G. ${EMAIL}
 * @version ${<VERSION>}
 * @project web_acces_control
 * @className EmailConfirmation
 * @see
 */
@Entity
@Table(name = "email_confirmation", schema = "disnetdb", catalog = "")
@XmlRootElement
@NamedQueries({
        @NamedQuery(name = "EmailConfirmation.findAll", query = "SELECT e FROM EmailConfirmation e")
        , @NamedQuery(name = "EmailConfirmation.findById", query = "SELECT e FROM EmailConfirmation e WHERE e.personId = :personId AND e.token = :token")
        , @NamedQuery(name = "EmailConfirmation.findByPersonId", query = "SELECT e FROM EmailConfirmation e WHERE e.personId = :personId")
        , @NamedQuery(name = "EmailConfirmation.findByToken", query = "SELECT e FROM EmailConfirmation e WHERE e.token = :token")
        , @NamedQuery(name = "EmailConfirmation.findBySent", query = "SELECT e FROM EmailConfirmation e WHERE e.sent = :sent")
        , @NamedQuery(name = "EmailConfirmation.findBySentDate", query = "SELECT e FROM EmailConfirmation e WHERE e.sentDate = :sentDate")
        , @NamedQuery(name = "EmailConfirmation.findBySentDatetime", query = "SELECT e FROM EmailConfirmation e WHERE e.sentDatetime = :sentDatetime")
        , @NamedQuery(name = "EmailConfirmation.findByEnabled", query = "SELECT e FROM EmailConfirmation e WHERE e.enabled = :enabled")
        , @NamedQuery(name = "EmailConfirmation.findByDate", query = "SELECT e FROM EmailConfirmation e WHERE e.date = :date")
        , @NamedQuery(name = "EmailConfirmation.findByDatetime", query = "SELECT e FROM EmailConfirmation e WHERE e.datetime = :datetime")})


@NamedNativeQueries({
        @NamedNativeQuery(
                name = "EmailConfirmation.findByIdNative",
                query = "SELECT e.person_id, e.token, e.sent, e.sent_date, e.sent_datetime, e.enabled, e.date, e.datetime " +
                        "FROM email_confirmation e " +
                        "WHERE e.person_id = :personId AND e.token = :token "
        ),

        @NamedNativeQuery(
                name = "EmailConfirmation.insertNative",
                query = "INSERT INTO email_confirmation (person_id, token, sent, sent_date, sent_datetime, enabled) " +
                        "VALUES ( :personId, :token, :sent, :sentDate, :sentDatetime, :enabled) "
        ),

        @NamedNativeQuery(
                name = "EmailConfirmation.updateEnabledNative",
                query = "UPDATE email_confirmation e " +
                        "SET e.enabled = :enabled, " +
                        "e.date = :date, " +
                        "e.datetime = :datetime " +
                        "WHERE e.person_id = :personId AND e.token = :token "
        )
})



@SqlResultSetMappings({
        @SqlResultSetMapping(
                name = "EmailConfirmationMapping",
                entities = @EntityResult(
                        entityClass = EmailConfirmation.class,
                        fields = {
                                @FieldResult(name = "personId", column = "person_id"),
                                @FieldResult(name = "token", column = "token"),
                                @FieldResult(name = "sent", column = "sent"),
                                @FieldResult(name = "sentDate", column = "sent_date"),
                                @FieldResult(name = "sentDatetime", column = "sent_datetime"),
                                @FieldResult(name = "enabled", column = "enabled"),
                                @FieldResult(name = "date", column = "date"),
                                @FieldResult(name = "datetime", column = "datetime")
                        }
                )
        )
})

@IdClass(EmailConfirmationPK.class)
public class EmailConfirmation {
    private String personId;
    private String token;
    private boolean sent;
    private Date sentDate;
    private Timestamp sentDatetime;
    private boolean enabled;
    private Date date;
    private Timestamp datetime;
    private Person personByPersonId;
    private Token tokenByToken;

    @Id
    @Column(name = "person_id", nullable = false, length = 150)
    public String getPersonId() {
        return personId;
    }

    public void setPersonId(String personId) {
        this.personId = personId;
    }

    @Id
    @Column(name = "token", nullable = false, length = 500)
    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    @Basic
    @Column(name = "sent", nullable = false)
    public boolean isSent() {
        return sent;
    }

    public void setSent(boolean sent) {
        this.sent = sent;
    }

    @Basic
    @Column(name = "sent_date", nullable = true)
    public Date getSentDate() {
        return sentDate;
    }

    public void setSentDate(Date sentDate) {
        this.sentDate = sentDate;
    }

    @Basic
    @Column(name = "sent_datetime", nullable = true)
    public Timestamp getSentDatetime() {
        return sentDatetime;
    }

    public void setSentDatetime(Timestamp sentDatetime) {
        this.sentDatetime = sentDatetime;
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
    @Column(name = "date", nullable = true)
    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @Basic
    @Column(name = "datetime", nullable = true)
    public Timestamp getDatetime() {
        return datetime;
    }

    public void setDatetime(Timestamp datetime) {
        this.datetime = datetime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        EmailConfirmation that = (EmailConfirmation) o;
        return Objects.equals(personId, that.personId) &&
                Objects.equals(token, that.token) &&
                Objects.equals(sent, that.sent) &&
                Objects.equals(sentDate, that.sentDate) &&
                Objects.equals(sentDatetime, that.sentDatetime) &&
                Objects.equals(enabled, that.enabled) &&
                Objects.equals(date, that.date) &&
                Objects.equals(datetime, that.datetime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(personId, token, sent, sentDate, sentDatetime, enabled, date, datetime);
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
    @JoinColumn(name = "token", referencedColumnName = "token", nullable = false, insertable = false, updatable = false)
    public Token getTokenByToken() {
        return tokenByToken;
    }

    public void setTokenByToken(Token tokenByToken) {
        this.tokenByToken = tokenByToken;
    }
}
