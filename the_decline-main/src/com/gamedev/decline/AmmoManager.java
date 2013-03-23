// Package Declaration //
package com.gamedev.decline;

//Java Package Support //
//{ Not Applicable }

//Badlogic Package Support //
import java.util.Random;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

/**
 * 
 * com/gamedev/decline/AmmoManager.java
 * 
 * @author(s) 	: Ian Middleton, Zach Coker, Zach Ogle
 * @version 	: 2.0
 * Last Update	: 3/22/2013
 * Update By	: Zach Ogle
 * 
 * Source code for the AmmoManager class. The AmmoManager class takes care of updating,
 *  drawing, and placing Ammo objects.
 * 
 */
public class AmmoManager
{
	// Global Singleton //
	GlobalSingleton gs = GlobalSingleton.getInstance();
	
	// Constants of the Object //
	public static final int MAX_AMMO = 5;
	
	// Internal Variables //
	Ammo[] ammoArray = new Ammo[MAX_AMMO];
	boolean[] ammoIsShowing = new boolean[MAX_AMMO];
	Texture texture;
	Random rand = new Random();
	int newAmmoPosition = 800 + rand.nextInt() % 500;
	int currentAmmoCount = 0;
	
	/**
	 * Instantiates a new AmmoManager object. The AmmoManager fills an array of new Ammo objects
	 * 	with the given Texture to be used in the game. This is done to create a buffer of Ammo objects.
	 * 
	 * @param texture	: The image to be used for the Ammo objects.
	 */
	public AmmoManager(Texture newTexture) 
	{
		texture = newTexture;
		for(int i = 0; i < ammoArray.length; i++)
		{
			ammoArray[i] = new Ammo(texture);
		}
	}
	
	/**
	 * Checks to see if the hero has moved farther than the random amount required for a new Ammo object to be spawned.
	 * 	If the hero has moved this distance then a new Ammo object is created. Afterwards, the function iterates through
	 * 	all Ammo objects on the screen and calls their update method.
	 */
	public void update()
	{
		if(gs.getHeroXPos() > newAmmoPosition)
		{
			ammoIsShowing[currentAmmoCount] = true;
			ammoArray[currentAmmoCount].setToStartPosition();
			ammoArray[currentAmmoCount].setAmountOfAmmoStored((rand.nextInt() % 10) * 5);
			currentAmmoCount++;
			currentAmmoCount = currentAmmoCount % MAX_AMMO;
			newAmmoPosition += 800 + rand.nextInt() % 500;
		}
		for(int i = 0; i < ammoIsShowing.length; i++)
		{
			if(ammoIsShowing[i])
			{
				ammoIsShowing[i] = ammoArray[i].update();
			}
		}
	}
	
	/**
	 * Iterates through the array of Ammo objects and if they are showing, the Ammo object's draw
	 *  function is called.
	 * 
	 * @param batch - The SpriteBatch object which will draw the Ammo objects.
	 */
	public void draw(SpriteBatch batch)
	{
		for(int i = 0; i < ammoIsShowing.length; i++)
		{
			if(ammoIsShowing[i])
			{
				ammoArray[i].draw(batch);
			}
		}
	}
}
