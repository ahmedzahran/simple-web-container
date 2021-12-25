package zahran;

import zahran.container.HttpServlet;

public class HelloWorldServlet extends HttpServlet {


    @Override
    public void doGet() {
        System.out.println("helloWorldServlet doGet");
    }
}
