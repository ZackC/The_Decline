package com.gamedev.decline;

import java.util.Random;

import sun.rmi.runtime.NewThreadAction;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
/*
 * The manager of the ammo objects
 * 
 * Important design decision to make for this class - 
 *   is the ammo still there when it goes out of the screen or
 *   is it gone for good.  If it is still there, how long?
 *      --- right now they disapear when going off of screen
 */
public class AmmoManager {

	//the amount of ammo to initialize at the start of the game
	int ammoInitializedAtBeginning = 3;
	// the x dimension size of the ammo
	int xSize = 50;
	// the y dimension size of the ammo
	int ySize = 50;
	// the array that stores the ammo objects for the game
	Ammo[] ammoArray = new Ammo[ammoInitializedAtBeginning];
	// the array used to determine if the ammo object is showing
	boolean[] ammoIsShowing = new boolean[ammoInitializedAtBeginning];
	// the texture for the ammo objects (can remove this if necessary 
	//     or change to list of multiple texture objects)
	Texture texture;
	// a random generator for the position of the ammo
	Random rand = new Random();
	// the holder for the new ammo position
	int newAmmoPosition = 800 + rand.nextInt() % 500;
	// the last ammo object to be displayed 
	int currentAmmoCount = 0;
	// the global singleton object
	GlobalSingleton gs = GlobalSingleton.getInstance();
	
	/*
	 * The default constructor for the class
	 * 
	 * newTexture - the texture for the ammo
	 */
	public AmmoManager(Texture newTexture) 
	{
		texture = newTexture;
		for(int i = 0; i < ammoArray.length; i++)
		{
			ammoArray[i] = new Ammo(texture,xSize);
		}
	}
	
	/*
	 * Checks to see if an ammo object needs to be displayed.
	 * Then it updates the currently displayed ammo objects
	 */
	public void update()
	{
		if(gs.getHeroXPos() > newAmmoPosition)
		{
			ammoIsShowing[currentAmmoCount] = true;
			ammoArray[currentAmmoCount].setToStartPosition();
			ammoArray[currentAmmoCount].setAmountOfAmmoStored((rand.nextInt() % 10) * 5);
			currentAmmoCount++;
			currentAmmoCount = currentAmmoCount % 3;
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
	
	/*
	 * handles drawing the visible ammo objects
	 * 
	 * batch - the drawing visitor
	 */
	public void draw(SpriteBatch batch)
	{
		for(int i = 0; i < ammoIsShowing.length; i++)
		{
			if(ammoIsShowing[i])
			{
				ammoArray[i].draw(batch,xSize,ySize);
			}
		}
	}
	

}
