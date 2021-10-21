package com.main;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.TextureArray;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;

public class Zombie {
    int x, y, w, h, speed, hp;
    String type;
    boolean active = true;

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
        hp = 5;

        rows = 1;
        cols = 4;
        w = Tables.zombie_resources.get(type) == null ? Resources.zombie.getWidth() / cols : Tables.zombie_resources.get(type).getWidth() / cols;
        h = Tables.zombie_resources.get(type) == null ? Resources.zombie.getHeight() / rows : Tables.zombie_resources.get(type).getHeight() / rows;
        init_animation();
    }

    void draw(SpriteBatch batch){
        frame_time += Gdx.graphics.getDeltaTime();
        frame = (TextureRegion)anim.getKeyFrame(frame_time, true);
        batch.draw(frame, x, y);

    }

    void update(){
        x -= speed;
        active = x + w > 0 && hp > 0;
    }

    void init_animation(){
        // split texture in individual cells
        TextureRegion[][] sheet =
                TextureRegion.split(Tables.zombie_resources.get(type) == null ? Resources.zombie : Tables.zombie_resources.get(type), w, h);

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
    Rectangle gethitbox(){ return new Rectangle( x, y, w, h);}

}
