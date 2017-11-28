package edu.upm.midas.data.relational.service;

import edu.upm.midas.data.relational.entities.disnetdb.LogQuery;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

public interface LogQuery_Service {

    LogQuery findById(String queryId);

    List<LogQuery> findAllQuery();

    void save(LogQuery logQuery);

    boolean deleteById(String queryId);

    void delete(LogQuery logQuery);

    LogQuery update(LogQuery logQuery);

    int updateRuntimeNative(String queryId, Timestamp startDatetime, Timestamp endDatetime);

    int updateByIdQuery(String queryId);

}
