package com.arashandishgar.game.entitites;

import com.arashandishgar.game.utils.Asset;
import com.arashandishgar.game.utils.ConstantKt;
import com.arashandishgar.game.utils.Enum;
import com.arashandishgar.game.utils.Util;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.DelayedRemovalArray;
import com.badlogic.gdx.utils.TimeUtils;

public class GigaGal {

  private final String TAG = this.getClass().getName();
  //lef foot Posstion
  private final Vector2 starPostion;
  public Vector2 eyePosition;
  private Vector2 velocity;
  private Enum.Direction walkDirection;
  private Enum.JumpState jumpState;
  private Enum.WalkState walkState;
  private long startJumpingTime;
  private long startWalkingTime;
  private Asset.GigaGalAsset gigaGalAsset;
  private TextureAtlas.AtlasRegion currentTextureRegion;
  private Vector2 lastFramPosition;
  public int maxBulletNumber;
  public GigaGal(Vector2 starPostion) {
    this.starPostion = starPostion;
    reset(starPostion);
  }

  public void reset(Vector2 starPostion) {

    startJumpingTime = 0;
    eyePosition = new Vector2(starPostion.x + ConstantKt.getGIGAGAL_STANCE_WIDTH() / 2, starPostion.y + 10 + ConstantKt.getGIGAGAL_EYE_HEIGHT());
    lastFramPosition = new Vector2();
    velocity = new Vector2();
    walkDirection = Enum.Direction.Right;
    gigaGalAsset = Asset.GigaGalAsset.INSTANCE;
    jumpState = Enum.JumpState.Falling;
    walkState = Enum.WalkState.Standing;
    startWalkingTime = 0;
    maxBulletNumber=ConstantKt.getGIGAGAL_MAX_BULLET_NUMBER();
  }

  public void update(float delta, Array<Platform> platforms, Array<Enemy> enemies, Array<Bullet> bullets,DelayedRemovalArray<PowerUp>powerUps) {

    lastFramPosition.x = eyePosition.x;
    lastFramPosition.y = eyePosition.y;
    if ((Gdx.input.isKeyPressed(Input.Keys.RIGHT) || Gdx.input.isKeyPressed(Input.Keys.LEFT)) && !jumpState.equals(Enum.JumpState.RECOIL)) {

      walkDirection = Enum.Direction.Right;
      if (Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
        walkDirection = Enum.Direction.Left;
      }
      handleWalking(delta);
    } else {
      walkState = Enum.WalkState.Standing;
      startWalkingTime = 0;
    }

    if ((Gdx.input.isKeyPressed(Input.Keys.UP) | !jumpState.equals(Enum.JumpState.Grounded))) {
      if (!Gdx.input.isKeyPressed(Input.Keys.UP)&&!jumpState.equals(Enum.JumpState.RECOIL)) {
        jumpState = Enum.JumpState.Falling;
      }
      handleJump(delta);
    }

    handlePLatform(platforms);
    hadnleKillPlane();
    handleEnemyCollition(enemies);
    handlePowerUp(powerUps);
    if(Gdx.input.isKeyJustPressed( Input.Keys.X)&&maxBulletNumber!=0){
      fireBullet(bullets);
      maxBulletNumber--;
    }
  }
  private void handlePowerUp(DelayedRemovalArray<PowerUp> powerUps) {
    Rectangle gigaGalBound = new Rectangle(eyePosition.x - ConstantKt.getGIGAGAL_STANCE_WIDTH() / 2, eyePosition.y - ConstantKt.getGIGAGAL_EYE_HEIGHT()
      , ConstantKt.getGIGAGAL_STANCE_WIDTH(), ConstantKt.getGIGAGAL_HEIGHT());
    for(PowerUp powerUp:powerUps){
      Rectangle powerUpBond=new Rectangle(powerUp.left,powerUp.bottom,ConstantKt.getPOWER_UP_WIDTH(),ConstantKt.getPOWER_UP_HEIGHT());
      if(gigaGalBound.overlaps(powerUpBond)){
        maxBulletNumber+=ConstantKt.getPOWER_UP_NUMBER();
        powerUp.isActive=false;
      }
    }
  }

