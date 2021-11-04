package com.main;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class UI {
    static int money = 99999;
    static int wave = 0;
    static BitmapFont font = new BitmapFont();

    static void draw(SpriteBatch batch){
        font.setColor(new Color(0.7f, 0.7f, 0.2f, 1.0f));
        font.draw(batch, "Money: " + money, 15, 585);
        font.setColor(new Color(0.9f, 0.2f, 0.2f, 1.0f));
        font.draw(batch, "Wave: " + wave, 15, 550);


    }
}
