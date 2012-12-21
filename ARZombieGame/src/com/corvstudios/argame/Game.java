package com.corvstudios.argame;

import edu.dhbw.andar.ARObject;
import android.util.Log;

public class Game {
	
	Enemies enemies;
	Player player;
	SpriteLibrary spriteLibrary;

	public Game() {
		enemies = new Enemies("test", "patt.hiro", 80.0, new double[]{0,0});
		player = new Player("test", "saw.pat", 80.0, new double[]{0,0});
	}
	public void advance() {
		enemies.advance();
		player.advance();
	}
	
	public void setTouch(boolean touch, float x, float y) {
		Log.v("Game", "Touch: "+touch+" "+x+" "+y);
	}
	public void setSpriteLibrary(SpriteLibrary sLib) {
		spriteLibrary = sLib;
		enemies.setSpriteLibrary(sLib);
		player.setSpriteLibrary(sLib);
	}
	
	public ARObject getARObject(int index) {
		switch(index) {
		case 0:	return enemies;
		case 1: return player;
		default: return null;
		}
	}
}
