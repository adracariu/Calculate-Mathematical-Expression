package main.beans;

import org.springframework.context.annotation.Bean;

public class Punct {
    private int x;
    private int y;

    public Punct() {}
    private Punct(int x, int y) {
        this.x = x;
        this.y = y;
    }


    @Bean
    public Punct punct1() {
        Punct punct = new Punct();
        return punct;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }
}
