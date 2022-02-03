package com.main;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;

public class Start extends Scene{
    MenuButton m1, m2 , m3;


    Start() {
        title = "Zombie Towerless Defence Game";
        font.getData().setScale(3f);
        layout.setText(font, title);
        m1 = new MenuButton("Stort", (1024 / 2) - (150 /2), 325, 150, 75, Color.DARK_GRAY);
        m2 = new MenuButton("About", (1024 / 2) - (150 /2), 200, 150, 75, Color.DARK_GRAY);
        m3 = new MenuButton("Exit", (1024 / 2) - (150 /2), 75, 150, 75, Color.DARK_GRAY);
    }

    void tap(int x, int y){
        if (m1.hitbox().contains(x ,y)) {
            Main.started = true;
            Main.game = new Game();

        }
        if (m2.hitbox().contains(x ,y)) {
            Main.info = true;

        }
    }

    void draw(SpriteBatch batch) {
        ScreenUtils.clear(new Color(0x00444444));

        font.draw(batch, layout, 1024 / 2 - layout.width / 2,565);
        m1.draw(batch);
        m2.draw(batch);
        m3.draw(batch);
    }

}
