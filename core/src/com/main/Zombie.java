package com.main;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.TextureArray;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class Zombie {
    int x, y, w, h, speed;
    String type;

    // ANIMATION VARIABLES
    int rows, cols;
    Animation anim;
    TextureRegion[] frames;
    TextureRegion frame;
    float frame_time = 0.2f;

    Zombie(String type, int x, int y, int speed) {
        this.type = type;
        this. x = x;
        this. y = y;
        this.speed = speed;

        rows = 1;
        cols = 4;
        w = Resources.zombie.getWidth() / cols;
        h = Resources.zombie.getHeight() / rows;
        init_animation();
    }

    void draw(SpriteBatch batch){
        frame_time += Gdx.graphics.getDeltaTime();
        frame = (TextureRegion)anim.getKeyFrame(frame_time, true);
        batch.draw(frame, x, y);

    }

    void update(){
        x -= speed;
    }

    void init_animation(){
        // split texture in individual cells
        TextureRegion[][] sheet = TextureRegion.split(Resources.zombie, Resources.zombie.getWidth() / cols, Resources.zombie.getHeight() / rows);

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
