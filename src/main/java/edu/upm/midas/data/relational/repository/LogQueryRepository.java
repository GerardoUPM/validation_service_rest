package edu.upm.midas.data.relational.repository;

import edu.upm.midas.data.relational.entities.disnetdb.LogQuery;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

public interface LogQueryRepository {

    LogQuery findById(String queryId);

    List<LogQuery> findAllQuery();

    void persist(LogQuery logQuery);

    boolean deleteById(String queryId);

    void delete(LogQuery logQuery);

    LogQuery update(LogQuery logQuery);

    int updateRuntimeNative(String queryId, String startDatetime, String endDatetime);

    int updateByIdQuery(String queryId);

}
