# quarkus-grpc-client-hangs-reproducer

This project shows the problem with Quarkus GRPC client on large messages.
Run tests to check if the "legacy" client is working fine and the Quarkus one hangs.
```shell script
./mvnw test
```
