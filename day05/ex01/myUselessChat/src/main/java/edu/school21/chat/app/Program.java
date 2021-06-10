package edu.school21.chat.app;

import edu.school21.chat.repositories.*;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class Program {
    public static void main(String[] args) throws SQLException {
        StartDatabase conn = new StartDatabase
                ("jdbc:postgresql://localhost:5432/postgres", "elaronda", "1111");
        Statement statement = conn.startDB();
        MessagesRepository someMessage = new MessagesRepositoryJdbcImpl(statement);
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter a message ID");
        someMessage.findById((long)sc.nextInt());
        }
}
