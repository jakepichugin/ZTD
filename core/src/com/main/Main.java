package com.main;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
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
	boolean paused = false;

	// GAME LISTS
	static ArrayList<Zombie> zombies = new ArrayList<Zombie>();
	static ArrayList<Cannon> cannons = new ArrayList<Cannon>();
	static ArrayList<Water> watery = new ArrayList<Water>();
	static ArrayList<Button> buttons = new ArrayList<Button>();
	static ArrayList<Bullet> bullets = new ArrayList<Bullet>();
	static ArrayList<Effect> effects  = new ArrayList<>();
	static ArrayList<Wall> walls  = new ArrayList<>();



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
		for (Water b : watery) b.draw(batch);
		UI.draw(batch);
		for (Button a : buttons) a.draw(batch);
		for (Bullet y : bullets) y.draw(batch);
		for (Effect d : effects) d.draw(batch);
		for (Wall w : walls) w.draw(batch);
		batch.end();
	}

	void update(){
		tap();
		spawn_zombie();

		if (!paused) {
			for(Bullet y : bullets) y.update();
			for(Zombie z : zombies) z.update();
			for(Cannon b : cannons) b.update();


		}

		for(Button a : buttons) a.update();
		for(Water b : watery) b.update();
		for(Effect d : effects) d.update();
		for(Wall e : walls) e.update();

		removing_assets();



	}

	void tap() {
		if (Gdx.input.justTouched()){
			int x = Gdx.input.getX(), y = Gdx.graphics.getHeight() - Gdx.input.getY();

			effects.add(new Effect("tap", x, y));

			for(Button b : buttons) {
				if (b.gethitbox().contains(x, y)) {
					if(b.type.equals("pause") || b.type.equals("play")) {

							System.out.println("CLICKED PAUSE ");
							paused = !paused;
							b.type = paused ? "play":"pause";

						return;
					}

					if (b.locked) {
						if (b.t.hidden) {
							hidtt();
							b.t.hidden = false;
						} else {
							if(UI.money >= (Tables.balance.get("unlock_"+b.type) == null ? 0 : (Tables.balance.get("unlock_"+b.type))))
								UI.money -= (Tables.balance.get("unlock_"+b.type) == null ? 0 : (Tables.balance.get("unlock_"+b.type)));
							else return;
							b.locked = false;
							b.t.hidden = true;

						}


					} else {
//						builtTipe = b.type;
						deselect();
						b.selected = true;
						current_type = b.type;
					}
					return;
				} else if (!b.t.hidden){
					if(b.t.close.gethitbox().contains(x, y)) { hidtt(); return;}
					if(b.t.gethitbox().contains(x, y)) return;
					if(!b.t.gethitbox().contains(x, y)) hidtt();

				}

			}
			if (walls.size() < 3 && (current_type.equals("wall") || current_type.equals("mounted"))) {
				walls.add(new Wall(x, 0, current_type.equals("mounted")));
				return;
			}

			for(Cannon c : cannons) {
				if(c.gethitbox().contains( x, y)) {
					return;
				}
			}
			if(buildableCannon(x,y)) {
				if (!current_type.equals("bucket") && UI.money >= (Tables.balance.get("cost_"+current_type) == null ? 10 : Tables.balance.get("cost_"+current_type))) {
					UI.money -= Tables.balance.get("cost_"+current_type) == null ? 10 : Tables.balance.get("cost_"+current_type);
					cannons.add(new Cannon(current_type, x, y));
				}
			}
			for(Water c : watery) if(c.gethitbox().contains( x, y)) return;

			if (buildableWaterCannon(x,y)) {
				if (current_type.equals("bucket") && UI.money >= (Tables.balance.get("cost_" + current_type) == null ? 10 : Tables.balance.get("cost_" + current_type))) {
					UI.money -= Tables.balance.get("cost_" + current_type) == null ? 10 : Tables.balance.get("cost_" + current_type);
					watery.add(new Water(current_type, x , y ));
				}
			}
		}
	}
	void hidtt(){
		for (Button b : buttons) b.t.hidden = true;
	}

	void deselect() {
		for(Button b : buttons) b.selected = false;
	}

	boolean buildableCannon(int x, int y){
		return (x < 1000 && ((y < 200 || y > 300) && y < 500));
	}
	boolean buildableWaterCannon(int x, int y){
		return (x < 1000 && (!(y < 200 || y > 300) && (y < 500)));
	}



	void setup() {
		Tables.init();
		spawn_zombie();

			buttons.add(new Button( "cannon",  buttons.size()* 75 + 200, 525));
			buttons.get(buttons.size() - 1).locked = false;
			buttons.get(buttons.size() - 1).selected = true;
			buttons.add(new Button( "bucket",  buttons.size()* 75 + 200, 525));
			buttons.add(new Button( "super",  buttons.size()* 75 + 200, 525));
			buttons.add(new Button( "fire",  buttons.size()* 75 + 200, 525));
			buttons.add(new Button( "double",  buttons.size()* 75 + 200, 525));
			buttons.add(new Button( "laser",  buttons.size()* 75 + 200, 525));
			buttons.add(new Button( "wall",  buttons.size()* 75 + 200, 525));
			buttons.get(buttons.size() - 1).locked = false;
			buttons.get(buttons.size() - 1).selected = false;
			buttons.add(new Button( "mounted",  buttons.size()* 75 + 200, 525));

			//pause button
			buttons.add(new Button("pause", 1025 - 75, 525));
			buttons.get(buttons.size() - 1).locked = false;
			buttons.get(buttons.size() - 1).selected = false;


	}

	void removing_assets() {
		for (Zombie z : zombies) if (!z.active) {
			zombies.remove(z);
			effects.add(new Effect("zombie_death", z.x, z.y));
			removing_assets();
			break;
		}
		for (Cannon c : cannons) if (c.active == false) {cannons.remove(c); break;}
		for (Bullet y : bullets) if (!y.active) { bullets.remove(y); break;}
		for (Effect d : effects) if (!d.active) { effects.remove(d); break;}
		for (Water w : watery) if (!w.active) { watery.remove(w); break;}
		for (Wall w : walls) if (!w.active) { walls.remove(w); break;}
	}

	void spawn_zombie() {
		if (!zombies.isEmpty()) return;
		UI.wave++;
		for(int i = 0; i < 5 * UI.wave; i++) {



			switch(r.nextInt(10)){
				case 0: case 1:
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
				case 2: case 7:
					zombies.add(zombie = new Zombie("water", 1024 + i * 50, r.nextInt(50) + 200));
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
