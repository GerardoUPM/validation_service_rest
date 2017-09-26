package edu.upm.midas.data.relational.entities.disnetdb;
import javax.persistence.*;
import java.sql.Date;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.List;
import java.util.Objects;

/**
 * Created by gerardo on 12/09/2017.
 *
 * @author Gerardo Lagunes G. ${EMAIL}
 * @version ${<VERSION>}
 * @project web_acces_control
 * @className Login
 * @see
 */
@Entity
public class Login {
    private String loginId;
    private Date date;
    private Integer seconds;
    private Time hour;
    private Timestamp datetime;
    private Timestamp endDate;
    private List<PersonLogin> personLoginsByLoginId;

    @Id
    @Column(name = "login_id", nullable = false, length = 45)
    public String getLoginId() {
        return loginId;
    }

    public void setLoginId(String loginId) {
        this.loginId = loginId;
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
    @Column(name = "seconds", nullable = false)
    public Integer getSeconds() {
        return seconds;
    }

    public void setSeconds(Integer seconds) {
        this.seconds = seconds;
    }

    @Basic
    @Column(name = "hour", nullable = false)
    public Time getHour() {
        return hour;
    }

    public void setHour(Time hour) {
        this.hour = hour;
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
    @Column(name = "end_date", nullable = true)
    public Timestamp getEndDate() {
        return endDate;
    }

    public void setEndDate(Timestamp endDate) {
        this.endDate = endDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Login login = (Login) o;
        return Objects.equals(loginId, login.loginId) &&
                Objects.equals(date, login.date) &&
                Objects.equals(seconds, login.seconds) &&
                Objects.equals(hour, login.hour) &&
                Objects.equals(datetime, login.datetime) &&
                Objects.equals(endDate, login.endDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(loginId, date, seconds, hour, datetime, endDate);
    }

    @OneToMany(mappedBy = "loginByLoginId")
    public List<PersonLogin> getPersonLoginsByLoginId() {
        return personLoginsByLoginId;
    }

    public void setPersonLoginsByLoginId(List<PersonLogin> personLoginsByLoginId) {
        this.personLoginsByLoginId = personLoginsByLoginId;
    }
}
