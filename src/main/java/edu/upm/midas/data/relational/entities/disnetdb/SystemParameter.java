package edu.upm.midas.data.relational.entities.disnetdb;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Objects;

/**
 * Created by gerardo on 12/09/2017.
 *
 * @author Gerardo Lagunes G. ${EMAIL}
 * @version ${<VERSION>}
 * @project web_acces_control
 * @className SystemParameter
 * @see
 */
@Entity
@Table(name = "system_parameter", schema = "disnetdb", catalog = "")
public class SystemParameter {
    private Integer parameterId;

    @Id
    @Column(name = "parameter_id", nullable = false)
    public Integer getParameterId() {
        return parameterId;
    }

    public void setParameterId(Integer parameterId) {
        this.parameterId = parameterId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SystemParameter that = (SystemParameter) o;
        return Objects.equals(parameterId, that.parameterId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(parameterId);
    }
}
