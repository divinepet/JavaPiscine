public class UsersArrayList implements UsersList {
    private User array[];

    UsersArrayList() {
        this.array = new User[10];
    }

    private void expandArray(User obj, int index) {
        User[] array_copy = new User[(int) (this.array.length * 1.5)];
        for (int i = 0; i < array.length; i++) {
            array_copy[i] = array[i];
        }
        this.array = array_copy;
        this.array[index] = obj;
    }

    public void add(User obj) {
        int i;
        for (i = 0; i < array.length - 1; i++) {
            if (array[i] == null) {
                array[i] = obj;
                return;
            }
        }
        expandArray(obj, i);
    }

    public User getById(int id) throws UserNotFoundException {
        for (int i = 0; i < array.length; i++) {
            if (array[i] != null && array[i].getId() == id)
                return array[i];
        }
        throw new UserNotFoundException(id);
    }

    public User getByIndex(int index) {
        return array[index];
    }

    public int getNumberOfUsers() {
        int i = 0;
        while (array[i] != null)
            i++;
        return i;
    }
}

class UserNotFoundException extends Exception {
    UserNotFoundException(int id)
    {
        System.out.println("User with ID " + id + " doesn't exist");
    }
}
