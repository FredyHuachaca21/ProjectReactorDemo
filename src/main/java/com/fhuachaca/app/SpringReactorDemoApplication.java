package com.fhuachaca.app;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.List;


@SpringBootApplication
public class SpringReactorDemoApplication implements CommandLineRunner {

    private static final Logger LOGGER = LoggerFactory.getLogger(SpringReactorDemoApplication.class);
    private static List<String> platos = new ArrayList<>();

    public static void main(String[] args) {
        platos.add("Arroz con pollo");
        platos.add("Aji de gallina");
        platos.add("Seco de cordero");
        platos.add("Lomo saltado");
        SpringApplication.run(SpringReactorDemoApplication.class, args);
    }

    public void crearMono(){
      Mono<Integer> monoNumero = Mono.just(7);
      monoNumero.subscribe(x -> LOGGER.info("Numero: "+ x));
    }

    public void crearFlux(){
        Flux<String> fxPlatos = Flux.fromIterable(platos);
        fxPlatos.subscribe( p -> LOGGER.info(p));
    }

    @Override
    public void run(String... args) throws Exception {
        crearFlux();
    }
}
