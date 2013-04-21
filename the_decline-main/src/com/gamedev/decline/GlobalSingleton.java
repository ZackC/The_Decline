// Package Declaration //
package com.gamedev.decline;

// Java Package Support //
// { Not Applicable }

// Badlogic Package Support //
// { Not Applicable }

/**
 * com/gamedev/decline/GlobalSingleton.java
 * 
 * @author(s) : Ian Middleton, Zach Coker, Zach Ogle
 * @version : 2.0 Last Update : 3/22/2013 Update By : Ian Middleton
 * 
 * Source code for the GlobalSingleton class. The GlobalSingleton class
 *	holds information that will need to be accessed across the game.
 * 
 */
public class GlobalSingleton
{
	// Global Singleton //
	private static GlobalSingleton instance = null;

	// Constants of the Object //
	public static final int HERO_XDRAW = 120;
	public static final int HERO_YDRAW = 20;
	public static final int RIGHT = 0;
	public static final int LEFT = 1;

	// Internal Variables //
	private float heroWidth, heroHeight, heroXDraw = HERO_XDRAW, heroYDraw = HERO_YDRAW;
	private float heroXPos = HERO_XDRAW, heroYPos = HERO_YDRAW, worldXPos = 0, heroMovement = 0;
	private int heroOrientation = 0;
	private boolean isHeroHiding = false, isHeroJumping = false, isHeroAlive = true;
	private boolean isGameOver = false, isGameWon = false, isHeroTryingToHide = false;
	private HealthBarManager hbm;

	/**
	 * Constructor for the GlobalSingleton object.
	 */
	private GlobalSingleton()
	{
	}

	/**
	 * Gets the instance of the class. If one does not exist it creates a new one.
	 * 
	 * @return : The instance of the class.
	 */
	public static GlobalSingleton getInstance() {
		if (instance == null)
		{
			instance = new GlobalSingleton();
			return instance;
		}
		else
		{
			return instance;
		}
	}

	/**
	 * Gets the width of the Hero.
	 * 
	 * @return : The width of the Hero.
	 */
	public float getHeroWidth()
	{
		return heroWidth;
	}

	/**
	 * Gets the height of the Hero.
	 * 
	 * @return : The height of the Hero.
	 */
	public float getHeroHeight()
	{
		return heroHeight;
	}

	/**
	 * Gets the x position of the Hero's world coordinates.
	 * 
	 * @return : The x position of the Hero's world coordinates.
	 */
	public float getHeroXPos()
	{
		return heroXPos;
	}
	
	/**
	 * Gets the x position of the Hero's screen coordinates.
	 * 
	 * @return : The x position of the Hero's screen coordinates.
	 */
	public float getHeroXDraw()
	{
		return heroXDraw;
	}
	
	/**
	 * Gets the y position of the Hero's screen coordinates.
	 * 
	 * @return : The y position of the Hero's screen coordinates.
	 */
	public float getHeroYDraw()
	{
		return heroYDraw;
	}

	/**
	 * Gets the current movement speed of the Hero. The movement speed is
	 * positive if the Hero is moving right and negative if the Hero is moving
	 * left.
	 * 
	 * @return : The movement speed of the Hero.
	 */
	public float getHeroMovement()
	{
		return heroMovement;
	}

	/**
	 * Gets the current orientation of the Hero. The orientation is set to 0 for right
	 *	and 1 for left.
	 * 
	 * @return : The orientation of the Hero.
	 */
	public int getHeroOrientation()
	{
		return heroOrientation;
	}

	/**
	 * Gets the x position of the left side of the screen's world coordinates.
	 * @return
	 */
	public float getWorldXPos()
	{
		return worldXPos;
	}

	/**
	 * Sets the width of the Hero.
	 * 
	 * @param width : The new width of the Hero.
	 */
	public void setHeroWidth(float width)
	{
		heroWidth = width;
	}

	/**
	 * Sets the height of the Hero.
	 * 
	 * @param height : The new height of the Hero.
	 */
	public void setHeroHeight(float height)
	{
		heroHeight = height;
	}

	/**
	 * Sets the x position of the Hero's world coordinates.
	 * 
	 * @param newPos : The new x position of the Hero's world coordinates.
	 */
	public void setHeroXPos(float newPos)
	{
		heroXPos = newPos;
	}
	
	/**
	 * Sets the x position of the Hero's screen coordinates.
	 * 
	 * @param newDrawPos : The new x position of the Hero's screen coordinates.
	 */
	public void setHeroXDraw(float newDrawPos)
	{
		heroXDraw = newDrawPos;
	}
	
