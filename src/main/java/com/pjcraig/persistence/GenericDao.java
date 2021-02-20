package com.pjcraig.persistence;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.Transaction;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

/**
 * A generic DAO inspired by https://rodrigouchoa.wordpress.com/.
 * @param <T>
 */
public class GenericDao<T> {
    private Class<T> type;
    private final Logger logger = LogManager.getLogger();

    /**
     * Instantiates a new GenericDao instance.
     * @param type The entity type, such as User.
     */
    public GenericDao(Class<T> type) {
        this.type = type;
    }

    /**
     * Opens a new session for use using the SessionFactoryProvider class.
     * @return The open Session in which to access the database.
     */
    private Session getSession() {
        return SessionFactoryProvider.getSessionFactory().openSession();
    }

    /**
     * Gets an entity by a given id.
     * @param id The entity id to search for.
     * @return The entity object or null.
     */
    public <T>T getById(int id) {
        Session session = getSession();
        T entity = (T) session.get(type, id);
        session.close();
        return entity;
    }

    /**
     * Updates or saves a given entity in the database.
     * @param entity The entity to add or update.
     */
    public void saveOrUpdate(T entity) {
        Session session = getSession();
        Transaction transaction = session.beginTransaction();
        session.saveOrUpdate(entity);
        transaction.commit();
        session.close();
    }

    /**
     * Inserts a given entity into the database.
     * @param entity The entity to insert.
     * @return The id of the newly added entity.
     */
    public int insert(T entity) {
        int id = 0;
        Session session = getSession();
        Transaction transaction = session.beginTransaction();
        id = (int) session.save(entity);
        transaction.commit();
        session.close();
        return id;
    }

    /**
     * Deletes a given entity from the database.
     * @param entity The entity to remove.
     */
    public void delete(T entity) {
        Session session = getSession();
        Transaction transaction = session.beginTransaction();
        session.delete(entity);
        transaction.commit();
        session.close();
    }

    /**
     * Gets all entities from the database.
     * @return A list of all entities.
     */
    public List<T> getAll() {
        Session session = getSession();
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<T> query = builder.createQuery(type);
        Root<T> root = query.from(type);
        List<T> entities = session.createQuery(query).getResultList();
        session.close();
        return entities;
    }
}
