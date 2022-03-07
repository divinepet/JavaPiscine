package edu.school21.chat.app;

import com.zaxxer.hikari.HikariDataSource;
import edu.school21.chat.models.Message;
import edu.school21.chat.models.User;
import edu.school21.chat.repositories.MessagesRepository;
import edu.school21.chat.repositories.MessagesRepositoryJdbcImpl;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.Optional;

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
        MessagesRepository messagesRepository = new MessagesRepositoryJdbcImpl(dataSource());
        List<User> users = messagesRepository.findAll(2, 3);
        for (User s : users)
            System.out.println(s);
    }
}
