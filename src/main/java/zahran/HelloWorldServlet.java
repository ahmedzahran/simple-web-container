package zahran;

import zahran.container.HttpServlet;
import zahran.container.Request;
import zahran.container.Response;

import java.io.PrintWriter;

public class HelloWorldServlet extends HttpServlet {


    @Override
    public void init() {
        System.out.println("HelloWorldServlet init() method called");
    }

    @Override
    public void doGet(Request request, Response response) {

        PrintWriter out = response.getPrintWriter();

        out.println("<html><body>");
        out.println("doGet() method in HelloWorldServlet");
        out.println("</body></html>");
    }

    @Override
    public void destroy() {
        System.out.println("cleanup resources in HelloWorldServlet destroy()");
    }
}
