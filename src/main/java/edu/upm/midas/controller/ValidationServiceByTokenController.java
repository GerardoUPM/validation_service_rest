package edu.upm.midas.controller;

import edu.upm.midas.model.ValidationResponse;
import edu.upm.midas.service.ValidationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mobile.device.Device;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by gerardo on 25/09/2017.
 *
 * @author Gerardo Lagunes G. ${EMAIL}
 * @version ${<VERSION>}
 * @project disnet_web_app
 * @className TokenValidationController
 * @see
 */
@RestController
@RequestMapping("${my.service.rest.request.mapping.general.url}" )
public class ValidationServiceByTokenController {

    @Autowired
    private ValidationService validationService;

    @RequestMapping(value = "${my.service.rest.request.mapping.validation.token.path}", method = RequestMethod.POST)
    public ValidationResponse validationServiceByToken(@RequestBody String tokenService, Device device) throws Exception {
        return validationService.authorizedTokenService( tokenService );
    }

    /*@RequestMapping(value = "${my.service.rest.request.mapping.validation.token.path}", method = RequestMethod.POST)
    public ValidationResponse validationServiceByToken(@RequestBody String tokenService, Device device) throws Exception {
        return validationService.authorizedTokenService( tokenService );
    }*/



}
