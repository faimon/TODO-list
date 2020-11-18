CREATE TABLE tasks
(
    id          serial primary key,
    description TEXT      not null,
    created     TIMESTAMP not null,
    done        boolean   not null,
    user_id     int references users (id)
);

CREATE TABLE users
(
    id       serial primary key,
    login    TEXT not null UNIQUE,
    password TEXT not null
);