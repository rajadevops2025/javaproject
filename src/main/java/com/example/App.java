package com.example;

import java.io.IOException;
import java.net.InetSocketAddress;

import com.sun.net.httpserver.HttpServer;

public class App {
    public static void main(String[] args) throws IOException {
        // Create an HTTP server listening on port 9090
        HttpServer server = HttpServer.create(new InetSocketAddress(9090), 0);
        server.createContext("/", exchange -> {
            String response = "Hello, World!";
            exchange.sendResponseHeaders(200, response.getBytes().length);
            exchange.getResponseBody().write(response.getBytes());
            exchange.close();
        });
        System.out.println("Server started on port 9090");
        server.start();
    }
}
