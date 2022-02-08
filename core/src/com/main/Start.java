package com.main;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;

public class Start extends Scene{
    mButton m1, m2, m3;
    Color bg = new Color(0xae5f5100);

    Start(){
        m1 = new mButton("start", (1024 / 2) - (mButton.bw / 2), 325, mButton.bw, mButton.bh, Color.DARK_GRAY);
        m2 = new mButton("about", (1024 / 2) - (mButton.bw / 2), 200, mButton.bw, mButton.bh, Color.DARK_GRAY);
        m3 = new mButton("achievements", (1024 / 2) - (mButton.bw / 2), 75, mButton.bw, mButton.bh, Color.DARK_GRAY);

        font.setColor(Resources.inverse_colour(bg));
        font.getData().setScale(5f);
        layout.setText(font, "Zombie Tower Defense");
    }

     void tap(int x, int y){
        if(m1.hitbox().contains(x, y)) Main.started = true;
        if(m2.hitbox().contains(x, y)) Main.about = true;
        if(m3.hitbox().contains(x, y)) Main.achievements = true;
     }

    void draw(SpriteBatch batch){
        ScreenUtils.clear(bg);
        batch.draw(Resources.tan, (float)1024 / 2 - layout.width / 2  - 15, 555 - layout.height - 15, layout.width + 30, 2);
        batch.draw(Resources.tan, (float)1024 / 2 - layout.width / 2  - 15, 555 - layout.height - 15, layout.width + 30, 2);
        batch.draw(Resources.tan, (float)1024 / 2 - layout.width / 2  - 15, 555 - layout.height - 15, layout.width + 30, layout.height + 30);
        batch.draw(Resources.tan, (float)1024 / 2 - layout.width / 2  - 15, 555 - layout.height - 15, layout.width + 30, 2);
        font.draw(batch, layout, (float)1024 / 2 - layout.width / 2, 555);
        m1.draw(batch);
        m2.draw(batch);
        m3.draw(batch);
    }
}
