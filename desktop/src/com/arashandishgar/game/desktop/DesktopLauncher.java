package com.arashandishgar.game.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.arashandishgar.game.GigaGalGame;

public class DesktopLauncher {
  private final String TAG =this.getClass().getName();
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		new LwjglApplication(new GigaGalGame(), config);
	}
}
