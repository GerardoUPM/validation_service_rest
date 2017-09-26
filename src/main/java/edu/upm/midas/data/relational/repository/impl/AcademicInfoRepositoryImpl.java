package edu.upm.midas.data.relational.repository.impl;
import edu.upm.midas.data.relational.entities.disnetdb.AcademicInfo;
import edu.upm.midas.data.relational.repository.AbstractDao;
import edu.upm.midas.data.relational.repository.AcademicInfoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by gerardo on 20/09/2017.
 *
 * @author Gerardo Lagunes G. ${EMAIL}
 * @version ${<VERSION>}
 * @project web_acces_control
 * @className AcademicInfoRepositoryImpl
 * @see
 */
@Repository("AcademicInfoRepositoryDao")
public class AcademicInfoRepositoryImpl extends AbstractDao<Integer, AcademicInfo>
                                        implements AcademicInfoRepository{
    @Override
    public AcademicInfo findById(int academicId) {
        return null;
    }

    @SuppressWarnings("unchecked")
    @Override
    public Object[] findByIdNative(int academicId) {
        return new Object[0];
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<AcademicInfo> findAllQuery() {
        return null;
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<Object[]> findAllNative() {
        return null;
    }

    @Override
    public void persist(AcademicInfo academicInfo) {
        super.persist(academicInfo);
    }

    @Override
    public int insertNative(String institution, int country, String occupation, String interest) {
        return 0;
    }

    @Override
    public boolean deleteById(int academicId) {
        return false;
    }

    @Override
    public void delete(AcademicInfo academicInfo) {

    }

    @Override
    public AcademicInfo update(AcademicInfo academicInfo) {
        return null;
    }

    @Override
    public int updateByIdQuery(AcademicInfo academicInfo) {
        return 0;
    }
}
