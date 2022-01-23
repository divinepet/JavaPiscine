package edu.school21.chat.app;

import com.zaxxer.hikari.HikariDataSource;
import edu.school21.chat.models.Message;
import edu.school21.chat.repositories.MessagesRepository;
import edu.school21.chat.repositories.MessagesRepositoryJdbcImpl;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.SQLException;
import java.sql.Statement;
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
        Optional<Message> messageOptional = messagesRepository.findById(1);
        if (messageOptional.isPresent()) {
            Message message = messageOptional.get();
            message.setText("Bye");
            message.setDate(null);
            messagesRepository.update(message);
        }
    }
}
