// Package Declaration //
package com.gamedev.decline;

// Java Package Support //
// { Not Applicable }

// Badlogic Package Support // 
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;

/**
 * 
 * com/gamedev/decline/CollidableObject.java
 * 
 * @author(s) 	: Ian Middleton, Zach Coker, Zach Ogle
 * @version 	: 1.0
 * Last Update	: 3/23/2013
 * Update By	: Ian Middleton
 * 
 * Source code for the CollidableObject class. The CollidableObject class is an abstract class that is meant to be 
 * 	extended for specific CollidableObjects within the game.
 *
 */

public class CollidableObject extends Sprite{
	
	protected float xPos;
	protected float yPos;
	
	public CollidableObject(Texture texture, float xPos, float yPos){
		super(texture);
		this.xPos = xPos;
		this.yPos = yPos;
	}
	
	public boolean collidesWith(CollidableObject otherCObject){
		if((xPos + getWidth()) >= otherCObject.getXPos()){
			return true;
		}		
		return false;
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
}