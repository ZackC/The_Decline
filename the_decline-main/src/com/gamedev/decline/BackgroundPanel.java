// Package Declaration //
package com.gamedev.decline;

// Java Package Support //
// { Not Applicable }

// Badlogic Package Support //
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

/**
 * com/gamedev/decline/BackgroundPanel.java
 * 
 * @author(s) : Ian Middleton, Zach Coker, Zach Ogle
 * @version : 1.0 Last Update : 4/19/2013 Update By : Zach Ogle
 * 
 * Source code for the BackgroundPanel class. The BackgroundPanel class
 *	represents the BackgroundPanel in the game.
 * 
 */
public class BackgroundPanel
{

	// Global Singleton //
	// { Not Applicable }

	// Constants //
	public static final int HEIGHT = 600;
	public static final int WIDTH = 1800;

	// Internal Variables //
	private Texture texture;
	private float xPos = 0;

	/**
	 * Default constructor for BackgroundPanel.
	 * 
	 * @param newTexture : The image for the BackgroundPanel.
	 */
	public BackgroundPanel(Texture newTexture)
	{
		texture = newTexture;
	}

	/**
	 * Gets the x position of the BackgroundPanel.
	 * 
	 * @return : The x position of the BackgroundPanel.
	 */
	public float getXPos()
	{
		return xPos;
	}

	/**
	 * Sets the x position of the BackgroundPanel.
	 * 
	 * @param newPos : The new x position of the BackgroundPanel.
	 */
	public void setXPos(float newPos)
	{
		xPos = newPos;
	}

	/**
	 * Draws the BackgroundPanel to the screen.
	 * 
	 * @param batch : The SpriteBatch to draw the BackgroundPanel.
	 */
	public void draw(SpriteBatch batch)
	{
		batch.draw(texture, xPos, 0, WIDTH, HEIGHT);
	}

	/**
	 * Returns the width of the BackgroundPanel.
	 * 
	 * @return : The width of the BackgroundPanel.
	 */
	public int getWidth()
	{
		return WIDTH;
	}

	/**
	 * Returns the height of the BackgroundPanel.
	 * 
	 * @return : The height of the BackgroundPanel.
	 */
	public int getHeight()
	{
		return HEIGHT;
	}
}