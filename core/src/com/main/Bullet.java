package com.main;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Bullet {
    int x, y, w, h, speed;
    float angle;
    String type;
    boolean active = true;

    Bullet(String type, int x, int y){
        this.type = type;
        w = Resources.bullet.getWidth();
        h = Resources.bullet.getHeight();
        this.x = x;
        this.y = y;
        speed = 5;
        angle = calculate_angle();
    }

    void draw(SpriteBatch batch){
        batch.draw(Resources.bullet, x, y);
    }

    void update() {
        x += Math.cos(angle) * speed;
        y += Math.sin(angle) * speed;
    }

    float calculate_angle(){
        float zx = Main.zombies.get(0).x - Main.zombies.get(0).w / 2, zy = Main.zombies.get(0).y - Main.zombies.get(0).h / 2;
        return (float)(Math.atan((y - zy) / (x - zx)) + (x >= zx ? Math.PI : 0));
    }
}