package edu.school21.chat.models;

import java.util.Date;
import java.util.Objects;

public class Message {
    private int id;
    private final User author;
    private final Chatroom chatroom;
    private String text;
    private Date date;

    public Message(int id, User author, Chatroom chatroom, String text, Date date) {
        this.id = id;
        this.author = author;
        this.chatroom = chatroom;
        this.text = text;
        this.date = date;
    }

    public void setText(String text) {
        this.text = text;
    }

    public void setDate(Date date) {
        this.date = date;
    }


    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public User getAuthor() {
        return author;
    }

    public Chatroom getChatroom() {
        return chatroom;
    }

    public String getText() {
        return text;
    }

    public Date getDate() {
        return date;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Message message = (Message) o;
        return id == message.id && author == message.author && chatroom == message.chatroom && Objects.equals(text, message.text) && Objects.equals(date, message.date);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, author, chatroom, text, date);
    }

    @Override
    public String
    toString() {
        return "Message {" +
                "\n id=" + id +
                ",\n author=" + author +
                ",\n chatroom=" + chatroom +
                ",\n text='" + text + '\'' +
                ",\n date=" + date +
                "\n}";
    }
}

