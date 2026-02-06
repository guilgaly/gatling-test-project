package example;

import io.gatling.javaapi.core.*;
import io.gatling.javaapi.http.*;

import java.time.Duration;

import static io.gatling.javaapi.core.CoreDsl.*;
import static io.gatling.javaapi.http.HttpDsl.*;

/**
 * This sample is based on our official tutorials:
 * <ul>
 *   <li><a href="https://gatling.io/docs/gatling/tutorials/quickstart">Gatling quickstart tutorial</a>
 *   <li><a href="https://gatling.io/docs/gatling/tutorials/advanced">Gatling advanced tutorial</a>
 * </ul>
 */
public class TestSimulation extends Simulation {
    private static final HttpProtocolBuilder httpProtocol = http.baseUrl("https://api-ecomm.gatling.io")
            .acceptHeader("application/json")
            .acceptEncodingHeader("gzip, deflate");

    {
        int nbUsers = Integer.getInteger("users", 1);
        int durationSeconds = Integer.getInteger("duration", 30);

        setUp(
            scenario("Scenario")
                .exec(http("Session").get("/session"))
                .injectOpen(constantUsersPerSec(nbUsers).during(Duration.ofSeconds(durationSeconds)))
        )
            .assertions(
                global().responseTime().percentile(99).lt(2000),
                global().successfulRequests().percent().gt(95.0)
            )
            .protocols(httpProtocol);
    }
}
