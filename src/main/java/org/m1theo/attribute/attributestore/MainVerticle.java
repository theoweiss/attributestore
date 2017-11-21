package org.m1theo.attribute.attributestore;

import io.vertx.core.AbstractVerticle;
import io.vertx.ext.web.Router;
import io.vertx.ext.web.handler.BodyHandler;
import org.m1theo.attribute.attributestore.store.DbService;
import org.m1theo.attribute.attributestore.store.Entry;

public class MainVerticle extends AbstractVerticle {
  private static final Integer port = 8090;
  private DbService dbService;

  @Override
  public void start() throws Exception {
    dbService = DbService.getInstance(vertx);
    Router router = Router.router(vertx);
    router.route().handler(BodyHandler.create());
    addRoutes(router);
    vertx.createHttpServer().requestHandler(router::accept).listen(port);
    System.out.println("HTTP server started on port " + port.toString());
  }

  private void addRoutes(Router router) {
    router.get("/entry/:name").handler(con -> {
      dbService.read(con.request().getParam("name"), res ->{
        if (res.succeeded()){
          con.response().end(res.result().toJson().encode());
        } else {
          con.response().setStatusCode(404).end("not found");
        }
      });
    });

    router.put("/entry").handler(con ->{
      con.getBodyAsJson();
      dbService.upsert(new Entry(con.getBodyAsJson()), res ->{
        if (res.succeeded()){
          con.response().end("succeeded");
        } else {
          con.response().setStatusCode(505).end(res.cause().getMessage());
        }
      });
    });

    router.delete("/entry/:name").handler(con -> {
      dbService.delete(con.request().getParam("name"), res -> {
        if (res.succeeded()){
          con.response().end("deleted");
        } else {
          con.response().setStatusCode(505).end(res.cause().getMessage());
        }
      });
    });
  }
}
