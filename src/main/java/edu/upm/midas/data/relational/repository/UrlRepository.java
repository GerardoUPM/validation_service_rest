package edu.upm.midas.data.relational.repository;

import edu.upm.midas.data.relational.entities.disnetdb.Url;

import java.util.List;

public interface UrlRepository {

    Url findById(Integer urlId);

    Url findByUrl(String url);

    List<Url> findAllQuery();

    void persist(Url url);

    boolean deleteById(Integer urlId);

    void delete(Url url);

    Url update(Url url);

    int updateByIdQuery(Integer urlId);

}
