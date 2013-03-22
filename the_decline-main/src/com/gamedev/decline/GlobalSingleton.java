package com.gamedev.decline;

/*
 * The class that holds the global variables for the game
 */
public class GlobalSingleton {
	
	// the singleton instance of the class
    private static GlobalSingleton instance = null;
    //the starting x position of the hero
	private int startingHeroXPos = 120;
	// the starting y position of the hero
	private int startingHeroYPos = 20;
	// the width of the hero
	private int heroWidth;
	// the height of the hero
	private int heroHeight;
	// the current x position of the hero
	private float heroXPos = startingHeroXPos;
	// the amount the hero is currently moving
	private float heroMovement = 0;

	
	/*
	 * the default constructor of the class
	 */
	private GlobalSingleton() 
	{
		
	}
	
	/*
	 * The way to get an instance of the class.
	 * Returns an instance if it is already created;
	 * Otherwise it creates a new one and then returns it.
	 */
	public static GlobalSingleton getInstance()
	{
		if(instance == null)
		{
			instance = new GlobalSingleton();
			return instance;
		}
		else
		{
			return instance;
		}
	}

	/*
	 * Returns the starting x position of the hero
	 */
	public int getStartingHeroXPos()
	{
		return startingHeroXPos;
	}
	
	
	/*
	 * Returns the starting y position of the hero
	 */
	public int getStartingHeroYPos()
	{
		return startingHeroYPos;
	}
	
	/*
	 * Returns the width of the hero
	 */
	public int getHeroWidth()
	{
		return heroWidth;
	}
	
	/*
	 * returns the current x position of the hero
	 */
	public float getHeroXPos()
	{
		return heroXPos;
	}
	
	/*
	 * returns the height of the hero
	 */
	public int getHeroHeight()
	{
		return heroHeight;
	}
	
	/*
	 * returns the current movement of the hero (how much he is moving 
	 * at that time)
	 */
	public float getHeroMovement()
	{
		return heroMovement;
	}
	/*
	 * returns the starting x position of the hero
	 */
	public void setStartingHeroXPos(int newXPos)
	{
		startingHeroXPos = newXPos;
	}
	
	/*
	 * sets the staring y position of the hero
	 * 
	 * newYPos - the new y position
	 */
	public void setStartingHeroYPos(int newYPos)
	{
		startingHeroYPos = newYPos;
	}
	
	/*
	 * sets the width of the hero
	 * 
	 * newWidth - the width 
	 */
	public void setHeroWidth(int newWidth)
	{
		heroWidth = newWidth;
	}
	/*
	 * sets the height of the hero
	 * 
	 * newHeight - the hero's height
	 */
	public void setHeroHeight(int newHeight)
	{
		heroHeight = newHeight;
	}
	/*
	 * sets the current x position of the hero
	 * 
	 * newPos - the new x position
	 */
	public void setHeroXPos(float newPos)
	{
		heroXPos = newPos;
	}
	
	/*
	 * sets the current hero movement
	 * 
	 * newHeroMovement - the current hero movement
	 */
	public void setHeroMovement(float newHeroMovement)
	{
		heroMovement = newHeroMovement;
	}
	
	
}
