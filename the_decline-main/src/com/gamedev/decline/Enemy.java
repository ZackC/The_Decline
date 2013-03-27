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
 * @version : 2.0 Last Update : 3/25/2013 Update By : Ian Middleton
 * 
 *          Source code for the Enemy class. The Enemy class represents an Enemy
 *          object in the game. It extends and uses the Unit class.
 * 
 */
public class Enemy extends Unit {

	// Global Singleton //
	// { Not Applicable }

	// Constants //
	public static final int INITIAL_SPEED = 125;
	public static final int WIDTH = 72;
	public static final int HEIGHT = 135;
	public static final int START_XDRAW = Gdx.graphics.getWidth() - WIDTH / 2;
	public static final int START_YDRAW = 20;

	// Internal Variables //
	// { Not Applicable }

	/**
	 * Instantiates a new Enemy object by calling the super constructor (Unit)
	 * and setting the draw size.
	 * 
	 * @param texture
	 *            : The image of the Enemy.
	 */
	public Enemy(Texture texture) {
		super(texture, INITIAL_SPEED, 0, 0);
		setSize(WIDTH, HEIGHT);
	} // end Enemy()

	/**
	 * Sets the Enemy's draw position to the right edge of the screen.
	 */
	public void setToInitialDrawPosition() {
		setPosition(START_XDRAW, START_YDRAW);
	}// end setToInitialDrawPosition()

	/**
	 * The update function is called every global game update. Moves the enemy
	 * towards the hero at the designated speed.
	 */
	public void update() {
		moveLeft();
	} // end update()
}// end Enemy class
