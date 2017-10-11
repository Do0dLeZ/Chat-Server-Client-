package entity.users;

import parser.IUserParser;
import parser.UsersParserGSON;

import java.io.File;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;

public class Users implements Serializable {
    private static final String FILE_PATH = "D:\\Programming\\SpringLessons\\MyChatServer\\src\\users.json";
    private HashMap<String, User> users = new HashMap<>();

    public Users() {
        IUserParser parser = new UsersParserGSON();
        ArrayList<User> userArrayList = parser.getUsers(new File(FILE_PATH));
        for (User user : userArrayList) {
            this.users.put(user.getLogin(), user);
        }
    }

    public ArrayList<User> getUsers() {
        return (ArrayList<User>) this.users.values();
    }

    public User getUser(String login, String password) {
        User user = this.users.get(login);
        if (user == null || !user.getPassword().equals(password)) {
            user = new User();
            user.setPrivacyStatus("Not authorized");
        }
        return user;
    }

    public User getUser(String userLogin) {
        return users.get(userLogin);
    }

    public synchronized void writeUsers() {
        IUserParser parser = new UsersParserGSON();
        parser.writeUsers(new ArrayList<>(this.users.values()), new File(FILE_PATH));
    }

    public synchronized String getUsersByStatus(String status) {
        StringBuilder result = new StringBuilder("Online status of users:\n");
        for (HashMap.Entry<String, User> item : users.entrySet()) {
            if (status.equals("all"))
                result.append(item.getValue().getLogin())
                        .append(" - is ")
                        .append(item.getValue().getOnlineStatus())
                        .append("\n");
            else if (item.getValue().getOnlineStatus().equals(status)) {
                result.append(item.getValue().getLogin())
                        .append(" - is ")
                        .append(status)
                        .append("\n");
            }
        }
        return result.toString();
    }
}
