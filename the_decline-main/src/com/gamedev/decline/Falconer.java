// Package Declaration //
package com.gamedev.decline;

// Java Package Support //
// { Not Applicable }

// Badlogic Package Support // 
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;

/**
 * 
 * com/gamedev/decline/Enemy.java
 * 
 * @author(s) : Ian Middleton, Zach Coker, Zach Ogle
 * @version : 2.0 Last Update : 4/8/2013 Update By : Zach Ogle
 * 
 *          Source code for the Falconer class. The Falconer class represents
 *          a Falconer object in the game. It extends and uses the Unit class.
 * 
 */
public class Falconer extends Unit
{
	// Global Singleton //
	// { Not Applicable }

	// Constants //
	public static final int INITIAL_SPEED = 125;
	public static final int WIDTH = 72;
	public static final int HEIGHT = 135;
	public static final int START_XDRAW = Gdx.graphics.getWidth() - WIDTH / 2;
	public static final int START_YDRAW = 20;
	public static final int MAX_HEALTH = 20;

	// Internal Variables //
	// { Not Applicable }
	
	/**
	 * Instantiates a new Falconer object by calling the super constructor (Unit)
	 * and setting the draw size.
	 * 
	 * @param texture
	 *            : The image of the Falconer.
	 */
	public Falconer(Texture texture)
	{
		super(texture, INITIAL_SPEED, 0, 0);
		setSize(WIDTH, HEIGHT);
	}

	/**
	 * Sets the Falconer's draw position to the right edge of the screen.
	 */
	public void setToInitialDrawPosition()
	{
		health = getMaxHealth();
		setPosition(START_XDRAW, START_YDRAW);
	}

	/**
	 * The update function is called every global game update. Keeps the Falconer
	 * 	as close to the right edge of the screen as possible.
	 */
	public void update()
	{
		if (speed * Gdx.graphics.getDeltaTime() + getX() + WIDTH < Gdx.graphics.getWidth())
		{
			moveRight();
		}
		else if (getX() + WIDTH > Gdx.graphics.getWidth() || gs.getHeroMovement() < 0)
		{
			moveLeft();
		}
	}

	/***
	 * Returns the max health of the Falconer.
	 * @return: the max health
	 */
	@Override
	public int getMaxHealth()
	{
	  return MAX_HEALTH;
	}

	/***
	 * Handles the Falconer dying
	 */
	@Override
	public void die()
	{
	  setIsAlive(false);
	  setHasHealthBar(false);
	}
}