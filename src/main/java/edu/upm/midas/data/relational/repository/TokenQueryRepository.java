package edu.upm.midas.data.relational.repository;

import edu.upm.midas.data.relational.entities.disnetdb.TokenQuery;
import edu.upm.midas.data.relational.entities.disnetdb.TokenQueryPK;

import java.util.List;

public interface TokenQueryRepository {

    TokenQuery findById(TokenQueryPK tokenQueryPK);

    List<TokenQuery> findAllQuery();

    void persist(TokenQuery tokenQuery);

    boolean deleteById(TokenQueryPK tokenQueryPK);

    void delete(TokenQuery tokenQuery);

    TokenQuery update(TokenQuery tokenQuery);

    int updateByIdQuery(TokenQueryPK tokenQueryPK);

}
