package org.m1theo.attribute.attributestore.store;

import io.vertx.codegen.annotations.VertxGen;
import io.vertx.core.AsyncResult;
import io.vertx.core.Handler;
import io.vertx.core.Vertx;
import org.m1theo.attribute.attributestore.store.impl.DbServiceImpl;

/**
 * @author theo@m1theo.org
 */

@VertxGen
public interface DbService {
  static DbService getInstance(Vertx vertx){
    return new DbServiceImpl(vertx);
  }
  void upsert(Entry entry, Handler<AsyncResult<Entry>> handler);
  void read(String name, Handler<AsyncResult<Entry>> handler);
  void delete(String name, Handler<AsyncResult<Void>> handler);
}
