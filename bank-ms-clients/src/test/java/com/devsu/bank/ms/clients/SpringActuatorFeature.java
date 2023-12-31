/* MIT License

Copyright (c) 2022 Thorben Stangenberg, thorben.stangenberg@42talents.com

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all
copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
SOFTWARE. */
package com.devsu.bank.ms.clients;

import com.devsu.bank.ms.clients.BankMsClientsApplication;
import com.intuit.karate.junit5.Karate;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;

@SpringBootTest(
    classes = {BankMsClientsApplication.class},
    webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class SpringActuatorFeature {

  @LocalServerPort private String localServerPort;

  @Karate.Test
  public Karate actuatorResourceIsAvailable() {
    return karateSzenario("actuator resource is available");
  }

  @Karate.Test
  public Karate healthResourceStatusIsUp() {
    return karateSzenario("health resource status is \"up\"");
  }

  private Karate karateSzenario(String s) {
    return Karate.run()
        .scenarioName(s)
        .relativeTo(getClass())
        .systemProperty("karate.port", localServerPort)
        .karateEnv("dev");
  }
}
