package zahran;

import zahran.container.HttpServlet;
import zahran.container.Request;
import zahran.container.Response;

import java.io.PrintWriter;

public class SignUpServlet extends HttpServlet {

    @Override
    public void init() {
        System.out.println("SignUpServlet init() method called");
    }

    @Override
    public void doGet(Request request, Response response) {
        PrintWriter out = response.getPrintWriter();

        out.println("<html><body>");
        out.println("<form method=\"post\">");
        out.println("First Name: <input type=\"text\" id=\"fname\" name=\"fname\" value=\"ahmed\"><br>");
        out.println("Last Name: <input type=\"text\" id=\"lname\" name=\"lname\" value=\"zahran\"><br><br>");
        out.println("<input type=\"submit\" value=\"Submit\">");
        out.println("</body></html>");
    }

    @Override
    public void doPost(Request request, Response response) {
        PrintWriter out = response.getPrintWriter();

        out.println("<html><body>");
        out.println("FirstName " + request.getRequestParam("fname"));
        out.println("<br>");
        out.println("LastName " + request.getRequestParam("lname"));
        out.println("<br>");
        out.println("doPost() method in SignUpServlet");
        out.println("</body></html>");
    }

    @Override
    public void destroy() {
        System.out.println("close connection in SignUpServlet destroy() method");
    }
}
