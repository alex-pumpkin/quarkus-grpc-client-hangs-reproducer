package reproducer;

import hello.Greeter;
import hello.Hello;
import io.quarkus.grpc.GrpcService;
import io.smallrye.mutiny.Uni;

@GrpcService
public class GreeterImpl implements Greeter {
    @Override
    public Uni<Hello.HelloReply> sayHello(Hello.HelloRequest request) {
        return Uni.createFrom().item(Hello.HelloReply.newBuilder()
                .setMessage("Some text repeated multiple times.\n".repeat(10000))
                .build());
    }
}
