package com.corvstudios.argame;

import java.nio.FloatBuffer;

import javax.microedition.khronos.opengles.GL10;

import android.util.Log;

import edu.dhbw.andar.ARObject;
import edu.dhbw.andar.util.GraphicsUtil;

public class Enemies extends ARObject {

	public Enemies(String name, String patternName, double markerWidth, double[] markerCenter) {
		super(name, patternName, markerWidth, markerCenter);
		float   mat_ambientf[]     = {1.0f, 1.0f, 1.0f, 1.0f};
		float   mat_flashf[]       = {1.0f, 1.0f, 1.0f, 1.0f};
		float   mat_diffusef[]     = {1.0f, 1.0f, 1.0f, 1.0f};
		float   mat_flash_shinyf[] = {50.0f};

		mat_ambient = GraphicsUtil.makeFloatBuffer(mat_ambientf);
		mat_flash = GraphicsUtil.makeFloatBuffer(mat_flashf);
		mat_flash_shiny = GraphicsUtil.makeFloatBuffer(mat_flash_shinyf);
		mat_diffuse = GraphicsUtil.makeFloatBuffer(mat_diffusef);
		
	}
	public Enemies(String name, String patternName, double markerWidth, double[] markerCenter, float[] customColor) {
		super(name, patternName, markerWidth, markerCenter);
		float   mat_flash_shinyf[] = {50.0f};

		mat_ambient = GraphicsUtil.makeFloatBuffer(customColor);
		mat_flash = GraphicsUtil.makeFloatBuffer(customColor);
		mat_flash_shiny = GraphicsUtil.makeFloatBuffer(mat_flash_shinyf);
		mat_diffuse = GraphicsUtil.makeFloatBuffer(customColor);
		
	}
	
	private float legHeight = 30, torsoHeight = 30, headHeight = 20, torsoWidth = 20, armWidth = 10;
	private RectPrism leg = new RectPrism(10, 10, legHeight);
	private RectPrism torso = new RectPrism(torsoWidth, 10, torsoHeight);
	private RectPrism head = new RectPrism(20, 20, headHeight);
	private RectPrism arm = new RectPrism(armWidth, 10, 30);
	private FloatBuffer mat_flash;
	private FloatBuffer mat_ambient;
	private FloatBuffer mat_flash_shiny;
	private FloatBuffer mat_diffuse;
	
	private float x_pos = 0;
	private float x_vel = 2f;
	
	private SpriteLibrary spriteLibrary;
    
	@Override
	public void init(GL10 gl) {
	}
	
	public final void advance() {
		/*x_pos += x_vel;
		if( x_pos > 100) {
			x_vel *= -1;
			x_pos = 100;
		} else if( x_pos < 0 ){
			x_vel *= -1;
			x_pos = 0;
		}*/
	}
	
	/**
	 * Everything drawn here will be drawn directly onto the marker,
	 * as the corresponding translation matrix will already be applied.
	 */
	@Override
	public final void draw(GL10 gl) {
		super.draw(gl);
		
		spriteLibrary.getSkin(0).bindTexture(gl);
		
		gl.glMaterialfv(GL10.GL_FRONT_AND_BACK, GL10.GL_SPECULAR, mat_flash);
		gl.glMaterialfv(GL10.GL_FRONT_AND_BACK, GL10.GL_SHININESS, mat_flash_shiny);	
		gl.glMaterialfv(GL10.GL_FRONT_AND_BACK, GL10.GL_DIFFUSE, mat_diffuse);	
		gl.glMaterialfv(GL10.GL_FRONT_AND_BACK, GL10.GL_AMBIENT, mat_ambient);
		
	    gl.glTranslatef( x_pos, 0.0f, legHeight);
	    
	    //draw the body
	    gl.glPushMatrix();
		    gl.glTranslatef(-10, 0.0f, 0.0f);
		    leg.draw(gl);
		    gl.glTranslatef(20, 0.0f, 0.0f);
		    leg.draw(gl);
	    gl.glPopMatrix();
	    
	    gl.glTranslatef(0.0f, 0.0f, legHeight+torsoHeight);
	    torso.draw(gl);

	    //right arm
	    gl.glPushMatrix();
	    	gl.glTranslatef(-(torsoWidth+armWidth), 0.0f, 0.0f);
	    	arm.draw(gl);
	    gl.glPopMatrix();
	    
	    //left arm
	    gl.glPushMatrix();
	    	gl.glTranslatef(torsoWidth+armWidth, 0.0f, 0.0f);
	    	arm.draw(gl);
	    gl.glPopMatrix();

	    gl.glTranslatef(0.0f, 0.0f, torsoHeight+headHeight);
	    head.draw(gl);
	}
	
