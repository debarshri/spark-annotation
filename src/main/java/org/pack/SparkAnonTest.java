package org.pack;

import spark.Request;
import spark.Response;
import spark.Route;

@SparkUrl(
        path = "/",
        method = "GET")
public class SparkAnonTest implements Route {

    @Override
    public Object handle(Request request, Response response) {
        return request.params("you");
    }
}
