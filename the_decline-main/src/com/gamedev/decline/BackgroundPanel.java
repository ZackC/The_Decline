// Package Declaration //
package com.gamedev.decline;

// Java Package Support //
// { Not Applicable }

// Badlogic Package Support //
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

/**
 * 
 * com/gamedev/decline/BackgroundPanel.java
 * 
 * @author(s) 	: Ian Middleton, Zach Coker, Zach Ogle
 * @version 	: 1.0
 * Last Update	: 3/25/2013
 * Update By	: Ian Middleton
 * 
 * Source code for the BackgroundPanel class. The BackgroundPanel class represents the BackgroundPanel
 *  in the game.
 * 
 */
public class BackgroundPanel {

	// Global Singleton //
	// { Not Applicable }
	
	// Constants //
	public static final int HEIGHT = 600;
	public static final int WIDTH = 1000;
	
	// Internal Variables //
	private Texture texture;
	private float xPos = 0;
	
	/**
	 * Default constructor
	 * 
	 * @param newTexture - The image for the BackgroundPanel.
	 */
	public BackgroundPanel(Texture newTexture) {
		texture = newTexture;
	}// end BackgroundPanel()

	/**
	 * Gets the x position of the background.
	 * 
	 * @return	: The x position of the background.
	 */
	public float getXPos()
	{
		return xPos;
	}// end getXPos()

	/**
	 * Sets the x position of the background.
	 * 
	 * @param newPos - The new x position of the background. 
	 */
	public void setXPos(float newPos)
	{
		xPos = newPos;
	}// end setXPos()
	
	/**
	 * The draw visitor for the background.
	 * 
	 * @param batch - The SpriteBatch to draw the object.
	 */
	public void draw(SpriteBatch batch)
	{
		System.out.println(xPos);
		batch.draw(texture, xPos, 0, WIDTH, HEIGHT);
	}// end draw()
	
	/**
	 * Returns the width of the BackgroundPanel.
	 * 
	 * @return	: The width of the BackgroundPanel.
	 */
	public int getWidth()
	{
		return WIDTH;
	}// end getWidth()
	
	public int getHeight()
	{
		return HEIGHT;
	}
}// end BackgroundPanel class
