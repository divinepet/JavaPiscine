package edu.school21.chat.app;

import com.zaxxer.hikari.HikariDataSource;
import edu.school21.chat.repositories.*;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class Program {
    public static HikariDataSource dataSource() throws SQLException, IOException {
        HikariDataSource dataSource = new HikariDataSource();
        dataSource.setJdbcUrl("jdbc:postgresql://ec2-52-48-137-75.eu-west-1.compute.amazonaws.com:5432/d1omoeskafutir");
        dataSource.setUsername("mstmeacrlarhsp");
        dataSource.setPassword("7c592bc3cd6943da24a2f18c1c836503d29b44708497da31b99dfcb3dc86b7ba");
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
        if (sc.hasNextInt())
            someMessage.findById(sc.nextInt());
    }
}
