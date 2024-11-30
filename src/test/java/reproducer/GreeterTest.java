package reproducer;

import hello.Greeter;
import hello.Hello;
import io.quarkus.grpc.GrpcClient;
import io.quarkus.test.junit.QuarkusTest;
import io.smallrye.mutiny.TimeoutException;
import org.junit.jupiter.api.Test;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;

@QuarkusTest
class GreeterTest {

    @GrpcClient("legacy")
    Greeter legacyClient;

    @GrpcClient("quarkus")
    Greeter quarkusClient;

    @Test
    void test() {
        var helloRequest = Hello.HelloRequest.getDefaultInstance();

        var result = legacyClient.sayHello(helloRequest)
                .await().atMost(Duration.ofSeconds(1));

        assertFalse(result.getMessage().isEmpty());

        // test Quarkus client hangs
        assertThrows(TimeoutException.class, () -> quarkusClient.sayHello(helloRequest)
                .await().atMost(Duration.ofSeconds(10)));
    }
}
