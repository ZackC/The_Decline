// Package Declaration //
package com.gamedev.decline;

// Badlogic Package Support // 
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;

/**
 * 
 * com/gamedev/decline/Unit.java
 * 
 * @author(s) 	: Ian Middleton, Zach Coker, Zach Ogle
 * @version 	: 1.0
 * Last Update	: 3/22/2013
 * Update By	: Ian Middleton
 * 
 * Source code for the Unit class. The Unit class is an abstract class that is meant to be 
 * 	extended for specific Units within the game.
 *
 */
public abstract class Unit extends Sprite{
	
	// Global Singleton //
	// { Not Applicable }
	
	// Constants of the Object //
	// { Not Applicable }
	
	// Internal Variables //
	protected Texture texture;
	protected float xPos;
	protected float yPos;
	protected int speed;
	protected int height;
	protected int width;
	
	/**
	 * Constructor for all Units in the game. Must be called by all classes extending Unit.
	 * 
	 * @param texture	: The image for this specific Unit.
	 * @param speed		: The speed of this specific Unit.
	 * @param xPos		: The xPos of this specific Unit.
	 * @param yPos		: The yPos of this specific Unit.
	 */
	public Unit(Texture texture, int speed, float xPos, float yPos){
		super(texture);
		this.texture = texture;
		height = texture.getHeight();
		width = texture.getWidth();
		this.speed = speed;
		this.xPos = xPos;
		this.yPos = yPos;
	}
	
	/**
	 * Sets the speed of the Unit.
	 * 
	 * @param newSpeed	: New speed of the Unit.
	 */
	public void setSpeed(int newSpeed){
		speed = newSpeed;
	}
	
	/**
	 * Gets the speed of the Unit.
	 * 
	 * @return	: The speed of the unit.
	 */
	public int getSpeed(){
		return speed;
	}
	
	/**
	 * Sets the x position of the Unit.
	 * 
	 * @param newXPos	: The new x position of the Unit.
	 */
	public void setXPos(float newXPos)
	{
		xPos = newXPos;
	}
	
	/**
	 * Gets the x position of the Unit.
	 * 
	 * @return	: The x position of the Unit.
	 */
	public float getXPos()
	{
		return xPos;
	}
	
	/**
	 * Sets the y position of the Unit.
	 * 
	 * @param newYPos	: The new y position of the Unit.
	 */
	public void setYPos(float newYPos){
		yPos = newYPos;
	}
	
	/**
	 * Gets the y position of the Unit.
	 * 
	 * @return	: The y position of the Unit.
	 */
	public float getYPos(){
		return yPos;
	}
	
	/**
	 * Abstract method that must be implemented by all extending classes.
	 */
	public abstract void update();
	
	/**
	 * Moves the unit right by the amount designated by speed.
	 */
	public void moveRight(){
		setXPos(getXPos() + speed * Gdx.graphics.getDeltaTime());
		if(isFlipX() == true)
			flip(true, false);
	}
	
	/**
	 * Moves the unit left by the amount designated by speed.
	 */
	public void moveLeft(){
		setXPos(getXPos() - speed * Gdx.graphics.getDeltaTime());
		if(isFlipX() == false)
			flip(true, false);
	}
	
	/**
	 * Makes the Unit jump.
	 * 
	 * *** INCOMPLETE ***
	 */
	public void jump(){
		
	}	
}