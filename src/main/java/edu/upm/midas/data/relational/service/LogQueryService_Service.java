package edu.upm.midas.data.relational.service;

import edu.upm.midas.data.relational.entities.disnetdb.LogQueryService;
import edu.upm.midas.data.relational.entities.disnetdb.LogQueryServicePK;

import java.util.List;

public interface LogQueryService_Service {

    LogQueryService findById(LogQueryServicePK logQueryServicePK);

    List<LogQueryService> findAllQuery();

    void save(LogQueryService logQueryService);

    boolean deleteById(LogQueryServicePK logQueryServicePK);

    void delete(LogQueryService logQueryService);

    LogQueryService update(LogQueryService logQueryService);

    int updateByIdQuery(LogQueryServicePK logQueryServicePK);

}
