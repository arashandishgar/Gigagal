package com.arashandishgar.game;

import com.arashandishgar.game.utils.Asset;
import com.arashandishgar.game.utils.ChaseCam;
import com.arashandishgar.game.utils.ConstantKt;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.utils.viewport.ExtendViewport;

public class GamePlayScreen extends ScreenAdapter {
  private final String TAG =this.getClass().getName();
  ExtendViewport extendViewport;
  Asset asset=Asset.INSTANCE;
  SpriteBatch spriteBatch;
  ShapeRenderer shapeRenderer;
  Level level;
  ChaseCam chaseCam;
  @Override
  public void show() {
    spriteBatch=new SpriteBatch();
    extendViewport=new ExtendViewport(ConstantKt.getWORLD_SIZE(),ConstantKt.getWORLD_SIZE());
    shapeRenderer=new ShapeRenderer();
    level=new Level(extendViewport);
    chaseCam=new ChaseCam(extendViewport.getCamera(),level.gigaGal);
    shapeRenderer.setAutoShapeType(true);
  }

  @Override
  public void resize(int width, int height) {
    super.resize(width, height);
    extendViewport.update(width,height,true);
    level.init();
    chaseCam.reset(level.gigaGal);


  }

  @Override
  public void hide() {
    super.hide();
    asset.dispose();
    shapeRenderer.dispose();
    spriteBatch.dispose();
  }

  @Override
  public void dispose() {
    super.dispose();
  }

  @Override
  public void render(float delta) {
    Color color=ConstantKt.getBACK_GROUND_COLOR();
    Gdx.gl.glClearColor(color.r,color.g,color.b,color.a);
    Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
    level.update(delta);
    chaseCam.update(delta);
    extendViewport.apply();
    spriteBatch.setProjectionMatrix(extendViewport.getCamera().combined);
    shapeRenderer.setProjectionMatrix(extendViewport.getCamera().combined);
    spriteBatch.begin();
    level.render(spriteBatch);
    spriteBatch.end();
    shapeRenderer.begin();
    level.render(shapeRenderer);
    shapeRenderer.end();
  }
}