// Package Declaration //
package com.gamedev.decline;

// Java Package Support //
// { Not Applicable }

// Badlogic Package Support //
import com.badlogic.gdx.graphics.Texture;

/**
 * com/gamedev/decline/Bullet.java
 * 
 * @author(s) : Ian Middleton, Zach Coker, Zach Ogle
 * @version : 2.0 Last Update : 4/19/2013 Update By : Zach Ogle
 * 
 * Source code for the Bullet class. The Bullet class represents a
 *	Bullet object in the game. It extends and uses the Unit class.
 * 
 */
public class Bullet extends Unit {
	// Global Singleton //
	// { Not Applicable }

	// Constants //
	public static final int DRAW_WIDTH = 30;
	public static final int DRAW_HEIGHT = 30;
	public static final int INITIAL_SPEED = 1000;
	public static final float START_XDRAW = (float) (GlobalSingleton.HERO_XDRAW);
	public static final float START_YDRAW = (GlobalSingleton.getInstance().getHeroHeight() / 2
			- GlobalSingleton.getInstance().getHeroHeight() / 7);
	public static final int MAX_HEALTH = 1;
	public static final int COLLISION_DAMAGE_TO_SELF = 1;

	// Internal Variables //
	private int bulletOrientation;

	/**
	 * Instantiates a new Bullet object by calling the super constructor (Unit)
	 * and setting the draw size of the Bullet.
	 * 
	 * @param texture : The image of the Bullet.
	 */
	public Bullet(Texture texture)
	{
		super(texture, INITIAL_SPEED, 0, 0);
		setSize(DRAW_WIDTH, DRAW_HEIGHT);
	}

	/**
	 * Sets the Bullet object to its initial start position based on its orientation.
	 */
	public void setToInitialDrawPosition()
	{
		if (bulletOrientation == GlobalSingleton.RIGHT)
		{
			setXPos(gs.getWorldXPos() + gs.getHeroXDraw() + gs.getHeroWidth() - gs.getHeroWidth() / 3);
			setYPos(START_YDRAW + gs.getHeroYDraw());
		}
		else
		{
			setXPos(gs.getWorldXPos() + gs.getHeroXDraw());
			setYPos(START_YDRAW + gs.getHeroYDraw());
		}
	}

	/**
	 * Sets the orienatation of the Bullet.
	 * 
	 * @param bulletOrientation : The direction the Bullet should be oriented.
	 */
	public void setOrientation(int bulletOrientation)
	{
		this.bulletOrientation = bulletOrientation;
	}

	/**
	 * Uses the orientation of the Bullet to determine which direction the Bullet should
	 *	move and updates the Bullet's position accordingly.
	 */
	public void update()
	{
		if (bulletOrientation == GlobalSingleton.RIGHT)
		{
			moveRight();
		}
		else if (bulletOrientation == GlobalSingleton.LEFT)
		{
			moveLeft();
		}
	}

	/***
	 * Gets the Bullet's max health.
	 * 
	 * @return: The max health of the Bullet.
	 */
	@Override
	public int getMaxHealth()
	{
	  return MAX_HEALTH;
	}

	/***
	 * Handles the Bullet dying.
	 */
	@Override
	public void die()
	{
	  setIsAlive(false);
	}
}