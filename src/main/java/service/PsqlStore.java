package service;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import persistance.Task;

import java.util.Collection;
import java.util.List;

public class PsqlStore implements Store {
    private final StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
            .configure().build();
    private final SessionFactory sf = new MetadataSources(registry)
            .buildMetadata().buildSessionFactory();

    private static final class Lazy {
        private static final Store INST = new PsqlStore();
    }

    public static Store instOf() {
        return Lazy.INST;
    }


    @Override
    public Collection<Task> findAllTask() {
        Session session = sf.openSession();
        session.beginTransaction();
        List<Task> rsl = session.createQuery("from " + Task.class.getSimpleName()).list();
        session.getTransaction().commit();
        session.close();
        return rsl;
    }

    @Override
    public Collection<Task> findNotDoneTask() {
        Session session = sf.openSession();
        session.beginTransaction();
        List<Task> rsl = session.createQuery("from " + Task.class.getSimpleName()
                + " where done = :done")
                .setParameter("done", false).list();
        session.getTransaction().commit();
        session.close();
        return rsl;
    }

    @Override
    public void addTask(Task task) {
        Session session = sf.openSession();
        session.beginTransaction();
        session.save(task);
        session.getTransaction().commit();
        session.close();
    }

    @Override
    public void deleteTask(Task task) {
        Session session = sf.openSession();
        session.beginTransaction();
        session.delete(task);
        session.getTransaction().commit();
        session.close();
    }
}
