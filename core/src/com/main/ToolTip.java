package com.main;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;

import java.net.ResponseCache;

public class ToolTip {
    int x, y, w, h;
    String type;
    BitmapFont font = new BitmapFont();
    GlyphLayout layout = new GlyphLayout();
    boolean hidden = true;
    Button close;

    ToolTip(String type, Button p){
        this.type = type;
        w = 200;
        h = 100;
        x = (p.x + p.w / 2) - w / 2;
        y = p.y - h - 15;
        close = new Button("close", x + w - 26, y + h - 26);
        close.locked = false;
    }

    void draw(SpriteBatch batch){
        if(hidden) return;
        batch.draw(Resources.tooltip_bg, x, y, w, h);
        close.draw(batch);

        String[] words = (Tables.tooltips.get(type) == null ? "No description found..." : Tables.tooltips.get(type)).split(" ");
        int rtx = 35, rty = 5; //relative positions to position of tooltip
        for(String s : words){
            if(rtx + layout.width >= w - 25){
                rtx = 35;
                rty += layout.height + 5;
            }
            font.setColor(Color.FIREBRICK);
            font.draw(batch, s, x + rtx, y + h - rty);
            layout.setText(font, " " + s);
            rtx += layout.width;
        }

        font.getData().setScale(1.25f);
        font.setColor(Color.BLACK);
        font.draw(batch, "Unlock: " + (Tables.values.get("unlock_" + type) == null ? "0" : Tables.values.get("unlock_" + type)), x + 46, y + 43);
        font.setColor(Color.FOREST);
        font.draw(batch, "Unlock: "  + (Tables.values.get("unlock_" + type) == null ? "0" : Tables.values.get("unlock_" + type)), x + 45, y + 45);
        font.getData().setScale(1f);

        font.setColor(Color.BLACK);
        font.draw(batch, "(tap again to unlock)", x + 35, y + 20);
    }

    Rectangle hitbox() { return new Rectangle(x, y, w, h); }
}
