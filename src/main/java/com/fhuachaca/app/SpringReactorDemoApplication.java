package com.fhuachaca.app;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import reactor.core.publisher.Mono;


@SpringBootApplication
public class SpringReactorDemoApplication implements CommandLineRunner {

    private static final Logger LOGGER = LoggerFactory.getLogger(SpringReactorDemoApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(SpringReactorDemoApplication.class, args);
    }

    public void crearMono(){
      Mono<Integer> monoNumero = Mono.just(7);
      monoNumero.subscribe(x -> LOGGER.info("Numero: "+ x));
    }

    @Override
    public void run(String... args) throws Exception {
        crearMono();
    }
}
