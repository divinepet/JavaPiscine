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
import java.util.Scanner;

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
        MessagesRepository someMessage = new MessagesRepositoryJdbcImpl(dataSource());
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter a message ID");
        Optional<Message> messageOptional;
        if (sc.hasNextInt()) {
            messageOptional = someMessage.findById(sc.nextInt());
            messageOptional.ifPresent(System.out::println);
        }
    }
}
