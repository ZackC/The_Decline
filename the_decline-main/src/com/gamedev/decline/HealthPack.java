// Package Declaration //
package com.gamedev.decline;

// Java Package Support //
// { Not Applicable }

// Badlogic Package Support //
import com.badlogic.gdx.graphics.Texture;

/**
 * com/gamedev/decline/HealthPack.java
 * 
 * @author(s) : Ian Middleton, Zach Coker, Zach Ogle
 * @version : 2.0 Last Update : 4/21/2013 Update By : Zach Ogle
 * 
 * Source code for the Ammo class. The Ammo class represents Ammo
 *	objects in the game.
 * 
 */
public class HealthPack extends Item
{
	// Global Singleton //
	GlobalSingleton gs = GlobalSingleton.getInstance();

	// Constants //
	// { Not Applicable }

	// Internal Variables //
	private int amountOfHealth;

	/**
	 * Instantiates a new HealthPack object by calling the super constructor (Item).
	 * 
	 * @param texture : The image for the HealthPack.
	 */
	public HealthPack(Texture texture)
	{
		super(texture, 0, 0, 0);
	}

	/**
	 * Gets the amount of health in the HealthPack.
	 * @return : The amount of health in the HealthPack.
	 */
	public int getAmountOfHealth()
	{
		return amountOfHealth;
	}

	/**
	 * Sets the amount of health in the HealthPack.
	 * 
	 * @param amountOfHealth : The amount of health in the HealthPack.
	 */
	public void setAmountOfHealth(int amountOfHealth)
	{
		this.amountOfHealth = amountOfHealth;
	}
}