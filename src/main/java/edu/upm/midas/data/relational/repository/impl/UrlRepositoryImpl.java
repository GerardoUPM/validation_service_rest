package edu.upm.midas.data.relational.repository.impl;

import edu.upm.midas.data.relational.entities.disnetdb.Url;
import edu.upm.midas.data.relational.repository.AbstractDao;
import edu.upm.midas.data.relational.repository.UrlRepository;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("UrlRepositoryDao")
public class UrlRepositoryImpl extends AbstractDao<Integer, Url>
                                implements UrlRepository{
    @Override
    public Url findById(Integer urlId) {
        Url url = getByKey(urlId);
        return url;
    }

    @Override
    public Url findByUrl(String url) {
        Url oUrl = null;
        List<Url> codeList = (List<Url>) getEntityManager()
                .createNamedQuery("Url.findByUrl")
                .setParameter("url", url)
                .getResultList();
        if (CollectionUtils.isNotEmpty(codeList))
            oUrl = codeList.get(0);
        return oUrl;
    }

    @SuppressWarnings("unchecked")
    public List<Url> findAllQuery() {
        return (List<Url>) getEntityManager()
                .createNamedQuery("Url.findAll")
                .getResultList();
    }

    @Override
    public void persist(Url url) {
        super.persist(url);
    }

    @Override
    public boolean deleteById(Integer urlId) {
        return false;
    }

    @Override
    public void delete(Url url) {
        super.delete(url);
    }

    @Override
    public Url update(Url url) {
        return super.update(url);
    }

    @Override
    public int updateByIdQuery(Integer urlId) {
        return 0;
    }
}
