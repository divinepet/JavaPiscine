package edu.school21.chat.repositories;

import com.zaxxer.hikari.HikariDataSource;
import edu.school21.chat.exceptions.NotSavedSubEntityException;
import edu.school21.chat.models.Chatroom;
import edu.school21.chat.models.Message;
import edu.school21.chat.models.User;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
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

    @Override
    public void save(Message message) {
        String insertSql = "insert into messages (author, chatroom, text, datetime)"
                + " values (?,?,?,?);";
        KeyHolder keyHolder = new GeneratedKeyHolder();
        try {
            super.update(connection -> {
                PreparedStatement ps = connection.prepareStatement(insertSql, new String[] { "id" });
                ps.setInt(1, message.getAuthor().getId());
                ps.setInt(2, message.getChatroom().getId());
                ps.setString(3, message.getText());
                ps.setTimestamp(4, new Timestamp(message.getDate().getTime()));
                return ps;
            }, keyHolder);
        } catch (DataAccessException e) {
            throw new NotSavedSubEntityException();
        }
        message.setId(keyHolder.getKey().intValue() - 1);
    }

    @Override
    public void update(Message message) {
        super.update(format("UPDATE messages " +
                "SET text = '%s' " +
                "WHERE id = %d;", message.getText(), message.getId()));
    }
}
