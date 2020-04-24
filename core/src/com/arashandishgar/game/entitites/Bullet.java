package com.arashandishgar.game.entitites;

import com.arashandishgar.game.utils.Asset;
import com.arashandishgar.game.utils.ConstantKt;
import com.arashandishgar.game.utils.Enum;
import com.arashandishgar.game.utils.Util;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.DelayedRemovalArray;
import com.badlogic.gdx.utils.viewport.ExtendViewport;

public class Bullet {
  public TextureRegion bulletAsset;
  public Vector2 posstion;
  public boolean isActive;
  private Enum.Direction direction;
  public Bullet(float left, float bottom, Enum.Direction direction) {
    posstion=new Vector2(left,bottom);
    init(direction);
  }
  public Bullet(Vector2 posstion, Enum.Direction direction) {
    this.posstion=posstion;
    init(direction);
  }

  private void init(Enum.Direction direction) {
    bulletAsset= Asset.Bullet.INSTANCE.getBullet();
    this.direction= direction;
    isActive=true;
  }

  public void update(float delta, ExtendViewport extendViewport, Array<Enemy> enemies, DelayedRemovalArray<Explossion> explossions){
    handleDirection(delta);
    if(checkLeft(extendViewport)||checkRight(extendViewport)){
      isActive=false;
    }
    Vector2 center=new Vector2(posstion.x+ConstantKt.getBULLET_WIDTH()/2,posstion.y+ConstantKt.getBULLET_HEIGHT()/2);
    for (Enemy enemy : enemies) {
      if(hitEnemy(enemy,center)){
        isActive=false;
        explossions.add(new Explossion(center));
        enemy.health--;
      }
    }

  }

  private boolean hitEnemy(Enemy enemy, Vector2 center) {
    return center.dst(enemy.centerPostion)<=ConstantKt.getENEMY_CIRCLE_RAIDIUS();
  }

  private boolean checkRight(ExtendViewport extendViewport) {
    return posstion.x>extendViewport.getCamera().position.x+extendViewport.getWorldWidth()/2;
  }

  private boolean checkLeft(ExtendViewport extendViewport) {
    return posstion.x<extendViewport.getCamera().position.x-extendViewport.getWorldWidth()/2;
  }

  private void handleDirection(float delta) {
    switch (direction){
      case Right:moveRight(delta);
        break;
      case Left:moveLeft(delta);
        break;
    }
  }

  private void moveLeft(float delta) {
    posstion.mulAdd(ConstantKt.getBULLET_SPEED(),-1*delta);

  }

  private void moveRight(float delta) {
    posstion.mulAdd(ConstantKt.getBULLET_SPEED(),delta);
  }

  public void render(SpriteBatch spriteBatch){
    Util.drawing(spriteBatch,bulletAsset,new Vector2(posstion.x,posstion.y),new Vector2(0,0));
  }
  public void render(ShapeRenderer shapeRenderer){
    shapeRenderer.rect(posstion.x,posstion.y,ConstantKt.getBULLET_WIDTH(),ConstantKt.getBULLET_HEIGHT());
  }

}
