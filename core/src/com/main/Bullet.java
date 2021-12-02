package com.main;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;

public class Bullet {
    int x, y, w, h;
    int speed, dt, md; // dt distance traveled, md maximum distance can travel
    float angle;
    String type;
    boolean active = true;
    Sprite sprite;

    Bullet(String type, int x, int y) {
        this.type = type;
        this.x = x;
        this.y = y;

        sprite = new Sprite(Tables.resources.get("bullet_" + type) == null ? Resources.Bullet : Tables.resources.get("bullet_" + type));
        w = Tables.bullet_resources.get(type) == null ? Resources.Bullet.getWidth() : Tables.bullet_resources.get(type).getWidth();
        h = Tables.bullet_resources.get(type) == null ? Resources.Bullet.getHeight() : Tables.bullet_resources.get(type).getHeight();
        speed =  5;
        dt = 0;
        md = 300;
        angle = calc_angle();
        sprite.setPosition(x, y);
        sprite.setRotation((float)Math.toDegrees(calc_angle()) - 90f);

    }

    void draw(SpriteBatch batch) {
        sprite.draw(batch);
     }

    void update() {
        x += Math.cos(angle) * speed;
        y += Math.sin(angle) * speed;
        sprite.setPosition(x, y);
        dt += Math.cos(angle) * speed + Math.sin(angle) * speed;
        active = dt < md;
        hitzombie();

    }

    Rectangle hitbox(){ return new Rectangle( x, y, w, h);}

    float calc_angle() {
        float zx = Main.zombies.get(0).x + (float)Main.zombies.get(0).w / 2, zy = Main.zombies.get(0).y + (float)Main.zombies.get(0).h / 2;
        return (float)(Math.atan((y - zy)/(x - zx)) + (x >= zx ? Math.PI : 0));
    }

    boolean hitzombie(){
        if (Main.zombies.isEmpty()) return false;
        for(Zombie z: Main.zombies) {
            if(z.gethitbox().contains(hitbox())) {
                z.hp--;
                this.active = false;
            }
         }
        return false;
    }

}
