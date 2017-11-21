# Attribute Server

```
curl -X PUT -d '{"name": "hans", "path": "/herbert"}' http://localhost:8090/entry
curl http://localhost:8090/entry/hans
curl -X PUT -d '{"name": "hans", "path": "/wehner"}' http://localhost:8090/entry
curl -X DELETE http://localhost:8090/entry/hans
```
