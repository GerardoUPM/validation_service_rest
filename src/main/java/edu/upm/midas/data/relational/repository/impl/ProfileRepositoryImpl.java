package edu.upm.midas.data.relational.repository.impl;
import edu.upm.midas.data.relational.entities.disnetdb.Profile;
import edu.upm.midas.data.relational.repository.AbstractDao;
import edu.upm.midas.data.relational.repository.ProfileRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by gerardo on 20/09/2017.
 *
 * @author Gerardo Lagunes G. ${EMAIL}
 * @version ${<VERSION>}
 * @project web_acces_control
 * @className ProfileRepositoryImpl
 * @see
 */
@Repository("ProfileRepositoryDao")
public class ProfileRepositoryImpl extends AbstractDao<String, Profile>
                                    implements ProfileRepository{
    @Override
    public Profile findById(String profileId) {
        Profile profile = getByKey(profileId);
        return profile;
    }

    @Override
    public Object[] findByIdNative(String profileId) {
        return new Object[0];
    }

    @Override
    public List<Profile> findAllQuery() {
        return null;
    }

    @Override
    public List<Object[]> findAllNative() {
        return null;
    }

    @Override
    public void persist(Profile profile) {
        super.persist(profile);
    }

    @Override
    public int insertNative(String profileId, boolean enable, String name, String authority) {
        return 0;
    }

    @Override
    public boolean deleteById(String profileId) {
        return false;
    }

    @Override
    public void delete(Profile profile) {

    }

    @Override
    public Profile update(Profile profile) {
        return null;
    }

    @Override
    public int updateByIdQuery(Profile profile) {
        return 0;
    }
}
