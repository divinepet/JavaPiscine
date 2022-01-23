package edu.school21.chat.models;

import java.util.List;
import java.util.Objects;

public class Chatroom {
    private final int id;
    private final String name;
    private final User owner;
    private List<Message> messageList;

    public Chatroom (int id, String name, User owner) {
        this.id = id;
        this.name = name;
        this.owner = owner;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public User getOwner() {
        return owner;
    }

    public List<Message> getMessageList() {
        return messageList;
    }

    public Chatroom(int id, String name, User owner, List<Message> messageList) {
        this.id = id;
        this.name = name;
        this.owner = owner;
        this.messageList = messageList;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Chatroom chatroom = (Chatroom) o;
        return id == chatroom.id && owner == chatroom.owner && Objects.equals(name, chatroom.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, owner);
    }

    @Override
    public String toString() {
        return "{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", owner=" + owner +
                '}';
    }
}
