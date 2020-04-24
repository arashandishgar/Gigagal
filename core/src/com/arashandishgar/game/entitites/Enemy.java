package com.arashandishgar.game.entitites;

import com.arashandishgar.game.utils.Asset;
import com.arashandishgar.game.utils.ConstantKt;
import com.arashandishgar.game.utils.Enum;
import com.arashandishgar.game.utils.Util;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.TimeUtils;

public class Enemy {
  private static final String TAG = Enemy.class.getName();
  public  Vector2 centerPostion;
  private final Platform platform;
  private Asset.EnemyTexture enemyTexture = Asset.EnemyTexture.INSTANCE;
  private TextureRegion textureRegion;
  private Enum.Direction direction;
  private long startTime;
  public int health;

  public Enemy(Platform platform) {
    this.platform = platform;
    init();
  }
  public void init(){
    this.centerPostion = new Vector2(platform.left, platform.top + ConstantKt.getENEMY_CENTER().y);
    direction = Enum.Direction.Left;
    startTime= TimeUtils.nanoTime();
    health =ConstantKt.getENEMY_HEALTH();
  }
  public void update(float delta) {
    handleEnemyWalkDirection(delta);
    handleEnemyJumpDirection(delta);
  }

  private void handleEnemyJumpDirection(float delta) {
    //not
    float duration=1+MathUtils.sin(MathUtils.PI2*MathUtils.nanoToSec*TimeUtils.timeSinceNanos(startTime)/ConstantKt.getEMEMY_DURATION());
    centerPostion.y=platform.top+ConstantKt.getENEMY_CENTER().y+duration*ConstantKt.getMAX_ENEMY_JUMP_HEIGHT();
    /*if (jumpDirection.equals(JumpDirection.UP)) {
      centerPostion.y += ConstantKt.getENEMY_SPEED().y * delta;
      if (centerPostion.y + ConstantKt.getENEMY_CENTER().y - platform.top >= ConstantKt.getMAX_ENEMY_JUMP_HEIGHT()) {
        centerPostion.y = platform.top + ConstantKt.getMAX_ENEMY_JUMP_HEIGHT() -ConstantKt.getENEMY_CENTER().y;
        jumpDirection = JumpDirection.DOWN;
      }
    } else {
      centerPostion.y -= ConstantKt.getENEMY_SPEED().y * delta;
      if (centerPostion.y-ConstantKt.getENEMY_CENTER().y< platform.top) {
        centerPostion.y = platform.top + ConstantKt.getENEMY_CENTER().y;
        jumpDirection = JumpDirection.UP;
      }
    }*/

  }

  private void handleEnemyWalkDirection(float delta) {
    if (direction.equals(Enum.Direction.Left)) {
      centerPostion.x += delta * ConstantKt.getENEMY_SPEED().x;
      if (centerPostion.x > platform.right) {
        centerPostion.x = platform.right;
        direction = Enum.Direction.Right;
      }
    } else {
      centerPostion.x -= delta * ConstantKt.getENEMY_SPEED().x;
      if (centerPostion.x < platform.left) {
        centerPostion.x = platform.left;
        direction = Enum.Direction.Left;
      }
    }
  }

  public void render(SpriteBatch spriteBatch) {
    textureRegion = enemyTexture.getEnemy();
    Util.drawing(spriteBatch,textureRegion,centerPostion,ConstantKt.getENEMY_CENTER());
  }



}
