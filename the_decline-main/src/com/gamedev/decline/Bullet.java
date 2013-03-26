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
 * Last Update	: 3/25/2013
 * Update By	: Ian Middleton
 * 
 * Source code for the Bullet class. The Bullet class represents a Bullet object
 * 	in the game. It extends and uses the Unit class.
 *
 */
public class Bullet extends Unit
{
	// Global Singleton //
	// { Not Applicable }
	
	// Constants //
	public static final int DRAW_WIDTH = 30;
	public static final int DRAW_HEIGHT = 30;
	public static final int INITIAL_SPEED = 1000;
	public static final int START_XDRAW = GlobalSingleton.HERO_XDRAW + GlobalSingleton.getInstance().getHeroWidth()/2;
	public static final int START_YDRAW = GlobalSingleton.HERO_YDRAW + GlobalSingleton.getInstance().getHeroHeight()/2;
	
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
	
	/**
	 * Sets the Bullet object to its initial start position based on whether it is
	 * 	a right or left facing bullet.
	 * 
	 * ***NOTE*** This will need to be changed when jumping is integrated.
	 */
	public void setToInitialDrawPosition(){
		if(bulletOrientation == GlobalSingleton.RIGHT){
			setPosition(START_XDRAW, START_YDRAW);
		}// end if
		else{
			setPosition(START_XDRAW, START_YDRAW);
		} // end else
	}// end setToInitialDrawPosition()
	
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
	 * 	to determine which direction the bullet should move. GlobalSingleton values for left and right
	 * 	are used.
	 */
	public void update(){
		if(bulletOrientation == GlobalSingleton.RIGHT){
			moveRight();
		} // end if
		else if(bulletOrientation == GlobalSingleton.LEFT){
			moveLeft();
		} // end else if
	} // end update()
} // end Bullet class
