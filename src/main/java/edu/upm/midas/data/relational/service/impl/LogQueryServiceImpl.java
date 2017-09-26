package edu.upm.midas.data.relational.service.impl;

import edu.upm.midas.data.relational.entities.disnetdb.LogQuery;
import edu.upm.midas.data.relational.repository.LogQueryRepository;
import edu.upm.midas.data.relational.service.LogQuery_Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service("logQueryServiceService")
public class LogQueryServiceImpl implements LogQuery_Service {

    @Autowired
    private LogQueryRepository daoLogQuery;


    @Transactional(propagation= Propagation.REQUIRED,readOnly=true)
    public LogQuery findById(String queryId) {
        LogQuery logQuery = daoLogQuery.findById(queryId);
        return logQuery;
    }

    @Transactional(propagation= Propagation.REQUIRED,readOnly=true)
    public List<LogQuery> findAllQuery() {
        return daoLogQuery.findAllQuery();
    }

    @Transactional(propagation= Propagation.REQUIRED)
    public void save(LogQuery logQuery) {
        daoLogQuery.persist(logQuery);
    }

    @Transactional(propagation= Propagation.REQUIRED)
    public boolean deleteById(String queryId) {
        return false;
    }

    @Transactional(propagation= Propagation.REQUIRED)
    public void delete(LogQuery logQuery) {
        daoLogQuery.delete(logQuery);
    }

    @Transactional(propagation= Propagation.REQUIRED)
    public LogQuery update(LogQuery logQuery) {
        return daoLogQuery.update(logQuery);
    }

    @Transactional(propagation= Propagation.REQUIRED)
    public int updateByIdQuery(String queryId) {
        return 0;
    }
}
