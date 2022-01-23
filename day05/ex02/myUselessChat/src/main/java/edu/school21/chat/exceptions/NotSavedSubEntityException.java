package edu.school21.chat.exceptions;

public class NotSavedSubEntityException extends RuntimeException {
    public NotSavedSubEntityException() {
        System.out.println("Data not saved");
    }
}
