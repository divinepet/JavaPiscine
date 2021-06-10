package edu.school21.chat.repositories;

import edu.school21.chat.models.Message;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Optional;

public class MessagesRepositoryJdbcImpl implements MessagesRepository {
    private final Statement statement;

    public MessagesRepositoryJdbcImpl(Statement statement) {
        this.statement = statement;
    }

    @Override
    public Optional<Message> findById(Long id) throws SQLException {

        ResultSet rs = statement.executeQuery("SELECT * FROM messages where id = " + id);
        String author = "";
        String chatroom = "";
        while (rs.next()) {
            System.out.println("id=" + rs.getString("id") + ",");
            author = rs.getString("authorid");
            chatroom = rs.getString("chatroomID");
            System.out.println("text=\"" + rs.getString("text") + "\",");
            System.out.println("dateTime=" + rs.getString("dateTime") + ",");
        }
        if (author.equals("")) {
            System.out.println("Message not found");
            System.exit(1);
        }
        ResultSet authorrs = statement.executeQuery("SELECT * FROM users where id = " + author);

        while (authorrs.next()) {
            System.out.print("author={");
            System.out.print("id=" + authorrs.getString("id"));
            System.out.print(",login=" + authorrs.getString("login"));
            System.out.print(",password=" + authorrs.getString("password"));
            System.out.print(",createdrooms=" + authorrs.getString("createdrooms"));
            System.out.println(",socializedrooms=" + authorrs.getString("socializedrooms") + "},");
        }

        ResultSet chatroomrs = statement.executeQuery("SELECT * FROM chatrooms where id = " + chatroom);
        while (chatroomrs.next()) {
            System.out.print("chatroom={");
            System.out.print("id=" + chatroomrs.getString("id"));
            System.out.print(",name=" + chatroomrs.getString("name"));
            System.out.println(",creator=" + chatroomrs.getString("ownerID") + "}");
        }
        return Optional.empty();
    }
}
