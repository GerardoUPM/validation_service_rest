package edu.upm.midas.data.relational.service.impl;
import edu.upm.midas.data.relational.entities.disnetdb.Country;
import edu.upm.midas.data.relational.repository.CountryRepository;
import edu.upm.midas.data.relational.service.CountryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by gerardo on 25/09/2017.
 *
 * @author Gerardo Lagunes G. ${EMAIL}
 * @version ${<VERSION>}
 * @project disnet_web_app
 * @className CountryServiceImpl
 * @see
 */
@Service("serviceService")
public class CountryServiceImpl implements CountryService {

    @Autowired
    private CountryRepository daoCountry;

    @Override
    public List<Country> findAll() {
        return daoCountry.findAll();
    }
}
