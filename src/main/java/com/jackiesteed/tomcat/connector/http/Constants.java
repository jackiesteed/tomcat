package com.jackiesteed.tomcat.connector.http;
import java.io.File;

public final class Constants {
  public static final String WEB_ROOT =
    System.getProperty("user.dir") + File.separator  + "webroot";
  public static final String Package = "com.jackiesteed.tomcat.connector.http";
  public static final int DEFAULT_CONNECTION_TIMEOUT = 60000;
  public static final int PROCESSOR_IDLE = 0;
  public static final int PROCESSOR_ACTIVE = 1;


  public static void main(String[] args){
    System.out.println(WEB_ROOT);
  }

}
