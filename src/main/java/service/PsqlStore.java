package service;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import persistence.Task;
import persistence.User;

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
    public Collection<Task> findAllTask(User user) {
        return tx(session -> session.createQuery("from Task WHERE user_id = :user_id")
                .setParameter("user_id", user.getId())
                .list());
    }

    @Override
    public Collection<Task> findNotDoneTask(User user) {
        return tx(session -> session.createQuery("FROM Task WHERE done = :done AND user_id = :user_id")
                .setParameter("done", false)
                .setParameter("user_id", user.getId())
                .list());
    }

    @Override
    public void addTask(Task task, User user) {
        tx(session -> {
            task.setUser(user);
            return session.save(task);
        });
    }

    @Override
    public void updateStatusTask(int id) {
        tx(session -> {
            Task task = session.get(Task.class, id);
            task.setDone(true);
            return task;
        });
    }

    @Override
    public void saveUser(User user) {
        tx(session -> session.save(user));
    }

    @Override
    public User findUser(User user) {
        return tx(session -> {
            User resultUser = null;
            List<User> list = session.createQuery("FROM User WHERE login = :login AND password = :password")
                    .setParameter("login", user.getLogin())
                    .setParameter("password", user.getPassword())
                    .list();
            if (list.size() != 0) {
                resultUser = list.get(0);
            }
            return resultUser;
        });
    }
}
