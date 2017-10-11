package entity.rooms;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;

public class ChatRooms {
    private static final String ROOMS_PATH = "D:\\Programming\\SpringLessons\\MyChatServer\\src\\rooms.json";
    private static ArrayList<ChatRoom> rooms;

    public static ArrayList<ChatRoom> getRooms() {
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(ROOMS_PATH))) {
            Gson gson = new GsonBuilder().create();
            Type roomsType = new TypeToken<ArrayList<ChatRoom>>(){}.getType();
            rooms = gson.fromJson(bufferedReader, roomsType);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return rooms;
    }

    public static ChatRoom getRoom(String name){
        for (ChatRoom chatRoom : getRooms()) {
            if (chatRoom.getName().equals(name))
                return chatRoom;
        }
        return new ChatRoom("***Not exit***");
    }
}