	/**
	 * Sets the y position of the Hero's screen coordinates.
	 * 
	 * @param newDrawPos : The new y position of the Hero's screen coordinates.
	 */
	public void setHeroYDraw(float newDrawPos)
	{
		heroYDraw = newDrawPos;
	}

	/**
	 * Sets the Hero's movement.
	 * 
	 * @param newHeroMovement : The Hero's new movement.
	 */
	public void setHeroMovement(float newHeroMovement)
	{
		heroMovement = newHeroMovement;
	}

	/**
	 * Sets the Hero's orientation.
	 * 
	 * @param newHeroOrientation : The Hero's new orientation.
	 */
	public void setHeroOrientation(int newHeroOrientation)
	{
		heroOrientation = newHeroOrientation;
	}

	/***
	 * Sets the x position of the left side of the screen's world coordinates.
	 * 
	 * @param newWorldXPos : The x position of the left side of the screen's world coordinates.
	 */
	public void setWorldXPos(float newWorldXPos)
	{
		worldXPos = newWorldXPos;
	}

	/***
	 * Gets whether the Hero is hiding.
	 * 
	 * @return: Whether the Hero is hiding.
	 */
	public boolean getIsHeroHiding()
	{
		return isHeroHiding;
	}

	/***
	 * Sets whether the Hero is hiding.
	 * 
	 * @param newHeroHidingValue : Whether the Hero is hiding.
	 */
	public void setIsHeroHiding(boolean newHeroHidingValue)
	{
		isHeroHiding = newHeroHidingValue;
	}

	/***
	 * Gets whether the Hero is jumping.
	 * 
	 * @return: Whether the Hero is jumping.
	 */
	public boolean getIsHeroJumping()
	{
		return isHeroJumping;
	}

	/***
	 * Sets whether the Hero is jumping.
	 * 
	 * @param newHeroJumpingValue : Whether the Hero is jumping.
	 */
	public void setIsHeroJumping(boolean newHeroJumpingValue)
	{
		isHeroJumping = newHeroJumpingValue;
	}

	/***
	 * Gets whether the Hero is alive.
	 * 
	 * @return : Whether the Hero is alive.
	 */
	public boolean getIsHeroAlive()
	{
		return isHeroAlive;
	}

	/***
	 * Sets whether the Hero is alive.
	 * 
	 * @param newHeroLivingValue : Whether the Hero is alive.
	 */
	public void setIsHeroAlive(boolean newHeroLivingValue)
	{
		isHeroAlive = newHeroLivingValue;
	}

	/***
	 * Sets the y position of the Hero in world coordinates.
	 * 
	 * @param newHeroYPos : The y position of the Hero in world coordinates.
	 */
	public void setHeroYPos(float newHeroYPos)
	{
		heroYPos = newHeroYPos;
	}

	/***
	 * Gets the y position of the Hero in world coordinates.
	 * 
	 * @return: The y position of the Hero in world coordinates.
	 */
	public float getHeroYPos()
	{
		return heroYPos;
	}
	
	/***
	 * Gets the game's HealthBarManager.
	 * @return: The game's HealthBarManager.
	 */
	public HealthBarManager getHealthBarManager()
	{
		return hbm;
	}
	
	/***
	 * Sets the game's HealthBarManager.
	 * 
	 * @param newHBM : The game's new HealthBarManager.
	 */
	public void setHealthBarManager(HealthBarManager newHBM)
	{
		hbm = newHBM;
	}
	
	/**
	 * Sets whether the game is over.
	 * 
	 * @param newIsGameOver : Whether the game is over.
	 */
	public void setIsGameOver(boolean newIsGameOver)
	{
		isGameOver = newIsGameOver;
	}
	
	/**
	 * Gets whether the game is over.
	 * 
	 * @return : Whether the game is over.
	 */
	public boolean getIsGameOver()
	{
		return isGameOver;
	}
	
	/**
	 * Gets whether the Hero is trying to hide.
	 * 
	 * @return : Whether the Hero is trying to hide.
	 */
	public boolean getIsHeroTryingToHide()
	{
		return isHeroTryingToHide;
	}
	
	/**
	 * Sets whether the Hero is trying to hide.
	 * @param newHeroIsTryingToHide : Whether the Hero is trying to hide.
	 */
	public void setIsHeroTryingToHide(boolean newHeroIsTryingToHide)
	{
		isHeroTryingToHide = newHeroIsTryingToHide;
	}

	/**
	 * Sets whether the game has been won.
	 * 
	 * @param isGameWon : Whether the game has been won.
	 */
	public void setIsGameWon(boolean isGameWon)
	{
		this.isGameWon = isGameWon;
	}
	
	/**
	 * Gets whether the game has been won.
	 * 
	 * @return : Whether the game has been won.
	 */
	public boolean getIsGameWon()
	{
		return isGameWon;
	}
}