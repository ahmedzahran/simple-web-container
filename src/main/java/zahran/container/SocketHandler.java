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
        PrintWriter printWriter = null;
        try {
            inputReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));

            String line = inputReader.readLine();

            while (! line.isEmpty()){
                System.out.println(line);
                line = inputReader.readLine();
            }

            printWriter = new PrintWriter(socket.getOutputStream());

            printWriter.println("Http/1.1 200 OK");
            printWriter.println("Content-type: text/html");
            printWriter.println();

            printWriter.println("<html><body> Current Time");
            printWriter.println(LocalDateTime.now());
            printWriter.println("</body></html>");
            printWriter.flush();

        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            try {
                printWriter.close();
                inputReader.close();
                socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }
}
