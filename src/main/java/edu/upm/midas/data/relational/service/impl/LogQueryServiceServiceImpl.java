package edu.upm.midas.data.relational.service.impl;

import edu.upm.midas.data.relational.entities.disnetdb.LogQueryService;
import edu.upm.midas.data.relational.entities.disnetdb.LogQueryServicePK;
import edu.upm.midas.data.relational.repository.LogQueryServiceRepository;
import edu.upm.midas.data.relational.service.LogQueryService_Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service("logQueryServiceS_Service")
public class LogQueryServiceServiceImpl implements LogQueryService_Service {

    @Autowired
    private LogQueryServiceRepository daoLogQueryService;


    @Transactional(propagation= Propagation.REQUIRED,readOnly=true)
    public LogQueryService findById(LogQueryServicePK logQueryServicePK) {
        LogQueryService logQueryService = daoLogQueryService.findById(logQueryServicePK);
        return logQueryService;
    }

    @Transactional(propagation= Propagation.REQUIRED,readOnly=true)
    public List<LogQueryService> findAllQuery() {
        return daoLogQueryService.findAllQuery();
    }

    @Transactional(propagation= Propagation.REQUIRED)
    public void save(LogQueryService logQueryService) {
        daoLogQueryService.persist(logQueryService);
    }

    @Transactional(propagation= Propagation.REQUIRED)
    public boolean deleteById(LogQueryServicePK logQueryServicePK) {
        return false;
    }

    @Transactional(propagation= Propagation.REQUIRED)
    public void delete(LogQueryService logQueryService) {
        daoLogQueryService.delete(logQueryService);
    }

    @Transactional(propagation= Propagation.REQUIRED)
    public LogQueryService update(LogQueryService logQueryService) {
        return daoLogQueryService.update(logQueryService);
    }

    @Transactional(propagation= Propagation.REQUIRED)
    public int updateByIdQuery(LogQueryServicePK logQueryServicePK) {
        return 0;
    }
}
