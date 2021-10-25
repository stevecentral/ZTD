package com.main;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class Zombie {
    int x, y,  w, h, speed;
    String type;
    boolean active = true;

    //animation variables
    int columns = 4, rows = 1;
    Animation animation;
    TextureRegion[] frames;
    TextureRegion frame;
    float frame_time;

    Zombie(String type, int x, int y, int speed){
        this.type = type;
        this.x = x;
        this.y = y;
        this.speed = speed;

        w = 50;
        h = 50;

        //make this last
        prep_animations();
    }

    void draw(SpriteBatch batch){
        frame_time  += Gdx.graphics.getDeltaTime();
        frame = (TextureRegion)animation.getKeyFrame(frame_time, true);
        batch.draw(frame, x, y);
    }

    void update(){
        active = x > 0;
        x -= speed;
    }

    void prep_animations(){
        //slice image into cells
        TextureRegion[][] sheet = TextureRegion.split(Resources.zombie_dif,
                                                            Resources.zombie_dif.getWidth() / columns,
                                                            Resources.zombie_dif.getHeight() / rows);

        //set frames to maximum numbers pf cells
        frames = new TextureRegion[rows*columns];
        int index = 0;
        //fill frames
        for(int r = 0; r < rows; r++)
            for(int c = 0; c < columns; c++)
                frames[index++] = sheet[r][c];

            animation = new Animation(0.2f, frames);
    }
}
