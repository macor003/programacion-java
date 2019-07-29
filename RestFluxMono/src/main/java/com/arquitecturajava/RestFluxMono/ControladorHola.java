package com.arquitecturajava.RestFluxMono;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping
public class ControladorHola {
    @Autowired
    HolaService servicio;

    @RequestMapping("/hola")
    public String hola() {

        return servicio.hola();
    }

    @RequestMapping("/hola2")
    public String hola2() {

        return servicio.hola2();
    }

    @RequestMapping("/holas")
    public String holas() {

        long t1 = System.currentTimeMillis();
        String texto = servicio.hola() + " - " + servicio.hola2();
        long t2 = System.currentTimeMillis();
        System.out.println(t2 - t1);
        return texto;
    }


    @RequestMapping("/holasAsync")
    public Flux<String> holasAync() {
        long t1 = System.currentTimeMillis();
        Mono<String> mono1 = servicio.holaAsync();
        Mono<String> mono2 = servicio.hola2Async();

        Flux<String> flujo = Flux.concat(mono1, mono2);
        long t2 = System.currentTimeMillis();
        System.out.println(t2 - t1);
        return flujo;
    }
}
