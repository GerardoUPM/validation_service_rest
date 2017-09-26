package edu.upm.midas.data.relational.service;

import edu.upm.midas.data.relational.entities.disnetdb.TokenQuery;
import edu.upm.midas.data.relational.entities.disnetdb.TokenQueryPK;

import java.util.List;

public interface TokenQueryService {

    TokenQuery findById(TokenQueryPK tokenQueryPK);

    List<TokenQuery> findAllQuery();

    void save(TokenQuery tokenQuery);

    boolean deleteById(TokenQueryPK tokenQueryPK);

    void delete(TokenQuery tokenQuery);

    TokenQuery update(TokenQuery tokenQuery);

    int updateByIdQuery(TokenQueryPK tokenQueryPK);

}
