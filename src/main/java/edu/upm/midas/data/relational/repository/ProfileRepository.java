package edu.upm.midas.data.relational.repository;
import edu.upm.midas.data.relational.entities.disnetdb.Profile;

import java.util.List;

/**
 * Created by gerardo on 20/09/2017.
 *
 * @author Gerardo Lagunes G. ${EMAIL}
 * @version ${<VERSION>}
 * @project web_acces_control
 * @className ProfileRepository
 * @see
 */
public interface ProfileRepository {

    Profile findById(String profileId);

    Object[] findByIdNative(String profileId);//por profileId

    List<Profile> findAllQuery();

    List<Object[]> findAllNative();

    void persist(Profile profile);

    int insertNative(String profileId, boolean enable, String name, String authority);

    boolean deleteById(String profileId);

    void delete(Profile profile);

    Profile update(Profile profile);

    int updateByIdQuery(Profile profile);

}
