package com.main;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;

import java.util.ArrayList;
import java.util.List;

public class Cannon {
    Sprite sprite;
    int x, y, w, h;
    int counter = 0, delay;
    String type;

    // animation variables
    // ANIMATION VARIABLES
    int rows, cols;
    Animation anim;
    TextureRegion[] frames;
    TextureRegion frame;
    float frame_time = 0.1f;





    Cannon(String type, int x, int y) {
        this.type = type;
        sprite = new Sprite(Tables.cannon_resources.get(type) == null ? Resources.cannon : Tables.cannon_resources.get(type));
        rows = 1;
        cols = Tables.balance.get("cols_"+type) == null ? 1 : Tables.balance.get("cols_"+type);
        w = (Tables.cannon_resources.get(type) == null ? Resources.cannon : Tables.cannon_resources.get(type)).getWidth() / cols;
        h = (Tables.cannon_resources.get(type) == null ? Resources.cannon : Tables.cannon_resources.get(type)).getHeight() / rows;
        delay = Tables.balance.get("delay_"+type) == null ? 30 : Tables.balance.get("delay_"+type);
        this.x = gridlock(x - w / 2);
        this.y = gridlock(y - h / 2);
        init_animation();


    }

    void draw(SpriteBatch batch) {
        sprite.draw(batch);
    }

    void update() {
        if(counter++ > delay) {if (!Main.zombies.isEmpty()) fire(); counter = 0;}
        frame_time += Gdx.graphics.getDeltaTime();
        frame = (TextureRegion)anim.getKeyFrame(frame_time, true);
        sprite = new Sprite(frame);
        sprite.setPosition(this.x, this.y);
        sprite.setRotation(calc_angle());

    }

    float calc_angle() {
        float zx = Main.zombies.get(0).x + (float)Main.zombies.get(0).w / 2, zy = Main.zombies.get(0).y + (float)Main.zombies.get(0).h / 2;
        return (float)Math.toDegrees(Math.atan((y - zy)/(x - zx)) + (x >= zx ? Math.PI : 0));
    }

    void fire(){
        Main.bullet.add(new Bullet("bbb", x + w / 2, y + h / 2 ));
        Resources.sfx_bullet.play(0.1f);
    }

    void init_animation(){
        // split texture in individual cells
        TextureRegion[][] sheet =
                TextureRegion.split((Tables.cannon_resources.get(type) == null ? Resources.cannon : Tables.cannon_resources.get(type)), w, h);

        // init numbers of frames to maximum number possible
        frames = new TextureRegion[rows * cols];

        //loop thro the frames
        int index = 0;
        for(int r = 0; r < rows; r++)
            for(int c = 0; c < cols; c++)
                frames[index++] = sheet[r][c];
        //init the animation object
        anim = new Animation(frame_time, frames);
    }

    int gridlock(int n) {
        return ((int)((n + 25) / 50) * 50);
    }

    Rectangle gethitbox(){ return new Rectangle( x, y, w, h);}



}