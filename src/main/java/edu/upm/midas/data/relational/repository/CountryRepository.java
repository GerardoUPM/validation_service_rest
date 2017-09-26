package edu.upm.midas.data.relational.repository;
import edu.upm.midas.data.relational.entities.disnetdb.Country;

import java.util.List;

/**
 * Created by gerardo on 25/09/2017.
 *
 * @author Gerardo Lagunes G. ${EMAIL}
 * @version ${<VERSION>}
 * @project disnet_web_app
 * @className CountryRepository
 * @see
 */
public interface CountryRepository {

    List<Country> findAll();

}
