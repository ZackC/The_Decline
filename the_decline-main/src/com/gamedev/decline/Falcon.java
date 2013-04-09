// Package Declaration //
package com.gamedev.decline;

// Java Package Support //
// { Not Applicable }

// Badlogic Package Support // 
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;

/**
 * 
 * com/gamedev/decline/Enemy.java
 * 
 * @author(s) : Ian Middleton, Zach Coker, Zach Ogle
 * @version : 2.0 Last Update : 4/8/2013 Update By : Zach Ogle
 * 
 *          Source code for the Falcon class. The Falcon class represents
 *          a Falcon object in the game. It extends and uses the Unit class.
 * 
 */
public class Falcon extends Unit
{
	// Global Singleton //
	// { Not Applicable }

	// Constants //
	public static final int INITIAL_SPEED = 400;
	public static final int WIDTH = 36;
	public static final int HEIGHT = 67;
	public static final int START_XDRAW = Gdx.graphics.getWidth() - WIDTH / 2;
	public static final int START_YDRAW = Gdx.graphics.getHeight() - HEIGHT / 2;
	public static final int MAX_HEALTH = 30;

	// Internal Variables //
	// { Not Applicable }
	
	/**
	 * Instantiates a new Falcon object by calling the super constructor (Unit)
	 * and setting the draw size.
	 * 
	 * @param texture
	 *            : The image of the Falcon.
	 */
	public Falcon(Texture texture) {
		super(texture, INITIAL_SPEED, 0, 0);
		setSize(WIDTH, HEIGHT);
	} // end Enemy()

	/**
	 * Sets the Falcon's draw position to the right edge of the screen.
	 */
	public void setToInitialDrawPosition() {
	        health = getMaxHealth();
		setPosition(START_XDRAW, START_YDRAW);
	}// end setToInitialDrawPosition()

	/**
	 * The update function is called every global game update. Moves the Falcon
	 * 	in an arc towards the Hero's feet.
	 */
	public void update() {
		moveLeft();
	} // end update()

	/***
	 * Returns the max health of the Falcon
	 * @return: the max health
	 */
	@Override
	public int getMaxHealth()
	{
	  return MAX_HEALTH;
	}

	/***
	 * Handles the Falcon dying
	 */
	@Override
	public void die()
	{
	  setIsAlive(false);
	  setHasHealthBar(false);
	}
}