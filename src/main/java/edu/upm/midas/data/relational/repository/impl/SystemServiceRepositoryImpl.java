package edu.upm.midas.data.relational.repository.impl;
import edu.upm.midas.data.relational.entities.disnetdb.SystemService;
import edu.upm.midas.data.relational.repository.AbstractDao;
import edu.upm.midas.data.relational.repository.SystemServiceRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by gerardo on 26/09/2017.
 *
 * @author Gerardo Lagunes G. ${EMAIL}
 * @version ${<VERSION>}
 * @project disnet_web_app
 * @className SystemServiceRepositoryImpl
 * @see
 */

@Repository("SystemServiceRepositoryDao")
public class SystemServiceRepositoryImpl extends AbstractDao<String, SystemService>
                                        implements SystemServiceRepository{
    @Override
    public SystemService findById(String serviceId) {
        SystemService systemService = getByKey(serviceId);
        return systemService;
    }

    @SuppressWarnings("unchecked")
    public List<SystemService> findAllQuery() {
        return (List<SystemService>) getEntityManager()
                .createNamedQuery("SystemService.findAll")
                .getResultList();
    }

    @Override
    public void persist(SystemService systemService) {
        super.persist(systemService);
    }

    @Override
    public boolean deleteById(String serviceId) {
        return false;
    }

    @Override
    public void delete(SystemService systemService) {
        super.delete(systemService);
    }

    @Override
    public SystemService update(SystemService systemService) {
        return super.update(systemService);
    }

    @Override
    public int updateByIdQuery(String serviceId) {
        return 0;
    }
}
