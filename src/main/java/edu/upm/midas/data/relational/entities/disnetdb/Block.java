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
 * @className Block
 * @see
 */
@Entity
public class Block {
    private String blockId;
    private Date date;
    private Integer seconds;
    private Time hour;
    private Timestamp datetime;
    private List<PersonBlock> personBlocksByBlockId;

    @Id
    @Column(name = "block_id", nullable = false, length = 45)
    public String getBlockId() {
        return blockId;
    }

    public void setBlockId(String blockId) {
        this.blockId = blockId;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Block block = (Block) o;
        return Objects.equals(blockId, block.blockId) &&
                Objects.equals(date, block.date) &&
                Objects.equals(seconds, block.seconds) &&
                Objects.equals(hour, block.hour) &&
                Objects.equals(datetime, block.datetime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(blockId, date, seconds, hour, datetime);
    }

    @OneToMany(mappedBy = "blockByBlockId")
    public List<PersonBlock> getPersonBlocksByBlockId() {
        return personBlocksByBlockId;
    }

    public void setPersonBlocksByBlockId(List<PersonBlock> personBlocksByBlockId) {
        this.personBlocksByBlockId = personBlocksByBlockId;
    }
}
