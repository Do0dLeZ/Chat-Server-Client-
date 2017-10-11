package parser;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import entity.users.User;

import java.io.*;
import java.lang.reflect.Type;
import java.util.ArrayList;

public class UsersParserGSON implements IUserParser {

    private static Gson gson = new GsonBuilder().create();

    @Override
    public ArrayList<User> getUsers(File file) {
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(file))) {
            Type usersListType = new TypeToken<ArrayList<User>>(){}.getType();
            return gson.fromJson(bufferedReader, usersListType);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void writeUsers(ArrayList<User> users, File file) {
        try (BufferedWriter write = new BufferedWriter(new FileWriter(file))) {
            gson.toJson(users, write);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String toJson(User user){
        return gson.toJson(user);
    }
}
