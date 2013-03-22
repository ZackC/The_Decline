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
 * @author(s) 	: Ian Middleton, Zach Coker, Zach Ogle
 * @version 	: 2.0
 * Last Update	: 3/22/2013
 * Update By	: Ian Middleton
 * 
 * Source code for the GlobalSingleton class. The GlobalSingleton class holds information that will need
 * 	to be accessed across the game.
 *
 */
public class GlobalSingleton {
	
	// Global Singleton //
    private static GlobalSingleton instance = null;
    
    // Constants of the Object //
    public static final int STARTING_HERO_XPOS = 120;
    public static final int STARTING_HERO_YPOS = 20;
	public static final int RIGHT = 0;
	public static final int LEFT = 1;
    
    // Internal Variables //
	private int heroWidth;
	private int heroHeight;
	private float heroXPos = STARTING_HERO_XPOS;
	private float heroMovement = 0;
	private int heroOrientation = 0;

	
	private GlobalSingleton() {
		
	} // end GlobalSingleton()
	
	/**
	 * Gets the instance of the class. If one does not exist it creates a new one.
	 * 
	 * @return	: The instance of the class.
	 */
	public static GlobalSingleton getInstance(){
		if(instance == null){
			instance = new GlobalSingleton();
			return instance;
		} // end if
		else{
			return instance;
		} // end else
	} // end getInstance()
	
	/**
	 * Gets the width of the hero.
	 * 
	 * @return	: Width of the hero. 
	 */
	public int getHeroWidth(){
		return heroWidth;
	} // end getHeroWidth()
	
	/**
	 * Gets the height of the hero.
	 * 
	 * @return	: Height of the hero.
	 */
	public int getHeroHeight(){
		return heroHeight;
	} // end getHeroHeight()
	
	/**
	 * Gets the x position of the hero.
	 * 
	 * @return	: X position of the hero.
	 */
	public float getHeroXPos(){
		return heroXPos;
	} // end getHeroXPos()
	
	/**
	 * Gets the current movement speed of the hero. The movement speed is positive if the hero is moving
	 * 	right and negative if the hero is moving left.
	 * 
	 * @return	: The movement speed of the hero.
	 */
	public float getHeroMovement(){
		return heroMovement;
	} // end getHeroMovement()
	
	/**
	 * Gets the current orientation of the hero. The orientation can be resolved by comparing the return value
	 * 	to the constants in the singleton for left and right.
	 * 
	 * @return	: The orientation of the hero.
	 */
	public int getHeroOrientation(){
		return heroOrientation;
	} // end getHeroOrientation()
	
	/**
	 * Sets the width of the hero.
	 * 
	 * @param newWidth	: The new width of the hero. 
	 */
	public void setHeroWidth(int newWidth){
		heroWidth = newWidth;
	} // end setHeroWidth()
	
	/**
	 * Sets the height of the hero.
	 * 
	 * @param newHeight	: The new height of the hero.
	 */
	public void setHeroHeight(int newHeight){
		heroHeight = newHeight;
	} // end setHeroHeight()
	
	/**
	 * Sets the current x position of the hero.
	 * 
	 * @param newPos	: The new x position of the hero.
	 */
	public void setHeroXPos(float newPos){
		heroXPos = newPos;
	} // end setHeroXPos()
	
	/**
	 * Sets the current hero movement.
	 * 
	 * @param newHeroMovement	: The current movement of the hero.
	 */
	public void setHeroMovement(float newHeroMovement){
		heroMovement = newHeroMovement;
	} // end setHeroMovement()
	
	/**
	 * Sets the current hero orientation.
	 * 
	 * @param newHeroOrientation	: The current orientation of the hero.
	 */
	public void setHeroOrientation(int newHeroOrientation){
		heroOrientation = newHeroOrientation;
	} // end setHeroOrientation()
	
}
