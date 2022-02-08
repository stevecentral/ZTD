package com.main;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;

public class aTile {

    //TODO: accepts a colour and uses the colour to create a box on the screen
    int x, y, w, h;
    Color color;

    aTile(int x, int y, int w, int h, Color color){
        this.color = color;
        this.x = x;
        this.y = y;
        this.w = w;
        this.h = h;
    }

    void draw(SpriteBatch batch){
        batch.draw(Resources.create_texture(color), x, y, w, h);
    }
}
