package example;

import io.gatling.javaapi.core.Simulation;

public class TestSimulationInstantiationFail extends Simulation {
    {
        if (true) {
            throw new RuntimeException("Oops, I broke this");
        }
    }
}
