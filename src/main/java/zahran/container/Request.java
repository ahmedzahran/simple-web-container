package zahran.container;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class Request {

    private BufferedReader inputReader;
    private String method;
    private String path;
    private Map<String,String> headers = new HashMap<>();
    private Map<String,String> requestParams = new HashMap<>();

    public Request(BufferedReader inputReader){

        this.inputReader = inputReader;
    }

    public String getMethod() {
        return method;
    }

    public String getPath() {
        return path;
    }

    public Map<String, String> getHeaders() {
        return headers;
    }

    public Map<String, String> getRequestParams() {
        return requestParams;
    }

    public String getRequestParam(String name){
        return requestParams.get(name);
    }
    //queryString: user=zahran&pwd=123
    private void parseRequestParams(String queryString){

        for(String pair: queryString.split("&")){

            String [] pairArr = pair.split("=");

            requestParams.put(pairArr[0],pairArr[1]);
        }
    }
    public boolean parse() throws IOException {

        String line = inputReader.readLine();//Get /hello?user=zahran&pwd=123 HTTP/1.1
                                            //Get /hello HTTP/1.1

        String [] firstLineArray = line.split(" ");

        if (firstLineArray.length != 3) return false;

        String method = firstLineArray[0];
        this.method = method;
        String url = firstLineArray[1];

        if (url.contains("?")){
            int queryStringIndex = url.indexOf("?");
            path = url.substring(0,queryStringIndex);
            parseRequestParams(url.substring(queryStringIndex+1));
        }else{
            path = url;
        }

        while (!line.isEmpty()){
            line = inputReader.readLine();//request headers
            if (!"".equals(line)) {
                String[] headerPair = line.split(":");
                headers.put(headerPair[0], headerPair[1]);
            }
        }

        if ("POST".equals(method)){
            StringBuilder body = new StringBuilder();

            while (inputReader.ready()){
                body.append((char)inputReader.read());
            }

            parseRequestParams(body.toString());

        }
        return  true;
    }
}
