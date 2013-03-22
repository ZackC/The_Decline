// Package Declaration //
package com.gamedev.decline;

// Java Package Support //
// { Not Applicable }

// Badlogic Package Support //
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

/**
 * 
 * com/gamedev/decline/Bullet.java
 * 
 * @author(s) 	: Ian Middleton, Zach Coker, Zach Ogle
 * @version 	: 2.0
 * Last Update	: 3/22/2013
 * Update By	: Ian Middleton
 * 
 * Source code for the Bullet class. The Bullet class represents a Bullet object
 * 	in the game. It extends and uses the Unit class.
 *
 */
public class Bullet extends Unit
{
	// Global Singleton //
	private GlobalSingleton gs = GlobalSingleton.getInstance();
	
	// Constants for the Object //
	public static final int DRAW_WIDTH = 30;
	public static final int DRAW_HEIGHT = 30;
	public static final int INITIAL_SPEED = 1000;
	public static final int INITIAL_XPOS = 0;
	public static final int INITIAL_YPOS = 0;
	
	// Internal Variables //
	private int bulletOrientation;
	
    /**
     * Instantiates a new Bullet object by calling the super constructor (Unit)
     * 	and setting the draw width and height of the object.
     * 
     * @param texture		: The image of the Bullet.
     */
	public Bullet(Texture texture) {
		super(texture, INITIAL_SPEED, INITIAL_XPOS, INITIAL_YPOS);
		setSize(DRAW_WIDTH, DRAW_HEIGHT);
	} // end Bullet()
	
	/**
	 * Used to set the orientation of the bullet and which direction it should fly.
	 * 
	 * @param bulletOrientation	: The direction the bullet should be oriented. Uses global
	 * 								singleton values for left and right.
	 */
	public void setOrientation(int bulletOrientation){
		this.bulletOrientation = bulletOrientation;
	} // end setOrientation()
	
	/**
	 * The update function is called every global game update. Uses the orientation of the bullet 
	 * 	to determine which direction the bullet should move. Also takes into account whether 
	 * 	the hero is moving and changes the speed of the bullet accordingly.
	 */
	public void update(){
		if(bulletOrientation == gs.RIGHT){
			if(speed <= INITIAL_SPEED){
				speed = INITIAL_SPEED - (int)gs.getHeroMovement();
			} // end if
			moveRight();
		} // end if
		else if(bulletOrientation == gs.LEFT){
			if(speed >= INITIAL_SPEED){
				speed = INITIAL_SPEED + (int)gs.getHeroMovement();
			} // end if
			moveLeft();
		} // end else if
		else{
			speed = INITIAL_SPEED;
		} // end else
	} // end update()
} // end Bullet class
