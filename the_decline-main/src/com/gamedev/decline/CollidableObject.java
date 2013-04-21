// Package Declaration //
package com.gamedev.decline;

// Java Package Support //
// { Not Applicable }

// Badlogic Package Support // 
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Intersector;

/**
 * com/gamedev/decline/CollidableObject.java
 * 
 * @author(s) : Ian Middleton, Zach Coker, Zach Ogle
 * @version : 1.0 Last Update : 4/19/2013 Update By : Zach Ogle
 * 
 * Source code for the CollidableObject class. The CollidableObject
 *	class is an abstract class that is meant to be extended for specific
 *	CollidableObjects within the game.
 * 
 */

public class CollidableObject extends Sprite
{

	// Global Singleton //
	// { Not Applicable }

	// Constants //
	// { Not Applicable }

	// Internal Variables //
	protected float xPos;
	protected float yPos;

	/**
	 * Basic constructor for a Collidable Object.
	 * 
	 * @param texture : The image for this CollidableObject.
	 * @param xPos : The x position in world coordinates.
	 * @param yPos : The y position in world coordinates.
	 */
	public CollidableObject(Texture texture, float xPos, float yPos)
	{
		super(texture);
		this.xPos = xPos;
		this.yPos = yPos;
	}

	/**
	 * Checks whether two CollidableObjects have collided.
	 * 
	 * @param otherCObject : The other CollidableObject to check for a collision with.
	 * @return : Whether or not there was a collision between the two CollidableObjects.
	 */
	public boolean collidesWith(CollidableObject otherCObject)
	{
		if(Intersector.intersectRectangles(getBoundingRectangle(), otherCObject.getBoundingRectangle()))
		{
			return true;
		}
		return false;
	}

	/**
	 * Sets the x position of the CollidableObject's world coordinates.
	 * 
	 * @param newXPos : The new x position of the CollidableObject's world coordinates.
	 */
	public void setXPos(float newXPos)
	{
		xPos = newXPos;
	}

	/**
	 * Gets the x position of the CollidableObject's in world coordinates.
	 * 
	 * @return : The x position of the CollidableObject's world coordinates.
	 */
	public float getXPos()
	{
		return xPos;
	}

	/**
	 * Sets the y position of the CollidableObject in world coordinates.
	 * 
	 * @param newYPos : The new y position of the CollidableObject's world coordinates.
	 */
	public void setYPos(float newYPos)
	{
		yPos = newYPos;
	}

	/**
	 * Gets the y position of the CollidableObject in world coordinates.
	 * 
	 * @return : The y position of the CollidableObject's world coordinates.
	 */
	public float getYPos()
	{
		return yPos;
	}
}