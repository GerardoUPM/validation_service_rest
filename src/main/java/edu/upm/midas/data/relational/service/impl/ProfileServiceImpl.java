package edu.upm.midas.data.relational.service.impl;
import edu.upm.midas.data.relational.entities.disnetdb.Profile;
import edu.upm.midas.data.relational.repository.ProfileRepository;
import edu.upm.midas.data.relational.service.ProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by gerardo on 20/09/2017.
 *
 * @author Gerardo Lagunes G. ${EMAIL}
 * @version ${<VERSION>}
 * @project web_acces_control
 * @className ProfileServiceImpl
 * @see
 */
@Service("profileService")
public class ProfileServiceImpl implements ProfileService {

    @Autowired
    private ProfileRepository daoProfile;

    @Transactional(propagation= Propagation.REQUIRED,readOnly=true)
    public Profile findById(String profileId) {
        Profile profile = daoProfile.findById(profileId);
        return profile;
    }

    @Transactional(propagation= Propagation.REQUIRED,readOnly=true)
    public Object[] findByIdNative(String profileId) {
        return new Object[0];
    }

    @Transactional(propagation= Propagation.REQUIRED,readOnly=true)
    public List<Profile> findAllQuery() {
        return null;
    }

    @Transactional(propagation= Propagation.REQUIRED,readOnly=true)
    public List<Object[]> findAllNative() {
        return null;
    }

    @Transactional(propagation= Propagation.REQUIRED)
    public void save(Profile profile) {
        daoProfile.persist(profile);
    }

    @Transactional(propagation=Propagation.REQUIRED)
    public int insertNative(String profileId, boolean enable, String name, String authority) {
        return 0;
    }

    @Transactional(propagation=Propagation.REQUIRED)
    public boolean deleteById(String profileId) {
        return false;
    }

    @Transactional(propagation=Propagation.REQUIRED)
    public void delete(Profile profile) {

    }

    @Transactional(propagation=Propagation.REQUIRED)
    public Profile update(Profile profile) {
        return null;
    }

    @Transactional(propagation=Propagation.REQUIRED)
    public int updateByIdQuery(Profile profile) {
        return 0;
    }
}
