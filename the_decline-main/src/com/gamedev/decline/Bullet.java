// Package Declaration //
package com.gamedev.decline;

// Java Package Support //
// { Not Applicable }

// Badlogic Package Support //
import com.badlogic.gdx.graphics.Texture;

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
	public static final int INITIAL_SPEED = 100;
	public static final int START_XDRAW = GlobalSingleton.HERO_XDRAW+80;
	public static final int START_YDRAW = GlobalSingleton.HERO_YDRAW+60;
	
	// Internal Variables //
	private int bulletOrientation;
	
    /**
     * Instantiates a new Bullet object by calling the super constructor (Unit)
     * 	and setting the draw width and height of the object.
     * 
     * @param texture		: The image of the Bullet.
     */
	public Bullet(Texture texture) {
		super(texture, INITIAL_SPEED, 0, 0);
		setSize(DRAW_WIDTH, DRAW_HEIGHT);
	} // end Bullet()
	
	public void setToInitialDrawPosition(){
		if(bulletOrientation == gs.RIGHT){
			setPosition(START_XDRAW, START_YDRAW);
		}
		else{
			setPosition(START_XDRAW-80, START_YDRAW);
		}
	}
	
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
		if(bulletOrientation == GlobalSingleton.RIGHT){
			if(speed <= INITIAL_SPEED){
				speed = INITIAL_SPEED - (int)gs.getHeroMovement();
			} // end if
			moveRight();
		} // end if
		else if(bulletOrientation == GlobalSingleton.LEFT){
			if(speed >= INITIAL_SPEED){
				speed = INITIAL_SPEED - (int)gs.getHeroMovement();
			} // end if
			moveLeft();
		} // end else if
		else{
			speed = INITIAL_SPEED;
		} // end else
	} // end update()
} // end Bullet class
