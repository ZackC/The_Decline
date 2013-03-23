// Package Declaration //
package com.gamedev.decline;

// Java Package Support //
// { Not Applicable }

// Badlogic Package Support //
import com.badlogic.gdx.graphics.Texture;

/**
 * 
 * com/gamedev/decline/Hero.java
 * 
 * @author(s) 	: Ian Middleton, Zach Coker, Zach Ogle
 * @version 	: 2.0
 * Last Update	: 3/22/2013
 * Update By	: Ian Middleton
 * 
 * Source code for the Hero class. The Hero class represents the Hero object
 * 	in the game. It extends and uses the Unit class.
 *
 */
public class Hero extends Unit {

	// Global Singleton //
	private GlobalSingleton gs = GlobalSingleton.getInstance();
	
	// Constants of the Object //
	public static final int STARTINGHEROXPOS = 120;
	public static final int STARTINGHEROYPOS = 20;
	public final static int SPEED = 200;
	
	// Internal Variables //
	// { Not Applicable }
	
	/**
	 * Instantiates a new Hero object by calling the super constructor (Unit) and
	 * 	setting the managers up in addition to other internal variables.
	 * 
	 * @param texture	: The image to be used for the hero.
	 * @param newBm		: The manager for the bullets.
	 * @param newEm		: The manager for the enemies.
	 */
	public Hero(Texture texture) {
		super(texture, SPEED, STARTINGHEROXPOS, STARTINGHEROYPOS);
	}
	
	/**
	 * The update function is called every global update. It checks for key presses and updates the hero
	 * 	accordingly. It then also updates the GlobalSingleton with new values if needed. Update functions
	 * 	for the managers are also called here.
	 */
	public void update(){
		gs.setHeroXPos(xPos);
	}

}
