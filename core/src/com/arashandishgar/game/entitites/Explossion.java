package com.arashandishgar.game.entitites;

import com.arashandishgar.game.utils.Asset;
import com.arashandishgar.game.utils.ConstantKt;
import com.arashandishgar.game.utils.Util;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.TimeUtils;

public class Explossion {
  public Animation<TextureRegion> explossionAnimattion;
  public Vector2 centerPosstion;
  public boolean isActive;
  long startTime;
  private TextureRegion currentTextureRegion;

  public Explossion(Vector2 centerPosstion) {
    init(centerPosstion);
  }

  private void init(Vector2 centerPosstion) {
    explossionAnimattion = Asset.Explosion.INSTANCE.getAnimation();
    startTime= TimeUtils.nanoTime();
    this.centerPosstion=centerPosstion;
    isActive=true;
  }

  public void update(float delta){

    float elpassedTime= MathUtils.nanoToSec*TimeUtils.timeSinceNanos(startTime);
    if(explossionAnimattion.isAnimationFinished(elpassedTime)){
      isActive=false;
    }
  }
  public void render(SpriteBatch spriteBatch){
    currentTextureRegion=explossionAnimattion.getKeyFrame( MathUtils.nanoToSec*TimeUtils.timeSinceNanos(startTime));
    Util.drawing(spriteBatch,currentTextureRegion,centerPosstion, ConstantKt.getEXPLOSION_OFFSET());
  }

  public void render(ShapeRenderer shapeRenderer) {
    /*shapeRenderer.rect(centerPosstion.x,centerPosstion.y,17,17);*/
  }
}
