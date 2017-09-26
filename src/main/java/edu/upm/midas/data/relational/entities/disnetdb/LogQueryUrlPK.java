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
 * @className LogQueryUrlPK
 * @see
 */
public class LogQueryUrlPK implements Serializable {
    private String queryId;
    private Integer urlId;

    @Column(name = "query_id", nullable = false, length = 55)
    @Id
    public String getQueryId() {
        return queryId;
    }

    public void setQueryId(String queryId) {
        this.queryId = queryId;
    }

    @Column(name = "url_id", nullable = false)
    @Id
    public Integer getUrlId() {
        return urlId;
    }

    public void setUrlId(Integer urlId) {
        this.urlId = urlId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LogQueryUrlPK that = (LogQueryUrlPK) o;
        return Objects.equals(queryId, that.queryId) &&
                Objects.equals(urlId, that.urlId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(queryId, urlId);
    }
}
