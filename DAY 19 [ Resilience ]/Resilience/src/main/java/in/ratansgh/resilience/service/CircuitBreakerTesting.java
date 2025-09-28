package in.ratansgh.resilience.service;

import org.springframework.stereotype.Service;

import java.util.Random;

@Service
public class CircuitBreakerTesting {

    private final Random random = new Random();

    public String callExternalService() {
        // Simulate a call to an external service
        // Generate a random boolean to simulate success or failure
      if (random.nextBoolean()){
          throw new RuntimeException("External service failure");
      }
      return "Success from external service";
    }

}
