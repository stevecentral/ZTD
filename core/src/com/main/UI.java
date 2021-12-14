package com.main;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class UI {
    protected static BitmapFont font = new BitmapFont();
    static int money = 5000;
    static int wave = 0;
    static int score = 0;

    static void draw(SpriteBatch b){
        font.getData().setScale(1.5f);

        font.setColor(Color.GOLD);
        font.draw(b, "Money:" + money, 4, 596);

        font.setColor(Color.GOLD);
        font.draw(b, "Wave:" + wave, 4, 596 - 16 * font.getData().scaleX);

        font.setColor(Color.GOLD);
        font.draw(b, "Score:" + score, 4, 596 - 16 * font.getData().scaleX * 2);

        font.getData().setScale(1f);
    }
}
