package edu.upm.midas.model;
import edu.upm.midas.constants.Constants;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.sql.Timestamp;

/**
 * Created by gerardo on 27/11/2017.
 *
 * @author Gerardo Lagunes G. ${EMAIL}
 * @version ${<VERSION>}
 * @project validation_service_rest
 * @className UpdateQueryRuntimeRequest
 * @see
 */
public class UpdateQueryRuntimeRequest {

    @Valid
    @NotNull(message = Constants.ERR_NO_PARAMETER)
    @Size(min = 30, message = Constants.ERR_EMPTY_PARAMETER)
    private String queryId;
    @Valid
    @NotNull(message = Constants.ERR_NO_PARAMETER)
    private String startDatetime;
    @Valid
    @NotNull(message = Constants.ERR_NO_PARAMETER)
    private String endDatetime;


    public String getQueryId() {
        return queryId;
    }

    public void setQueryId(String queryId) {
        this.queryId = queryId;
    }

    public String getStartDatetime() {
        return startDatetime;
    }

    public void setStartDatetime(String startDatetime) {
        this.startDatetime = startDatetime;
    }

    public String getEndDatetime() {
        return endDatetime;
    }

    public void setEndDatetime(String endDatetime) {
        this.endDatetime = endDatetime;
    }
}
