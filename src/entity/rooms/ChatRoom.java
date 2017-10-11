package entity.rooms;

public class ChatRoom {
    private Integer id;
    private String name;

    public ChatRoom() {
    }

    public ChatRoom(String name) {
        this.id = id;
        this.name = name;
    }

    @Override
    public String toString() {
        return "Chat room: " + name;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
