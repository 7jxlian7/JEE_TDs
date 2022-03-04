package fr.iut;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class HelloServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response)
            throws ServletException, IOException {
        PrintWriter out = response.getWriter();
        out.println( "<HTML>" );
        out.println( "<HEAD>");
        out.println( "<TITLE>Mon super site !</TITLE>" );
        out.println( "</HEAD>" );
        out.println( "<BODY>" );
        out.println( "<H1>Hello !</H1>" );
        out.println( "<p>Bienvenue sur ma <span class=\"red\">page</span> !</p>" );
        out.println( "<style>" +
                    ".red { color: red; }" +
                    " body { background: url(https://www.zooplus.fr/magazine/wp-content/uploads/2019/06/arriv%C3%A9e-dun-chaton-%C3%A0-la-maison.jpeg); }" +
                    "</style>" );
        out.println( "</BODY>" );
        out.println( "</HTML>" );
        out.close();
    }
}
