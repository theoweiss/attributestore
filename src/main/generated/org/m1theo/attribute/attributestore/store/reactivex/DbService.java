/*
 * Copyright 2014 Red Hat, Inc.
 *
 * Red Hat licenses this file to you under the Apache License, version 2.0
 * (the "License"); you may not use this file except in compliance with the
 * License.  You may obtain a copy of the License at:
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.  See the
 * License for the specific language governing permissions and limitations
 * under the License.
 */

package org.m1theo.attribute.attributestore.store.reactivex;

import java.util.Map;
import io.reactivex.Observable;
import io.reactivex.Flowable;
import io.reactivex.Single;
import io.reactivex.Completable;
import io.reactivex.Maybe;
import io.vertx.reactivex.core.Vertx;
import io.vertx.core.AsyncResult;
import io.vertx.core.Handler;
import org.m1theo.attribute.attributestore.store.Entry;

/**
 *
 * <p/>
 * NOTE: This class has been automatically generated from the {@link org.m1theo.attribute.attributestore.store.DbService original} non RX-ified interface using Vert.x codegen.
 */

@io.vertx.lang.reactivex.RxGen(org.m1theo.attribute.attributestore.store.DbService.class)
public class DbService {

  @Override
  public String toString() {
    return delegate.toString();
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    DbService that = (DbService) o;
    return delegate.equals(that.delegate);
  }
  
  @Override
  public int hashCode() {
    return delegate.hashCode();
  }

  public static final io.vertx.lang.reactivex.TypeArg<DbService> __TYPE_ARG = new io.vertx.lang.reactivex.TypeArg<>(
    obj -> new DbService((org.m1theo.attribute.attributestore.store.DbService) obj),
    DbService::getDelegate
  );

  private final org.m1theo.attribute.attributestore.store.DbService delegate;
  
  public DbService(org.m1theo.attribute.attributestore.store.DbService delegate) {
    this.delegate = delegate;
  }

  public org.m1theo.attribute.attributestore.store.DbService getDelegate() {
    return delegate;
  }

  public static DbService getInstance(Vertx vertx) { 
    DbService ret = DbService.newInstance(org.m1theo.attribute.attributestore.store.DbService.getInstance(vertx.getDelegate()));
    return ret;
  }

  public void upsert(Entry entry, Handler<AsyncResult<Entry>> handler) { 
    delegate.upsert(entry, handler);
  }

  public Single<Entry> rxUpsert(Entry entry) { 
    return new io.vertx.reactivex.core.impl.AsyncResultSingle<Entry>(handler -> {
      upsert(entry, handler);
    });
  }

  public void read(String name, Handler<AsyncResult<Entry>> handler) { 
    delegate.read(name, handler);
  }

  public Single<Entry> rxRead(String name) { 
    return new io.vertx.reactivex.core.impl.AsyncResultSingle<Entry>(handler -> {
      read(name, handler);
    });
  }

  public void delete(String name, Handler<AsyncResult<Void>> handler) { 
    delegate.delete(name, handler);
  }

  public Completable rxDelete(String name) { 
    return new io.vertx.reactivex.core.impl.AsyncResultCompletable(handler -> {
      delete(name, handler);
    });
  }


  public static  DbService newInstance(org.m1theo.attribute.attributestore.store.DbService arg) {
    return arg != null ? new DbService(arg) : null;
  }
}
