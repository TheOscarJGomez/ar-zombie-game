package com.corvstudios.argame;

import java.nio.FloatBuffer;

import javax.microedition.khronos.opengles.GL10;

import edu.dhbw.andar.ARObject;
import edu.dhbw.andar.util.GraphicsUtil;

/**
 * An example of an AR object being drawn on a marker.
 * @author tobi
 *
 */
public class Player extends ARObject {

	public Player(String name, String patternName, double markerWidth, double[] markerCenter) {
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
	public Player(String name, String patternName, double markerWidth, double[] markerCenter, float[] customColor) {
		super(name, patternName, markerWidth, markerCenter);
		float   mat_flash_shinyf[] = {50.0f};

		mat_ambient = GraphicsUtil.makeFloatBuffer(customColor);
		mat_flash = GraphicsUtil.makeFloatBuffer(customColor);
		mat_flash_shiny = GraphicsUtil.makeFloatBuffer(mat_flash_shinyf);
		mat_diffuse = GraphicsUtil.makeFloatBuffer(customColor);
		
	}
	
	private float height = 20;
	private RectPrism box = new RectPrism(20,20,height);
	private RectPrism barrel = new RectPrism(5,30,5);
	private FloatBuffer mat_flash;
	private FloatBuffer mat_ambient;
	private FloatBuffer mat_flash_shiny;
	private FloatBuffer mat_diffuse;
	
	private SpriteLibrary spriteLibrary;

	@Override
	public void init(GL10 gl) {
	}
	
	public final void advance() {
		
	}
	
	/**
	 * Everything drawn here will be drawn directly onto the marker,
	 * as the corresponding translation matrix will already be applied.
	 */
	@Override
	public final void draw(GL10 gl) {
		super.draw(gl);
		
		spriteLibrary.getItem(0).bindTexture(gl);
		
		gl.glMaterialfv(GL10.GL_FRONT_AND_BACK, GL10.GL_SPECULAR,mat_flash);
		gl.glMaterialfv(GL10.GL_FRONT_AND_BACK, GL10.GL_SHININESS, mat_flash_shiny);	
		gl.glMaterialfv(GL10.GL_FRONT_AND_BACK, GL10.GL_DIFFUSE, mat_diffuse);	
		gl.glMaterialfv(GL10.GL_FRONT_AND_BACK, GL10.GL_AMBIENT, mat_ambient);
        //gl.glDisable(GL10.GL_LIGHTING);
			
	    //color and move
	    gl.glTranslatef( 0.0f, 0.0f, height);
	    
	    //draw the gun
	    box.draw(gl);
	    //gl.glTranslatef( 0.0f, 30.0f, 0.0f);
	    //barrel.draw(gl);
	}
	
	public void setSpriteLibrary(SpriteLibrary sLib) {
		spriteLibrary = sLib;

	    float[] tntTexture = {
	    			// TOP
	    			0.25f, 0.0f,
	    			0.25f, 1.0f,
		    		0.5f, 0.0f,
		    		0.5f, 1.0f,
		    		// BOTTOM
		    		0.5f, 0.0f,
		    		0.5f, 1.0f,
		    		0.75f, 0.0f,
		    		0.75f, 1.0f,
		    		// LEFT
		    		0.25f, 0.0f,
		    		0.0f, 0.0f,
		    		0.25f, 1.0f,
		    		0.0f, 1.0f,
		    		// RIGHT
		    		0.0f, 1.0f,
		    		0.25f, 1.0f,
		    		0.0f, 0.0f,
		    		0.25f, 0.0f,
		    		// BACK
		    		0.25f, 0.0f,
		    		0.0f, 0.0f,
		    		0.25f, 1.0f,
		    		0.0f, 1.0f,
		    		// FRONT
		    		0.0f, 0.0f,
		    		0.0f, 1.0f,
		    		0.25f, 0.0f,
		    		0.25f, 1.0f
				};
		box.setTextureCoords(tntTexture);
		barrel.setTextureCoords(tntTexture);
	}
}
