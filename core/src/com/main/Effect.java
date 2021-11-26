package com.main;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class Effect {
    String type;
    int x, y, w, h;
    boolean active = true;

    int rows, cols;
    Animation anim;
    TextureRegion[] frames;
    TextureRegion frame;
    float frame_time = 0.05f;
    float frame_time_death = 0.001f;

    Effect(String type, int x, int y){
        this.type = type;
        cols = (Tables.balance.get("cols_"+type) == null ? 1 : Tables.balance.get("cols_"+type));
        rows = 1;
        w = (Tables.resources.get(type) == null ? Resources.tap_effect : Tables.resources.get(type)).getWidth() / cols;
        h = (Tables.resources.get(type) == null ? Resources.tap_effect : Tables.resources.get(type)).getHeight() / rows;
        this.x = x - w /2;
        this.y = y - h /2;
        init_animation();
    }

    void update(){
        active = !anim.isAnimationFinished(("zombie_death".equals(type)) ? frame_time_death : frame_time );//????????????????????????????
    }

    void draw(SpriteBatch batch){
        frame_time += Gdx.graphics.getDeltaTime();
        frame = (TextureRegion)anim.getKeyFrame(frame_time, false);
        batch.draw(frame, x, y);

    }


    void init_animation(){
        // split texture in individual cells
        TextureRegion[][] sheet =
                TextureRegion.split((Tables.resources.get(type) == null ? Resources.tap_effect : Tables.resources.get(type)), w, h);

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
}
