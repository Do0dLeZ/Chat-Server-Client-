package servlets;

import entity.msg.MessageList;
import entity.users.User;
import entity.users.Users;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;

public class GetMessagesServlet extends HttpServlet{

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String userLogin = req.getParameter("user");
        int indexRoom = Integer.parseInt(req.getParameter("indexRoom"));
        int indexPrivate = Integer.parseInt(req.getParameter("indexPrivate"));

        Users users = new Users();
        User user = users.getUser(userLogin);

        MessageList list = MessageList.getInstance();
        String json = list.getMsgListJson(user, indexRoom, indexPrivate);
        if (json != null) {
            OutputStream os = resp.getOutputStream();
            byte[] buf = json.getBytes(StandardCharsets.UTF_8);
            os.write(buf);
            os.flush();
        }
    }
}
