package com.main;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;

public class GameOver extends Scene{


    MenuButton m1, m2;


    GameOver() {
        title = "GAMEOVER";
        font.getData().setScale(3f);
        layout.setText(font, title);
        m1 = new MenuButton("Back to start", (1024 / 2) - (150 /2), 325, 150, 75, Color.DARK_GRAY);
        m1 = new MenuButton("Try again", (1024 / 2) - (150 /2), 200, 150, 75, Color.DARK_GRAY);

    }

    void tap(int x, int y){
        if (m1.hitbox().contains(x ,y)) {
            Main.started = false;
            Main.info = false;
            Main.lose = false;
            Main.game = new Game();

        }
    }

    void draw(SpriteBatch batch) {
        ScreenUtils.clear(new Color(0x00444444));

        font.draw(batch, layout, 1024 / 2 - layout.width / 2,565);
        m1.draw(batch);

    }
}
