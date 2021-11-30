package com.main;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.ScreenUtils;

import java.util.ArrayList;
import java.util.Random;

public class Main extends ApplicationAdapter {
	//TODO: game variables / objects
	SpriteBatch batch;
	static Random r = new Random();
	static String current_type = "";

	//TODO: game lists
	static ArrayList<Zombie> zombies = new ArrayList<Zombie>();
	static ArrayList<Cannon> cannons = new ArrayList<Cannon>();
	static ArrayList<Button> buttons = new ArrayList<Button>();
	static ArrayList<Bullet> bullets = new ArrayList<Bullet>();
	static ArrayList<Effect> effect = new ArrayList<Effect>();
	static ArrayList<Wall> walls = new ArrayList<Wall>();

	//create runs *once* when the application starts / opens
	@Override
	public void create () {
		batch = new SpriteBatch();
		setup();
	}


	//render runs once per frame
	@Override
	public void render () {
		ScreenUtils.clear(0, 0, 0, 1);
		//right before drawing
		update();
		//add drawing code after this
		batch.begin();
		batch.draw(Resources.bg, 0, 0);
		for(Zombie z: zombies) z.draw(batch);
		for(Cannon c: cannons) c.draw(batch);
		for(Button b: buttons) b.draw(batch);
		for(Bullet b: bullets) b.draw(batch);
		for(Wall w: walls) w.draw(batch);
		for(Effect e: effect) e.draw(batch);
		//end drawing code before this
		batch.end();
	}

	void update(){
		tap();
		for(Zombie z: zombies) z.update();
		for(Cannon c: cannons) c.update();
		for(Button b: buttons) b.update();
		for(Bullet b: bullets) b.update();
		for(Wall w: walls) w.update();

		//clean up after updates
		housekeeping();
		spawn_zombies();
	}

	void housekeeping(){
		for(Zombie z: zombies) if(!z.active) {zombies.remove(z); break;}
		for(Bullet b: bullets) if(!b.active) {bullets.remove(b); break;}
		for(Effect e: effect) if(!e.active) {effect.remove(e); break;}
		for(Wall w: walls) if(!w.active) {walls.remove(w); break;}
	}

	void tap(){
		if(Gdx.input.justTouched()){
			int x = Gdx.input.getX(), y = Gdx.graphics.getHeight() - Gdx.input.getY();

			effect.add(new Effect("click", x, y));

			for(Button b : buttons) {
				if (b.t != null && !b.t.hidden && b.t.close.hitbox().contains(x, y)) {
					b.t.hidden = true;
					return;
				}
				if (b.t != null && !b.t.hidden && b.t.hitbox().contains(x, y)) return;
				if (b.hitbox().contains(x, y)) {
					if (b.locked) {
						if (b.t.hidden) {
							hidett();
							b.t.hidden = false;
						} else {
							b.locked = false;
							b.t.hidden = true;
						}
						return;
					} else {
						if(b.type.equals("wall") || b.type.equals("mounted")){
							if(walls.size() < 3) walls.add(new Wall(walls.size() * 50, 0, b.type.equals("mounted")));
							return;
						}
						hidett();
						deselect();
						b.selected = true;
						current_type = b.type;
					}
					return;
				}
			}

			for (Cannon c : cannons) if(c.hitbox().contains(x, y)) return;
			if(buildable(x, y))cannons.add(new Cannon(current_type, x, y));
		}
	}

	void deselect(){
		for(Button b : buttons) {
			b.selected = false;
		}
	}

	void hidett(){
		for(Button b : buttons) if(b.t != null) b.t.hidden = true;
	}

	boolean buildable(int x, int y){
		return ((y < 200 || 300 < y && y < 500) && (x < 1000));
		//return true if you can build a cannon at this location

		//return false if you can't
	}

	void setup(){
		Tables.init();
		spawn_zombies();
		buttons_appear();
	}

	void spawn_zombies(){
		if(!zombies.isEmpty()) return;
		//create 15 zombies
		for(int i = 0; i < 15; i++){
			zombies.add(new Zombie("riot", 1024 + i * 50, r.nextInt(450)));
		}
	}

	void buttons_appear(){
		//create 5 buttons
		buttons.add(new Button("bbb", 50 + buttons.size() * 75, 525));
		buttons.get(buttons.size() - 1).selected = true;
		buttons.get(buttons.size() - 1).locked = false;
		buttons.add(new Button("fire", 50 + buttons.size() * 75, 525));
		buttons.add(new Button("super", 50 + buttons.size() * 75, 525));
		buttons.add(new Button("double", 50 + buttons.size() * 75, 525));
		buttons.add(new Button("laser", 50 + buttons.size() * 75, 525));
		buttons.add(new Button("missile", 50 + buttons.size() * 75, 525));
		buttons.add(new Button("wall", 50 + buttons.size() * 75, 525));
		buttons.get(buttons.size() - 1).locked = false;
		buttons.add(new Button("mounted", 50 + buttons.size() * 75, 525));
	}

	//*********************END OF FILE*********************
	@Override
	public void dispose () {
		batch.dispose();
	}
}
