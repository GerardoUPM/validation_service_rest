package edu.upm.midas.data.relational.service.impl;

import edu.upm.midas.data.relational.entities.disnetdb.Token;
import edu.upm.midas.data.relational.repository.TokenRepository;
import edu.upm.midas.data.relational.service.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

/**
 * Created by gerardo on 24/9/17.
 */
@Service("tokenService")
public class TokenServiceImpl implements TokenService {


    @Autowired
    private TokenRepository daoToken;

    @Transactional(propagation= Propagation.REQUIRED,readOnly=true)
    public Token findById(String token) {
        Token oToken = daoToken.findById(token);
        return oToken;
    }

    @Transactional(propagation= Propagation.REQUIRED,readOnly=true)
    public Object[] findByIdNative(String token) {
        return daoToken.findByIdNative(token);
    }

    @Transactional(propagation= Propagation.REQUIRED,readOnly=true)
    public List<Token> findAllQuery() {
        return null;
    }

    @Transactional(propagation= Propagation.REQUIRED,readOnly=true)
    public List<Object[]> findAllNative() {
        return null;
    }

    @Transactional(propagation= Propagation.REQUIRED)
    public void save(Token token) {
        daoToken.persist(token);
    }

    @Transactional(propagation=Propagation.REQUIRED)
    public int insertNative(String token, String type, boolean enabled, long expiration, String scope, Date date) {
        return daoToken.insertNative(token, type, enabled, expiration, scope, date);
    }

    @Transactional(propagation=Propagation.REQUIRED)
    public boolean deleteById(String token) {
        return false;
    }

    @Transactional(propagation=Propagation.REQUIRED)
    public void delete(Token token) {

    }

    @Transactional(propagation=Propagation.REQUIRED)
    public Token update(Token token) {
        return null;
    }

    @Transactional(propagation=Propagation.REQUIRED)
    public int updateEnabledNative(String token) {
        return daoToken.updateEnabledNative(token);
    }

    @Transactional(propagation=Propagation.REQUIRED)
    public int updateByIdQuery(Token token) {
        return 0;
    }
}
