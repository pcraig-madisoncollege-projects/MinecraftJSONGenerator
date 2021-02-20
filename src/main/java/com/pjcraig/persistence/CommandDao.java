package com.pjcraig.persistence;

import com.pjcraig.entity.Command;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

public class CommandDao {
    private final Logger logger = LogManager.getLogger(this.getClass());
    SessionFactory sessionFactory = SessionFactoryProvider.getSessionFactory();

    public Command getById(int id) {
        Session session = sessionFactory.openSession();
        Command command = session.get(Command.class, id);
        session.close();
        return command;
    }

    public void saveOrUpdate(Command command) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.saveOrUpdate(command);
        transaction.commit();
        session.close();
    }

    public int insert(Command command) {
        int id = 0;
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        id = (int) session.save(command);
        transaction.commit();
        session.close();
        return id;
    }

    public void delete(Command command) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.delete(command);
        transaction.commit();
        session.close();
    }

    public List<Command> getAll() {
        Session session = sessionFactory.openSession();
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<Command> query = builder.createQuery(Command.class);
        Root<Command> root = query.from(Command.class);
        List<Command> commands = session.createQuery(query).getResultList();
        session.close();
        return commands;
    }
}
