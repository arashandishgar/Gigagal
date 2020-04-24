package com.arashandishgar.game.entitites;

import com.arashandishgar.game.utils.Asset;
import com.arashandishgar.game.utils.ConstantKt;
import com.arashandishgar.game.utils.Util;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;

public class PowerUp {
  public boolean isActive;
  private TextureRegion powrUpTexture=Asset.PowrUp.INSTANCE.getPowerUp();
  public float left, right,top,bottom;
  public PowerUp(float left, float bottom) {
    this.left = left;
    this.bottom = bottom;
    right=left+ ConstantKt.getPOWER_UP_WIDTH();
    top=bottom+ ConstantKt.getPOWER_UP_HEIGHT();
    init();
  }

  private void init() {
    powrUpTexture=Asset.PowrUp.INSTANCE.getPowerUp();
    isActive=true;
  }

  public void update(float delta){

  }
  public void render(SpriteBatch spriteBatch){
    Util.drawing(spriteBatch,powrUpTexture,new Vector2(left,bottom),new Vector2());
  }

  public void render(ShapeRenderer shapeRenderer) {
  }
}
