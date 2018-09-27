package com.example;

import ratpack.handling.Context;
import ratpack.handling.Handler;

import java.util.Collections;

import static ratpack.jackson.Jackson.json;

public class SuppliesHandler implements Handler {
    @Override
    public void handle(Context ctx) {
        ctx.getResponse().contentType("application/json;charset=utf-8");
        ctx.render(json(Collections.singletonList(new Supply(151, 10, true))));
    }
}
