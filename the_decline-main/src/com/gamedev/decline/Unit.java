// Package Declaration //
package com.gamedev.decline;

// Java Package Support //
// { Not Applicable }

// Badlogic Package Support // 
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;

/**
 * 
 * com/gamedev/decline/Unit.java
 * 
 * @author(s) 	: Ian Middleton, Zach Coker, Zach Ogle
 * @version 	: 1.0
 * Last Update	: 3/23/2013
 * Update By	: Ian Middleton
 * 
 * Source code for the Unit class. The Unit class is an abstract class that is meant to be 
 * 	extended for specific Units within the game.
 *
 */
public abstract class Unit extends CollidableObject{
	
	// Global Singleton //
	// { Not Applicable }
	
	// Constants of the Object //
	// { Not Applicable }
	
	// Internal Variables //
	protected int speed;
	private float posChange;
	
	/**
	 * Constructor for all Units in the game. Must be called by all classes extending Unit.
	 * 
	 * @param texture	: The image for this specific Unit.
	 * @param speed		: The speed of this specific Unit.
	 * @param xPos		: The xPos of this specific Unit.
	 * @param yPos		: The yPos of this specific Unit.
	 */
	public Unit(Texture texture, int speed, float xPos, float yPos){
		super(texture, xPos, yPos);
		this.speed = speed;
	}
	
	public abstract void setToInitialDrawPosition();
	
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
	 * Abstract method that must be implemented by all extending classes.
	 */
	public abstract void update();
	
	/**
	 * Moves the unit right by the amount designated by speed.
	 */
	public void moveRight(){
		posChange = speed * Gdx.graphics.getDeltaTime();
		setXPos(getXPos() + posChange);
		setPosition(getX() + posChange, getY());
		if(isFlipX() == true)
			flip(true, false);
	}
	
	/**
	 * Moves the unit left by the amount designated by speed.
	 */
	public void moveLeft(){
		posChange = -(speed * Gdx.graphics.getDeltaTime());
		setXPos(getXPos() + posChange);
		System.out.println(getXPos());
		setPosition(getX() + posChange, getY());
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