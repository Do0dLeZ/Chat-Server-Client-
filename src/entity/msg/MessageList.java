package entity.msg;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import entity.rooms.ChatRoom;
import entity.rooms.ChatRooms;
import entity.users.User;

import java.util.ArrayList;
import java.util.HashMap;

public class MessageList {
    private static MessageList instance;

    private static HashMap<String, ArrayList<Message>> messages = new HashMap<>();
    private static HashMap<String, ArrayList<Message>> privateMsg = new HashMap<>();

    private MessageList() {
        for (ChatRoom room : ChatRooms.getRooms()) {
            messages.put(room.getName(), new ArrayList<>());
        }
    }

    public static synchronized MessageList getInstance(){
        if (instance == null)
            return instance = new MessageList();
        else return instance;
    }

    public void addPrivateMsg(String userLogin, Message msg){
        privateMsg.computeIfAbsent(userLogin, k -> new ArrayList<>());
        privateMsg.get(userLogin).add(msg);
    }

    public void addMsg(String chatRoom, Message msg){
        messages.get(chatRoom).add(msg);
    }

    public synchronized String getMsgListJson(User user, int nRoom, int nPrivate) {
        ArrayList<Message> allMsg = new ArrayList<>();
        ArrayList<Message> temp;

        temp = getArrMsg(messages, user.getChatRoom().getName(), nPrivate);
        int indexRoom = temp.size();
        allMsg.addAll(temp);

        temp = getArrMsg(privateMsg ,user.getLogin(), nRoom);
        int indexPrivate = temp.size();
        allMsg.addAll(temp);

        JsonMsgList jsonMsgList = new JsonMsgList(allMsg, indexRoom, indexPrivate);

        Gson gson = new GsonBuilder().create();
        return gson.toJson(jsonMsgList);
    }

    private synchronized ArrayList<Message> getArrMsg(HashMap<String, ArrayList<Message>> map, String key, int index){
        ArrayList<Message> temp = map.get(key);
        ArrayList<Message> buffer = new ArrayList<>();
        if (temp != null && temp.size() > index) {
            for (int i = index; i < temp.size(); i++) {
                buffer.add(temp.get(i));
            }
        }
        return buffer;
    }
}
