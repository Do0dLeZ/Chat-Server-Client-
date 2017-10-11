package servlets;

import entity.users.User;
import entity.users.Users;
import parser.UsersParserGSON;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;

public class LoginServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String login = req.getParameter("login");
        String password = req.getParameter("password");

        Users users = new Users();
        User user = users.getUser(login, password);
        user.setOnlineStatus("online");
        users.writeUsers();
        if (user.getPrivacyStatus().equals("Not authorized")) {
            resp.setStatus(401);
        } else {
            OutputStream outputStream = resp.getOutputStream();
            byte[] buf = UsersParserGSON.toJson(user).getBytes(StandardCharsets.UTF_8);
            outputStream.write(buf);
            outputStream.flush();
        }
    }
}
