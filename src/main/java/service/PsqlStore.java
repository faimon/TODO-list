package service;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import persistance.Task;

import java.util.Collection;
import java.util.List;
import java.util.function.Function;

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

    private <T> T tx(final Function<Session, T> command) {
        final Session session = sf.openSession();
        final Transaction tx = session.beginTransaction();
        try {
            T rsl = command.apply(session);
            tx.commit();
            return rsl;
        } catch (final Exception e) {
            session.getTransaction().rollback();
            throw e;
        } finally {
            session.close();
        }
    }

    @Override
    public Collection<Task> findAllTask() {
        return tx(session -> session.createQuery("from Task").list());
    }

    @Override
    public Collection<Task> findNotDoneTask() {
        return tx(session -> session.createQuery("from Task where done = :done")
                .setParameter("done", false).list());
    }

    @Override
    public void addTask(Task task) {
        tx(session -> session.save(task));
    }
}
