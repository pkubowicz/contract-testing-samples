package com.example;

import ratpack.server.RatpackServer;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;

import static ratpack.jackson.Jackson.json;

public class Runner {
    private RatpackServer server;

    public static void main(String[] args) throws Exception {
        new Runner().start(8080);
    }

    public void start(int port) throws Exception {
        List<Supply> supplies = Collections.singletonList(new Supply(151, 10, true));

        server = RatpackServer.start(server -> server
                .serverConfig(c -> c.port(port))
                .handlers(chain -> chain
                        .get("supplies", ctx -> {
                            ctx.getResponse().contentType("application/json;charset=utf-8");
                            ctx.render(json(supplies));
                        })
                )
        );
    }

    public void stop() throws Exception {
        server.stop();
    }
}
