package com.main;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;

import java.util.ArrayList;
import java.util.Random;

public class Main extends ApplicationAdapter {
	// GAME VARIABLES
	SpriteBatch batch;
	Zombie zombie;
	Cannon cannon;
	Button button;
	Random r;

	// CONTROL VARIABLES

	// GAME LISTS
	static ArrayList<Zombie> zombies = new ArrayList<Zombie>();
	static ArrayList<Cannon> cannons = new ArrayList<Cannon>();
	static ArrayList<Button> buttons = new ArrayList<Button>();
	static ArrayList<Bullet> bullet = new ArrayList<Bullet>();



	@Override
	public void create () {
		batch = new SpriteBatch();
		r = new Random();
		setup();
	}

	@Override
	public void render () {
		ScreenUtils.clear(0, 0, 0, 1);
		update();
		batch.begin();
		batch.draw(Resources.bg, 0, 0);
//		zombie.draw(batch);
		for (Zombie z : zombies) z.draw(batch);
		for (Cannon b : cannons) b.draw(batch);
		for (Button a : buttons) a.draw(batch);
		for (Bullet y : bullet) y.draw(batch);
		batch.end();
	}

	void update(){
		tap();
		spawn_zombie();
		for(Zombie z : zombies) z.update();
		for(Cannon b : cannons) b.update();
		for(Button a : buttons) a.update();
		for (Bullet y : bullet) y.update();

		removing_assets();



	}

	void tap() {
		if (Gdx.input.justTouched()){
			int x = Gdx.input.getX(), y = Gdx.graphics.getHeight() - Gdx.input.getY();

			for(Cannon c : cannons) if(c.gethitbox().contains( x, y)) return;
			if(buildable(x,y))cannons.add(new Cannon("super", x, y));

		}
	}

	boolean buildable(int x, int y){
		return (x < 1000 && ((y < 200 || y > 300) && y < 500));
	}

	void setup() {
		Tables.init();
		spawn_zombie();
		for(int i = 0; i < 5; i++){

			buttons.add(new Button( "play", i * 75 + 25, 525));

		}
	}

	void removing_assets() {
		for (Zombie z : zombies) if (!z.active) { zombies.remove(z); break;}
		for (Bullet y : bullet) if (!y.active) { bullet.remove(y); break;}
	}

	void spawn_zombie() {
		if (!zombies.isEmpty()) return;
		for(int i = 0; i < 15; i++) {
			zombies.add(zombie = new Zombie("zombie", 1024 + i * 50, r.nextInt(450), 1));


		}
	}




	// END OF FILE DON'T ADD BELOW
	@Override
	public void dispose () {
		batch.dispose();
	}
}
