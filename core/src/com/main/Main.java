package com.main;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.ScreenUtils;

import java.util.ArrayList;
import java.util.Random;

public class Main extends ApplicationAdapter {
	SpriteBatch batch;
	Start start;
	About about_scene;
	ztd ztd;
	Lose lose;
	static boolean started = false, about = false, gameover = false;

	//create runs *once* when the application starts / opens
	@Override
	public void create () {
		batch = new SpriteBatch();
		start = new Start();
		about_scene = new About();
		ztd = new ztd();
		lose = new Lose();
	}


	//render runs once per frame
	@Override
	public void render () {
		ScreenUtils.clear(0, 0, 0, 1);
		//right before drawing
		tap();
		//add drawing code after this
		batch.begin();
		if(!started)
			if(about)
				about_scene.draw(batch);
			else start.draw(batch);
			else {
				if(gameover) lose.draw(batch);
				else {
					ztd.update();
					ztd.draw(batch);
				}
		}
		//end drawing code before this
		batch.end();
	}

	void tap(){
		if(Gdx.input.justTouched()){
			int x = Gdx.input.getX(), y = Gdx.graphics.getHeight() - Gdx.input.getY();
			if(!started)
				if(about)
					about_scene.tap(x, y);
				else start.tap(x, y);
				else if (gameover) lose.tap(x, y);
				else ztd.tap(x, y);
		}
	}

	//*********************END OF FILE*********************
	@Override
	public void dispose () {
		batch.dispose();
	}
}
