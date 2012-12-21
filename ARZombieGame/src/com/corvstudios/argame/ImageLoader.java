package com.corvstudios.argame;

import java.io.IOException;
import java.io.InputStream;

import javax.microedition.khronos.opengles.GL10;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.opengl.GLUtils;

public class ImageLoader
{
	// Pre-allocated arrays to use at runtime so that allocation during the
	// test can be avoided.
	private int[] mTexture = new int[1];

	// Specifies the format our textures should be converted to upon load.
	private static BitmapFactory.Options sBitmapOptions = new BitmapFactory.Options();
	
    public ImageLoader()
    {
        // Set our bitmaps to 16-bit, 565 format.
        //sBitmapOptions.inPreferredConfig = Bitmap.Config.RGB_565;
    }
    
	/** 
	 * Loads a bitmap into OpenGL and sets up the common parameters for 
	 * 2D texture maps. 
	 */
	public Sprite loadBitmap(Context context, GL10 gl, int resourceId)
	{
	    if(context != null && gl != null)
	    {
	    	//Generate one texture pointer...
	        gl.glGenTextures(1, mTexture, 0);
	        
	        //grab location index
	        int textureID = mTexture[0];
	        
			//...and bind it to our array
	        gl.glBindTexture(GL10.GL_TEXTURE_2D, textureID);
	        
			//Create Nearest Filtered Texture //GL_NEAREST
	        gl.glTexParameterf(GL10.GL_TEXTURE_2D, GL10.GL_TEXTURE_MIN_FILTER, GL10.GL_LINEAR);
	        gl.glTexParameterf(GL10.GL_TEXTURE_2D, GL10.GL_TEXTURE_MAG_FILTER, GL10.GL_LINEAR);
	
			//Different possible texture parameters, e.g. GL10.GL_CLAMP_TO_EDGE
	        gl.glTexParameterf(GL10.GL_TEXTURE_2D, GL10.GL_TEXTURE_WRAP_S, GL10.GL_REPEAT);
	        gl.glTexParameterf(GL10.GL_TEXTURE_2D, GL10.GL_TEXTURE_WRAP_T, GL10.GL_REPEAT);
			
	        // select modulate to mix texture with color for shading
	        gl.glTexEnvf(GL10.GL_TEXTURE_ENV, GL10.GL_TEXTURE_ENV_MODE, /*GL10.GL_REPLACE*/ GL10.GL_MODULATE);
	
	        InputStream is = context.getResources().openRawResource(resourceId);
	        Bitmap bitmap;
	        try
	        {
	            bitmap = BitmapFactory.decodeStream(is, null, sBitmapOptions);
	        }
	        finally
	        {
	            try
	            {
	                is.close();
	            }
	            catch(IOException e)
	            {
	                //Log.e("ImageLoaderIO", "DecodeStreamError: " + e);
	            }
	        }
	
	        GLUtils.texImage2D(GL10.GL_TEXTURE_2D, 0, bitmap, 0);
	        int width = bitmap.getWidth();
	        int height = bitmap.getHeight();
	        
	        // clean up
	        bitmap.recycle();
	
	        return new Sprite(textureID,width,height);
	    }
	    return null;
	}
}
