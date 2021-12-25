package zahran.container;

public abstract class HttpServlet {

    public void init(){
        System.out.println("HttpServlet init default");
    }

    public void service(){ //Todo request , response objects
    }
    public void doGet(){
        System.out.println("HttpServlet doGet Default");

    }
    public void doPost(){
        System.out.println("HttpServlet doPost Default");

    }
}
