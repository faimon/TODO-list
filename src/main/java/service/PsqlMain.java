package service;

import persistance.Task;

import java.sql.Timestamp;

public class PsqlMain {
    public static void main(String[] args) {
        //  PsqlStore.instOf().addTask(new Task(1, "Buy a bread", new Timestamp(System.currentTimeMillis()), true));
        //  System.out.println(PsqlStore.instOf().findAllTask());
        System.out.println(PsqlStore.instOf().findNotDoneTask());
    }
}
