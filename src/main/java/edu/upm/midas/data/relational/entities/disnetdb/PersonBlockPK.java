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
 * @className PersonBlockPK
 * @see
 */
public class PersonBlockPK implements Serializable {
    private String personId;
    private String blockId;

    @Column(name = "person_id", nullable = false, length = 150)
    @Id
    public String getPersonId() {
        return personId;
    }

    public void setPersonId(String personId) {
        this.personId = personId;
    }

    @Column(name = "block_id", nullable = false, length = 45)
    @Id
    public String getBlockId() {
        return blockId;
    }

    public void setBlockId(String blockId) {
        this.blockId = blockId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PersonBlockPK that = (PersonBlockPK) o;
        return Objects.equals(personId, that.personId) &&
                Objects.equals(blockId, that.blockId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(personId, blockId);
    }
}
