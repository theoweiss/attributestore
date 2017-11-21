package org.m1theo.attribute.attributestore.store.impl;

import io.vertx.core.AsyncResult;
import io.vertx.core.Future;
import io.vertx.core.Handler;
import io.vertx.core.json.JsonObject;
import io.vertx.reactivex.core.Vertx;
import io.vertx.reactivex.ext.mongo.MongoClient;
import org.m1theo.attribute.attributestore.exceptions.NotFoundExecption;
import org.m1theo.attribute.attributestore.store.DbService;
import org.m1theo.attribute.attributestore.store.Entry;

/**
 * @author theo@m1theo.org
 */
public class DbServiceImpl implements DbService {

  private final MongoClient mongoClient;
  private final static String collection = "attributes";
  public DbServiceImpl(io.vertx.core.Vertx vertx) {
    JsonObject config = Vertx.currentContext().config();

    String uri = config.getString("mongo_uri");
    if (uri == null) {
      uri = "mongodb://localhost:27017";
    }
    String db = config.getString("mongo_db");
    if (db == null) {
      db = "domain1";
    }

    JsonObject mongoconfig = new JsonObject()
        .put("connection_string", uri)
        .put("db_name", db);

     mongoClient = MongoClient.createShared(Vertx.newInstance(vertx), mongoconfig);

  }

  @Override
  public void upsert(Entry entry, Handler<AsyncResult<Entry>> handler) {
    mongoClient.save(collection, entry.toJson().put("_id", entry.getName()), res ->{
      if (res.succeeded()){
        handler.handle(Future.succeededFuture());
      } else {
        handler.handle(Future.failedFuture(res.cause()));
      }
    });
  }

  @Override
  public void read(String name, Handler<AsyncResult<Entry>> handler) {
    mongoClient.find(collection, new JsonObject().put("_id", name),res ->{
      if (res.succeeded()){
        if (res.result().size() == 0){
          handler.handle(Future.failedFuture(new NotFoundExecption("not found")));
        } else {
          handler.handle(Future.succeededFuture(new Entry(res.result().get(0))));
        }
      } else {
        handler.handle(Future.failedFuture(res.cause()));
      }
    });
  }

  @Override
  public void delete(String name, Handler<AsyncResult<Void>> handler) {
    mongoClient.remove(collection, new JsonObject().put("_id", name), res -> {
      if (res.succeeded()){
        handler.handle(Future.succeededFuture());
      } else {
        handler.handle(Future.failedFuture(res.cause()));
      }
    });
  }
}
