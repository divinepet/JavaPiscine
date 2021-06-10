create table if not exists users (
                        id SERIAL PRIMARY KEY,
                        login varchar(20) unique not null,
                        password varchar(20) not null,
                        createdrooms varchar(20)[],
                        socializedrooms varchar(20)[]
);

create table if not exists chatrooms (
                        id serial primary key,
                        name varchar(20) not null,
                        ownerID bigint
);



create table if not exists messages (
                        id serial primary key,
                        authorID bigint,
                        chatroomID bigint references chatrooms(id),
                        text text,
                        dateTime timestamp
);

create table if not exists createdrooms (
                        ownerID bigint references users(id),
                        chatroomID bigint references chatrooms(id)

);

create table if not exists socializedrooms (
                         ownerID bigint references users(id),
                         chatroomID bigint references chatrooms(id)
);

create table if not exists chatroommessages (
                        chatroomID bigint references chatrooms(id),
                        messageID bigint references messages(id)
);
