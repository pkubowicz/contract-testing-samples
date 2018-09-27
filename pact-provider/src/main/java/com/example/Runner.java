package com.example;

import ratpack.server.RatpackServer;

public class Runner {

    private RatpackServer server;

    public static void main(String[] args) throws Exception {
        new Runner().start(8080);
    }

    public void start(int port) throws Exception {
        server = RatpackServer.start(server -> server
                .serverConfig(c -> c.port(port))
                .handlers(chain -> chain.get("supplies", new SuppliesHandler()))
        );
    }

    public void stop() throws Exception {
        server.stop();
    }
}