	public void setSpriteLibrary(SpriteLibrary sLib) {
		spriteLibrary = sLib;
		
		float width = sLib.getSkin(0).getWidth();
		float height = sLib.getSkin(0).getHeight();
		Log.v("Enemies", width+" "+height);

	    float[] legTexture = {
	    			// TOP
	    			4/width, 16/height,
	    			4/width, 20/height,
	    			8/width, 16/height,
	    			8/width, 20/height,
		    		// BOTTOM
	    			8/width, 16/height,
	    			8/width, 20/height,
	    			12/width, 16/height,
	    			12/width, 20/height,
		    		// RIGHT
		    		4/width, 20/height,
		    		0.0f, 20/height,
		    		4/width, 1.0f,
		    		0.0f, 1.0f,
		    		// LEFT
		    		8/width, 1.0f,
		    		12/width, 1.0f,
		    		8/width, 20/height,
		    		12/width, 20/height,
		    		// BACK
		    		16/width, 20/height,
		    		12/width, 20/height,
		    		16/width, 1.0f,
		    		12/width, 1.0f,
		    		// FRONT
		    		4/width, 20/height,
		    		4/width, 1.0f,
		    		8/width, 20/height,
		    		8/width, 1.0f
				};
		leg.setTextureCoords(legTexture);
		
        float[] torsoTexture = {
        			// TOP
        			20/width, 16/height,
        			20/width, 20/height,
        			28/width, 16/height,
        			28/width, 20/height,
    	    		// BOTTOM
        			28/width, 0.0f,
        			28/width, 20/height,
        			36/width, 0.0f,
        			36/width, 20/height,
    	    		// LEFT
    	    		32/width, 20/height,
    	    		28/width, 20/height,
    	    		32/width, 1.0f,
    	    		28/width, 1.0f,
    	    		// RIGHT
    	    		16/width, 1.0f,
    	    		20/width, 1.0f,
    	    		16/width, 20/height,
    	    		20/width, 20/height,
    	    		// BACK
    	    		40/width, 20/height,
    	    		32/width, 20/height,
    	    		40/width, 1.0f,
    	    		32/width, 1.0f,
    	    		// FRONT
    	    		20/width, 20/height,
    	    		20/width, 1.0f,
    	    		28/width, 20/height,
    	    		28/width, 1.0f
    			};
        torso.setTextureCoords(torsoTexture);
		
        float[] headTexture = {
	    			// TOP
        			8/width, 0.0f,
        			8/width, 8/height,
        			16/width, 0.0f,
        			16/width, 8/height,
		    		// BOTTOM
        			16/width, 0.0f,
        			16/width, 8/height,
        			24/width, 0.0f,
        			24/width, 8/height,
		    		// LEFT
		    		24/width, 8/height,
		    		16/width, 8/height,
		    		24/width, 16/height,
		    		16/width, 16/height,
		    		// RIGHT
		    		0.0f, 16/height,
		    		8/width, 16/height,
		    		0.0f, 8/height,
		    		8/width, 8/height,
		    		// BACK
		    		32/width, 8/height,
		    		24/width, 8/height,
		    		32/width, 16/height,
		    		24/width, 16/height,
		    		// FRONT
		    		8/width, 8/height,
		    		8/width, 16/height,
		    		16/width, 8/height,
		    		16/width, 16/height
    			};
        head.setTextureCoords(headTexture);
        
        float[] armTexture = {
	    			// TOP
        			44/width, 16/height,
        			44/width, 20/height,
        			48/width, 16/height,
        			48/width, 20/height,
		    		// BOTTOM
        			48/width, 16/height,
        			48/width, 20/height,
        			52/width, 16/height,
        			52/width, 20/height,
		    		// LEFT
		    		52/width, 20/height,
		    		48/width, 20/height,
		    		52/width, 1.0f,
		    		48/width, 1.0f,
		    		// RIGHT
		    		40/width, 1.0f,
		    		44/width, 1.0f,
		    		40/width, 20/height,
		    		44/width, 20/height,
		    		// BACK
		    		56/width, 20/height,
		    		52/width, 20/height,
		    		56/width, 1.0f,
		    		52/width, 1.0f,
		    		// FRONT
		    		44/width, 20/height,
		    		44/width, 1.0f,
		    		48/width, 20/height,
		    		48/width, 1.0f
    			};
        arm.setTextureCoords(armTexture);
	}
}
