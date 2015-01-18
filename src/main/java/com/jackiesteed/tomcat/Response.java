package com.jackiesteed.tomcat;

import javax.servlet.ServletOutputStream;
import javax.servlet.ServletResponse;
import java.io.*;
import java.util.Locale;

/**
 * @Created by  fangxue.zhang on 2014/12/20
 */
class Response implements ServletResponse{
    private static final int BUFFER_SIZE = 1024;

    Request request;
    OutputStream outputStream;
    PrintWriter printWriter;

    public Response(OutputStream outputStream){
        this.outputStream = outputStream;
    }

    public void setRequest(Request request){
        this.request = request;
    }


    public void sendStaticResource() throws IOException {

        byte[] bytes = new byte[BUFFER_SIZE];

        FileInputStream fis = null;
        
        try{
            File file = new File(Constants.WEB_ROOT, request.getUri());

            fis = new FileInputStream(file);
            int ch = fis.read(bytes, 0, BUFFER_SIZE);
            while(ch != -1){
                outputStream.write(bytes);
                ch = fis.read(bytes, 0, BUFFER_SIZE);
            }

        } catch (FileNotFoundException e) {
            String errorMessage = "HTTP/1.1 404 File Not Found\r\n"
                    + "Content-Type: text/html\r\n"
                    + "Content-Length: 23\r\n"
                    + "\r\n"
                    +"<h1>File Not Found</h1>";
            outputStream.write(errorMessage.getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if(fis != null)
                fis.close();
        }

    }

    @Override
    public String getCharacterEncoding() {
        return null;
    }

    @Override
    public String getContentType() {
        return null;
    }

    @Override
    public ServletOutputStream getOutputStream() throws IOException {
        return null;
    }

    @Override
    public PrintWriter getWriter() throws IOException {
        printWriter = new PrintWriter(outputStream, true);
        return printWriter;
    }

    @Override
    public void setCharacterEncoding(String s) {

    }

    @Override
    public void setContentLength(int i) {

    }

    @Override
    public void setContentType(String s) {

    }

    @Override
    public void setBufferSize(int i) {

    }

    @Override
    public int getBufferSize() {
        return 0;
    }

    @Override
    public void flushBuffer() throws IOException {

    }

    @Override
    public void resetBuffer() {

    }

    @Override
    public boolean isCommitted() {
        return false;
    }

    @Override
    public void reset() {

    }

    @Override
    public void setLocale(Locale locale) {

    }

    @Override
    public Locale getLocale() {
        return null;
    }
}
