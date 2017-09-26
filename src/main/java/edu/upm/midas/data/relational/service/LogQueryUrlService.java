package edu.upm.midas.data.relational.service;

import edu.upm.midas.data.relational.entities.disnetdb.LogQueryUrl;
import edu.upm.midas.data.relational.entities.disnetdb.LogQueryUrlPK;

import java.util.List;

public interface LogQueryUrlService {

    LogQueryUrl findById(LogQueryUrlPK logQueryUrlPK);

    List<LogQueryUrl> findAllQuery();

    void save(LogQueryUrl logQueryUrl);

    boolean deleteById(LogQueryUrlPK logQueryUrlPK);

    void delete(LogQueryUrl logQueryUrl);

    LogQueryUrl update(LogQueryUrl logQueryUrl);

    int updateByIdQuery(LogQueryUrlPK logQueryUrlPK);

}
