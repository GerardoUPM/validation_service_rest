package edu.upm.midas.data.relational.entities.disnetdb;
import javax.persistence.*;
import java.util.Objects;

/**
 * Created by gerardo on 12/09/2017.
 *
 * @author Gerardo Lagunes G. ${EMAIL}
 * @version ${<VERSION>}
 * @project web_acces_control
 * @className PersonBlock
 * @see
 */
@Entity
@Table(name = "person_block", schema = "disnetdb", catalog = "")
@IdClass(PersonBlockPK.class)
public class PersonBlock {
    private String personId;
    private String blockId;
    private Byte enabled;
    private Integer attempts;
    private Person personByPersonId;
    private Block blockByBlockId;

    @Id
    @Column(name = "person_id", nullable = false, length = 150)
    public String getPersonId() {
        return personId;
    }

    public void setPersonId(String personId) {
        this.personId = personId;
    }

    @Id
    @Column(name = "block_id", nullable = false, length = 45)
    public String getBlockId() {
        return blockId;
    }

    public void setBlockId(String blockId) {
        this.blockId = blockId;
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
        PersonBlock that = (PersonBlock) o;
        return Objects.equals(personId, that.personId) &&
                Objects.equals(blockId, that.blockId) &&
                Objects.equals(enabled, that.enabled) &&
                Objects.equals(attempts, that.attempts);
    }

    @Override
    public int hashCode() {
        return Objects.hash(personId, blockId, enabled, attempts);
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
    @JoinColumn(name = "block_id", referencedColumnName = "block_id", nullable = false, insertable = false, updatable = false)
    public Block getBlockByBlockId() {
        return blockByBlockId;
    }

    public void setBlockByBlockId(Block blockByBlockId) {
        this.blockByBlockId = blockByBlockId;
    }
}
