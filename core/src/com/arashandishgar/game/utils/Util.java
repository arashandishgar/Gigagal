package com.arashandishgar.game.utils;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;

public class Util {
  public static void drawing(SpriteBatch spriteBatch, TextureRegion textureRegion, Vector2 posstion, Vector2 offset) {
    spriteBatch.draw(
      textureRegion.getTexture(),
      posstion.x - offset.x,
      posstion.y - offset.y,
      0,
      0,
      textureRegion.getRegionWidth(),
      textureRegion.getRegionHeight(),
      1,
      1,
      0,
      textureRegion.getRegionX(),
      textureRegion.getRegionY(),
      textureRegion.getRegionWidth(),
      textureRegion.getRegionHeight(),
      false,
      false);
  }
  public static void logDebug(Object o){
    String className=Thread.currentThread().getStackTrace()[2].getClassName();
    String methodName=Thread.currentThread().getStackTrace()[2].getMethodName();
    String fileName=Thread.currentThread().getStackTrace()[2].getFileName();
    int lineNumber=Thread.currentThread().getStackTrace()[2].getLineNumber();
    Gdx.app.log(fileName+" : "+className+" : "+methodName+" : "+ lineNumber,o.toString());
  }
}
