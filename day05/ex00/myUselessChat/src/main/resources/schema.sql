create table users (
                        id SERIAL PRIMARY KEY,
                        login varchar(20) unique not null,
                        password varchar(20) not null
);



create table chatrooms (
                        id serial primary key,
                        name varchar(20) not null,
                        ownerID bigint references users(id)
);



create table messages (
                        id serial primary key,
                        authorID bigint references users(id),
                        chatroomID bigint references chatrooms(id),
                        text text,
                        dateTime timestamp
);

create table createdrooms (
                        ownerID bigint references users(id),
                        chatroomID bigint references chatrooms(id)

);

create table socializedrooms (
                         ownerID bigint references users(id),
                         chatroomID bigint references chatrooms(id)
);

create table chatroommessages (
                        chatroomID bigint references chatrooms(id),
                        messageID bigint references messages(id)
);
