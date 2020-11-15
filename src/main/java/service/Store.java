package service;

import persistance.Task;

import java.util.Collection;

public interface Store {
    Collection<Task> findAllTask();

    Collection<Task> findNotDoneTask();

    void addTask(Task task);

    void deleteTask(Task task);
}
