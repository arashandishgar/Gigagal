package com.arashandishgar.game.entitites;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

public class ExitPortal {
    private  float left;
    private  float bottom;
    public Vector2 centerPossition;

    public ExitPortal(Vector2 centerPossition) {
        this.centerPossition = centerPossition;
    }


    public ExitPortal(float left, float bottom) {
        this.left=left;
        this.bottom=bottom;
    }

    public void upadte(float delta) {

    }

    public void render(SpriteBatch spriteBatch) {

    }
}
