package service;

import persistence.Task;
import persistence.User;

import java.util.Collection;

public interface Store {
    Collection<Task> findAllTask(User user);

    Collection<Task> findNotDoneTask(User user);

    void addTask(Task task, User user);

    void updateStatusTask(int id);

    void saveUser(User user);

    User findUser(User user);
}
