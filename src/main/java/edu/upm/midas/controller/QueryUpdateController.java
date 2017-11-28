package edu.upm.midas.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import edu.upm.midas.common.util.TimeProvider;
import edu.upm.midas.data.relational.entities.disnetdb.LogQuery;
import edu.upm.midas.data.relational.service.LogQuery_Service;
import edu.upm.midas.model.UpdateQueryRuntimeRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.mobile.device.Device;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.sql.Timestamp;

@RestController
@RequestMapping("${my.service.rest.request.mapping.general.url}" )
public class QueryUpdateController {

    @Autowired
    private LogQuery_Service logQuery_Service;
    @Autowired
    private TimeProvider timeProvider;

    @Autowired
    ObjectMapper objectMapper;
    private static final Logger logger = LoggerFactory.getLogger(QueryUpdateController.class);

    @RequestMapping(value = "${my.service.rest.request.mapping.validation.update_query_runtime.path}",
            method = RequestMethod.POST)
    public HttpStatus updateQueryRunTime(@RequestBody @Valid UpdateQueryRuntimeRequest request,
                                         Device device) throws Exception {
        System.out.println(request.getQueryId() + " - " + request.getStartDatetime() + " - " + request.getEndDatetime());
        //try {
            int update = logQuery_Service.updateRuntimeNative(request.getQueryId(), timeProvider.convertStringToTimestamp(request.getStartDatetime()), timeProvider.convertStringToTimestamp(request.getEndDatetime()));
            if (update > 0) {
                return HttpStatus.OK;
            }else {
                return HttpStatus.NOT_FOUND;
            }
        //}catch (Exception e){
        //    return HttpStatus.INTERNAL_SERVER_ERROR;
        //}
    }

}
