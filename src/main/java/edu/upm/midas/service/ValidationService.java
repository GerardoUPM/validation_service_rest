package edu.upm.midas.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import edu.upm.midas.common.util.TimeProvider;
import edu.upm.midas.common.util.UniqueId;
import edu.upm.midas.constants.Constants;
import edu.upm.midas.data.relational.entities.disnetdb.*;
import edu.upm.midas.data.relational.service.*;
import edu.upm.midas.token.model.JwtTokenUtil;
import edu.upm.midas.model.ValidationRequest;
import edu.upm.midas.model.ValidationResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mobile.device.Device;
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
    @Autowired
    private Constants constants;


    /**
     * Método que verifica si un token está autorizado para consumir un servicio de DISNET
     *
     * Sea el token o no autorizado si se encuentra en la base de datos se insertará la
     * consulta que se este realizando
     *
     * @param tokenService
     * @return Retorna un objeto ValidationResponse
     * @throws JsonProcessingException
     */
    public ValidationResponse authorizedTokenService(String tokenService, Device device) throws Exception {
        boolean isValid = false;
        String message;
        ValidationResponse validationResponse = new ValidationResponse();

        System.out.println("A token will be authorized...");

        //Verifica que el token no esté vacío
        if ( !tokenService.isEmpty() ) {
            ValidationRequest validationRequest = jwtTokenUtil.getServiceJWTDecode( tokenService );
            //Verifica los parametros internos del token "claims"
            if ( validationRequest.isEnabled() ) {
                //Verifica que el token de acceso del usuario no esté vacío
                if ( !validationRequest.getToken().isEmpty() ) {
                    String personId = jwtTokenUtil.getEmailWithJWTDecode( validationRequest );
                    //Verifica que dentro del token de acceso del usuario se encuentre su email
                    if ( validationRequest.isEnabled() ) {
                        //Verifica que el email no se encuentre vacío
                        if (!personId.isEmpty()) {
                            SystemService systemService = systemService_Service.findById(validationRequest.getApiCode());
                            //Verifica que el servicio que solicita autorización no esté vacío (exista en la BD)
                            if (systemService != null) {
                                //Verifica que el servicio sea válido
                                if (systemService.isEnabled()) {
                                    //Verifica que la persona, token y su relación estén habilitados
                                    // de lo contrario se tiene un token no autorizado
                                    isValid = isPersonAndTokenValid(personId, validationRequest);
                                    if ( validationRequest.isEnabled() ) {
                                        //Se registra OK en el cuerpo de la respuesta
                                        if (isValid) message = Constants.OK_AUTHORIZED;
                                        else message = validationRequest.getMessage();
                                        //Sea o no autorizado el token se debe registrar la consulta en la BD
                                        //Inicia el registro del query
                                        try {
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

                                            //<editor-fold desc="GENERA UN TOKEN QUE RETORNA INFORMACIÓN EXTRA">
                                            String token = jwtTokenUtil.generateTokenToUpdateQueryRuntime(logQuery.getQueryId(), device);
                                            validationResponse.setToken(token);
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

                                            //<editor-fold desc="INSERTA LA URL DEL SERVICIO SI NO EXISTE">
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
                                        } catch (Exception e) {
                                            System.out.println(constants.ERR_AUTH_CODE_010 + ": " + constants.ERR_AUTH_THERE_WAS_A_PROBLEM_REGISTERING_A_QUERY + " - " + e.getMessage());
                                            message = constants.ERR_AUTH_CODE_010 + ": " + constants.ERR_AUTH_THERE_WAS_A_PROBLEM_REGISTERING_A_QUERY + " - " + e.getMessage();
                                        }
                                    }else {
                                        System.out.println(validationRequest.getMessage());
                                        message = validationRequest.getMessage();
                                    }
                                } else {
                                    System.out.println(constants.ERR_AUTH_CODE_006() + ": " + constants.ERR_AUTH_ENABLED_APP_CODE);
                                    message = constants.ERR_AUTH_CODE_006() + ": " + constants.ERR_AUTH_ENABLED_APP_CODE;
                                }
                            } else {
                                System.out.println(constants.ERR_AUTH_CODE_005() + ": " + constants.ERR_AUTH_INVALID_APP_CODE);
                                message = constants.ERR_AUTH_CODE_005() + ": " + constants.ERR_AUTH_INVALID_APP_CODE;
                            }
                        } else {
                            System.out.println(constants.ERR_AUTH_CODE_004() + ": " + constants.ERR_AUTH_PERSON_ACCESS_TOKEN_NOT_CORRECTLY_FORMED);
                            message = constants.ERR_AUTH_CODE_004() + ": " + constants.ERR_AUTH_PERSON_ACCESS_TOKEN_NOT_CORRECTLY_FORMED;
                        }
                    }else{
                        System.out.println(validationRequest.getMessage());
                        message = validationRequest.getMessage();
                    }
                }else{
                    System.out.println(constants.ERR_AUTH_CODE_002() + ": " + constants.ERR_AUTH_PERSON_ACCESS_TOKEN_CANNOT_EMPTY);
                    message = constants.ERR_AUTH_CODE_002() + ": " + constants.ERR_AUTH_PERSON_ACCESS_TOKEN_CANNOT_EMPTY;
                }
            } else {
                System.out.println(validationRequest.getMessage());
                message = validationRequest.getMessage();
            }
        }else{
            System.out.println(constants.ERR_AUTH_CODE_000() + ": " + constants.ERR_AUTH_TOKEN_CANNOT_EMPTY);
            message = constants.ERR_AUTH_CODE_000() + ": " + constants.ERR_AUTH_TOKEN_CANNOT_EMPTY;
        }

        validationResponse.setAuthorized( isValid );
        validationResponse.setMessage( message );

        return validationResponse;
    }


    /**
     * Método que verifica si un usuario/persona (tabla person) y su token (tabla token y person_token)
     * tengan status válido para operar.
     *
     * @param personId
     * @param validationRequest
     * @return
     * @throws Exception
     */
    public boolean isPersonAndTokenValid(String personId, ValidationRequest validationRequest) throws Exception {
        boolean isValid = false;
        try {
            //Buscar persona y token
            Person person = personService.findById(personId);
            Token oToken = tokenService.findById(validationRequest.getToken());

            PersonTokenPK personTokenPK = new PersonTokenPK();
            personTokenPK.setPersonId(personId);
            personTokenPK.setToken(validationRequest.getToken());
            //Busca la relación entre el token y la person
            PersonToken personToken = personTokenService.findById(personTokenPK);

            //Validar que tanto la persona (person) como el token (person_token y token)
            // se encuentren habilitados para realizar servicios
            //De entrar isValid seguirá siendo false
            if (person != null || personToken != null) {
                //Validar que el status de la persona sea OK
                //Validar que la relación persona-token esté habilitada
                //Validar que el token esté habilitado
                if (person.getStatus().equals(Status.OK) && person.isEnabled() &&
                        personToken.isEnabled() && oToken.isEnabled()) {
                    isValid = true;
                } else {
                    System.out.println(constants.ERR_AUTH_CODE_009() + ": " + constants.ERR_AUTH_UNATHORIZED_TOKEN_OR_PERSON_STATUS);
                    validationRequest.setMessage(constants.ERR_AUTH_CODE_009() + ": " + constants.ERR_AUTH_UNATHORIZED_TOKEN_OR_PERSON_STATUS);
                }
            } else {
                System.out.println(constants.ERR_AUTH_CODE_008() + ": " + constants.ERR_AUTH_NO_PERSON_OR_TOKEN_IS_FOUND);
                validationRequest.setMessage(constants.ERR_AUTH_CODE_008() + ": " + constants.ERR_AUTH_NO_PERSON_OR_TOKEN_IS_FOUND);
            }
        }catch (Exception e){
            validationRequest.setEnabled(false);
            System.out.println(constants.ERR_AUTH_CODE_007() + ": " + constants.ERR_AUTH_THERE_WAS_A_PROBLEM_FINDING_A_PERSON_AND_THEIR_TOKEN + " - " + e.getMessage());
            validationRequest.setMessage(constants.ERR_AUTH_CODE_007() + ": " + constants.ERR_AUTH_THERE_WAS_A_PROBLEM_FINDING_A_PERSON_AND_THEIR_TOKEN + " - " + e.getMessage());
        }
        return isValid;
    }


    public void errorCatch(ValidationRequest validationRequest){

    }


}
