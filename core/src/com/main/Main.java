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
	String builtTipe;
	String current_type = "ccc";
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
		UI.draw(batch);
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


			for(Button b : buttons) {

				if (b.gethitbox().contains(x, y)) {

					if (b.locked) {
						if (b.t.hidden) {
							hidtt();
							b.t.hidden = false;
						} else {
							b.locked = false;
							b.t.hidden = true;
						}


					} else {
						builtTipe = b.type;
						deselect();
						b.selected = true;
						current_type = b.type;
					}
					return;
				} else {
					if(b.t.close.gethitbox().contains(x, y) && !b.t.hidden) { hidtt(); return;}
					if(b.t.gethitbox().contains(x, y) && !b.t.hidden) return;
					if(!b.t.gethitbox().contains(x, y) && !b.t.hidden) { hidtt(); return;}

				}

			}

			for(Cannon c : cannons) if(c.gethitbox().contains( x, y)) return;
			if(buildable(x,y)) if (UI.money >= (Tables.balance.get("cost_"+current_type) == null ? 10 : Tables.balance.get("cost_"+current_type))) {
				UI.money -= Tables.balance.get("cost_"+current_type) == null ? 10 : Tables.balance.get("cost_"+current_type);
				cannons.add(new Cannon(builtTipe, x, y));
			}

		}
	}
	void hidtt(){
		for (Button b : buttons) b.t.hidden = true;
	}

	void deselect() {
		for(Button b : buttons) b.selected = false;
	}

	boolean buildable(int x, int y){
		return (x < 1000 && ((y < 200 || y > 300) && y < 500));
	}

	void setup() {
		Tables.init();
		spawn_zombie();

			buttons.add(new Button( "cannon",  buttons.size()* 75 + 200, 525));
			buttons.add(new Button( "double",  buttons.size()* 75 + 200, 525));
			buttons.add(new Button( "super",  buttons.size()* 75 + 200, 525));
			buttons.add(new Button( "fire",  buttons.size()* 75 + 200, 525));
			buttons.add(new Button( "laser",  buttons.size()* 75 + 200, 525));


	}

	void removing_assets() {
		for (Zombie z : zombies) if (!z.active) { zombies.remove(z); break;}
		for (Bullet y : bullet) if (!y.active) { bullet.remove(y); break;}
	}

	void spawn_zombie() {
		if (!zombies.isEmpty()) return;
		UI.wave++;
		for(int i = 0; i < 5 * UI.wave; i++) {



			switch(r.nextInt(10)){
				case 0: case 1: case 2: case 7:
					zombies.add(zombie = new Zombie("zombie", 1024 + i * 50, r.nextInt(450)));
					break;
				case 3: case 4:
					zombies.add(zombie = new Zombie("fast", 1024 + i * 50, r.nextInt(450)));
					break;
				case 5:
					zombies.add(zombie = new Zombie("riot", 1024 + i * 50, r.nextInt(450)));
					break;
				default:
					zombies.add(zombie = new Zombie("dif", 1024 + i * 50, r.nextInt(450)));
					break;
				case 8:
					zombies.add(zombie = new Zombie("speedy", 1024 + i * 50, r.nextInt(450)));
					break;
			}


		}
	}


	// END OF FILE DON'T ADD BELOW
	@Override
	public void dispose () {
		batch.dispose();
	}
}
