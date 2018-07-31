package edu.upm.midas.token.model;

import edu.upm.midas.common.util.TimeProvider;
import edu.upm.midas.constants.Constants;
import edu.upm.midas.data.relational.entities.disnetdb.Person;
import edu.upm.midas.model.ValidationRequest;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mobile.device.Device;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Component
public class JwtTokenUtil implements Serializable {

    private static final long serialVersionUID = -3301605591108950415L;

    static final String CLAIM_KEY_USERNAME = "sub";
    static final String CLAIM_KEY_AUDIENCE = "aud";
    static final String CLAIM_KEY_CREATED = "iat";

    static final String AUDIENCE_UNKNOWN = "unknown";
    static final String AUDIENCE_WEB = "web";
    static final String AUDIENCE_MOBILE = "mobile";
    static final String AUDIENCE_TABLET = "tablet";

    @Autowired
    private TimeProvider timeProvider;
    @Autowired
    private Constants constants;

    @Value("${jwt.secret}")
    private String secret;

    @Value("${jwt.claims.name.user}")
    private String claim_name_user;
    @Value("${jwt.claims.name.name}")
    private String claim_name_name;

    @Value("${jwt.claims.name.token}")
    private String claim_name_token;
    @Value("${jwt.claims.name.api_code}")
    private String claim_name_apiCode;
    @Value("${jwt.claims.name.request}")
    private String claim_name_request;
    @Value("${jwt.claims.name.url}")
    private String claim_name_url;
    @Value("${jwt.claims.name.method}")
    private String claim_name_method;
    @Value("${jwt.claims.name.transaction_id}")
    private String claim_name_transactionId;

    @Value("${jwt.expiration}")
    private Long expiration;

    public String getUsernameFromToken(String token) {
        return getClaimFromToken(token, Claims::getSubject);
    }

    public Date getIssuedAtDateFromToken(String token) {
        return getClaimFromToken(token, Claims::getIssuedAt);
    }

    public Date getExpirationDateFromToken(String token) {
        return getClaimFromToken(token, Claims::getExpiration);
    }

    public String getAudienceFromToken(String token) {
        return getClaimFromToken(token, Claims::getAudience);
    }

    public <T> T getClaimFromToken(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = getAllClaimsFromToken(token);
        return claimsResolver.apply(claims);
    }

    private Claims getAllClaimsFromToken(String token) {
        return Jwts.parser()
                .setSigningKey(secret)
                .parseClaimsJws(token)
                .getBody();
    }

    private Boolean isTokenExpired(String token) {
        final Date expiration = getExpirationDateFromToken(token);
        return expiration.before(timeProvider.getNow());
    }

    private Boolean isCreatedBeforeLastPasswordReset(Date created, Date lastPasswordReset) {
        return (lastPasswordReset != null && created.before(lastPasswordReset));
    }

    private String generateAudience(Device device) {
        String audience = AUDIENCE_UNKNOWN;
        if (device.isNormal()) {
            audience = AUDIENCE_WEB;
        } else if (device.isTablet()) {
            audience = AUDIENCE_TABLET;
        } else if (device.isMobile()) {
            audience = AUDIENCE_MOBILE;
        }
        return audience;
    }

    private Boolean ignoreTokenExpiration(String token) {
        String audience = getAudienceFromToken(token);
        return (AUDIENCE_TABLET.equals(audience) || AUDIENCE_MOBILE.equals(audience));
    }

    public String generateToken(Person person, Device device) {
        Map<String, Object> claims = new HashMap<>();
        claims.put(claim_name_user, true);
        claims.put(claim_name_name, person.getFirstName() + " " + person.getLastName());
        //claims.put("secret_claim", "Perter Parker");
        return doGenerateToken(claims, person, generateAudience(device));
    }

    public String generateTokenToUpdateQueryRuntime(String queryId, Device device) {
        Map<String, Object> claims = new HashMap<>();
        claims.put(claim_name_transactionId, queryId);
        return doGenerateTokenWithoutPerson(claims, generateAudience(device));
    }

