// Package Declaration //
package com.gamedev.decline;

// Java Package Support //
// { Not Applicable }

// Badlogic Package Support // 
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

/**
 * com/gamedev/decline/Boss.java
 * 
 * @author(s) : Ian Middleton, Zach Coker, Zach Ogle
 * @version : 2.0 Last Update : 4/19/2013 Update By : Zach Ogle
 * 
 * Source code for the Boss class. The Boss class represents a Boss
 *	object in the game. It extends and uses the Unit class.
 * 
 */
public class Boss extends Unit
{
	// Global Singleton //
	// { Not applicable }

	// Constants //
	public static final int INITIAL_SPEED = 0;
	public static final int WIDTH = 60;
	public static final int HEIGHT = 135;
	public static final int START_XDRAW = Gdx.graphics.getWidth() - (WIDTH*2) - 50;
	public static final int START_YDRAW = 800;
	public static final int MAX_HEALTH = 100;

	// Internal Variables //
	private int orientation = GlobalSingleton.RIGHT;
	private Texture fireballTexture;
	private boolean fireballMode = false, goingUp = false;

	/**
	 * Instantiates a new Boss object by calling the super constructor (Unit),
	 *	setting the draw size, and saving the fireball texture.
	 *
	 * @param bossTexture : The texture to use for the Boss.
	 * @param fireballTexture : The texture to use for the Boss's fireball form.
	 */
	public Boss(Texture bossTexture, Texture fireballTexture)
	{
		super(bossTexture, INITIAL_SPEED, 0, 0);
		setSize(WIDTH, HEIGHT);
		this.fireballTexture = fireballTexture;
	}

	/**
	 * Sets the Boss's draw position to the right side of and above the screen.
	 */
	public void setToInitialDrawPosition()
	{
	    health = getMaxHealth();
		setXPos(START_XDRAW);
		setYPos(START_YDRAW);
		setPosition(getXPos(), getYPos());
	}
	
	/**
	 * Sets the Boss's draw position to his ground coordinates.
	 */
	public void setToGroundDrawPosition()
	{
		setPosition(START_XDRAW + 25, 20);
	}

	/**
	 * Changes the Boss's orientation.
	 */
	public void flipOrientation()
	{
		if(orientation == GlobalSingleton.RIGHT)
		{
			orientation = GlobalSingleton.LEFT;
		}
		else
		{
			orientation = GlobalSingleton.RIGHT;
		}
		flip(true, false);
	}
	
	/**
	 * Sets the Boss's fireball mode.
	 * 
	 * @param mode : Whether fireball mode should be active or not.
	 */
	public void setFireballMode(boolean mode)
	{
		fireballMode = mode;
	}
	
	/**
	 * Sets the Boss's fireball mode directions
	 * 
	 * @param goingUp : Whether fireball mode direction is up or not.
	 */
	public void setFireballDirection(boolean goingUp)
	{
		this.goingUp = goingUp;
	}
	
	/**
	 * Gets the Boss's orientation.
	 * 
	 * @return : The Boss's orientation.
	 */
	public int getOrientation()
	{
		return orientation;
	}
	
	/**
	 * Required implementation of abstract method.
	 */
	public void update()
	{
	}

	/**
	 * Gets the Boss's max health.
	 * 
	 * @return : The Boss's max health.
	 */
	@Override
	public int getMaxHealth()
	{
	  return MAX_HEALTH;
	}

	/**
	 * Handles the death of the Boss.
	 */
	@Override
	public void die()
	{
	  setIsAlive(false);
	  setHasHealthBar(false);
	}
	
	/**
	 * Moves the Boss up.
	 */
	public void moveUp()
	{
		yPosChange = 600 * Gdx.graphics.getDeltaTime();
		setYPos(getYPos() + yPosChange);
		setPosition(getXPos(), getYPos());
	}
	
	/**
	 * Moves the Boss down.
	 */
	public void moveDown()
	{
		yPosChange = -600 * Gdx.graphics.getDeltaTime();
		setYPos(getYPos() + yPosChange);
		setPosition(getXPos(), getYPos());
	}
	
	/**
	 * Draws the Boss to the screen.
	 * 
	 * @param batch : The SpriteBatch object that draws the Boss.
	 */
	@Override
	public void draw(SpriteBatch batch)
	{
		if(fireballMode)
		{
			if(goingUp == true)
			{
				batch.draw(new TextureRegion(fireballTexture), getX(), getY(), 200/2f, 200/2f, 200f, 200f, 1f, 1f, 0f, true); 
			}
			else
			{
				batch.draw(new TextureRegion(fireballTexture), getX(), getY(), 200/2f, 200/2f, 200f, 200f, 1f, 1f, 180f, true);
			}		
		}
		else
		{
			super.draw(batch);
		}
	}
}