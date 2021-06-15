package edu.school21.models;

import java.awt.*;

public class Tank {
    private int hp;
    private Point position;

    public Tank(Point position) {
        this.position = position;
        hp = 100;
    }

    public int getHp() {
        return hp;
    }

    public Point getPosition() {
        return position;
    }

    

}