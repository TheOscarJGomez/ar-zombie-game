package com.corvstudios.argame;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.widget.FrameLayout;
import edu.dhbw.andar.ARToolkit;
import edu.dhbw.andar.AndARActivity;
import edu.dhbw.andar.exceptions.AndARException;

/**
 * Example of an application that makes use of the AndAR toolkit.
 * @author Tobi
 *
 */
public class CustomActivity extends AndARActivity {

	ARToolkit artoolkit;
	CustomView touchView;
	Game game;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		
		super.onCreate(savedInstanceState);
		
		//create game type
		game = new Game();
		
		//adding touch input
		Context mContext = getApplicationContext();
		touchView = new CustomView(mContext,null,game);
		FrameLayout frame = (FrameLayout)this.findViewById(android.R.id.content);
		frame.addView(touchView);
		
		mContext = getApplicationContext();
		CustomRenderer renderer = new CustomRenderer(mContext);//optional, may be set to null
		renderer.setGame(game);
		super.setNonARRenderer(renderer);//or might be omited
		try {
			//register an object for each marker type
			artoolkit = super.getArtoolkit();
			artoolkit.registerARObject(game.getARObject(0));
			artoolkit.registerARObject(game.getARObject(1));
		} catch (AndARException ex){
			//handle the exception, that means: show the user what happened
		}		
		startPreview();
	}

	/**
	 * Inform the user about exceptions that occurred in background threads.
	 * This exception is rather severe and can not be recovered from.
	 * TODO Inform the user and shut down the application.
	 */
	@Override
	public void uncaughtException(Thread thread, Throwable ex) {
		Log.e("AndAR EXCEPTION", ex.getMessage());
		finish();
	}
}
