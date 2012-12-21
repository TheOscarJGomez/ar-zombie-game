package com.corvstudios.argame;

import javax.microedition.khronos.opengles.GL10;

import edu.dhbw.andar.sample.R;

import android.content.Context;

public class SpriteLibrary {
	Sprite[] skins;
	Sprite[] items;
	
	public SpriteLibrary(Context context, GL10 gl) {
		ImageLoader iL = new ImageLoader();
		
		skins = new Sprite[1];
		skins[0]= iL.loadBitmap(context, gl, R.raw.spiderman);

		items = new Sprite[1];
		items[0]= iL.loadBitmap(context, gl, R.raw.tnt);
	}
	
	public Sprite getSkin(int i) {
		return skins[i];
	}
	public Sprite getItem(int i) {
		return items[i];
	}
}