    private String doGenerateToken(Map<String, Object> claims, Person person, String audience) {
        final Date createdDate = timeProvider.getNow();
        final Date expirationDate = calculateExpirationDate(createdDate);

        System.out.println("doGenerateToken " + createdDate);

        return Jwts.builder()
                .setId(person.getPersonId())
                .setClaims(claims)
                .setSubject(person.getPersonId())
                .setAudience(audience)
                .setIssuedAt(createdDate)
                //.setExpiration(expirationDate)
                .signWith(SignatureAlgorithm.HS512, secret)
                .compact();
    }

    private String doGenerateTokenWithoutPerson(Map<String, Object> claims, String audience) {
        final Date createdDate = timeProvider.getNow();
        final Date expirationDate = calculateExpirationDate(createdDate);

        System.out.println("doGenerateToken " + createdDate);

        return Jwts.builder()
                .setClaims(claims)
                .setAudience(audience)
                .setIssuedAt(createdDate)
                .signWith(SignatureAlgorithm.HS512, secret)
                .compact();
    }

    public String getEmailWithJWTDecode(ValidationRequest validationRequest){
        //System.out.println("USER TOKEN: "+ token);
        try {
            Claims claims = Jwts.parser()
                    .setSigningKey( secret )
                    .parseClaimsJws( validationRequest.getToken() ).getBody();

            //System.out.println("EMAIL CLAIM: " + claims.toString());
            //TEST//System.out.println("EXTRACT CLAIM: " + claims.get("secret_claim"));
            return claims.getSubject();
        }catch (Exception e){
            //ESTE TIPO DE ERRORES (e.getMessage()) NO SE DEBEN MOSTRAR AL USUARIO FINAL, SINO DEBEN IR EN EL LOG
            validationRequest.setEnabled(false);
            validationRequest.setMessage(constants.ERR_AUTH_CODE_003() + ": " + constants.ERR_AUTH_CANNOT_READ_THE_TOKENS_EMAIL_PROPERTY /*+ " - " + e.getMessage()*/);
            return "";
        }

    }


    /**
     * @param token
     * @return
     */
    public ValidationRequest getServiceJWTDecode(String token){
        ValidationRequest validationRequest = new ValidationRequest();
        try {
            Claims claims = Jwts.parser()
                    .setSigningKey(secret)
                    .parseClaimsJws(token).getBody();

            System.out.println("CLAIMS: " + claims.toString());

            validationRequest.setToken( claims.get(claim_name_token).toString()) ;
            validationRequest.setApiCode( claims.get(claim_name_apiCode).toString() );
            validationRequest.setRequest( claims.get(claim_name_request).toString() );
            validationRequest.setUrl( claims.get(claim_name_url).toString() );
            validationRequest.setMethod( claims.get(claim_name_method).toString() );
            validationRequest.setEnabled( !claims.isEmpty() );
        }catch (Exception e){
            validationRequest.setEnabled( false );
            validationRequest.setMessage( constants.ERR_AUTH_CODE_001() + ": " + constants.ERR_AUTH_CANT_READ_TOKEN_PROPERTIES + " - " + e.getMessage() );
        }

        return validationRequest;
    }


    public Boolean canTokenBeRefreshed(String token, Date lastPasswordReset) {
        final Date created = getIssuedAtDateFromToken(token);
        return !isCreatedBeforeLastPasswordReset(created, lastPasswordReset)
                && (!isTokenExpired(token) || ignoreTokenExpiration(token));
    }

    public String refreshToken(String token) {
        final Date createdDate = timeProvider.getNow();
        final Date expirationDate = calculateExpirationDate(createdDate);

        final Claims claims = getAllClaimsFromToken(token);
        claims.setIssuedAt(createdDate);
        claims.setExpiration(expirationDate);

        return Jwts.builder()
                .setClaims(claims)
                .signWith(SignatureAlgorithm.HS512, secret)
                .compact();
    }

/*
    public Boolean validateToken(String token, UserDetails userDetails) {
        JwtUser user = (JwtUser) userDetails;
        final String username = getUsernameFromToken(token);
        final Date created = getIssuedAtDateFromToken(token);
        //final Date expiration = getExpirationDateFromToken(token);
        return (
              username.equals(user.getUsername())
                    && !isTokenExpired(token)
                    //&& !isCreatedBeforeLastPasswordReset(created, user.getLastPasswordResetDate())
        );
    }
*/

    private Date calculateExpirationDate(Date createdDate) {
        return new Date(createdDate.getTime() + expiration * 1000);
    }
}
