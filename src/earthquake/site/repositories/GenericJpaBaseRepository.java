package earthquake.site.repositories;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.*;
import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Iterator;
import java.util.Map;

public abstract class
        GenericJpaBaseRepository<ID extends Serializable, E>
        implements GenericRepository<ID, E> {
    protected final Class<ID> idClass;
    protected final Class<E> entityClass;

    @PersistenceContext
    protected EntityManager entityManager;

    @SuppressWarnings("unchecked")
    public GenericJpaBaseRepository() {
        Type genericSuperclass = getClass().getGenericSuperclass();
        while (!(genericSuperclass instanceof ParameterizedType)) {
            if (!(genericSuperclass instanceof Class))
                throw new IllegalStateException("Unable to determine type " +
                        "arguments because generic superclass neither " +
                        "parameterized type nor class.");
            if (genericSuperclass == GenericJpaBaseRepository.class)
                throw new IllegalStateException("Unable to determine type " +
                        "arguments because no parameterized generic superclass " +
                        "found.");

            genericSuperclass = ((Class) genericSuperclass).getGenericSuperclass();
        }

        ParameterizedType type = (ParameterizedType) genericSuperclass;
        Type[] arguments = type.getActualTypeArguments();
        idClass = (Class<ID>) arguments[0];
        entityClass = (Class<E>) arguments[1];
    }

    @Override
    public Iterable<E> getAll() {
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<E> query = builder.createQuery(entityClass);

        return entityManager.createQuery(
                query.select(query.from(entityClass))
        ).getResultList();
    }

    @Override
    public E get(ID id) {
        return entityManager.find(entityClass, id);
    }

    @Override
    public void add(E entity) {
        entityManager.persist(entity);
    }

    @Override
    public void update(E entity) {
        entityManager.merge(entity);
    }

    @Override
    public void delete(E entity) {
        entityManager.remove(entity);
    }

    @Override
    public void deleteById(ID id) {
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaDelete<E> query = builder.createCriteriaDelete(entityClass);

        entityManager.createQuery(query.where(
                builder.equal(query.from(entityClass).get("id"), id)
        )).executeUpdate();
    }

    @Override
    public void deleteAll(){

    }


}
