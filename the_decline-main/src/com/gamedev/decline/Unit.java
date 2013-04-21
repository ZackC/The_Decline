// Package Declaration //
package com.gamedev.decline;

// Java Package Support //
// { Not Applicable }

// Badlogic Package Support // 
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;

/**
 * com/gamedev/decline/Unit.java
 * 
 * @author(s) : Ian Middleton, Zach Coker, Zach Ogle
 * @version : 1.0 Last Update : 4/21/2013 Update By : Zach Ogles
 * 
 * Source code for the Unit class. The Unit class is an abstract class
 *	that is meant to be extended for specific Units within the game. It
 *	extends and uses the CollidableObject class.
 * 
 */
public abstract class Unit extends CollidableObject
{
	// Global Singleton //
	protected GlobalSingleton gs = GlobalSingleton.getInstance();

	// Constants of the Object //
	// { Not Applicable }

	// Internal Variables //
	protected int speed, jumpSpeed, health = getMaxHealth();
	protected float xPosChange, yPosChange;
	protected boolean isAlive, hasHealthBar = false;

	/**
	 * Constructs a new Unit by calling the super constructor (CollidableObject)
	 *	and setting the speed.
	 * 
	 * @param texture : The image for this specific Unit.
	 * @param speed : The speed of this specific Unit.
	 * @param xPos : The x position in world coordinates of this specific Unit.
	 * @param yPos : The y position in world coordinates of this specific Unit.
	 */
	public Unit(Texture texture, int speed, float xPos, float yPos)
	{
		super(texture, xPos, yPos);
		this.speed = speed;
	}

	/***
	 * Sets the Unit to its initial draw position. Must be implemented by all Units.
	 */
	public abstract void setToInitialDrawPosition();

	/**
	 * Sets the speed of the Unit.
	 * 
	 * @param newSpeed : The speed of the Unit.
	 */
	public void setSpeed(int newSpeed)
	{
		speed = newSpeed;
	}

	/**
	 * Gets the speed of the Unit.
	 * 
	 * @return : The speed of the unit.
	 */
	public int getSpeed()
	{
		return speed;
	}

	/**
	 * Sets the speed of the Unit.
	 * 
	 * @param newSpeed : The speed of the Unit.
	 */
	public void setJumpSpeed(int newSpeed)
	{
		jumpSpeed = newSpeed;
	}

	/**
	 * Gets the speed of the Unit.
	 * 
	 * @return : The speed of the unit.
	 */
	public int getJumpSpeed()
	{
		return jumpSpeed;
	}

	/**
	 * Updates the Unit's position. Must be implemented by all Units.
	 */
	public abstract void update();

	/**
	 * Moves the Unit right according to its speed.
	 */
	public void moveRight()
	{
		xPosChange = speed * Gdx.graphics.getDeltaTime();
		setXPos(getXPos() + xPosChange);
		setPosition(getXPos() - gs.getWorldXPos(), getYPos());
		if (isFlipX() == true)
		{
			flip(true, false);
		}
	}

	/**
	 * Moves the Unit left according to its speed.
	 */
	public void moveLeft()
	{
		xPosChange = -(speed * Gdx.graphics.getDeltaTime());
		setXPos(getXPos() + xPosChange);
		setPosition(getXPos() - gs.getWorldXPos(), getYPos());
		if (isFlipX() == false)
		{
			flip(true, false);
		}
	}
	
	/**
	 * Makes the Unit stand still.
	 */
	public void standStill()
	{
		xPosChange = -gs.getHeroMovement() * Gdx.graphics.getDeltaTime();
		setPosition(getX() - xPosChange, getY());
	}
	
	/**
	 * Gets the Unit's max health. Must be implemented by all Units.
	 * 
	 * @return : The Unit's max health.
	 */
	public abstract int getMaxHealth();
	
	/**
	 * Handles the Unit dying. Must be implemented by all Units.
	 */
	public abstract void die();
	
	/**
	 * Sets the Unit's health.
	 * 
	 * @param health : The Unit's health.
	 */
	public void setHealth(int newHealth)
	{
		health = newHealth;
		if (health < 1) 
		{
		  die();
		}
	}
	
	/**
	 * Gets the Unit's health.
	 * @return : The Unit's health.
	 */
	public int getHealth()
	{
		return health;
	}
	/**
	 * Gets whether the Unit is alive.
	 * 
	 * @return : Whether the Unit is alive.
	 */
	public boolean getIsAlive()
	{
		return isAlive;
	}
	
	/**
	 * Sets whether the Unit is alive.
	 * 
	 * @param newIsAlive : Whether the Unit is alive.
	 */
	public void setIsAlive(boolean newIsAlive)
	{
		isAlive = newIsAlive; 
	}
	
	/**
	 * Gets whether the Unit has a HealthBar.
	 * @return : Whether the Unit has a HealthBar.
	 */
	public boolean getHasHealthBar()
	{
		return hasHealthBar;
	}
	
	/**
	 * Sets whether the Unit has a HealthBar.
	 * 
	 * @param newHasHealthBarStatus : Whether the Unit has a HealthBar.
	 */
	public void setHasHealthBar(boolean newHasHealthBarStatus)
	{
		hasHealthBar = newHasHealthBarStatus;
	}
}