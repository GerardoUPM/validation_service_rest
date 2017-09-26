package edu.upm.midas.data.relational.service;

import edu.upm.midas.data.relational.entities.disnetdb.Url;

import java.util.List;

public interface UrlService {

    Url findById(Integer urlId);

    Url findByUrl(String url);

    List<Url> findAllQuery();

    void save(Url url);

    boolean deleteById(Integer urlId);

    void delete(Url url);

    Url update(Url url);

    int updateByIdQuery(Integer urlId);

}
