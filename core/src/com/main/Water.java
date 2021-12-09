package com.main;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;

public class Water {
    Sprite sprite;
    int x, y, w, h;
    int counter = 0, delay;
    boolean active = true;
    String type;

    // animation variables
    // ANIMATION VARIABLES
    int rows, cols;
    Animation anim;
    TextureRegion[] frames;
    TextureRegion frame;
    float frame_time = 0.1f;

    Water(String type, int x, int y) {
        this.type = type;
        sprite = new Sprite(Tables.water_resources.get(type) == null ? Resources.water_bucket : Tables.water_resources.get(type));
        rows = 1;
        cols = Tables.balance.get("cols_"+type) == null ? 1 : Tables.balance.get("cols_"+type);
        w = (Tables.water_resources.get(type) == null ? Resources.water_bucket : Tables.water_resources.get(type)).getWidth() / cols;
        h = (Tables.water_resources.get(type) == null ? Resources.water_bucket : Tables.water_resources.get(type)).getHeight() / rows;
//        delay = Tables.balance.get("delay_"+type) == null ? 30 : Tables.balance.get("delay_"+type);
        this.x = (int)gridlock(x);
        this.y = (int)gridlock(y);
        init_animation();


    }

    void draw(SpriteBatch batch) {
        sprite.draw(batch);
    }

    void update() {
//        if(counter++ > delay) {if (!Main.zombies.isEmpty())  counter = 0;}
        frame_time += Gdx.graphics.getDeltaTime();
        frame = (TextureRegion)anim.getKeyFrame(frame_time, true);
        sprite = new Sprite(frame);
        sprite.setPosition(this.x, this.y);
        active = !anim.isAnimationFinished(frame_time);


    }





    void init_animation(){
        // split texture in individual cells
        TextureRegion[][] sheet =
                TextureRegion.split((Tables.water_resources.get(type) == null ? Resources.water_bucket : Tables.water_resources.get(type)), w, h);

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

    float gridlock(int n) {
        return ((float)((n) / 50) * 50);
    }

    Rectangle gethitbox(){ return new Rectangle( x, y, w, h);}



}
