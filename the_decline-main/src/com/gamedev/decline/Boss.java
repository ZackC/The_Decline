// Package Declaration //
package com.gamedev.decline;

// Java Package Support //
// { Not Applicable }

// Badlogic Package Support // 
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;

/**
 * 
 * com/gamedev/decline/Boss.java
 * 
 * @author(s) 	: Ian Middleton, Zach Coker, Zach Ogle
 * @version 	: 2.0 
 * Last Update 	: 4/09/2013 
 * Update By 	: Ian Middleton
 * 
 *          Source code for the Boss class. The Boss class represents a Boss
 *          object in the game. It extends and uses the Unit class.
 * 
 */
public class Boss extends Unit {

	// Global Singleton //

	// Constants //
	public static final int INITIAL_SPEED = 0;
	public static final int WIDTH = 60;
	public static final int HEIGHT = 135;
	public static final int START_XDRAW = Gdx.graphics.getWidth() - (WIDTH*2);
	public static final int START_YDRAW = 20;
	public static final int MAX_HEALTH = 100;

	// Internal Variables //
	int orientation = GlobalSingleton.RIGHT;

	/**
	 */
	public Boss(Texture texture) {
		super(texture, INITIAL_SPEED, 0, 0);
		setSize(WIDTH, HEIGHT);
	} // end Enemy()

	/**
	 */
	public void setToInitialDrawPosition() {
	    health = getMaxHealth();
		setPosition(START_XDRAW, START_YDRAW);
	}// end setToInitialDrawPosition()

	public void flipOrientation(){
		if(orientation == GlobalSingleton.RIGHT){
			orientation = GlobalSingleton.LEFT;
		}else{
			orientation = GlobalSingleton.RIGHT;
		}
		flip(true, false);
	}
	
	public int getOrientation(){
		return orientation;
	}
	
	/**
	 */
	public void update() {
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
	  setHasHealthBar(false);
	}
	
}// end Enemy class
