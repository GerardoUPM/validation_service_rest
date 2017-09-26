package edu.upm.midas.data.relational.service.impl;
import edu.upm.midas.data.relational.entities.disnetdb.AcademicInfo;
import edu.upm.midas.data.relational.repository.AcademicInfoRepository;
import edu.upm.midas.data.relational.service.AcademicInfoService;
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
 * @className AcademicInfoServiceImpl
 * @see
 */
@Service("academicInfoService")
public class AcademicInfoServiceImpl implements AcademicInfoService {

    @Autowired
    private AcademicInfoRepository daoAcademicInfo;

    @Transactional(propagation= Propagation.REQUIRED,readOnly=true)
    public AcademicInfo findById(int academicId) {
        AcademicInfo academicInfo = daoAcademicInfo.findById(academicId);
        return academicInfo;
    }

    @Transactional(propagation= Propagation.REQUIRED,readOnly=true)
    public Object[] findByIdNative(int academicId) {
        return new Object[0];
    }

    @Transactional(propagation= Propagation.REQUIRED,readOnly=true)
    public List<AcademicInfo> findAllQuery() {
        return null;
    }

    @Transactional(propagation= Propagation.REQUIRED,readOnly=true)
    public List<Object[]> findAllNative() {
        return null;
    }

    @Transactional(propagation=Propagation.REQUIRED)
    public void save(AcademicInfo academicInfo) {
        daoAcademicInfo.persist(academicInfo);
    }

    @Transactional(propagation=Propagation.REQUIRED)
    public int insertNative(String institution, int country, String occupation, String interest) {
        return 0;
    }

    @Transactional(propagation=Propagation.REQUIRED)
    public boolean deleteById(int academicId) {
        return false;
    }

    @Transactional(propagation=Propagation.REQUIRED)
    public void delete(AcademicInfo academicInfo) {

    }

    @Transactional(propagation=Propagation.REQUIRED)
    public AcademicInfo update(AcademicInfo academicInfo) {
        return null;
    }

    @Transactional(propagation=Propagation.REQUIRED)
    public int updateByIdQuery(AcademicInfo academicInfo) {
        return 0;
    }
}
