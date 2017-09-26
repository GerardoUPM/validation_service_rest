package edu.upm.midas.data.relational.repository.impl;

import edu.upm.midas.data.relational.entities.disnetdb.LogQuery;
import edu.upm.midas.data.relational.repository.AbstractDao;
import edu.upm.midas.data.relational.repository.LogQueryRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("LogQueryRepositoryDao")
public class LogQueryRepositoryImpl extends AbstractDao<String, LogQuery>
                                    implements LogQueryRepository{
    @Override
    public LogQuery findById(String queryId) {
        LogQuery logQuery = getByKey(queryId);
        return logQuery;
    }

    @SuppressWarnings("unchecked")
    public List<LogQuery> findAllQuery() {
        return (List<LogQuery>) getEntityManager()
                .createNamedQuery("LogQuery.findAll")
                .getResultList();
    }

    @Override
    public void persist(LogQuery logQuery) {
        super.persist(logQuery);
    }

    @Override
    public boolean deleteById(String queryId) {
        return false;
    }

    @Override
    public void delete(LogQuery logQuery) {
        super.delete(logQuery);
    }

    @Override
    public LogQuery update(LogQuery logQuery) {
        return super.update(logQuery);
    }

    @Override
    public int updateByIdQuery(String queryId) {
        return 0;
    }
}
