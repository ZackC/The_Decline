// Package Declaration //
package com.gamedev.decline;

// Java Package Support //
// { Not Applicable }

// Badlogic Package Support // 
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;

/**
 * com/gamedev/decline/Enemy.java
 * 
 * @author(s) : Ian Middleton, Zach Coker, Zach Ogle
 * @version : 2.0 Last Update : 4/19/2013 Update By : Zach Ogle
 * 
 * Source code for the Enemy class. The Enemy class represents an Enemy
 *	object in the game. It extends and uses the Unit class.
 * 
 */
public class Enemy extends Unit
{
	// Global Singleton //
	// { Not Applicable }

	// Constants //
	public static final int INITIAL_SPEED = 125;
	public static final int WIDTH = 72;
	public static final int HEIGHT = 135;
	public static final int START_XDRAW = Gdx.graphics.getWidth() - WIDTH / 2;
	public static final int START_YDRAW = 20;
	public static final int MAX_HEALTH = 30;

	// Internal Variables //
	// { Not Applicable }

	/**
	 * Instantiates a new Enemy object by calling the super constructor (Unit)
	 * and setting the draw size.
	 * 
	 * @param texture : The image of the Enemy.
	 */
	public Enemy(Texture texture)
	{
		super(texture, INITIAL_SPEED, 0, 0);
		setSize(WIDTH, HEIGHT);
	}

	/**
	 * Sets the Enemy's draw position to the right edge of the screen.
	 */
	public void setToInitialDrawPosition()
	{
		health = getMaxHealth();
		setPosition(START_XDRAW, START_YDRAW);
	}

	/**
	 * Moves the Enemy left.
	 */
	public void update()
	{
		moveLeft();
	}

	/***
	 * Gets the Enemy's max health.
	 * 
	 * @return : The Enemy's max health.
	 */
	@Override
	public int getMaxHealth()
	{
		return MAX_HEALTH;
	}

	/***
	 * Handles the Enemy dying.
	 */
	@Override
	public void die()
	{
		setIsAlive(false);
		setHasHealthBar(false);
	}
}