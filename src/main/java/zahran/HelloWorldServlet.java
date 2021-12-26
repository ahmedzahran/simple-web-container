package zahran;

import zahran.container.HttpServlet;

public class HelloWorldServlet extends HttpServlet {


    @Override
    public void init() {
        System.out.println("HelloWorldServlet init() method called");
    }


    @Override
    public void doGet() {
        System.out.println("helloWorldServlet doGet");
    }
}
