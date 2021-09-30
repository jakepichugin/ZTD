package com.main;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;

import java.util.ArrayList;
import java.util.Random;

public class Main extends ApplicationAdapter {
	// GAME VARIABLES
	SpriteBatch batch;
	Zombie zombie;
	Random r;

	// CONTROL VARIABLES

	// GAME LISTS
	ArrayList<Zombie> zombies = new ArrayList<Zombie>();


	@Override
	public void create () {
		batch = new SpriteBatch();
		r = new Random();
		for(int i = 0; i < 1000; i++) {

			zombies.add(zombie = new Zombie("zzz", 526 + i * 50, r.nextInt(450), 1));


		}
	}

	@Override
	public void render () {
		ScreenUtils.clear(0, 0, 0, 1);
		update();
		batch.begin();
		batch.draw(Resources.bg, 0, 0);
//		zombie.draw(batch);
		for(Zombie z : zombies) z.draw(batch);
		batch.end();
	}

	void update(){
		for(Zombie z : zombies) z.update();

	}
	// END OF FILE DON'T ADD BELOW
	@Override
	public void dispose () {
		batch.dispose();
	}
}
