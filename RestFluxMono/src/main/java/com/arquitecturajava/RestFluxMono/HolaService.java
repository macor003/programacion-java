package com.arquitecturajava.RestFluxMono;

import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.time.Duration;

@Service
public class HolaService {
    public String hola() {
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return "hola sincrono";
    }

    public String hola2() {
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return "hola sincrono 2";

    }

    public Mono<String> holaAsync() {
        return Mono.just("hola asincrono Async 1").delayElement(Duration.ofSeconds(3));
    }

    public Mono<String> hola2Async() {

        return Mono.just("hola asincrono Async 2").delayElement(Duration.ofSeconds(3));
    }
}
