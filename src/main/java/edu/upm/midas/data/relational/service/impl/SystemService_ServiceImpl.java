package edu.upm.midas.data.relational.service.impl;
import edu.upm.midas.data.relational.entities.disnetdb.SystemService;
import edu.upm.midas.data.relational.repository.SystemServiceRepository;
import edu.upm.midas.data.relational.service.SystemService_Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by gerardo on 26/09/2017.
 *
 * @author Gerardo Lagunes G. ${EMAIL}
 * @version ${<VERSION>}
 * @project disnet_web_app
 * @className SystemService_ServiceImpl
 * @see
 */
@Service("systemService_Service")
public class SystemService_ServiceImpl implements SystemService_Service {

    @Autowired
    private SystemServiceRepository daoSystemService;

    @Transactional(propagation= Propagation.REQUIRED,readOnly=true)
    public SystemService findById(String serviceId) {
        SystemService systemService = daoSystemService.findById(serviceId);
        return systemService;
    }

    @Transactional(propagation= Propagation.REQUIRED,readOnly=true)
    public List<SystemService> findAllQuery() {
        return daoSystemService.findAllQuery();
    }

    @Transactional(propagation= Propagation.REQUIRED)
    public void save(SystemService systemService) {
        daoSystemService.persist(systemService);
    }

    @Transactional(propagation= Propagation.REQUIRED)
    public boolean deleteById(String serviceId) {
        return false;
    }

    @Transactional(propagation= Propagation.REQUIRED)
    public void delete(SystemService systemService) {
        daoSystemService.delete(systemService);
    }

    @Transactional(propagation= Propagation.REQUIRED)
    public SystemService update(SystemService systemService) {
        return daoSystemService.update(systemService);
    }

    @Transactional(propagation= Propagation.REQUIRED)
    public int updateByIdQuery(String serviceId) {
        return 0;
    }
}
