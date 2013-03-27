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
 * @author(s) : Ian Middleton, Zach Coker, Zach Ogle
 * @version : 2.0 Last Update : 3/25/2013 Update By : Ian Middleton
 * 
 *          Source code for the Ammo class. The Ammo class represents ammo
 *          objects in the game.
 * 
 */
public class Ammo extends Item {
	// Global Singleton //
	GlobalSingleton gs = GlobalSingleton.getInstance();

	// Constants //
	// { Not Applicable }

	// Internal Variables //
	int amountOfAmmoStored;

	/**
	 * Instantiates a new Ammo object by calling the super constructor (Item)
	 * and setting the start position.
	 * 
	 * @param texture
	 *            : The image to be used for the ammo.
	 */
	public Ammo(Texture texture) {
		super(texture, 0, 0);
	}// end Ammo()

	/**
	 * Gets the amount of ammo stored.
	 * 
	 * @return : Amount of ammo stored.
	 */
	public int getAmountOfAmmoStored() {
		return amountOfAmmoStored;
	}// end getAmountOfAmmoStored()

	/**
	 * Sets the amount of ammo stored.
	 * 
	 * @param newAmount
	 *            : Amount of ammo to be stored.
	 */
	public void setAmountOfAmmoStored(int newAmount) {
		amountOfAmmoStored = newAmount;
	}// end setAmountOfAmmoStored()
}// end Ammo class
