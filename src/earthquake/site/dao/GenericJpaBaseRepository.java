package earthquake.site.dao;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.*;
import java.io.Serializable;
import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * 继承通用仓库接口，并实现基于HQL的通用查询操作
 *
 * @param <ID>
 * @param <E>
 */
public abstract class
GenericJpaBaseRepository<ID extends Serializable, E, F>
        implements GenericRepository<ID, E> {
    protected final Class<ID> idClass;
    protected final Class<E> entityClass;

    @PersistenceContext
    protected EntityManager entityManager;

    private static final Logger log = LogManager.getLogger();

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
    public E getById(ID id) {
        return entityManager.find(entityClass, id);
    }

    @Override
    public void insert(E entity) {
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

    public int getCount() {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Long> critQuery = criteriaBuilder.createQuery(Long.class);
        Root<E> root = critQuery.from(entityClass);
        critQuery.select(criteriaBuilder.countDistinct(root));
        return entityManager.createQuery(critQuery).getSingleResult().intValue();
    }

    public void batchInsert(List<E> entityList) {
        for (int i = 0; i < entityList.size(); i++) {
            entityManager.persist(entityList.get(i));
            if (i % 20 == 0) {
                entityManager.flush();
                entityManager.clear();
            }
        }
        entityManager.flush();
        entityManager.clear();
    }

    //构造条件映射
    public HashMap<String, Object> getConditionMap(F form) {

        HashMap<String, Object> attrsMap = new HashMap<>();
        Field[] fields = form.getClass().getDeclaredFields();
        boolean flag = false;
        for (int i = 0; i < fields.length; i++) {
            Field field = fields[i];
            String attrName = field.getName();
            if (attrName.equals("pageCount")) {
                flag = true;
            }
            try {
                Object value = field.get(form);
                if (value != null && !value.equals("")) {
                    attrsMap.put(attrName, value);
                }
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        if (!flag) {
            attrsMap.put("pageCount", "1");
            attrsMap.put("pageNum", "10");
        }
        return attrsMap;
    }

    //构造typedQuery
    public TypedQuery<E> getTypedQuery(ArrayList<String> subQuery, HashMap<String, Object> attrsMap) {

        String query = "select entity from " + entityClass.getSimpleName() + " entity";

        for (int i = 0; i < subQuery.size(); i++) {
            String sub = subQuery.get(i);
            if (i == 0) {
                query = query + " where " + sub;
            } else {
                query = query + " and " + sub;
            }
        }

        if (attrsMap.get("orderName") != null && !attrsMap.get("orderName").equals("")) {
            String sqlOrder = "";
            if (attrsMap.get("order").equals("2")) {
                sqlOrder = "desc";
            }
            query += " order by entity." + attrsMap.get("orderName") + " " + sqlOrder;
        }

        log.info(query);
        System.out.println(query);
        TypedQuery<E> typedQuery = entityManager.createQuery(query, entityClass);

        if (!attrsMap.get("pageCount").equals("")) {
            Integer pageCount = Integer.parseInt((String) attrsMap.get("pageCount"));
            Integer pageNum = Integer.parseInt((String) attrsMap.get("pageNum"));
            typedQuery.setFirstResult((pageCount - 1) * pageNum).setMaxResults(pageNum);
        }

        return typedQuery;
    }

    public TypedQuery<E> getTypedQuery(ArrayList<String> subQuery, HashMap<String, Object> attrsMap, String orderName, String order) {

        String query = "select entity from " + entityClass.getSimpleName() + " entity";

        for (int i = 0; i < subQuery.size(); i++) {
            String sub = subQuery.get(i);
            if (i == 0) {
                query = query + " where " + sub;
            } else {
                query = query + " and " + sub;
            }
        }


        if (!orderName.equals("") && !order.equals("")) {
            String sqlOrder = "";
            if (order.equals("2")) {
                sqlOrder = "desc";
            }
            query += " order by entity." + orderName + " " + sqlOrder;
        }

        log.info(query);
        System.out.println(query);
        TypedQuery<E> typedQuery = entityManager.createQuery(query, entityClass);

        if (!attrsMap.get("pageCount").equals("")) {
            Integer pageCount = Integer.parseInt((String) attrsMap.get("pageCount"));
            Integer pageNum = Integer.parseInt((String) attrsMap.get("pageNum"));
            typedQuery.setFirstResult((pageCount - 1) * pageNum).setMaxResults(pageNum);
        }

        return typedQuery;
    }
}
