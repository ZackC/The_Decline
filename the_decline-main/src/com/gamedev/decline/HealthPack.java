// Package Declaration //
package com.gamedev.decline;

// Java Package Support //
// { Not Applicable }

// Badlogic Package Support //
import com.badlogic.gdx.graphics.Texture;

/**
 * 
 * com/gamedev/decline/Ammo.java
 * 
 * @author(s) 	: Ian Middleton, Zach Coker, Zach Ogle
 * @version 	: 2.0
 * Last Update	: 3/22/2013
 * Update By	: Zach Ogle
 * 
 * Source code for the Ammo class. The Ammo class represents ammo objects
 *  in the game.
 * 
 */
public class HealthPack extends Item
{
	// Global Singleton //
	GlobalSingleton gs = GlobalSingleton.getInstance();
	
	// Constants of the Object //
	// { Not Applicable }
	
	// Internal Variables //
	// { Not Applicable }
	
	/**
	 * Instantiates a new HealthPack object by calling the super constructor (Item) and setting
	 *  the start position.
	 * 
	 * @param texture	: The image to be used for the health pack.
	 */
	public HealthPack(Texture texture)
	{
		super(texture, 0, 0);
	}
}