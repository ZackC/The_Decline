// Package Declaration //
package com.gamedev.decline;

// Java Package Support //
// { Not Applicable }

// Badlogic Package Support //
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

/**
 * 
 * com/gamedev/decline/Item.java
 * 
 * @author(s) 	: Ian Middleton, Zach Coker, Zach Ogle
 * @version 	: 2.0
 * Last Update	: 3/22/2013
 * Update By	: Zach Ogle
 * 
 * Source code for the Item class. The Item class is an abstract class that is meant to be
 *  extended for specific Items within the game.
 * 
 */
public abstract class Item extends Sprite
{
	// Global Singleton //
	private GlobalSingleton gs = GlobalSingleton.getInstance();
	
	// Constants of the Object //
	public static final int ITEM_SIZE = 50;
	public static final int START_XPOS = Gdx.graphics.getWidth()-ITEM_SIZE/2;
	
	// Internal Variables
	protected float xPos;
	protected float yPos;
	
	/**
	 * Constructor for all Items in the game. Must be called by all classes extending Item.
	 * 
	 * @param texture	: The image for this specific Item.
	 * @param xPos		: The xPos of this specific Item.
	 * @param yPos		: The yPos of this specific Item.
	 */
	public Item(Texture texture, float xPos, float yPos)
	{
		super(texture);
		setSize(ITEM_SIZE, ITEM_SIZE);
		this.xPos = xPos;
		this.yPos = yPos;
	}
	
	/**
	 * Overrides the Sprite draw for Item in order to allow for the setPosition function to be called before
	 * 	the Sprite draw function is called.
	 * 
	 * @param batch	: The SpriteBatch which should draw the Item.
	 */
	@Override
	public void draw(SpriteBatch batch){
		setPosition(xPos, yPos);
		super.draw(batch);
	}
	
	/**
	 * Sets the x position of the Item.
	 * 
	 * @param newXPos	: The new x position of the Item.
	 */
	public void setXPos(float newXPos)
	{
		xPos = newXPos;
	}
	
	/**
	 * Gets the x position of the Item.
	 * 
	 * @return	: The x position of the Item.
	 */
	public float getXPos()
	{
		return xPos;
	}
	
	/**
	 * Sets the y position of the Item.
	 * 
	 * @param newYPos	: The new y position of the Item.
	 */
	public void setYPos(float newYPos){
		yPos = newYPos;
	}
	
	/**
	 * Gets the y position of the Item.
	 * 
	 * @return	: The y position of the Item.
	 */
	public float getYPos(){
		return yPos;
	}
	
	/**
	 * Sets the position of the Item to its starting position. 
	 */
	public void setToStartPosition()
	{
		xPos = START_XPOS;
	}
	
	/**
	 * Updates the position of Items.
	 */
	public void update()
	{
		xPos -= gs.getHeroMovement() * Gdx.graphics.getDeltaTime();
	}
}
