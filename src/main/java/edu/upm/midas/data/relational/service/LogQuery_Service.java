package edu.upm.midas.data.relational.service;

import edu.upm.midas.data.relational.entities.disnetdb.LogQuery;

import java.util.List;

public interface LogQuery_Service {

    LogQuery findById(String queryId);

    List<LogQuery> findAllQuery();

    void save(LogQuery logQuery);

    boolean deleteById(String queryId);

    void delete(LogQuery logQuery);

    LogQuery update(LogQuery logQuery);

    int updateByIdQuery(String queryId);

}
