package com.gamedev.decline;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

/*
 * The class for one background panel.  Is used by the RepeatingBackground
 * class to make multiple background tiles of the same panel.
 */

public class BackgroundPanel {

	//The background image
	private Texture texture;
	//where to draw the image
	private float xPos = 0;
	
	/*
	 * Default constrcutor
	 * 
	 * newTexture - the background picture
	 */
	public BackgroundPanel(Texture newTexture) {
		texture = newTexture;
	}

	/*
	 * returns the x position of the background
	 */
	public float getXPos()
	{
		return xPos;
	}

	/*
	 * sets the x position of the background
	 * 
	 * newPos - the new X position of the background 
	 */
	public void setXPos(float newPos)
	{
		xPos = newPos;
	}
	
	/*
	 * The draw visitor for the background
	 * 
	 * batch - the SpriteBatch to draw the object
	 */
	public void draw(SpriteBatch batch)
	{
		batch.draw(texture, xPos, 0);
	}
	
	/*
	 * returns the width of the background panel
	 */
	public int getWidth()
	{
		return texture.getWidth();
	}
}
