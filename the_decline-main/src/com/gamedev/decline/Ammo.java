// Package Declaration //
package com.gamedev.decline;

// Java Package Support //
// { Not Applicable }

// Badlogic Package Support //
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

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
public class Ammo 
{
	// Global Singleton //
	GlobalSingleton gs = GlobalSingleton.getInstance();
	
	// Constants of the Object //
	public static final int AMMO_SIZE = 50;
	
	// Internal Variables //
	Texture texture;
	float xPos;
	float yPos = Hero.STARTINGHEROYPOS;
	int startPosition;
	int amountOfAmmoStored;
	
	/**
	 * Instantiates a new Ammo object by setting internal variables.
	 * 
	 * @param texture	: The image to be used for the ammo.
	 */
	public Ammo(Texture newTexture)
	{
		texture = newTexture;
		startPosition = Gdx.graphics.getWidth() - AMMO_SIZE/2;
	}
	
	/**
	 * Gets the amount of ammo stored.
	 * 
	 * @return	: Amount of ammo stored. 
	 */
	public int getAmountOfAmmoStored()
	{
		return amountOfAmmoStored;
	}
	
	/**
	 * Sets the amount of ammo stored.
	 * 
	 * @param newAmount	: Amount of ammo to be stored. 
	 */
	public void setAmountOfAmmoStored(int newAmount)
	{
		amountOfAmmoStored = newAmount;
	}
	
	/**
	 * Sets the position of the ammo to its starting position. 
	 */
	public void setToStartPosition()
	{
		xPos = startPosition;
	}
	
	/**
	 * The update function is called every global update. It updates the ammo's position
	 *  according to the Hero's. It also checks whether the ammo is still on the screen.
	 *  
	 *  @return	: Whether or not the ammo is on the screen.
	 */
	public boolean update()
	{
		xPos -= gs.getHeroMovement() * Gdx.graphics.getDeltaTime();
		if(xPos < -1 * AMMO_SIZE || xPos > Gdx.graphics.getWidth() + AMMO_SIZE)
		{
			return false;
		}
		else
		{
			return true;
		}
	}
	
	/**
	 * Draws the Ammo object.
	 * 
	 * @param batch - The SpriteBatch object which will draw the Ammo object.
	 */
	public void draw(SpriteBatch batch)
	{
		batch.draw(texture, xPos, yPos, AMMO_SIZE, AMMO_SIZE);
	}
}
