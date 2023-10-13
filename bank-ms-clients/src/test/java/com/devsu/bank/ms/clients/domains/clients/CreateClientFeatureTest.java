package com.devsu.bank.ms.clients.domains.clients;

import com.devsu.bank.ms.clients.BankMsClientsApplication;
import com.intuit.karate.junit5.Karate;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;

@SpringBootTest(
        classes = {BankMsClientsApplication.class},
        webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class CreateClientFeatureTest {

    @LocalServerPort
    private String localServerPort;

    @Karate.Test
    public Karate createAClientIsCorrect() {
        return loadScenario("create a client is correct");
    }

    private Karate loadScenario(String s) {
        return Karate.run()
                .scenarioName(s)
                .relativeTo(getClass())
                .systemProperty("karate.port", localServerPort)
                .karateEnv("dev");
    }
}
