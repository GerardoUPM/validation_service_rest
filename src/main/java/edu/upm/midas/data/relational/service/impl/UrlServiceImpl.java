package edu.upm.midas.data.relational.service.impl;

import edu.upm.midas.data.relational.entities.disnetdb.Url;
import edu.upm.midas.data.relational.repository.UrlRepository;
import edu.upm.midas.data.relational.service.UrlService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service("urlService")
public class UrlServiceImpl implements UrlService {

    @Autowired
    private UrlRepository daoUrl;


    @Transactional(propagation= Propagation.REQUIRED,readOnly=true)
    public Url findById(Integer urlId) {
        Url url = daoUrl.findById(urlId);
        return url;
    }

    @Override
    public Url findByUrl(String url) {
        return daoUrl.findByUrl(url);
    }

    @Transactional(propagation= Propagation.REQUIRED,readOnly=true)
    public List<Url> findAllQuery() {
        return daoUrl.findAllQuery();
    }

    @Transactional(propagation= Propagation.REQUIRED)
    public void save(Url url) {
        daoUrl.persist(url);
    }

    @Transactional(propagation= Propagation.REQUIRED)
    public boolean deleteById(Integer urlId) {
        return false;
    }

    @Transactional(propagation= Propagation.REQUIRED)
    public void delete(Url url) {
        daoUrl.delete(url);
    }

    @Transactional(propagation= Propagation.REQUIRED)
    public Url update(Url url) {
        return daoUrl.update(url);
    }

    @Transactional(propagation= Propagation.REQUIRED)
    public int updateByIdQuery(Integer urlId) {
        return 0;
    }
}
