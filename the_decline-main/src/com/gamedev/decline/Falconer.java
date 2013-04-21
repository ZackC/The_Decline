// Package Declaration //
package com.gamedev.decline;

// Java Package Support //
// { Not Applicable }

// Badlogic Package Support // 
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.TimeUtils;

/**
 * com/gamedev/decline/Falconer.java
 * 
 * @author(s) : Ian Middleton, Zach Coker, Zach Ogle
 * @version : 2.0 Last Update : 4/20/2013 Update By : Zach Ogle
 * 
 * Source code for the Falconer class. The Falconer class represents
 *	a Falconer object in the game. It extends and uses the Unit class.
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
	private Falcon falcon;
	private float timeBetweenFlights = 8;
	private long lastFlight;
	private Texture falconerTexture, falconerWithoutFalconTexture;
	private Color color;
	
	/**
	 * Instantiates a new Falconer object by calling the super constructor (Unit)
	 * and setting the draw size. The Falconer is also assigned a Falcon.
	 * 
	 * @param falconerTexture : The image of the Falconer with a Falcon.
	 * @param falconerWithoutFalconTexture : The image of the Falconer without a Falcon.
	 * @param falconTexture : The image of the Falcon.
	 */
	public Falconer(Texture falconerTexture, Texture falconerWithoutFalconTexture, Texture falconTexture)
	{
		super(falconerTexture, INITIAL_SPEED, 0, 0);
		setSize(WIDTH, HEIGHT);
		this.falcon = new Falcon(falconTexture);
		lastFlight = TimeUtils.nanoTime();
		this.falconerTexture = falconerTexture;
		this.falconerWithoutFalconTexture = falconerWithoutFalconTexture;
		color = falcon.getColor();
		falcon.setColor(Color.CLEAR);
	}

	/**
	 * Sets the Falconer's draw position to the right edge of the screen.
	 */
	public void setToInitialDrawPosition()
	{
		
		health = getMaxHealth();
		setPosition(START_XDRAW, START_YDRAW);
		falcon.setToInitialDrawPosition();
	}

	/**
	 * Keeps the Falconer as close to the right edge of the screen as possible. The Falcon will
	 *	stay with the Falconer until it flies.
	 */
	public void update()
	{
		if (speed * Gdx.graphics.getDeltaTime() + getX() + WIDTH < Gdx.graphics.getWidth())
		{
			moveRight();
			if (isFlipX() == false)
			{
				flip(true, false);
			}
		}
		else if (getX() + WIDTH > Gdx.graphics.getWidth() || gs.getHeroMovement() < 0)
		{
			moveLeft();
		}
		else
		{
			standStill();
		}
		if (TimeUtils.nanoTime() > lastFlight + (timeBetweenFlights * 1000000000L))
		{
			falcon.setIsFlying(true);
			lastFlight = TimeUtils.nanoTime();
			setTexture(falconerWithoutFalconTexture);
			falcon.setColor(color);
			
		}
		else if (falcon.getIsFlying())
		{
			xPosChange = -(Falcon.X_SPEED * Gdx.graphics.getDeltaTime());
			falcon.setXPos(falcon.getXPos() + xPosChange);
			yPosChange = falcon.getJumpSpeed();
			falcon.setYPos(falcon.getYPos() + yPosChange);
			if (falcon.getYPos() < GlobalSingleton.HERO_YDRAW)
			{
				falcon.setYPos(GlobalSingleton.HERO_YDRAW);
			}
			if (falcon.getXPos() + WIDTH / 2 <= gs.getHeroXPos() + gs.getHeroWidth() / 2)
			{
				falcon.setJumpSpeed(-Falcon.Y_SPEED * 2);
			}
			if (falcon.getX() < -1 * Falcon.WIDTH)
			{
				falcon.setIsFlying(false);
				falcon.setIsLanding(true);
				falcon.setJumpSpeed(Falcon.Y_SPEED * 2);
				falcon.setYPos(Gdx.graphics.getHeight());
				falcon.setX(getX());
				falcon.setXPos(getXPos());
				falcon.setPosition(falcon.getX(), falcon.getYPos());
				falcon.setHasDamagedHero(false);
			}
			else
			{
				falcon.setPosition(falcon.getXPos() - gs.getWorldXPos(), falcon.getYPos());
			}
		}
		else if (falcon.getIsLanding())
		{
			yPosChange = falcon.getJumpSpeed();
			falcon.setYPos(falcon.getYPos() + yPosChange);
			if (falcon.getYPos() <= Falcon.START_YDRAW)
			{
				falcon.setYPos(Falcon.START_YDRAW);
				falcon.setIsLanding(false);
				setTexture(falconerTexture);
				falcon.setColor(Color.CLEAR);
			}
			falcon.setPosition(falcon.getX(), falcon.getYPos());
		}
		if (!falcon.getIsFlying())
		{
			if (speed * Gdx.graphics.getDeltaTime() + getX() + WIDTH < Gdx.graphics.getWidth())
			{
				falcon.moveRight();
			}
			else if (getX() + WIDTH > Gdx.graphics.getWidth() || gs.getHeroMovement() < 0) 
			{
				falcon.moveLeft();
				if (falcon.isFlipX() == true)
				{
					falcon.flip(true, false);
				}
			}
			else
			{
				falcon.standStill();
			}
		}
	}

	/**
	 * Gets the Falconer's max health.
	 * @return : The Falconer's max health.
	 */
	@Override
	public int getMaxHealth()
	{
		return MAX_HEALTH;
	}

	/**
	 * Handles the Falconer and Falcon dying.
	 */
	@Override
	public void die()
	{
		setIsAlive(false);
		setHasHealthBar(false);
		falcon.setIsAlive(false);
	}
	
	/**
	 * Sets whether the Falconer and Falcon are alive.
	 * 
	 * @param newIsAlive : Whether the Falconer and Falcon are alive.
	 */
	public void setIsAlive(boolean newIsAlive)
	{
		super.setIsAlive(newIsAlive);
		falcon.setIsAlive(newIsAlive);
	}
	
	/**
	 * Sets the x position of the Falcon's world coordinates.
	 * 
	 * @param newXPos : The x position of the Falcon's world coordinates.
	 */
	public void setFalconXPos(float newXPos)
	{
		falcon.setXPos(newXPos);
	}
	
	/**
	 * Sets the y position of the Falcon's world coordinates.
	 * 
	 * @param newYPos : The y position of the Falcon's world coordinates.
	 */
	public void setFalconYPos(float newYPos)
	{
		falcon.setYPos(newYPos + Falcon.HEIGHT);
	}
	
	/**
	 * Gets the Falconer's Falcon.
	 * 
	 * @return : The Falconer's Falcon.
	 */
	public Falcon getFalcon()
	{
		return falcon;
	}
	
	/**
	 * Draws the Falconer and Falcon to the screen.
	 * 
	 * @param batch : The SpriteBatch object that draws the Falconer and Falcon.
	 */
	public void draw(SpriteBatch batch)
	{
		super.draw(batch);
		falcon.draw(batch);
	}
}