  private void fireBullet(Array<Bullet> bullets) {
    float left;
    if( walkDirection.equals(Enum.Direction.Right)){
     left= eyePosition.x+ConstantKt.getGIGAGAL_CANNON_OFFSET_FROM_EYE().x;
    }else{
     left= eyePosition.x-ConstantKt.getGIGAGAL_CANNON_OFFSET_FROM_EYE().x;
    }
    bullets.add(new Bullet(left
      ,eyePosition.y+ConstantKt.getGIGAGAL_CANNON_OFFSET_FROM_EYE().y, walkDirection));
  }


  private void handleEnemyCollition(Array<Enemy> enemies) {
    Rectangle gigaGalBound = new Rectangle(eyePosition.x - ConstantKt.getGIGAGAL_STANCE_WIDTH() / 2, eyePosition.y - ConstantKt.getGIGAGAL_EYE_HEIGHT()
      , ConstantKt.getGIGAGAL_STANCE_WIDTH(), ConstantKt.getGIGAGAL_HEIGHT());
    for (Enemy enemy : enemies) {
      //just for enemy circel
      /*Rectangle enemyBound=new Rectangle(enemy.centerPostion.x-ConstantKt.getENEMY_WIDTH()/2,enemy.centerPostion.y-ConstantKt.getENEMY_HEIGHT()/2
        ,ConstantKt.getENEMY_WIDTH(),ConstantKt.getENEMY_HEIGHT());*/
      Rectangle enemyBound = new Rectangle(enemy.centerPostion.x - ConstantKt.getENEMY_CIRCLE_RAIDIUS(),
        enemy.centerPostion.y - ConstantKt.getENEMY_CIRCLE_RAIDIUS(),
        ConstantKt.getENEMY_CIRCLE_RAIDIUS() * 2, ConstantKt.getENEMY_CIRCLE_RAIDIUS() * 2);
      if (gigaGalBound.overlaps(enemyBound)) {
        jumpState = Enum.JumpState.RECOIL;
        velocity.y = ConstantKt.getGigaGal_ENEMY_KNOCKBACK().y;
        startJumpingTime = TimeUtils.nanoTime();
        if (eyePosition.x >= enemy.centerPostion.x) {
          velocity.x = ConstantKt.getGigaGal_ENEMY_KNOCKBACK().x;
          return;
        }
        velocity.x = -ConstantKt.getGigaGal_ENEMY_KNOCKBACK().x;
        return;
      }

    }
  }

  private void hadnleKillPlane() {
    if (eyePosition.y < ConstantKt.getKILL_PLANE()) reset(starPostion);
  }


  private void handlePLatform(Array<Platform> platforms) {
    for (Platform platform : platforms) {
      if (checkCanlandOnThePlatForm(platform) && chackWidthOnPlatform(platform, eyePosition)&&!jumpState.equals(Enum.JumpState.Grounded)) {
        if (jumpState.equals(Enum.JumpState.RECOIL)) {
          velocity.x = 0;
        }
        velocity.y = 0;
        eyePosition.y = platform.top + ConstantKt.getGIGAGAL_EYE_HEIGHT();
        jumpState = Enum.JumpState.Grounded;

        return;
      } else if (chackOnThePlatfrom(platform, lastFramPosition) && !chackWidthOnPlatform(platform, eyePosition)) {
        jumpState = Enum.JumpState.Falling;
      }

    }
  }

  private boolean chackOnThePlatfrom(Platform platform, Vector2 lastFramPosition) {
    return lastFramPosition.y - ConstantKt.getGIGAGAL_EYE_HEIGHT() == platform.top;
  }

  private boolean chackWidthOnPlatform(Platform platform, Vector2 position) {
    return checkLeft(platform, position) && checkRight(platform, position);
  }

  private boolean checkRight(Platform platform, Vector2 position) {
    return position.x - ConstantKt.getGIGAGAL_EYE_POSITION().x / 2 <= platform.right;
  }

  private boolean checkLeft(Platform platform, Vector2 position) {
    return position.x + ConstantKt.getGIGAGAL_EYE_POSITION().x / 2 >= platform.left;
  }

