package com.main;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;

public class Zombie {
    int x, y,  w, h, speed, hp;
    float chunk;
    String type;
    boolean active = true;

    //animation variables
    int cols, rows = 1;
    Animation animation;
    TextureRegion[] frames;
    TextureRegion frame;
    float frame_time;

    Zombie(String type, int x, int y){
        this.type = type;
        this.x = x;
        this.y = y;
        this.speed = Tables.values.get("speed_" + type) == null ? 1 : Tables.values.get("speed_" + type);
        this.cols = Tables.values.get("columns_" + type) == null ? 4 : Tables.values.get("columns_" + type);

        w = (Tables.zombie_resources.get(type) == null ? Resources.zombie : Tables.zombie_resources.get(type)).getWidth() / cols;
        h = (Tables.zombie_resources.get(type) == null ? Resources.zombie : Tables.zombie_resources.get(type)).getHeight() / rows;
        hp = (Tables.values.get("health" + type) == null ? 5 : Tables.values.get("health_" + type));
        if (UI.wave > 20) hp *= 3;
        else if(UI.wave > 10) hp *= 2;
        chunk = (float)w / hp;

        //make this last
        prep_animations();
    }

    void draw(SpriteBatch batch){
        frame_time  += Gdx.graphics.getDeltaTime();
        frame = (TextureRegion)animation.getKeyFrame(frame_time, true);
        batch.draw(frame, x, y);

        batch.draw(Resources.red_bar, x, y + h + 3, w, 4);
        batch.draw(Resources.green_bar, x, y + h + 3, hp * chunk, 4);
    }

    void update(){
        x -= speed;
        UI.score += hp > 0 ? 0 : (Tables.values.get("score_" + type) == null ? 1 : Tables.values.get("score_" + type));;
        active = x >= 0 && hp > 0;
    }

    void prep_animations(){
        //slice image into cells
        TextureRegion[][] sheet = TextureRegion.split(Tables.zombie_resources.get(type) == null ? Resources.zombie : Tables.zombie_resources.get(type), w, h);

        //set frames to maximum numbers pf cells
        frames = new TextureRegion[rows*cols];
        int index = 0;
        //fill frames
        for(int r = 0; r < rows; r++)
            for(int c = 0; c < cols; c++)
                frames[index++] = sheet[r][c];

            animation = new Animation(0.2f, frames);
    }

    Rectangle hitbox() { return new Rectangle(x, y, w, h); }
}
