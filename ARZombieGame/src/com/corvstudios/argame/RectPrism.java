package com.corvstudios.argame;

import java.nio.FloatBuffer;

import javax.microedition.khronos.opengles.GL10;

import edu.dhbw.andar.util.GraphicsUtil;

public class RectPrism {
	private FloatBuffer vertexBuffer;
    private FloatBuffer normalBuffer;
    private FloatBuffer textureBuffer;
	
	public RectPrism(float w,float d,float h) {
        float vertexf[] =  {
     		   		// TOP
        			-w, -d,  h,
					 w, -d,  h,
					-w,  d,  h,
					 w,  d,  h,
			    	// BOTTOM
					-w, -d, -h,
					-w,  d, -h,
					 w, -d, -h,
					 w,  d, -h,
					// LEFT
					-w, -d,  h,
					-w,  d,  h,
					-w, -d, -h,
					-w,  d, -h,
					// RIGHT
					 w, -d, -h,
					 w,  d, -h,
					 w, -d,  h,
					 w,  d,  h,
					// BACK
					-w,  d,  h,
					 w,  d,  h,
					-w,  d, -h,
					 w,  d, -h,
					// FRONT
					-w, -d,  h,
					-w, -d, -h,
					 w, -d,  h,
					 w, -d, -h
                };
        float normalsf[] =  {
 		   			// TOP
					 0.0f, 0.0f,  1.0f,
					 0.0f, 0.0f,  1.0f,
					 0.0f, 0.0f,  1.0f,
					 0.0f, 0.0f,  1.0f,
			    	// BOTTOM
					 0.0f, 0.0f,  -1.0f,
					 0.0f, 0.0f,  -1.0f,
					 0.0f, 0.0f,  -1.0f,
					 0.0f, 0.0f,  -1.0f,
					// LEFT
					-1.0f, 0.0f,  0.0f,
					-1.0f, 0.0f,  0.0f,
					-1.0f, 0.0f,  0.0f,
					-1.0f, 0.0f,  0.0f,
					// RIGHT
					 1.0f, 0.0f,  0.0f,
					 1.0f, 0.0f,  0.0f,
					 1.0f, 0.0f,  0.0f,
					 1.0f, 0.0f,  0.0f,
					// BACK
					 0.0f, 1.0f,  0.0f,
					 0.0f, 1.0f,  0.0f,
					 0.0f, 1.0f,  0.0f,
					 0.0f, 1.0f,  0.0f,
					// FRONT
					 0.0f, -1.0f,  0.0f,
					 0.0f, -1.0f,  0.0f,
					 0.0f, -1.0f,  0.0f,
					 0.0f, -1.0f,  0.0f
                };

        float[] texturef = {
        			// TOP
        			0.0f, 0.0f,
        			0.0f, 1.0f,
        			1.0f, 0.0f,
        			1.0f, 1.0f,
    	    		// BOTTOM
    	    		0.0f, 0.0f,
    	    		0.0f, 1.0f,
    	    		1.0f, 0.0f,
    	    		1.0f, 1.0f,
    	    		// LEFT
    	    		1.0f, 0.0f,
    	    		0.0f, 0.0f,
    	    		1.0f, 1.0f,
    	    		0.0f, 1.0f,
    	    		// RIGHT
    	    		0.0f, 1.0f,
    	    		1.0f, 1.0f,
    	    		0.0f, 0.0f,
    	    		1.0f, 0.0f,
    	    		// BACK
    	    		1.0f, 0.0f,
    	    		0.0f, 0.0f,
    	    		1.0f, 1.0f,
    	    		0.0f, 1.0f,
    	    		// FRONT
    	    		0.0f, 0.0f,
    	    		0.0f, 1.0f,
    	    		1.0f, 0.0f,
    	    		1.0f, 1.0f
    			};
        
        vertexBuffer = GraphicsUtil.makeFloatBuffer(vertexf);
        normalBuffer = GraphicsUtil.makeFloatBuffer(normalsf);
        textureBuffer = GraphicsUtil.makeFloatBuffer(texturef);
	}
	public void setTextureCoords(float[] texturef) {
        textureBuffer = GraphicsUtil.makeFloatBuffer(texturef);
	}
	
	public final void draw(GL10 gl) {       
        gl.glEnableClientState(GL10.GL_VERTEX_ARRAY);
        gl.glEnableClientState(GL10.GL_NORMAL_ARRAY);
        gl.glEnableClientState(GL10.GL_TEXTURE_COORD_ARRAY);
        
        gl.glVertexPointer(3, GL10.GL_FLOAT, 0, vertexBuffer);
        gl.glNormalPointer(GL10.GL_FLOAT, 0, normalBuffer);
		gl.glTexCoordPointer(2, GL10.GL_FLOAT, 0, textureBuffer);
		
        gl.glDrawArrays(GL10.GL_TRIANGLE_STRIP, 0, 4);
        gl.glDrawArrays(GL10.GL_TRIANGLE_STRIP, 4, 4);
        gl.glDrawArrays(GL10.GL_TRIANGLE_STRIP, 8, 4);
        gl.glDrawArrays(GL10.GL_TRIANGLE_STRIP, 12, 4);
        gl.glDrawArrays(GL10.GL_TRIANGLE_STRIP, 16, 4);
        gl.glDrawArrays(GL10.GL_TRIANGLE_STRIP, 20, 4);
        
        gl.glDisableClientState(GL10.GL_VERTEX_ARRAY);
        gl.glDisableClientState(GL10.GL_NORMAL_ARRAY);
        gl.glDisableClientState(GL10.GL_TEXTURE_COORD_ARRAY);
    }
}
