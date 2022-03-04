package fr.iut;

import freemarker.template.DefaultObjectWrapper;
import freemarker.template.Template;
import freemarker.template.TemplateException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@WebServlet(name = "rooms", urlPatterns = {"/rooms"})
public class RoomServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response)
            throws ServletException, IOException {
        Template freemarkerTemplate = null;
        freemarker.template.Configuration freemarkerConfiguration =
                new freemarker.template.Configuration();
        freemarkerConfiguration.setClassForTemplateLoading(this.getClass(), "/");
        freemarkerConfiguration.setObjectWrapper(new DefaultObjectWrapper());
        try {
            freemarkerTemplate =
                    freemarkerConfiguration.getTemplate("templates/listRoom.ftl");
        } catch (IOException e) {
            System.out.println("Unable to process request, error during freemarker template retrieval.");
        }

        Map<String, Object> root = new HashMap<String, Object>();

        ArrayList<Room> fakeRooms = new ArrayList<>();

        fakeRooms.add(new Room("R1", 3, 10));
        fakeRooms.add(new Room("R2", 6, 10));
        fakeRooms.add(new Room("R3", 7, 10));

        // navigation data and links
        root.put("title", "List of FakeRooms");
        root.put("fakeRooms", fakeRooms);
        PrintWriter out = response.getWriter();
        assert freemarkerTemplate != null;
        try {
            freemarkerTemplate.process(root, out);
            out.close();}
        catch (TemplateException e) { e.printStackTrace(); }
        // set mime type
        response.setContentType("text/html");
    }
}