  private boolean checkCanlandOnThePlatForm(Platform platform) {
    return lastFramPosition.y - ConstantKt.getGIGAGAL_EYE_HEIGHT() >= platform.top && eyePosition.y - ConstantKt.getGIGAGAL_EYE_HEIGHT() <= platform.top;
  }

  private void handleWalking(float delta) {
    // refer to previous frame WalkState
    if (walkState.equals(Enum.WalkState.Standing) && !jumpState.equals(Enum.JumpState.Grounded)) {
      startWalkingTime = TimeUtils.nanoTime();
    } else if (jumpState.equals(Enum.JumpState.Grounded)) {
      startWalkingTime = 0;
    }
    walkState = Enum.WalkState.Walking;
    switch (walkDirection) {
      case Left:
        moveLeft(delta);
        break;
      case Right:
        moveRight(delta);
        break;
    }
  }

  private void handleJump(float delta) {
    switch (jumpState) {
      case Grounded:
        startJumping(delta);
        break;
      case RECOIL:
        recoil(delta);
        break;
      case Jumping:
        jumping(delta);
        break;
      case Falling:
        falling(delta);
        break;
    }

  }

  private void recoil(float delta) {
    eyePosition.x += velocity.x * delta;
    eyePosition.y += (velocity.y -= ConstantKt.getGRAVITY()) * delta;
  }

  private void falling(float delta) {
    velocity.y -= ConstantKt.getGRAVITY();
    //velocity become negetive if subtrack instead of add it cause y increase instead of decrease
    eyePosition.y += velocity.y * delta;
  }

  private void jumping(float delta) {
    float elpaseedTime = TimeUtils.nanoTime();
    if ((elpaseedTime - startJumpingTime) * MathUtils.nanoToSec > ConstantKt.getGIGAGAL_MAX_JUMPTIME()) {
      jumpState = Enum.JumpState.Falling;
      falling(delta);
      return;
    }
    eyePosition.y += velocity.y * delta;
  }

  private void startJumping(float delta) {
    jumpState = Enum.JumpState.Jumping;
    startJumpingTime = TimeUtils.nanoTime();
    velocity.y = ConstantKt.getGIGAGAL_MOVMENT_SPEED().y;
    jumping(delta);
  }

  private void moveLeft(float delta) {
    eyePosition.add(-ConstantKt.getGIGAGAL_MOVMENT_SPEED().x * delta, 0);
  }

  private void moveRight(float delta) {
    eyePosition.add(ConstantKt.getGIGAGAL_MOVMENT_SPEED().x * delta, 0);
  }

  public void render(SpriteBatch batch) {
    float elpassedTimeForWalking = MathUtils.nanoToSec * TimeUtils.timeSinceNanos(startWalkingTime);
    //first jump check afer walk
    if (walkDirection.equals(Enum.Direction.Right)) {
      if (!jumpState.equals(Enum.JumpState.Grounded)) {
        currentTextureRegion = gigaGalAsset.getJumpingRight();
      } else if (walkState.equals(Enum.WalkState.Walking)) {

        currentTextureRegion = (TextureAtlas.AtlasRegion) gigaGalAsset.getAnimationWalkLoopRight().getKeyFrame(elpassedTimeForWalking);
      } else {
        currentTextureRegion = gigaGalAsset.getStnadingRight();

      }
    } else if (walkDirection.equals(Enum.Direction.Left)) {
      if (!jumpState.equals(Enum.JumpState.Grounded)) {
        currentTextureRegion = gigaGalAsset.getJumpingLeft();
      } else if (walkState.equals(Enum.WalkState.Walking)) {
        currentTextureRegion = (TextureAtlas.AtlasRegion) gigaGalAsset.getAnimationWalkLoopLeft().getKeyFrame(elpassedTimeForWalking);
      } else {
        currentTextureRegion = gigaGalAsset.getStnadingLeft();

      }
    }
    Util.drawing(batch,currentTextureRegion,eyePosition,ConstantKt.getGIGAGAL_EYE_POSITION());
  }

  public void render(ShapeRenderer shapeRenderer) {
  }

}
