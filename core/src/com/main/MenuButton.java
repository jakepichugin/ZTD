package com.main;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;

public class MenuButton {
    int x, y, w, h;
    Color color;
    String type;
    BitmapFont font;
    GlyphLayout layout;

    MenuButton(String type, int x, int y, int w, int h, Color color){
        this.type = type;
        this.color = color;
        this.x = x;
        this.y = y;
        this.w = w;
        this.h = h;
        font = new BitmapFont();
        font.setColor(Resources.inverse_color(color));

        layout = new GlyphLayout();
        while(layout.width < w - (4 * (float)(w / 10)) && layout.height < h - (3 * (float)(h / 10))){
            font.getData().setScale(font.getData().scaleX + 0.1f);
            layout.setText(font, type);
        }


    }


    void draw(SpriteBatch batch) {
        batch.draw(Resources.createTexture(color), x, y, w, h);
        font.draw(batch, layout,  x + (float)w / 2 - layout.width / 2, y + (float)h / 2 + layout.height / 2);
    }
    Rectangle hitbox() {
        return new Rectangle(x, y, w, h);
    }

}
