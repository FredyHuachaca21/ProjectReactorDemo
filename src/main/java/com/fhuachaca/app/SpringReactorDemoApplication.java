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
        /*Flujo de plastos en Flux*/
//        fxPlatos.subscribe( p -> LOGGER.info(p));

        /*Envuelve en un mono la lista de platos con collections*/
        /*  Proceso inverso de Mono a Flux no es posible*/
        fxPlatos.collectList().subscribe(lista -> LOGGER.info(lista.toString()));
    }

    public void metodo1doOnNext(){
        Flux<String> fxPlatos = Flux.fromIterable(platos);
        /*NADA SUCEDE HASTA QUE TE SUSCRIBAS*/
        /*doOnNext sirve para ver que pasa por cada flujo*/
        fxPlatos.doOnNext(p -> LOGGER.info(p)).subscribe();
    }

    public void metodo2map(){
        Flux<String> fxPlatos = Flux.fromIterable(platos);
        /*map sirve para transformar elementos*/
        fxPlatos.map(p -> p.toUpperCase())
                .subscribe(p -> LOGGER.info(p));

        /*Se verifica que pasa antes de transformar el flujo -> Como una depuración*/
        fxPlatos.doOnNext(p -> LOGGER.info(p))
                .map(p -> "Platos: " + p)
                .subscribe(p -> LOGGER.info(p));
    }

    public void metodo3flatMap(){
        Flux<String> fxPlatos = Flux.fromIterable(platos);
        /*flatMap se tiene que especificar el retorno explícito*/
        Mono.just("Fredy")
                .doOnNext(d -> LOGGER.info( "Viene como : " + d + " y lo transforma a ..."))
                .flatMap(x -> Mono.just("Edgar"))
                .subscribe(n -> LOGGER.info(n));
    }
    

    @Override
    public void run(String... args) throws Exception {
        metodo3flatMap();
    }
}
