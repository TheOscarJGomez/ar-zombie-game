package com.corvstudios.argame;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

public class CustomView extends View {
	
	Game game;
	
	public CustomView(Context context, AttributeSet attrs, Game g) {
		super(context,attrs);
		setFocusableInTouchMode(true);
		game = g;
	}
	
	@Override
	public boolean onTouchEvent(final MotionEvent event) {
		if(event.getAction()==MotionEvent.ACTION_DOWN || event.getAction()==MotionEvent.ACTION_MOVE) {
			game.setTouch(true,event.getX(),event.getY());
		}
		else if(event.getAction()==MotionEvent.ACTION_UP) {
			game.setTouch(false,event.getX(),event.getY());
		}
		return true;
	}
}
