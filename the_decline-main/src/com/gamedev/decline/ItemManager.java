// Package Declaration //
package com.gamedev.decline;

//Java Package Support //
import java.util.Random;
import java.util.Iterator;

//Badlogic Package Support //
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Array;

/**
 * com/gamedev/decline/ItemManager.java
 * 
 * @author(s) : Ian Middleton, Zach Coker, Zach Ogle
 * @version : 2.0 Last Update : 4/21/2013 Update By : Zach Ogle
 * 
 * Source code for the ItemManager class. The ItemManager class takes
 *	care of updating, drawing, and placing Item objects (HealthPacks,
 *	Ammo, and Bushes).
 * 
 */
public class ItemManager
{
	// Global Singleton //
	GlobalSingleton gs = GlobalSingleton.getInstance();

	// Constants //
	// { Not applicable }

	// Internal Variables //
	private Array<Ammo> currentAmmo = new Array<Ammo>();
	private Array<Bush> currentBushes = new Array<Bush>();
	private Array<HealthPack> currentHealthPacks = new Array<HealthPack>();
	private Iterator<Ammo> ammoIter;
	private Iterator<HealthPack> healthIter;
	private Iterator<Bush> bushIter;
	private Ammo ammo;
	private HealthPack pack;
	private Bush bush;
	private Random rand = new Random();
	private int newAmmoPosition = 800 + rand.nextInt() % 500;
	private int newHealthPackPosition = 800 + rand.nextInt() % 500;
	private int newBushPosition = 500 + rand.nextInt() % 500;
	private Texture ammoTexture, healthTexture, bushTexture;

	/**
	 * Saves textures for creating Ammo, Health, and Bush objects.
	 * 
	 * @param ammoTexture : The image for Ammo objects.
	 * @param healthTexture : The image for HealthPack objects.
	 * @param bushTexture : The image for Bush objects.
	 */
	public ItemManager(Texture ammoTexture, Texture healthTexture, Texture bushTexture)
	{
		this.ammoTexture = ammoTexture;
		this.healthTexture = healthTexture;
		this.bushTexture = bushTexture;
	}

	/**
	 * Gets the Array of active Ammo.
	 * 
	 * @return : The Array of active Ammo.
	 */
	public Array<Ammo> getActiveAmmo()
	{
		return currentAmmo;
	}
	
	/**
	 * Gets the Array of active Bushes.
	 * 
	 * @return : The Array of active Bushes.
	 */
	public Array<Bush> getActiveBush()
	{
	   return currentBushes;
	}

	/**
	 * Gets the Array of active HealthPacks.
	 * 
	 * @return : The Array of active HealthPacks.
	 */
	public Array<HealthPack> getActiveHealthPacks()
	{
		return currentHealthPacks;
	}

	/**
	 * Creates a new Ammo object with a random amount of ammo and
	 *	places it in the world.
	 */
	public void makeAmmoAppear()
	{
		ammo = new Ammo(ammoTexture);
		ammo.setToInitialDrawPosition();
		ammo.setXPos(gs.getWorldXPos() + Item.START_XDRAW);
		ammo.setYPos(Item.START_YDRAW);
		ammo.setAmountOfAmmoStored(rand.nextInt(5) * 5 + 5);
		currentAmmo.add(ammo);
	}
	
	/**
	 * Creates a new Bush object and places it in the world.
	 */
	public void makeBushAppear()
	{
		bush = new Bush(bushTexture);
		bush.setToInitialDrawPosition();
		bush.setXPos(gs.getWorldXPos() + Item.START_XDRAW);
		bush.setYPos(Item.START_YDRAW);
		currentBushes.add(bush);
	}

	/**
	 * Creates a new HealthPack object with a random amount of health
	 *	and places it in the world.
	 */
	public void makeHealthAppear()
	{
		pack = new HealthPack(healthTexture);
		pack.setToInitialDrawPosition();
		pack.setXPos(gs.getWorldXPos() + Item.START_XDRAW);
		pack.setYPos(Item.START_YDRAW);
		pack.setAmountOfHealth(rand.nextInt(10) * 5 + 5);
		currentHealthPacks.add(pack);
	}

	/**
	 * Checks to see if the Hero has moved farther than the random amount
	 * required for a new Item object to be spawned. If the Hero has moved this
	 * distance then a new Item object is created. Afterwards, the function
	 * iterates through all Item objects on the screen and calls their update
	 * method.
	 */
	public void update()
	{
		if(gs.getHeroXPos() < 9000)
		{
			if (gs.getHeroXPos() > newAmmoPosition)
			{
				makeAmmoAppear();
				newAmmoPosition += 800 + rand.nextInt() % 500;
			}
			if (gs.getHeroXPos() > newBushPosition)
			{
				makeBushAppear();
				newBushPosition += 500 + rand.nextInt() % 500;
			}
			if (gs.getHeroXPos() > newHealthPackPosition)
			{
				makeHealthAppear();
				newHealthPackPosition += 800 + rand.nextInt() % 500;
			}
		}
		ammoIter = currentAmmo.iterator();
		bushIter = currentBushes.iterator();
		healthIter = currentHealthPacks.iterator();
		while (ammoIter.hasNext())
		{
			ammo = ammoIter.next();
			ammo.update();
			if (ammo.getX() < -1 * ammo.getWidth())
			{
				ammoIter.remove();
			}
		}
		while (bushIter.hasNext())
		{
			bush = bushIter.next();
			bush.update();
			if (bush.getX() < -1 * (bush.getWidth() + 1000))
			{
				bushIter.remove();
			}
		}
		while (healthIter.hasNext())
		{
			pack = healthIter.next();
			pack.update();
			if (pack.getX() < -1 * pack.getWidth())
			{
				healthIter.remove();
			}
		}
	}

	/**
	 * Iterates through all active Items and draws them to the screen.
	 * 
	 * @param batch : The SpriteBatch object which will draw the Items.
	 */
	public void draw(SpriteBatch batch)
	{
		bushIter = currentBushes.iterator();
		ammoIter = currentAmmo.iterator();
		healthIter = currentHealthPacks.iterator();
		while (bushIter.hasNext())
		{
		  bush = bushIter.next();
		  bush.draw(batch);
		}
		while (ammoIter.hasNext())
		{
			ammo = ammoIter.next();
			ammo.draw(batch);
		}
		while (healthIter.hasNext())
		{
			pack = healthIter.next();
			pack.draw(batch);
		}
	}
}