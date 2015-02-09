package com.jackiesteed.tomcat;


import com.jackiesteed.tomcat.connector.http.HttpRequest;
import com.jackiesteed.tomcat.connector.http.HttpResponse;

import java.io.IOException;

public class StaticResourceProcessor {

  public void process(HttpRequest request, HttpResponse response) {
    try {
      response.sendStaticResource();
    }
    catch (IOException e) {
      e.printStackTrace();
    }
  }

}
