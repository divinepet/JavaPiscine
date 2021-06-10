package edu.school21.chat.models;

import java.util.Objects;

public class Chatroom {
    private Long id;
    private String name;
    private Long ownerID;

    Chatroom (String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Chatroom chatroom = (Chatroom) o;
        return id == chatroom.id && ownerID == chatroom.ownerID && Objects.equals(name, chatroom.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, ownerID);
    }

    @Override
    public String toString() {
        return "Chatroom{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", ownerID=" + ownerID +
                '}';
    }
}
