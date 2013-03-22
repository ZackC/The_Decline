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
 * @author(s) 	: Ian Middleton, Zach Coker, Zach Ogle
 * @version 	: 2.0
 * Last Update	: 3/22/2013
 * Update By	: Ian Middleton
 * 
 * Source code for the Enemy class. The Enemy class represents an Enemy object
 * 	in the game. It extends and uses the Unit class.
 *
 */
public class Enemy extends Unit {
	
	// Global Singleton //
	private GlobalSingleton gs = GlobalSingleton.getInstance();
	
	// Constants for the Object
	public static final int INITIAL_SPEED = 100;
	public static final int WIDTH = 100;
	public static final int HEIGHT = 100;
	
	// Internal Variables //
	// { Not Applicable }

	/**
	 * Instantiates a new Enemy object by calling the super constructor (Unit), setting the xPos
	 * to the edge of the screen, and setting the draw size.
	 * 
	 * @param texture	: The image of the Enemy.
	 */
	public Enemy(Texture texture) {
		super(texture, INITIAL_SPEED, 0, 0);
		setXPos(Gdx.graphics.getWidth()-width/2);
		setSize(WIDTH, HEIGHT);
	} // end Enemy()
	
	/**
	 * The update function is called every global game update. Moves the enemy towards the hero at
	 * 	the designated speed. Also takes into account whether the hero is moving and changes the 
	 * 	speed of the enemy accordingly.
	 */
	public void update()
	{
		if(gs.getHeroMovement() != 0){
			if(gs.getHeroOrientation() == GlobalSingleton.RIGHT){
				if(speed <= INITIAL_SPEED){
					speed = INITIAL_SPEED + (int)gs.getHeroMovement();
				} // end if
			} // end if
			else if(gs.getHeroOrientation() == GlobalSingleton.LEFT){
				if(speed >= INITIAL_SPEED){
					speed = INITIAL_SPEED + (int)gs.getHeroMovement();
				} // end if
			} // end else if
		} // end if
		else{
			speed = INITIAL_SPEED;
		} // end else
		moveLeft();
	} // end update()
}
