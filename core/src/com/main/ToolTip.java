package com.main;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;

public class ToolTip {
    int x, y, w, h;
    String type;
    Boolean hidden = true;
    Button close;
    BitmapFont font = new BitmapFont();
    GlyphLayout layout = new GlyphLayout();


    ToolTip(String type, Button parent){
        this.type = type;
        w = 200;
        h = 100;
        x = (parent.x + parent.w / 2) - w / 2;
        y = parent.y - h - 10;
        close = new Button("close", x + w - Resources.button_close.getWidth() - 1, y + h - Resources.button_close.getHeight() - 1);
        close.locked = false;

    }

    void draw(SpriteBatch batch){
        if (hidden) return;
        batch.draw(Resources.tooltip_bg, x, y, w, h);
        close.draw(batch);

        String[] words = "Fires some bullets at some rate of fire.".split(" ");
        int rx = 35, ry = 5; // relative position of the text to the position of the tooltip
        for(String s : words){
            if(rx + layout.width >= w - 25) {
                rx = 35;
                ry += layout.height + 5;
            }
            font.setColor(Color.MAROON);
            font.draw(batch, s, x + rx, y + h - ry);
            layout.setText(font, " " + s);
            rx += layout.width;
        }
    }

    Rectangle gethitbox(){ return new Rectangle( x, y, w, h);}

}
