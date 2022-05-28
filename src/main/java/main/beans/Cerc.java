package main.beans;

import org.springframework.context.annotation.Bean;

public class Cerc {
    double raza;
    Punct centru;

    private Cerc(){}

    public Cerc(double raza, Punct centru) {
        this.raza = raza;
        this.centru = centru;
    }

    public double getRaza() {
        return raza;
    }

    public void setRaza(double raza) {
        this.raza = raza;
    }

    public Punct getCentru() {
        return centru;
    }

    public void setCentru(Punct punct) {
        this.centru = punct;
    }

    @Bean
    public Cerc cerc () {
        Cerc c1 = new Cerc();

        return c1;
    }
}
