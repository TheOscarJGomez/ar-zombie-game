package com.corvstudios.argame;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;

import javax.microedition.khronos.opengles.GL10;

public class Sprite {	
	private int width = 0;
	private int height = 0;
	
	private int textureID = -1;

	//rotatable sprite
	private FloatBuffer vertexBuffer;
	private FloatBuffer textureBuffer;
	private ByteBuffer indexBuffer;
	private float[] vertices;
	private float[] texture = {0.0f, 0.0f,
							1.0f, 0.0f,
							0.0f, 1.0f,
							1.0f, 1.0f};
	private byte[] indices = {0, 1, 2, 3, 2, 1};
	
	/**
     * Sprite constructor creates the object.
     *
     * @param id  identifier for the sprite given by ImageLoader.
     * @param w   width of the sprite.
     * @param h   height of the sprite.
     * @param r   if the Sprite can be rotated.
     * @param f   if the Sprite can be flipped.
     * @see ImageLoader
     */
	public Sprite(int id,int w,int h) {
		textureID = id;
		width = w;
		height = h;
		
		vertices = new float[8];
		vertices[0] = 0.0f; vertices[1] = 0.0f;
		vertices[2] = w; vertices[3] = 0.0f;
		vertices[4] = 0.0f; vertices[5] = h;
		vertices[6] = w; vertices[7] = h;
		
        //rotatable texture drawing
        vertexBuffer = ByteBuffer.allocateDirect(vertices.length*4).order(ByteOrder.nativeOrder()).asFloatBuffer();
		vertexBuffer.put(vertices);
		vertexBuffer.position(0);
		
        textureBuffer = ByteBuffer.allocateDirect(texture.length*4).order(ByteOrder.nativeOrder()).asFloatBuffer();
		textureBuffer.put(texture);
		textureBuffer.position(0);
		
        indexBuffer = ByteBuffer.allocateDirect(indices.length);
		indexBuffer.put(indices);
		indexBuffer.position(0);
	}
	
	public void bindTexture(GL10 gl) {
		gl.glBindTexture(GL10.GL_TEXTURE_2D, textureID);
	}
	
	public int getWidth() {
		return width;
	}
	public int getHeight() {
		return height;
	}
}