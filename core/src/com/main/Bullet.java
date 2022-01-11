package com.main;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;

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
        Move();
        zombie_collision();
    }

    void zombie_collision(){
        if (ztd.zombies.isEmpty()) return;
        for(Zombie z: ztd.zombies)
            if(z.hitbox().contains(this.hitbox())) {
                z.hp --;
                this.active = false;
            }
    }

    void Move(){
        x += Math.cos(angle) * speed;
        y += Math.sin(angle) * speed;
    }

    float calculate_angle(){
        Zombie closest = null;
        for(Zombie z: ztd.zombies){
            if(closest == null) {closest = z; continue; }
            float closest_dif = (float)Math.sqrt((x - closest.x)^2 + (y - closest.y)^2);
            float z_dif = (float)Math.sqrt((x - z.x)^2 + (y - z.y)^2);
            if(z_dif < closest_dif) closest = z;
        }
        return (float)(Math.atan((float)(y - (closest.y + closest.h / 2)) / (float)(x - (closest.x + closest.w / 2))) + (x >= closest.x ? Math.PI : 0));
    }

    Rectangle hitbox() { return new Rectangle(x, y, w, h); }
}
