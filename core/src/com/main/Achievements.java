package com.main;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.ScreenUtils;

import java.util.ArrayList;

public class Achievements extends Scene{
    mButton m1;
    aTile tile = new aTile(105, 380, 64, 64, Color.GOLD);
    ArrayList<aTile> tiles = new ArrayList<aTile>();
    Color color = new Color(0x5010cc00);
    int[] box = {100, 475, 1024 - 200, 300};

    Achievements(){
        m1 = new mButton("Back", (1024 / 2) - (mButton.bw / 2), 75, mButton.bw, mButton.bh, Resources.inverse_colour(new Color(0x5010cc00)));
        for(int i = 0; i < 15; i++){
            tiles.add(new aTile(130 + (64 * tiles.size() + 30 * tiles.size()), 380, 64, 64, Color.GOLD));
        }
    }

    void draw(SpriteBatch batch) {
        //clear
        ScreenUtils.clear(color);
        //scene box
        batch.draw(Resources.light_gray, 1024 - 10 - 10, 10, 10, 600 - 10 - 10);
        batch.draw(Resources.dark, 10, 10, 1024 - 10 - 10, 10);
        batch.draw(Resources.dark, 10, 10, 10, 600 - 10 - 10);
        batch.draw(Resources.light_gray, 10, 600 - 10 - 10, 1024 - 10 - 10, 10);
        //achievement box
        batch.draw(Resources.create_texture(Resources.darken_color(color)), box[0], box[1] - box[3], box[2], box[3]);
        m1.draw(batch);
        tile.draw(batch);
        for(aTile t : tiles) t.draw(batch);
    }

    void tap(int x, int y){
        if(m1.hitbox().contains(x, y)) Main.achievements = false;
    }



}
