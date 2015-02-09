package com.jackiesteed.tomcat.startup;


import com.jackiesteed.tomcat.connector.http.HttpConnector;


/**
 * the start point of the framework
 */

public final class Bootstrap {
  public static void main(String[] args) {
    HttpConnector connector = new HttpConnector();
    connector.start();
  }
}