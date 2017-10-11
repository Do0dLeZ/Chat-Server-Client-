package servlets;

import entity.users.Users;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;

public class LogoutServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String login = req.getParameter("login");

        Users users = new Users();
        users.getUser(login).setOnlineStatus("offline");
        users.writeUsers();

        OutputStream outputStream = resp.getOutputStream();
        byte[] buf = "Disconnected".getBytes(StandardCharsets.UTF_8);
        outputStream.write(buf);
        outputStream.flush();
    }
}
