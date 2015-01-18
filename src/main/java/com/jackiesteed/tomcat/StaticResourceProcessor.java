package com.jackiesteed.tomcat;

import java.io.IOException;

/**
 * @Created by  fangxue.zhang on 2014/12/21
 */
public class StaticResourceProcessor {

    public void process(Request request, Response response){
        try{
            response.sendStaticResource();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
