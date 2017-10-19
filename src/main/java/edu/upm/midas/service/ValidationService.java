package edu.upm.midas.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import edu.upm.midas.common.util.TimeProvider;
import edu.upm.midas.common.util.UniqueId;
import edu.upm.midas.data.relational.entities.disnetdb.*;
import edu.upm.midas.data.relational.service.*;
import edu.upm.midas.token.model.JwtTokenUtil;
import edu.upm.midas.model.ValidationRequest;
import edu.upm.midas.model.ValidationResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by gerardo on 26/09/2017.
 *
 * @author Gerardo Lagunes G. ${EMAIL}
 * @version ${<VERSION>}
 * @project disnet_web_app
 * @className ValidationService
 * @see
 */
@Service
public class ValidationService {

    private static final Logger logger = LoggerFactory.getLogger(ValidationService.class);
    @Autowired
    ObjectMapper objectMapper;

    @Autowired
    private PersonService personService;
    @Autowired
    private TokenService tokenService;
    @Autowired
    private PersonTokenService personTokenService;
    @Autowired
    private LogQuery_Service logQuery_Service;
    @Autowired
    private TokenQueryService tokenQueryService;
    @Autowired
    private SystemService_Service systemService_Service;
    @Autowired
    private LogQueryService_Service logQueryService_Service;
    @Autowired
    private UrlService urlService;
    @Autowired
    private LogQueryUrlService logQueryUrlService;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;
    @Autowired
    private UniqueId uniqueId;
    @Autowired
    private TimeProvider timeProvider;


    /**
     * @param tokenService
     * @return
     * @throws JsonProcessingException
     */
    public ValidationResponse authorizedTokenService(String tokenService) throws JsonProcessingException {
        boolean isValid = false;
        String message = "Proccess not initialized";
        ValidationResponse validationResponse = new ValidationResponse();

        System.out.println("Se va a validar una api");

        if (!tokenService.isEmpty()) {
            ValidationRequest validationRequest = jwtTokenUtil.getServiceJWTDecode(tokenService);

            //Validar los parametros internos del token "claims"
            if (!validationRequest.getToken().isEmpty()) {
                String personId = jwtTokenUtil.getEmailWithJWTDecode(validationRequest.getToken());
                SystemService systemService = systemService_Service.findById(validationRequest.getApiCode());
                //Validar que el servicio que solicita autorización sea valido
                if (systemService != null) {
                    if (systemService.isEnabled()) {
                        isValid = isPersonAndTokenValid(personId, validationRequest.getToken());
                        if (isValid) message = "Authorized";
                        else message = "Person or token unauthorized";
                        //Inicia el registro del query
                        //<editor-fold desc="INSERTA EL QUERY">
                        LogQuery logQuery = new LogQuery();
                        logQuery.setQueryId(uniqueId.generate(35));
                        logQuery.setAuthorized(isValid);
                        logQuery.setRequest(validationRequest.getRequest());
                        logQuery.setDate(timeProvider.getNow());
                        logQuery.setDatetime(timeProvider.getTimestamp());
                        logger.info("Object Persist: {}", objectMapper.writeValueAsString(logQuery));
                        logQuery_Service.save(logQuery);
                        logger.info("Object Persist: {}", objectMapper.writeValueAsString(logQuery));
                        //</editor-fold>

                        //<editor-fold desc="ENLAZA EL QUERY CON EL TOKEN">
                        TokenQuery tokenQuery = new TokenQuery();
                        tokenQuery.setToken(validationRequest.getToken());
                        tokenQuery.setQueryId(logQuery.getQueryId());
                        tokenQuery.setDate(timeProvider.getNow());
                        tokenQuery.setDatetime(timeProvider.getTimestamp());
                        logger.info("Object Persist: {}", objectMapper.writeValueAsString(tokenQuery));
                        tokenQueryService.save(tokenQuery);
                        logger.info("Object Persist: {}", objectMapper.writeValueAsString(tokenQuery));
                        //</editor-fold>

                        //<editor-fold desc="ENLAZA EL QUERY CON EL SERVICIO">
                        LogQueryService logQueryService = new LogQueryService();
                        logQueryService.setQueryId(logQuery.getQueryId());
                        logQueryService.setServiceId(systemService.getServiceId());
                        logger.info("Object Persist: {}", objectMapper.writeValueAsString(logQueryService));
                        logQueryService_Service.save(logQueryService);
                        logger.info("Object Persist: {}", objectMapper.writeValueAsString(logQueryService));
                        //</editor-fold>

                        //<editor-fold desc="ALMACENA EL PATH DEL SERVICIO">
                        Url url = urlService.findByUrl(validationRequest.getUrl());
                        if (url == null) {
                            url = new Url();
                            url.setUrl(validationRequest.getUrl());
                            logger.info("Object Persist: {}", objectMapper.writeValueAsString(url));
                            urlService.save(url);
                            logger.info("Object Persist: {}", objectMapper.writeValueAsString(url));
                        }
                        //</editor-fold>

                        //<editor-fold desc="ENLAZA EL QUERY CON EL PATH (URL)">
                        LogQueryUrl logQueryUrl = new LogQueryUrl();
                        logQueryUrl.setQueryId(logQuery.getQueryId());
                        logQueryUrl.setUrlId(url.getUrlId());
                        logger.info("Object Persist: {}", objectMapper.writeValueAsString(logQueryUrl));
                        logQueryUrlService.save(logQueryUrl);
                        logger.info("Object Persist: {}", objectMapper.writeValueAsString(logQueryUrl));
                        //</editor-fold>
                    } else {
                        System.out.println("It's not enabled the Application Code!. Please Verify!");
                        message = "It's not enabled the Application Code!. Please Verify!";
                    }
                } else {
                    System.out.println("Invalid Application Code!. Please Verify!");
                    message = "Invalid Application Code!. Please Verify!";
                }
            } else {
                System.out.println(validationRequest.getMessage());
                message = validationRequest.getMessage();
            }
        }else{
            message = "The token cannot be empty!. Please Verify!";
        }

        validationResponse.setAuthorized( isValid );
        validationResponse.setMessage( message );

        return validationResponse;
    }


    /**
     * @param personId
     * @param token
     * @return
     */
    public boolean isPersonAndTokenValid(String personId, String token){
        boolean isValid = false;
        //Buscar persona y token
        Person person = personService.findById( personId );

        Token oToken = tokenService.findById( token );

        PersonTokenPK personTokenPK = new PersonTokenPK();
        personTokenPK.setPersonId( personId );
        personTokenPK.setToken( token );
        PersonToken personToken = personTokenService.findById( personTokenPK );

        //Validar que tanto la persona (person) como el token (person_token y token) se encuentren habilitados para realizar servicios
        if (person != null || personToken != null){
            if (person.getStatus().equals(Status.OK) &&
                    person.isEnabled() && personToken.isEnabled() &&
                    oToken.isEnabled()){
                isValid = true;
            }
        }
        return isValid;
    }


}
