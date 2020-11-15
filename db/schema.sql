CREATE TABLE tasks
(
    id          serial primary key,
    description TEXT      not null,
    created     TIMESTAMP not null,
    done        boolean   not null
);