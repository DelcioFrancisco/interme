CREATE TABLE users (
    id text primary key unique not null,
    login text not null unique,
    password text not null,
    role text not null
);

INSERT INTO users (id, login, password, role)
VALUES ('user_001', 'devchicala', '123456789', 'ADMIN');
