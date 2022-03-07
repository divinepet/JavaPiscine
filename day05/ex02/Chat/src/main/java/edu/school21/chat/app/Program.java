package edu.school21.chat.app;

import com.zaxxer.hikari.HikariDataSource;
import edu.school21.chat.models.Chatroom;
import edu.school21.chat.models.Message;
import edu.school21.chat.models.User;
import edu.school21.chat.repositories.MessagesRepository;
import edu.school21.chat.repositories.MessagesRepositoryJdbcImpl;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;

public class Program {
    public static HikariDataSource dataSource() throws SQLException, IOException {
        HikariDataSource dataSource = new HikariDataSource();
        dataSource.setJdbcUrl("");
        dataSource.setUsername("");
        dataSource.setPassword("");
        dataSource.setDriverClassName("org.postgresql.Driver");
        Statement st = dataSource.getConnection().createStatement();
        st.execute(new String(Files.readAllBytes(Paths.get("./src/main/resources/schema.sql"))));
//        st.execute(new String(Files.readAllBytes(Paths.get("./src/main/resources/data.sql"))));
        return dataSource;
    }

    public static void main(String[] args) throws SQLException, IOException {
        User creator = new User(2, "user", "user", new ArrayList(), new ArrayList());
        User author = creator;

        Chatroom room = new Chatroom(3, "room", creator, new ArrayList());
        Message message = new Message(0, author, room, "Hello!", new Date());
        MessagesRepository messagesRepository = new MessagesRepositoryJdbcImpl(dataSource());
        messagesRepository.save(message);
        System.out.println(message.getId());
    }
}
