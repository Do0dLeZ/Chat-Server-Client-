package entity.msg;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import entity.rooms.ChatRoom;
import entity.users.User;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Message {
    private String msgTxt;
    private User from;
    private String to;
    private ChatRoom chatRoom;
    private Date date = new Date();

    public static final SimpleDateFormat SIMPLE_DATE_FORMAT = new SimpleDateFormat("dd.MM HH:mm");

    public Message() {
    }

    @Override
    public String toString() {
        StringBuffer buffer = new StringBuffer("");

        buffer  .append(SIMPLE_DATE_FORMAT.format(date))
                .append(" ")
                .append(from != null ? " from " + from.getLogin() : "")
                .append(chatRoom != null && chatRoom.getId() != 0 ? chatRoom.getName() : "")
                .append(":")
                .append(msgTxt);

        return buffer.toString();
    }

    public String toJSON() {
        Gson gson = new GsonBuilder().create();
        return gson.toJson(this);
    }

    public static Message fromJSON(String s) {
        Gson gson = new GsonBuilder().create();
        return gson.fromJson(s, Message.class);
    }

    public String getMsgTxt() {
        return msgTxt;
    }

    public void setMsgTxt(String msgTxt) {
        this.msgTxt = msgTxt;
    }

    public User getFrom() {
        return from;
    }

    public void setFrom(User from) {
        this.from = from;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public ChatRoom getChatRoom() {
        return chatRoom;
    }

    public void setChatRoom(ChatRoom chatRoom) {
        this.chatRoom = chatRoom;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
