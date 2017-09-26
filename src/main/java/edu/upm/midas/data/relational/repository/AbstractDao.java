package edu.upm.midas.data.relational.repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.io.Serializable;
import java.lang.reflect.ParameterizedType;

/**
 * Created by gerardo on 28/04/2017.
 *
 * @author Gerardo Lagunes G. ${EMAIL}
 * @version ${<VERSION>}
 * @project ExtractionInformationDiseasesWikipedia
 * @className AbstractDao
 * @see
 */

public abstract class AbstractDao<PK extends Serializable, T> {

    private final Class<T> persistentClass;

    @SuppressWarnings("unchecked")
    public AbstractDao(){
        this.persistentClass =(Class<T>) ((ParameterizedType) this.getClass().getGenericSuperclass()).getActualTypeArguments()[1];
    }

    @PersistenceContext
    EntityManager entityManager;

    protected EntityManager getEntityManager(){
        return this.entityManager;
    }

    protected T getByKey(PK key) {
        return (T) entityManager.find(persistentClass, key);
    }

    protected void persist(T entity) {
        entityManager.persist(entity);
    }

    protected T update(T entity) {
        return entityManager.merge(entity);
    }

    protected void delete(T entity) {
        entityManager.remove(entity);
    }

}
