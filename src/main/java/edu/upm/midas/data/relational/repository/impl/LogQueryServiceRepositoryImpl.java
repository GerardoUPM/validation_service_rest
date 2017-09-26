package edu.upm.midas.data.relational.repository.impl;

import edu.upm.midas.data.relational.entities.disnetdb.LogQueryService;
import edu.upm.midas.data.relational.entities.disnetdb.LogQueryServicePK;
import edu.upm.midas.data.relational.repository.AbstractDao;
import edu.upm.midas.data.relational.repository.LogQueryServiceRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("LogQueryServiceRepositoryDao")
public class LogQueryServiceRepositoryImpl extends AbstractDao<LogQueryServicePK, LogQueryService>
                                            implements LogQueryServiceRepository{

    @Override
    public LogQueryService findById(LogQueryServicePK logQueryServicePK) {
        LogQueryService logQueryService = getByKey(logQueryServicePK);
        return logQueryService;
    }

    @SuppressWarnings("unchecked")
    public List<LogQueryService> findAllQuery() {
        return (List<LogQueryService>) getEntityManager()
                .createNamedQuery("LogQueryService.findAll")
                .getResultList();
    }

    @Override
    public void persist(LogQueryService logQueryService) {
        super.persist(logQueryService);
    }

    @Override
    public boolean deleteById(LogQueryServicePK logQueryServicePK) {
        return false;
    }

    @Override
    public void delete(LogQueryService logQueryService) {
        super.delete(logQueryService);
    }

    @Override
    public LogQueryService update(LogQueryService logQueryService) {
        return super.update(logQueryService);
    }

    @Override
    public int updateByIdQuery(LogQueryServicePK logQueryServicePK) {
        return 0;
    }
}
