package zahran.container;

public abstract class HttpServlet {

    public void init(){
        System.out.println("HttpServlet init default");
    }

    public void service(Request request,Response response){

        String method = request.getMethod();

        if ("GET".equals(method)){
            doGet(request,response);
        }else if ("POST".equals(method)){
            doPost(request,response);
        }else{
            throw new RuntimeException("method not supported");
        }

    }
    public void doGet(Request request,Response response){
        System.out.println("HttpServlet doGet Default");

    }
    public void doPost(Request request,Response response){
        System.out.println("HttpServlet doPost Default");

    }
}
