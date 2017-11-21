package org.m1theo.attribute.attributestore.store;

import io.vertx.codegen.annotations.DataObject;
import io.vertx.core.json.JsonObject;

import java.time.Instant;

/**
 * @author theo@m1theo.org
 */
@DataObject(generateConverter = true)
public class Entry {
  private String name;
  private String path;
  private Instant time;

  public Entry(String name, String path, Instant time) {
    this.name = name;
    this.path = path;
    this.time = time;
  }

  public Entry(JsonObject json) {
    this();
    EntryConverter.fromJson(json, this);
  }

  public Entry() {
  }

  public JsonObject toJson(){
    JsonObject json = new JsonObject();
    EntryConverter.toJson(this, json);
    return json;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getPath() {
    return path;
  }

  public void setPath(String path) {
    this.path = path;
  }

  public Instant getTime() {
    return time;
  }

  public void setTime(Instant time) {
    this.time = time;
  }
}
