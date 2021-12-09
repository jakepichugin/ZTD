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
    TextureRegion last_frame;
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
        if(!type.equals("laser") && counter++ > delay) {if (!Main.zombies.isEmpty()) fire(); counter = 0;}
        if(type.equals("laser") && check_frame()) if(!Main.zombies.isEmpty()) fire();

        frame_time += Gdx.graphics.getDeltaTime();
        frame = (TextureRegion)anim.getKeyFrame(frame_time, true);
        sprite = new Sprite(frame);
        sprite.setPosition(this.x, this.y);
        sprite.setRotation(calc_angle());


    }

    boolean check_frame(){
        return (last_frame == (TextureRegion)anim.getKeyFrame(frame_time, true));
    }



    float calc_angle() {

        Zombie closest = null;
        for (Zombie z : Main.zombies){
            if (closest == null) {
                closest = z; continue;
            }
            float hyp_closest = (float)Math.sqrt(((x - closest.x) * (x - closest.x)) + ((y - closest.y) * (y - closest.y)));
            float hyp_closest_z = (float)Math.sqrt(((x - z.x) * (x - z.x)) + ((y - z.y) * (y - z.y)));
            if (hyp_closest > hyp_closest_z) {
                closest = z;
            }

        }
               float zx = closest.x + (float)closest.w / 2, zy = closest.y + (float)closest.h / 2;
                return (float)Math.toDegrees(Math.atan((y - zy)/(x - zx)) + (x >= zx ? Math.PI : 0));
    }





    void fire(){
        Main.bullet.add(new Bullet(type, x + w / 2, y + h / 2 ));
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
        if(type.equals("laser")) {last_frame = (TextureRegion)anim.getKeyFrames()[anim.getKeyFrames().length - 6];}



    }

    int gridlock(int n) {
        return ((int)((n + 25) / 50) * 50);
    }

    Rectangle gethitbox(){ return new Rectangle( x, y, w, h);}



}