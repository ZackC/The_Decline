// Package Declaration //
package com.gamedev.decline;

// Java Package Support //
// { Not applicable }

// Badlogic Package Support //
import java.util.Iterator;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.TimeUtils;

/**
 * com/gamedev/decline/BossManager.java
 * 
 * @author(s) : Ian Middleton, Zach Coker, Zach Ogle
 * @version : 2.0 Last Update : 4/19/2013 Update By : Zach Ogle
 * 
 * Source code for the BossManager class. The BossManager class takes
 *	care of creating, updating, drawing, and removing the Boss object
 *	and Fireball objects.
 * 
 */
public class BossManager {
	// Global Singleton //
	private GlobalSingleton gs = GlobalSingleton.getInstance();
	
	// Constants //
	// { Not applicable }
	
	// Internal Variables //
	private Boss boss;
	private Array<Fireball> currentFireballs = new Array<Fireball>();
	private Fireball currentFireball;
	private Iterator<Fireball> fireballIter;
	private float timeBetweenShots = 1.1f, timeBetweenJumps = 5f;
	private long lastShot, lastJump;
	private boolean intro = true, goUp = false, goDown = false;
	private Texture fireballTexture;
	
	/**
	 * Instantiates a new BossManager object. Creates a new Boss object
	 * 	and sets his initial position. Saves the image to be used for
	 *	Fireball objects.
	 * 
	 * @param bossTexture : The image to use for the Boss object.
	 * @param fireballTexture : The image to use for Fireball objects.
	 */
	public BossManager(Texture bossTexture, Texture fireballTexture)
	{
		boss = new Boss(bossTexture, fireballTexture);
		boss.setToInitialDrawPosition();
		boss.flipOrientation();
		this.fireballTexture = fireballTexture;
	}
	
	/**
	 * Gets the Boss object.
	 * @return : The Boss object.
	 */
	public Boss getBoss()
	{
		return boss;
	}
	
	/**
	 * Gets the Array of active Fireballs.
	 * @return : The Array of active Fireballs.
	 */
	public Array<Fireball> getActiveFireballs()
	{
		return currentFireballs;
	}
	
	/**
	 * Handles the Boss being damaged.
	 * 
	 * @param damage : The amount of damage the Boss has taken.
	 */
	public void bossDamagedEvent(int damage)
	{
		if(!boss.getHasHealthBar())
		{
			gs.getHealthBarManager().add(boss);
			boss.setHasHealthBar(true);
		}
		boss.setHealth(boss.getHealth() - damage);
	}
	
	/**
	 * Handles the Boss shooting a Fireball.
	 */
	public void shootFireball()
	{
		currentFireball = new Fireball(fireballTexture);
		if (boss.getOrientation() == GlobalSingleton.RIGHT) 
		{
		   currentFireball.setOrientation(GlobalSingleton.RIGHT);
		}
		else
		{
			currentFireball.setOrientation(GlobalSingleton.LEFT);
		}
		currentFireball.setToInitialDrawPosition(boss.getX(), boss.getY());
        currentFireball.setIsAlive(true);
		currentFireballs.add(currentFireball);
	}
	
	/**
	 * Updates the Boss's position. Handles the Boss's fireball mode
	 *	and changes orientation based on the Hero's position. Also periodically
	 *	makes the Boss shoot a fireball. The Boss and all Fireball objects are
	 *	updated on the screen. Also handles the end of game screen.
	 */
	public void update()
	{
		if(intro)
		{
			boss.setFireballMode(true);
			boss.setFireballDirection(false);
			boss.moveDown();
			if(boss.getYPos() < 20)
			{
				boss.setFireballMode(false);
				intro = false;
				boss.setToGroundDrawPosition();
				boss.setIsAlive(true);
				lastJump = TimeUtils.nanoTime();
			}
		}
		else if(goUp)
		{
			boss.setFireballMode(true);
			boss.setFireballDirection(true);
			boss.moveUp();
			if(boss.getYPos() > 800)
			{
				goUp = false;
				goDown = true;
				boss.setXPos(gs.getHeroXDraw());
				boss.setYPos(800);
				boss.setPosition(gs.getHeroXDraw(), 800);
			}
		}
		else if(goDown)
		{
			boss.setFireballDirection(false);
			boss.moveDown();
			if(boss.getYPos() < 20)
			{
				boss.setFireballMode(false);
				goDown = false;
				boss.setYPos(20);
				boss.setPosition(boss.getX()+50, 20);
				lastJump = TimeUtils.nanoTime();
				if(gs.getHeroXDraw() > boss.getX() && boss.getOrientation() == GlobalSingleton.LEFT)
				{
					boss.flipOrientation();
				}
				else if(gs.getHeroXDraw() < boss.getX() && boss.getOrientation() == GlobalSingleton.RIGHT)
				{
					boss.flipOrientation();
				}
			}
		}
		else
		{
			if (TimeUtils.nanoTime() > lastShot + (timeBetweenShots * 1000000000L))
			{
				shootFireball();
				lastShot = TimeUtils.nanoTime();
			}
			if (TimeUtils.nanoTime() > lastJump + (timeBetweenJumps * 1000000000L)){
				goUp = true;
			}
		}
		
		fireballIter = currentFireballs.iterator();
		while (fireballIter.hasNext())
		{
			currentFireball = fireballIter.next();
			currentFireball.update();
			if (currentFireball.getX() > Gdx.graphics.getWidth())
			{
				fireballIter.remove();
			}
			else if (currentFireball.getX() < 0)
			{
				fireballIter.remove();
			}
		}
		
		if(boss.getHealth() <= 0)
		{
			gs.setIsGameWon(true);
		}
	}
	
	/**
	 * Draws the Boss and all active Fireballs to the screen.
	 * 
	 * @param batch : The SpriteBatch object that draws the Boss and Fireballs.
	 */
	public void draw(SpriteBatch batch)
	{
		boss.draw(batch);
		
		fireballIter = currentFireballs.iterator();
		while(fireballIter.hasNext())
		{
			currentFireball = fireballIter.next();
			currentFireball.draw(batch);
		}
	}
}