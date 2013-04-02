// Package Declaration //
package com.gamedev.decline;

// Java Package Support //
// { Not Applicable }

// Badlogic Package Support //
// { Not Applicable }

/**
 * 
 * com/gamedev/decline/GlobalSingleton.java
 * 
 * @author(s) : Ian Middleton, Zach Coker, Zach Ogle
 * @version : 2.0 Last Update : 3/22/2013 Update By : Ian Middleton
 * 
 *          Source code for the GlobalSingleton class. The GlobalSingleton class
 *          holds information that will need to be accessed across the game.
 * 
 */
public class GlobalSingleton {

	// Global Singleton //
	private static GlobalSingleton instance = null;

	// Constants of the Object //
	public static final int HERO_XDRAW = 120;
	public static final int HERO_YDRAW = 20;
	public static final int RIGHT = 0;
	public static final int LEFT = 1;

	// Internal Variables //
	private float heroWidth;
	private float heroHeight;
	private float heroXDraw = HERO_XDRAW;
	private float heroYDraw = HERO_YDRAW;
	private float heroXPos = HERO_XDRAW;
	private float heroYPos = HERO_YDRAW;
	private float worldXPos = 0;
	private float heroMovement = 0;
	private int heroOrientation = 0;
	private boolean isHeroHiding = false;
	private boolean isHeroJumping = false;
	private boolean isHeroAlive = true;
	HealthBarManager hbm;
	private boolean isGameOver = false;

	private GlobalSingleton() {

	} // end GlobalSingleton()

	/**
	 * Gets the instance of the class. If one does not exist it creates a new
	 * one.
	 * 
	 * @return : The instance of the class.
	 */
	public static GlobalSingleton getInstance() {
		if (instance == null) {
			instance = new GlobalSingleton();
			return instance;
		} // end if
		else {
			return instance;
		} // end else
	} // end getInstance()

	/**
	 * Gets the width of the hero.
	 * 
	 * @return : Width of the hero.
	 */
	public float getHeroWidth() {
		return heroWidth;
	} // end getHeroWidth()

	/**
	 * Gets the height of the hero.
	 * 
	 * @return : Height of the hero.
	 */
	public float getHeroHeight() {
		return heroHeight;
	} // end getHeroHeight()

	/**
	 * Gets the x position of the hero.
	 * 
	 * @return : X position of the hero.
	 */
	public float getHeroXPos() {
		return heroXPos;
	} // end getHeroXPos()
	
	public float getHeroXDraw(){
		return heroXDraw;
	}
	
	public float getHeroYDraw(){
		return heroYDraw;
	}

	/**
	 * Gets the current movement speed of the hero. The movement speed is
	 * positive if the hero is moving right and negative if the hero is moving
	 * left.
	 * 
	 * @return : The movement speed of the hero.
	 */
	public float getHeroMovement() {
		return heroMovement;
	} // end getHeroMovement()

	/**
	 * Gets the current orientation of the hero. The orientation can be resolved
	 * by comparing the return value to the constants in the singleton for left
	 * and right.
	 * 
	 * @return : The orientation of the hero.
	 */
	public int getHeroOrientation() {
		return heroOrientation;
	} // end getHeroOrientation()

	public float getWorldXPos() {
		return worldXPos;
	}

	/**
	 * Sets the width of the hero.
	 * 
	 * @param f
	 *            : The new width of the hero.
	 */
	public void setHeroWidth(float f) {
		heroWidth = f;
	} // end setHeroWidth()

	/**
	 * Sets the height of the hero.
	 * 
	 * @param f
	 *            : The new height of the hero.
	 */
	public void setHeroHeight(float f) {
		heroHeight = f;
	} // end setHeroHeight()

	/**
	 * Sets the current x position of the hero.
	 * 
	 * @param newPos
	 *            : The new x position of the hero.
	 */
	public void setHeroXPos(float newPos) {
		heroXPos = newPos;
	} // end setHeroXPos()
	
	public void setHeroXDraw(float newDrawPos){
		heroXDraw = newDrawPos;
	}
	
	public void setHeroYDraw(float newDrawPos){
		heroYDraw = newDrawPos;
	}

	/**
	 * Sets the current hero movement.
	 * 
	 * @param newHeroMovement
	 *            : The current movement of the hero.
	 */
	public void setHeroMovement(float newHeroMovement) {
		heroMovement = newHeroMovement;
	} // end setHeroMovement()

	/**
	 * Sets the current hero orientation.
	 * 
	 * @param newHeroOrientation
	 *            : The current orientation of the hero.
	 */
	public void setHeroOrientation(int newHeroOrientation) {
		heroOrientation = newHeroOrientation;
	} // end setHeroOrientation()

	/***
	 * Sets the x position in the work of the hero
	 * 
	 * @param newWorldXPos
	 *            : the x position in the world of the hero
	 */
	public void setWorldXPos(float newWorldXPos) {
		worldXPos = newWorldXPos; // end setWorldXPos()
	}

	/***
	 * Returns the boolean for if the hero is hiding
	 * 
	 * @return: true if the hero is hiding, false otherwise
	 */
	public boolean getIsHeroHiding() {
		return isHeroHiding;
	}

	/***
	 * Sets the boolean for if the hero is hiding
	 * 
	 * @param newHeroHidingValue
	 *            : the new boolean for the hiding state of the hero
	 */
	public void setIsHeroHiding(boolean newHeroHidingValue) {
		isHeroHiding = newHeroHidingValue;
	}

	/***
	 * 
	 * @return: true if the hero is jumping; false otherwise
	 */
	public boolean getIsHeroJumping() {
		return isHeroJumping;
	}

	/***
	 * 
	 * Sets the boolean for if the hero is jumping
	 * 
	 * @param newHeroJumpingValue
	 *            - the boolean for if the hero is jumping
	 */
	public void setIsHeroJumping(boolean newHeroJumpingValue) {
		isHeroJumping = newHeroJumpingValue;
	}

	/***
	 * Returns the boolean for if the hero is alive
	 * 
	 * @return : true if the hero is alive; false otherwise
	 */
	public boolean getIsHeroAlive() {
		return isHeroAlive;
	}

	/***
	 * The method that sets the variable for if the hero is alive or not
	 * 
	 * @param newHeroLivingValue
	 *            - the new boolean that states if the hero is alive
	 */
	public void setIsHeroAlive(boolean newHeroLivingValue) {
		isHeroAlive = newHeroLivingValue;
	}

	/***
	 * Sets the global variable for the y position of the hero in the world and
	 * the screen
	 * 
	 * @param newHeroYPos
	 *            : the new y position of the hero
	 */
	public void setHeroYPos(float newHeroYPos) {
		heroYPos = newHeroYPos;
	}

	/***
	 * Returns the global variable of the y position of the hero in the world
	 * and the screen
	 * 
	 * @return: the y position of the hero.
	 */
	public float getHeroYPos() {
		return heroYPos;
	}
	
	/***
	 * Returns the health bar manager of the game
	 * @return: the health bar manager for the game
	 */
	public HealthBarManager getHealthBarManager()
	{
	  return hbm;
	}
	
	/***
	 * Sets the games health bar manager to the new health bar manager
	 * @param newHBM: the new Health Bar Manager for the game
	 */
	public void setHealthBarManager(HealthBarManager newHBM)
	{
	  hbm = newHBM;
	}
	
	public void setIsGameOver(boolean newIsGameOver)
	{
	  isGameOver = newIsGameOver;
	}
	
	public boolean getIsGameOver()
	{
	  return isGameOver;
	}

}
