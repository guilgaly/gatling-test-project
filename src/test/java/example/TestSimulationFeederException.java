package example;

import io.gatling.javaapi.core.*;
import io.gatling.javaapi.http.*;

import java.time.Duration;

import static io.gatling.javaapi.core.CoreDsl.*;
import static io.gatling.javaapi.http.HttpDsl.*;

public class TestSimulationFeederException extends Simulation {
    HttpProtocolBuilder httpProtocol =
        http.baseUrl("https://computer-database.gatling.io")
            .acceptHeader("text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8")
            .acceptLanguageHeader("en-US,en;q=0.5")
            .acceptEncodingHeader("gzip, deflate")
            .userAgentHeader(
                "Mozilla/5.0 (Macintosh; Intel Mac OS X 10.8; rv:16.0) Gecko/20100101 Firefox/16.0"
            );

    FeederBuilder<String> feeder = csv("search.csv").queue();

    int nbUsers = Integer.getInteger("users", 1);
    int durationSeconds = Integer.getInteger("duration", 30);

    {
        setUp(
            scenario("Test")
                .feed(feeder)
                .exec(http("Home").get("/"))
                .pause(5)
                .exec(http("Home").get("/"))
                .injectOpen(constantUsersPerSec(nbUsers).during(Duration.ofSeconds(durationSeconds)))
        )
            .protocols(httpProtocol);
    }
}
