package com.jackiesteed.tomcat;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;

/**
 * @Created by  fangxue.zhang on 2014/12/19
 */
public class HttpServer {

    public static final String WEB_ROOT =
            System.getProperty("user.dir")
            + File.separator
            + "webroot";

    private static final String SHUTDOWN_COMMAND = "/SHUTDOWN";

    private boolean shutdown = false;

    public  void await(){

        ServerSocket serverSocket = null;
        int port = 8080;

        try{
            serverSocket = new ServerSocket(port, 1, InetAddress.getByName("127.0.0.1"));
        } catch (UnknownHostException e) {
            e.printStackTrace();
            System.exit(1);
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(1);
        }

        while(!shutdown){
            Socket socket = null;
            InputStream inputStream = null;
            OutputStream outputStream = null;

            try{
                socket = serverSocket.accept();
                inputStream = socket.getInputStream();
                outputStream = socket.getOutputStream();

                Request request = new Request(inputStream);

                request.parse();

                Response response = new Response(outputStream);
                response.setRequest(request);

                System.out.println("uri:   " + request.getUri());

                if(request.getUri().startsWith("/servlet/")){

//                    System.out.println("uri:   " + request.getUri());

                    ServletProcessor servletProcessor = new ServletProcessor();
                    servletProcessor.process(request, response);

                }else{
                    StaticResourceProcessor staticResourceProcessor = new StaticResourceProcessor();
                    staticResourceProcessor.process(request, response);
                }
                socket.close();

                shutdown = request.getUri().equals(SHUTDOWN_COMMAND);

            } catch (IOException e) {
                e.printStackTrace();
                System.exit(1);
            }
        }

    }

    public static void main(String[] args){

        HttpServer httpServer = new HttpServer();

        httpServer.await();
    }
}
