package edu.upm.midas.data.relational.repository.impl;
import edu.upm.midas.data.relational.entities.disnetdb.Country;
import edu.upm.midas.data.relational.repository.AbstractDao;
import edu.upm.midas.data.relational.repository.CountryRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by gerardo on 25/09/2017.
 *
 * @author Gerardo Lagunes G. ${EMAIL}
 * @version ${<VERSION>}
 * @project disnet_web_app
 * @className CountryRepositoryImpl
 * @see
 */
@Repository("CountryRepositoryDao")
public class CountryRepositoryImpl extends AbstractDao<Integer, Country>
                                    implements CountryRepository{
    @Override
    public List<Country> findAll() {
        return (List<Country>) getEntityManager()
                .createNamedQuery("Country.findAll")
                .getResultList();
    }
}
