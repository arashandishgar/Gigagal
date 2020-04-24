package com.arashandishgar.game;

import com.badlogic.gdx.Game;

public class GigaGalGame extends Game {
  private final String TAG =this.getClass().getName();
	@Override
	public void create () {

	  setScreen(new GamePlayScreen());

	}

	
	@Override
	public void dispose () {
	}
}
