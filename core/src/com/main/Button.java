package com.main;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;

public class Button {
    int x, y, w, h;
    float angle;
    String type;
    boolean locked = true;
    boolean selected = false;
    ToolTip t;

    Button(String type, int x, int y) {
        this.type = type;
        this.x = x;
        this.y = y;
        w = Tables.button_resources.get(type) == null ? 50 : Tables.button_resources.get(type).getWidth();
        h = Tables.button_resources.get(type) == null ? 50 : Tables.button_resources.get(type).getHeight();
        angle = 0f;
        t = type.equals("close") ? null : new ToolTip(type, this);

    }

    void draw(SpriteBatch batch) {
        batch.draw(Tables.button_resources.get(type) == null ? Resources.button_cannon : Tables.button_resources.get(type), x, y);
        if (locked) batch.draw(Resources.locked, x, y);
        if (selected) batch.draw(Resources.selected, x - 7, y - 7);
        if (t != null) t.draw(batch);
    }

    void update() {
        angle += 10f;
    }

    Rectangle gethitbox(){ return new Rectangle( x, y, w, h);}

}
