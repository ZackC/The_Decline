// Package Declaration //
package com.gamedev.decline;

// Java Package Support //
// { Not Applicable }

// Badlogic Package Support // 
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Intersector;

/**
 * 
 * com/gamedev/decline/CollidableObject.java
 * 
 * @author(s) : Ian Middleton, Zach Coker, Zach Ogle
 * @version : 1.0 Last Update : 3/25/2013 Update By : Ian Middleton
 * 
 *          Source code for the CollidableObject class. The CollidableObject
 *          class is an abstract class that is meant to be extended for specific
 *          CollidableObjects within the game.
 * 
 */

public class CollidableObject extends Sprite {

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
	 * @param texture
	 *            : The image for this CollidableObject.
	 * @param xPos
	 *            : The x position in world coordinates.
	 * @param yPos
	 *            : The y position in world coordinates.
	 */
	public CollidableObject(Texture texture, float xPos, float yPos) {
		super(texture);
		this.xPos = xPos;
		this.yPos = yPos;
	}// end CollidableObject()

	/**
	 * Checks whether two CollidableObjects have collided.
	 * 
	 * @param otherCObject
	 *            : The other CollidableObject to check for a collision with.
	 * @return : True if there is a collision, false if not.
	 */
	public boolean collidesWith(CollidableObject otherCObject) {
		//if ((xPos + getWidth()) >= otherCObject.getXPos()) {
	          if(Intersector.intersectRectangles(getBoundingRectangle(), otherCObject.getBoundingRectangle())){
			return true;
		}// end if
		return false;
	}// end collidesWith()

	/**
	 * Sets the x position of the Unit's world coordinates.
	 * 
	 * @param newXPos
	 *            : The new world x position of the Unit.
	 */
	public void setXPos(float newXPos) {
		xPos = newXPos;
	}// end setXPos()

	/**
	 * Gets the x position of the Unit in world coordinates.
	 * 
	 * @return : The world x position of the Unit.
	 */
	public float getXPos() {
		return xPos;
	}// end getXPos()

	/**
	 * Sets the y position of the Unit in world coordinates.
	 * 
	 * @param newYPos
	 *            : The new world y position of the Unit.
	 */
	public void setYPos(float newYPos) {
		yPos = newYPos;
	}// end setYPos()

	/**
	 * Gets the y position of the Unit in world coordinates.
	 * 
	 * @return : The world y position of the Unit.
	 */
	public float getYPos() {
		return yPos;
	}// end getYPos()
}// end CollidableObject class