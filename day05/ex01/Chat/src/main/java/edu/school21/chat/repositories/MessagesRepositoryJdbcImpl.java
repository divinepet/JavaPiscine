package edu.school21.chat.repositories;

import com.zaxxer.hikari.HikariDataSource;
import edu.school21.chat.models.Chatroom;
import edu.school21.chat.models.Message;
import edu.school21.chat.models.User;
import org.springframework.jdbc.core.JdbcTemplate;

import java.sql.*;
import java.util.Optional;

import static java.lang.String.format;

public class MessagesRepositoryJdbcImpl extends JdbcTemplate implements MessagesRepository {
    public MessagesRepositoryJdbcImpl(HikariDataSource dataSource) {
        super.setDataSource(dataSource);
    }

    @Override
    public Optional<Message> findById(int id) {
        int userId = super.queryForObject("SELECT author FROM messages where id = " + id, Integer.class);
        int chatroomId = super.queryForObject("SELECT chatroom FROM messages where id = " + id, Integer.class);

        User author = super.queryForObject(format("SELECT * FROM users WHERE id = %d;", userId),
                (ResultSet rs, int rowNum) ->
                        new User(
                                rs.getInt("id"),
                                rs.getString("login"),
                                rs.getString("password"))
                );

        Chatroom chatroom = super.queryForObject(format("SELECT * FROM chatrooms WHERE id = %d;", chatroomId),
                (ResultSet rs, int rowNum) ->
                        new Chatroom(
                                rs.getInt("id"),
                                rs.getString("name"),
                                super.queryForObject(format("SELECT * FROM users WHERE id = %d;", rs.getInt("owner")),
                                (ResultSet rss, int rn) ->
                                        new User(
                                                rss.getInt("id"),
                                                rss.getString("login"),
                                                rss.getString("password"))
                                )
                        )
        );

        Message message = super.queryForObject(format("SELECT * FROM messages WHERE id = %d;", id),
                (ResultSet rs, int rowNum) ->
                        new Message(
                                rs.getInt("id"),
                                author,
                                chatroom,
                                rs.getString("text"),
                                rs.getDate("datetime")
                        )
        );
        return Optional.ofNullable(message);
    }
}
