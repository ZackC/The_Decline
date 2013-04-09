// Package Declaration //
package com.gamedev.decline;

// Java Package Support //
// { Not Applicable }

// Badlogic Package Support //
import com.badlogic.gdx.graphics.Texture;

/**
 * 
 * com/gamedev/decline/Fireball.java
 * 
 * @author(s) : Ian Middleton, Zach Coker, Zach Ogle
 * @version : 2.0 Last Update : 3/25/2013 Update By : Ian Middleton
 * 
 *
 */
public class Fireball extends Unit {
	// Global Singleton //
	// { Not Applicable }

	// Constants //
	public static final int DRAW_WIDTH = 30;
	public static final int DRAW_HEIGHT = 30;
	public static final int INITIAL_SPEED = 1000;
	public static final int MAX_HEALTH = 1;
	public static final int COLLISION_DAMAGE_TO_SELF = 1;

	// Internal Variables //
	private int orientation;

	/**
	 */
	public Fireball(Texture texture) {
		super(texture, INITIAL_SPEED, 0, 0);
		setSize(DRAW_WIDTH, DRAW_HEIGHT);
	} // end Bullet()

	@Override
	public void setToInitialDrawPosition(){
		
	}
	
	/**
	 */
	public void setToInitialDrawPosition(float bossX, float bossY) {
		setXPos(gs.getWorldXPos() + bossX - 60);
		setYPos(gs.getWorldXPos() + bossY);
	}// end setToInitialDrawPosition()

	/**
	 * Used to set the orientation of the bullet and which direction it should
	 * fly.
	 * 
	 * @param bulletOrientation
	 *            : The direction the bullet should be oriented. Uses global
	 *            singleton values for left and right.
	 */
	public void setOrientation(int orientation) {
		this.orientation = orientation;
	} // end setOrientation()

	/**
	 */
	public void update() {
		if (orientation == GlobalSingleton.RIGHT) {
			moveRight();
		} // end if
		else if (orientation == GlobalSingleton.LEFT) {
			moveLeft();
		} // end else if
	} // end update()

	/**
	 */
	@Override
	public int getMaxHealth()
	{
	  return MAX_HEALTH;
	}

	/**
	 */
	@Override
	public void die()
	{
	  setIsAlive(false);
	  
	}
	
} // end Bullet class
