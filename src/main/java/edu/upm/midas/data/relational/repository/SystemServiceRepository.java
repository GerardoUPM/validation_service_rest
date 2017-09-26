package edu.upm.midas.data.relational.repository;

import edu.upm.midas.data.relational.entities.disnetdb.SystemService;

import java.util.List;

public interface SystemServiceRepository {

    SystemService findById(String serviceId);

    List<SystemService> findAllQuery();

    void persist(SystemService systemService);

    boolean deleteById(String serviceId);

    void delete(SystemService systemService);

    SystemService update(SystemService systemService);

    int updateByIdQuery(String serviceId);

}
