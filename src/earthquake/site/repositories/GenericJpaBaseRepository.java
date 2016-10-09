package earthquake.site.repositories;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaDelete;
import javax.persistence.criteria.CriteriaQuery;
import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

public abstract class
GenericJpaBaseRepository<ID extends Serializable, E>
    implements GenericRepository<ID, E>
{
    protected final Class<ID> idClass;
    protected final Class<E> entityClass;

    @PersistenceContext
    protected EntityManager entityManager;

    @SuppressWarnings("unchecked")
    public GenericJpaBaseRepository()
    {
        Type genericSuperclass = this.getClass().getGenericSuperclass();
        while(!(genericSuperclass instanceof ParameterizedType))
        {
            if(!(genericSuperclass instanceof Class))
                throw new IllegalStateException("Unable to determine type " +
                        "arguments because generic superclass neither " +
                        "parameterized type nor class.");
            if(genericSuperclass == GenericJpaBaseRepository.class)
                throw new IllegalStateException("Unable to determine type " +
                        "arguments because no parameterized generic superclass " +
                        "found.");

            genericSuperclass = ((Class)genericSuperclass).getGenericSuperclass();
        }

        ParameterizedType type = (ParameterizedType)genericSuperclass;
        Type[] arguments = type.getActualTypeArguments();
        this.idClass = (Class<ID>)arguments[0];
        this.entityClass = (Class<E>)arguments[1];
    }

    @Override
    public Iterable<E> getAll()
    {
        CriteriaBuilder builder = this.entityManager.getCriteriaBuilder();
        CriteriaQuery<E> query = builder.createQuery(this.entityClass);

        return this.entityManager.createQuery(
                query.select(query.from(this.entityClass))
        ).getResultList();
    }

    @Override
    public E get(ID id)
    {
        return this.entityManager.find(this.entityClass, id);
    }

    @Override
    public void add(E entity)
    {
        this.entityManager.persist(entity);
    }

    @Override
    public void update(E entity)
    {
        this.entityManager.merge(entity);
    }

    @Override
    public void delete(E entity)
    {
        this.entityManager.remove(entity);
    }

    @Override
    public void deleteById(ID id)
    {
        CriteriaBuilder builder = this.entityManager.getCriteriaBuilder();
        CriteriaDelete<E> query = builder.createCriteriaDelete(this.entityClass);

        this.entityManager.createQuery(query.where(
                builder.equal(query.from(this.entityClass).get("id"), id)
        )).executeUpdate();
    }


}
