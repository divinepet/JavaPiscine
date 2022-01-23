create table if not exists users (
                        id SERIAL PRIMARY KEY,
                        login varchar(20) unique not null,
                        password varchar(20) not null
);

create table if not exists chatrooms (
                        id serial primary key,
                        name varchar(20) not null,
                        owner bigint references users(id)
);

create table if not exists messages (
                        id serial primary key,
                        author bigint references users(id),
                        chatroom bigint references chatrooms(id),
                        text text,
                        datetime timestamp
);