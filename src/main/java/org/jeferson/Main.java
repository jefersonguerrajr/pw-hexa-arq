package org.jeferson;

import org.jeferson.config.AppConfig;
import org.glassfish.grizzly.http.server.HttpServer;
import org.glassfish.jersey.grizzly2.httpserver.GrizzlyHttpServerFactory;

import java.net.URI;


public class Main {

    public static final String BASE_URI = "http://localhost:8080/api/";

    public static void main(String[] args) {

        final HttpServer server = GrizzlyHttpServerFactory.createHttpServer(URI.create(BASE_URI), new AppConfig());
        System.out.println("Url: " + BASE_URI);
        Runtime.getRuntime().addShutdownHook(new Thread(server::shutdownNow));

    }
}
