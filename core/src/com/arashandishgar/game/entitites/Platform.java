package com.arashandishgar.game.entitites;

import com.arashandishgar.game.utils.Asset;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

public class Platform {
  private final String TAG = this.getClass().getName();
 public   int top, bottom, left, right, width, height;



  public Platform(int left, int top, int width, int height) {
    this.top = top;
    this.height = height;
    bottom = top - height;
    this.left = left;
    right = left + width;
    this.width = width;

  }

  public void update(float delta) {

  }

  public void render(ShapeRenderer shapeRenderer) {
  }

  public void render(SpriteBatch spriteBatch) {
    float width = right - left;
    float height = top - bottom;
    Asset.PlatformAsset.INSTANCE.getPlatformNinePathch().draw(spriteBatch,left-1 , bottom-1 , width+2 , height+2 );
  }
}
