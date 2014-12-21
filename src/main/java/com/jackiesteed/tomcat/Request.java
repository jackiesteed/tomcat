package com.jackiesteed.tomcat;

import java.io.IOException;
import java.io.InputStream;

/**
 * @Created by  fangxue.zhang on 2014/12/20
 */
public class Request {

    private InputStream inputStream;
    private static final int BUFFER_SIZE = 2048;
    private String uri;

    public Request(InputStream inputStream){
        this.inputStream = inputStream;
    }

    public void parse(){

        StringBuffer request = new StringBuffer(BUFFER_SIZE);
        int i;
        byte[] buffer = new byte[BUFFER_SIZE];

        try {
            i = inputStream.read(buffer);
        } catch (IOException e) {
            e.printStackTrace();
            i = -1;
        }

        for(int j = 0; j < i; j++){
            request.append((char)buffer[j]);
        }
        System.out.println(request.toString());
        uri = parseUri(request.toString());
    }

    private String parseUri(String requestString){
        int index1, index2;
        index1 = requestString.indexOf(' ');
        if(index1 != -1){
            index2 = requestString.indexOf(' ', index1 + 1);
            if(index2 > index1){
                return requestString.substring(index1 + 1, index2);
            }
        }
        return null;
    }

    public String getUri(){
        return uri;
    }


}
