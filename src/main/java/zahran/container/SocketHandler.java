package zahran.container;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.time.LocalDateTime;
import java.util.Map;

public class SocketHandler extends  Thread{

    private Socket socket;
    private Map<String,HttpServlet> handlers;

    public SocketHandler(Socket socket, Map<String,HttpServlet> handlers){
        this.socket = socket;
        this.handlers = handlers;
    }

    @Override
    public void run() {
        BufferedReader inputReader = null;
        try {

            inputReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            Request request = new Request(inputReader);

            if (! request.parse()){
                PrintWriter printWriter = new PrintWriter(socket.getOutputStream());

                printWriter.println("Http/1.1 500 Internal Server Error");
                printWriter.println("Content-type: text/plain");
                printWriter.println();
                printWriter.println("<html><body> Cannot process your request </html></body>");
                printWriter.flush();
            }else{

                HttpServlet servlet = handlers.get(request.getPath());

                if (servlet == null) {
                    PrintWriter printWriter = new PrintWriter(socket.getOutputStream());
                    printWriter.println("Http/1.1 404 Not Found");
                    printWriter.println("Content-type: text/html");
                    printWriter.println();
                    printWriter.println("<html><body> No Servlet found </body></html>");
                    printWriter.flush();
                }else{
                    Response response = new Response(socket.getOutputStream());
                    PrintWriter printWriter = response.getPrintWriter();
                    printWriter.println("Http/1.1 200 OK");
                    printWriter.println("Content-type: text/html");
                    printWriter.println();
                    servlet.service(request,response);
                    printWriter.flush();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            try {
                socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }
}
