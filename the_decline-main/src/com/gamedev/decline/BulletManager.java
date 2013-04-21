// Package Declaration //
package com.gamedev.decline;

//Java Package Support //
import java.util.Iterator;

//Badlogic Package Support //
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Array;

/**
 * com/gamedev/decline/BulletManager.java
 * 
 * @author(s) : Ian Middleton, Zach Coker, Zach Ogle
 * @version : 2.0 Last Update : 4/9/2013 Update By : Zach Ogle
 * 
 * Source code for the BulletManager class. The BulletManager class
 *	takes care of creating, updating, drawing, and removing Bullet
 *	objects.
 * 
 */
public class BulletManager {

	// Global Singleton //
	private GlobalSingleton gs = GlobalSingleton.getInstance();

	// Constants //
	// { Not applicable }

	// Internal Variables //
	private Array<Bullet> shotBullets = new Array<Bullet>();
	private Iterator<Bullet> bulletIter;
	private Bullet currentBullet;
	private Texture texture;

	/**
	 * Instantiates a new BulletManager object. The BulletManager saves the
	 *	image for Bullet objects.
	 * 
	 * @param texture : The image to be used for Bullet objects.
	 */
	public BulletManager(Texture texture)
	{
		this.texture = texture;
	}

	/**
	 * Gets the Array of active Bullets.
	 * 
	 * @return : The Array of active Bullets.
	 */
	public Array<Bullet> getActiveBullets()
	{
		return shotBullets;
	}

	/**
	 * Creates a new Bullet using the Bullet image. This Bullet is then given
	 *	a direction and a starting position based on which way the Hero object
	 *	is oriented. This Bullet is then added to the Array of active Bullets
	 *	that are to be drawn to the screen and updated.
	 */
	public void shootBullet() 
	{
		currentBullet = new Bullet(texture);
		if (gs.getHeroOrientation() == GlobalSingleton.RIGHT) 
		{
		   currentBullet.setOrientation(GlobalSingleton.RIGHT);
		}
		else
		{
			currentBullet.setOrientation(GlobalSingleton.LEFT);
		}
		currentBullet.setToInitialDrawPosition();
		currentBullet.setIsAlive(true);
		shotBullets.add(currentBullet);
	}

	/**
	 * Calls the update function of each active Bullet. Also removes
	 *	Bullets when they go off the screen.
	 */
	public void update()
	{
		bulletIter = shotBullets.iterator();
		while (bulletIter.hasNext())
		{
			currentBullet = bulletIter.next();
			currentBullet.update();
			if (currentBullet.getX() > Gdx.graphics.getWidth())
			{
				bulletIter.remove();
			}
			else if (currentBullet.getX() < 0)
			{
				bulletIter.remove();
			}
		}
	}

	/**
	 * Draws all currently active Bullets to the screen.
	 * 
	 * @param batch : The SpriteBatch object that draws the Bullets.
	 */
	public void draw(SpriteBatch batch)
	{
		bulletIter = shotBullets.iterator();
		while (bulletIter.hasNext())
		{
			currentBullet = bulletIter.next();
			currentBullet.draw(batch);
		}
	}
}