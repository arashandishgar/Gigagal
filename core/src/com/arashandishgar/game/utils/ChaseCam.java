package com.arashandishgar.game.utils;

import com.arashandishgar.game.entitites.GigaGal;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Camera;

public class ChaseCam {
  private final String TAG =this.getClass().getName() ;
  public Camera camera;
  private GigaGal target;
  private boolean toggle=false;

  public ChaseCam(Camera camera, GigaGal target) {
    this.camera = camera;
    this.target = target;
  }
  public void reset(GigaGal target){
    this.target = target;
  }
  public void update(float delta) {
    if (Gdx.input.isKeyJustPressed(Input.Keys.SPACE)) {
      toggle = !toggle;
    }
    if (toggle) {
      float x = camera.position.x;
      float y = camera.position.y;
      if (Gdx.input.isKeyPressed(Input.Keys.W)) y += ConstantKt.getCAMERA_DEBUG_SPEED().y * delta;
      if (Gdx.input.isKeyPressed(Input.Keys.S)) y -= ConstantKt.getCAMERA_DEBUG_SPEED().y * delta;
      if (Gdx.input.isKeyPressed(Input.Keys.A)) x -= ConstantKt.getCAMERA_DEBUG_SPEED().x * delta;
      if (Gdx.input.isKeyPressed(Input.Keys.D)) x += ConstantKt.getCAMERA_DEBUG_SPEED().x * delta;
      camera.position.set(x, y, 0);
    } else {
      camera.position.set(target.eyePosition.x, target.eyePosition.y, 0);

    }
  }
}
