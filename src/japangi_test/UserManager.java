package japangi_test;

class UserManager {
    private User[] users = new User[100];
    private int count = 0;

    public void addUser(String id, int stamps) {
        users[count++] = new User(id, stamps);
    }

    public User findUser(String id) {
        for (int i = 0; i < count; i++) {
            if (users[i].id.equals(id)) return users[i];
        }
        return null;
    }
}