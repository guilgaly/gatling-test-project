package computerdatabase;

import io.gatling.javaapi.core.*;
import io.gatling.javaapi.http.*;

import static io.gatling.javaapi.core.CoreDsl.*;
import static io.gatling.javaapi.http.HttpDsl.*;

import io.gatling.javaapi.core.Simulation;

public class TestSimulationInstantiationFail extends Simulation {
    {
        if (true) {
            throw new RuntimeException("Oops, I broke this");
        }
    }
}
