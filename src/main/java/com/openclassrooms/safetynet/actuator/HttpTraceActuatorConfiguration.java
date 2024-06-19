package com.openclassrooms.safetynet.actuator;

import org.springframework.boot.actuate.web.exchanges.HttpExchangeRepository;
import org.springframework.boot.actuate.web.exchanges.InMemoryHttpExchangeRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
//@Profile("actuator-endpoints")
public class HttpTraceActuatorConfiguration {

 @Bean
 public HttpExchangeRepository httpTraceRepository() {
     return new InMemoryHttpExchangeRepository();
 }

}