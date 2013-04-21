// Package Declaration //
package com.gamedev.decline;

// Java Package Support //
// { Not Applicable }

// Badlogic Package Support // 
import com.badlogic.gdx.graphics.Texture;

/**
 * com/gamedev/decline/Falcon.java
 * 
 * @author(s) : Ian Middleton, Zach Coker, Zach Ogle
 * @version : 2.0 Last Update : 4/19/2013 Update By : Zach Ogle
 * 
 * Source code for the Falcon class. The Falcon class represents
 *	a Falcon object in the game. It extends and uses the Unit class.
 * 
 */
public class Falcon extends Unit
{
	// Global Singleton //
	// { Not Applicable }

	// Constants //
	public static final int INITIAL_SPEED = 125;
	public static final int X_SPEED = 500;
	public static final int Y_SPEED = -4;
	public static final int WIDTH = 36;
	public static final int HEIGHT = 67;
	public static final int START_XDRAW = Falconer.START_XDRAW;
	public static final int START_YDRAW = Falconer.START_YDRAW + HEIGHT;
	public static final int MAX_HEALTH = 30;

	// Internal Variables //
	private boolean isFlying = false, isLanding = false, hasDamagedHero = false;
	
	/**
	 * Instantiates a new Falcon object by calling the super constructor (Unit)
	 * and setting the draw size and vertical speed.
	 * 
	 * @param texture : The image of the Falcon.
	 */
	public Falcon(Texture texture)
	{
		super(texture, INITIAL_SPEED, 0, 0);
		setSize(WIDTH, HEIGHT);
		jumpSpeed = Y_SPEED;
	}

	/**
	 * Sets the Falcon's draw position to the right edge of the screen.
	 */
	public void setToInitialDrawPosition()
	{
		health = getMaxHealth();
		setPosition(START_XDRAW, START_YDRAW);
	}

	/**
	 * Required implementation of abstract method.
	 */
	public void update()
	{
	}

	/***
	 * Gets the max health of the Falcon.
	 * 
	 * @return : The Falcon's max health.
	 */
	@Override
	public int getMaxHealth()
	{
		return MAX_HEALTH;
	}

	/***
	 * Handles the Falcon dying.
	 */
	@Override
	public void die()
	{
		setIsAlive(false);
		setHasHealthBar(false);
	}
	
	/**
	 * Sets whether the Falcon is flying.
	 * 
	 * @param newIsFlying : Whether the Falcon is flying.
	 */
	public void setIsFlying(boolean newIsFlying)
	{
		isFlying = newIsFlying;
	}
	
	/**
	 * Gets whether the Falcon is flying.
	 * 
	 * @return : Whether the Falcon is flying.
	 */
	public boolean getIsFlying()
	{
		return isFlying;
	}
	
	/**
	 * Sets whether the Falcon is landing.
	 * 
	 * @param newIsLanding : Whether the Falcon is landing.
	 */
	public void setIsLanding(boolean newIsLanding)
	{
		isLanding = newIsLanding;
	}
	
	/**
	 * Gets whether the Falcon is landing.
	 * 
	 * @return : Whether the Falcon is landing.
	 */
	public boolean getIsLanding()
	{
		return isLanding;
	}
	
	/**
	 * Sets whether the Falcon has damaged the Hero.
	 * 
	 * @param newHasDamagedHero : Whether the Falcon has damaged the Hero.
	 */
	public void setHasDamagedHero(boolean newHasDamagedHero)
	{
		hasDamagedHero = newHasDamagedHero;
	}
	
	/**
	 * Gets whether the Falcon has damaged the Hero.
	 * 
	 * @return : Whether the Falcon has damaged the Hero.
	 */
	public boolean getHasDamagedHero()
	{
		return hasDamagedHero;
	}
}