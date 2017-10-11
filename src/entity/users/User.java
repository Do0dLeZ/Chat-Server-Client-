package entity.users;

import entity.rooms.ChatRoom;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import java.util.ArrayList;

@XmlRootElement(name = "user")
public class User implements Serializable{
    private String login;
    private String password;
    private ChatRoom chatRoom;
    private String onlineStatus;
    private String privacyStatus = "";

    public String getLogin() {
        return login;
    }

    @XmlElement(name = "login")
    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    @XmlElement(name = "password")
    public void setPassword(String password) {
        this.password = password;
    }

    public ChatRoom getChatRoom() {
        return chatRoom;
    }

    public void setChatRoom(ChatRoom chatRoom) {
        this.chatRoom = chatRoom;
    }

    public String getOnlineStatus() {
        return onlineStatus;
    }

    @XmlElement(name = "onlineStatus")
    public void setOnlineStatus(String onlineStatus) {
        this.onlineStatus = onlineStatus;
    }

    public String getPrivacyStatus() {
        return privacyStatus;
    }

    @XmlElement(name = "privacyStatus")
    public void setPrivacyStatus(String privacyStatus) {
        this.privacyStatus = privacyStatus;
    }
}
