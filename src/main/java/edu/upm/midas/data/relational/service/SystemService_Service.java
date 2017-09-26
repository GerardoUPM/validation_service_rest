package edu.upm.midas.data.relational.service;

import edu.upm.midas.data.relational.entities.disnetdb.SystemService;

import java.util.List;

public interface SystemService_Service {

    SystemService findById(String serviceId);

    List<SystemService> findAllQuery();

    void save(SystemService systemService);

    boolean deleteById(String serviceId);

    void delete(SystemService systemService);

    SystemService update(SystemService systemService);

    int updateByIdQuery(String serviceId);

}
