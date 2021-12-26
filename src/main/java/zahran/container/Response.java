package zahran.container;

import java.io.OutputStream;
import java.io.PrintWriter;

public class Response {

    private OutputStream outputStream;
    private PrintWriter printWriter;

    public Response(OutputStream outputStream){
        this.outputStream = outputStream;
        this.printWriter = new PrintWriter(this.outputStream);
    }

    public OutputStream getOutputStream() {
        return outputStream;
    }

    public PrintWriter getPrintWriter() {
        return printWriter;
    }
}
