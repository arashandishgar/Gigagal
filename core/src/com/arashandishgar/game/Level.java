package com.arashandishgar.game;

import com.arashandishgar.game.entitites.Bullet;
import com.arashandishgar.game.entitites.Enemy;
import com.arashandishgar.game.entitites.Explossion;
import com.arashandishgar.game.entitites.GigaGal;
import com.arashandishgar.game.entitites.Platform;
import com.arashandishgar.game.entitites.PowerUp;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.DelayedRemovalArray;
import com.badlogic.gdx.utils.viewport.ExtendViewport;

class Level {
  private final String TAG = this.getClass().getName();
  public GigaGal gigaGal;
  private Array<Platform> platforms;
  private DelayedRemovalArray<Enemy> enemies;
  private DelayedRemovalArray<Bullet> bullets;
  private DelayedRemovalArray<Explossion> explossions;
  private DelayedRemovalArray<PowerUp> powerUps;
  ExtendViewport extendViewport;

  public Level(ExtendViewport extendViewport) {
    this.extendViewport = extendViewport;
    init();
  }

  void init() {
    platforms = new Array<>();
    enemies = new DelayedRemovalArray<>();
    bullets = new DelayedRemovalArray<>();
    explossions=new DelayedRemovalArray<>();
    powerUps=new DelayedRemovalArray<>();
    addDebugLevel(platforms);
    addDebugPowerUp(powerUps,platforms);
    gigaGal = new GigaGal(new Vector2(platforms.get(0).left, platforms.get(0).top));

  }


  public void update(float delta) {
    for (Platform platform : platforms) {
      platform.update(delta);
    }
    gigaGal.update(delta, platforms, enemies, bullets,powerUps);
    bullets.begin();
    for (int i = 0; i < bullets.size; i++) {
      Bullet bullet = bullets.get(i);
      bullet.update(delta, extendViewport,enemies,explossions);
      if (!bullet.isActive) {
        bullets.removeIndex(i);
      }
    }
    bullets.end();
    explossions.begin();
    for(Explossion explossion:explossions){
      explossion.update(delta);
      if(!explossion.isActive){
        explossions.removeValue(explossion,false);
      }
    }
    explossions.end();
    enemies.begin();
    for (Enemy enemy : enemies) {
      enemy.update(delta);
      if(enemy.health<1){
        enemies.removeValue(enemy,true);
        explossions.add(new Explossion(enemy.centerPostion));
      }
    }
    enemies.end();
    powerUps.begin();
    for(PowerUp powerUp:powerUps){
      powerUp.update(delta);
      if(!powerUp.isActive){
        powerUps.removeValue(powerUp,false);
      }
    }
    powerUps.end();
  }

  public void render(SpriteBatch spriteBatch) {
    for (Platform platform : platforms) {
      platform.render(spriteBatch);
    }
    for (Enemy enemy : enemies) {
      enemy.render(spriteBatch);
    }
    gigaGal.render(spriteBatch);
    for (Bullet bullet : bullets) {
      bullet.render(spriteBatch);

    }
    for(PowerUp powerUp:powerUps){
      powerUp.render(spriteBatch);
    }
    for(Explossion explossion:explossions){
      explossion.render(spriteBatch);
    }

  }

  public void render(ShapeRenderer shapeRenderer) {
    for (Platform platform : platforms) {
      platform.render(shapeRenderer);
    }
    for (PowerUp powerUp : powerUps) {
      powerUp.render(shapeRenderer);
    }
    gigaGal.render(shapeRenderer);
  }

  private void addDebugLevel(Array<Platform> platforms) {
    platforms.add(new Platform(15, 15, 9, 9));
    platforms.add(new Platform(100, 90, 100, 65));
    platforms.add(new Platform(35, 55, 50, 20));
    platforms.add(new Platform(210, 140, 50, 20));
    platforms.add(new Platform(210, 140, 50, 20));
    platforms.add(new Platform(270, 140, 50, 20));
    platforms.add(new Platform(340, 140, 50, 20));
    platforms.add(new Platform(400, 140, 50, 20));

    enemies.add(new Enemy(this.platforms.get(1)));
    enemies.add(new Enemy(this.platforms.get(2)));
    enemies.add(new Enemy(this.platforms.get(3)));
    enemies.add(new Enemy(this.platforms.get(4)));
    enemies.add(new Enemy(this.platforms.get(5)));
  }
  private void addDebugPowerUp(DelayedRemovalArray<PowerUp> powerUps, Array<Platform> platforms) {
    powerUps.add(new PowerUp(platforms.get(2).left+10,platforms.get(2).top));
    powerUps.add(new PowerUp(platforms.get(3).left+10,platforms.get(3).top));
    powerUps.add(new PowerUp(platforms.get(3).left+10,platforms.get(4).top));
    powerUps.add(new PowerUp(platforms.get(3).left+10,platforms.get(5).top));
  }
}
