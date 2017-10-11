package parser;

import entity.users.User;
import entity.users.Users;

import java.io.File;
import java.util.ArrayList;

public interface IUserParser {
    ArrayList<User> getUsers(File file);
    void writeUsers(ArrayList<User> users, File file);
}
