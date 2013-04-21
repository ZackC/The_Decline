// Package Declaration //
package com.gamedev.decline;

// Java Package Support //
// { Not Applicable }

// Badlogic Package Support //
import com.badlogic.gdx.graphics.Texture;

/**
 * com/gamedev/decline/Fireball.java
 * 
 * @author(s) : Ian Middleton, Zach Coker, Zach Ogle
 * @version : 2.0 Last Update : 4/21/2013 Update By : Zach Ogle
 * 
 * Source code for the Fireball class. The Fireball class represents
 *	a Fireball object in the game. It extends and uses the Unit class.
 *
 */
public class Fireball extends Unit
{
	// Global Singleton //
	// { Not Applicable }

	// Constants //
	public static final int DRAW_WIDTH = 30;
	public static final int DRAW_HEIGHT = 30;
	public static final int INITIAL_SPEED = 500;
	public static final int MAX_HEALTH = 1;
	public static final int COLLISION_DAMAGE_TO_SELF = 1;

	// Internal Variables //
	private int orientation;

	/**
	 * Instantiates a new Fireball object by calling the super constructor (Unit)
	 *	and setting the draw size.
	 */
	public Fireball(Texture texture)
	{
		super(texture, INITIAL_SPEED, 0, 0);
		setSize(DRAW_WIDTH, DRAW_HEIGHT);
	}

	/**
	 * Required implementation of abstract method.
	 */
	@Override
	public void setToInitialDrawPosition()
	{
	}
	
	/**
	 * Sets the Fireball to its initial draw position depending on
	 *	the Boss coordinates.
	 *
	 * @param bossX : The x position of the Boss's screen coordinates.
	 * @param bossY : The y position of the Boss's screen coordinates.
	 */
	public void setToInitialDrawPosition(float bossX, float bossY)
	{
		setXPos(gs.getWorldXPos() + bossX - 10);
		setYPos(bossY + 45);
	}

	/**
	 * Sets the orientation of the Fireball.
	 * 
	 * @param orientation : The Fireball's orientation.
	 */
	public void setOrientation(int orientation)
	{
		this.orientation = orientation;
	}

	/**
	 * Moves the Fireball according to its orientation.
	 */
	public void update()
	{
		if (orientation == GlobalSingleton.RIGHT)
		{
			moveRight();
		}
		else if (orientation == GlobalSingleton.LEFT)
		{
			moveLeft();
		}
	}

	/**
	 * Gets the Fireball's max health.
	 * 
	 * @return : The Fireball's max health.
	 */
	@Override
	public int getMaxHealth()
	{
		return MAX_HEALTH;
	}

	/**
	 * Handles the Fireball dying.
	 */
	@Override
	public void die()
	{
		setIsAlive(false);
	}
}