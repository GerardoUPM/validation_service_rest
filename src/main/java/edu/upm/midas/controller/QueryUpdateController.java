package edu.upm.midas.controller;

import edu.upm.midas.data.relational.entities.disnetdb.LogQuery;
import edu.upm.midas.data.relational.service.LogQueryService_Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.mobile.device.Device;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("${my.service.rest.request.mapping.general.url}" )
public class QueryUpdateController {

    @Autowired
    private LogQueryService_Service logQueryService;

    @RequestMapping(value = "${my.service.rest.request.mapping.validation.update_datetime_query.path}",
            method = RequestMethod.POST)
    public HttpStatus validationServiceByToken(@RequestBody String tokenService,
                                               Device device) throws Exception {
        LogQuery logQuery = new LogQuery();
        logQuery.setQueryId();
        logQueryService.update();

        return validationService.authorizedTokenService( tokenService );
    }

}
