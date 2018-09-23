package com.example;

import ratpack.server.RatpackServer;

import java.util.Collections;

import static ratpack.jackson.Jackson.json;

public class Runner {

    private RatpackServer server;

    public static void main(String[] args) throws Exception {
        new Runner().start(8080);
    }

    public void start(int port) throws Exception {
        server = RatpackServer.start(server -> server
                .serverConfig(c -> c.port(port))
                .handlers(chain -> chain.get("supplies", ctx -> {
                    ctx.render(json(Collections.singletonList(new Supply(151))));
                }))
        );
    }

    public void stop() throws Exception {
        server.stop();
    }
}
