interface UsersList {
    void add(User obj);
    User getById(int id) throws UserNotFoundException;
    User getByIndex(int index);
    int getNumberOfUsers();

}
