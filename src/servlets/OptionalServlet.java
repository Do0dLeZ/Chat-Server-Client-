package servlets;

import entity.rooms.ChatRoom;
import entity.rooms.ChatRooms;
import entity.users.User;
import entity.users.Users;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;

public class OptionalServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String room = req.getParameter("room");
        String login = req.getParameter("login");
        String usersStatus = req.getParameter("usersStatus");
        String showRooms = req.getParameter("all");

        String result = "";

        if (room != null && !room.isEmpty()) {
            result = changeRoom(room, login);
        } else if (usersStatus != null && !usersStatus.isEmpty()) {
            result = showUsers(usersStatus);
        } else if (showRooms != null && !showRooms.isEmpty()) {
            if (showRooms.equals("true"))
                result = showChatRooms();
        }

        OutputStream outputStream = resp.getOutputStream();
        byte[] buf = result.getBytes(StandardCharsets.UTF_8);
        outputStream.write(buf);
        outputStream.flush();
    }

    private String showChatRooms() {
        return ChatRooms.getRooms().toString();
    }

    private String showUsers(String usersStatus) {
        Users users = new Users();
        return users.getUsersByStatus(usersStatus);
    }

    private String changeRoom(String room, String login) {
        Users users = new Users();
        User user = users.getUser(login);
        user.setChatRoom(ChatRooms.getRoom(room));
        users.writeUsers();
        return "The room you selected: " + user.getChatRoom().getName();
    }
}
