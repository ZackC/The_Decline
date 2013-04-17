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
	private Falcon falcon;
	private int timeBetweenFlights = 5;
	private long lastFlight;
	private Texture falconerTexture, falconerWithoutFalconTexture;
	private Color color;
	
	/**
	 * Instantiates a new Falconer object by calling the super constructor (Unit)
	 * and setting the draw size.
	 * 
	 * @param texture
	 *            : The image of the Falconer.
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
	 * The update function is called every global game update. Keeps the Falconer
	 * 	as close to the right edge of the screen as possible.
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
		//System.out.println("Falcon is flying: "+falcon.getIsFlying());
		//System.out.println("Falcon is landing: "+falcon.getIsLanding());
		if (TimeUtils.nanoTime() > lastFlight + (timeBetweenFlights * 1000000000L))
		{
		       // System.out.println("1");
			falcon.setIsFlying(true);
			lastFlight = TimeUtils.nanoTime();
			setTexture(falconerWithoutFalconTexture);
			falcon.setColor(color);
			
		}
		else if (falcon.getIsFlying())
		{
		        //System.out.println("3");
			xPosChange = -(Falcon.X_SPEED * Gdx.graphics.getDeltaTime());
			falcon.setXPos(falcon.getXPos() + xPosChange);
			yPosChange = falcon.getJumpSpeed();
			falcon.setYPos(falcon.getYPos() + yPosChange);
			if (falcon.getYPos() < GlobalSingleton.HERO_YDRAW)
			{
			    //System.out.println("8");
				falcon.setYPos(GlobalSingleton.HERO_YDRAW);
			}
			if (falcon.getXPos() + WIDTH / 2 <= gs.getHeroXPos() + gs.getHeroWidth() / 2)
			{
			  //System.out.println("9");
				falcon.setJumpSpeed(-Falcon.Y_SPEED * 2);
			}
			if (falcon.getX() < -1 * Falcon.WIDTH)
			{
			  //System.out.println("10");
				falcon.setIsFlying(false);
				falcon.setIsLanding(true);
				falcon.setJumpSpeed(Falcon.Y_SPEED * 2);
				falcon.setYPos(Gdx.graphics.getHeight());
				falcon.setX(getX());
				falcon.setXPos(getXPos());
				falcon.setPosition(falcon.getX(), falcon.getYPos());
			}
			else
			{
			  //System.out.println("11");
			  //System.out.println(falcon.getXPos());
			  //System.out.println(gs.getWorldXPos());
				falcon.setPosition(falcon.getXPos() - gs.getWorldXPos(), falcon.getYPos());
			}
		}
		else if (falcon.getIsLanding())
		{
		        //System.out.println("4");
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
		        //System.out.println("2");
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
	  falcon.setIsAlive(false);
	}
	
	public void setIsAlive(boolean newIsAlive)
	{
		super.setIsAlive(newIsAlive);
		falcon.setIsAlive(newIsAlive);
	}
	
	public void setFalconXPos(float newXPos)
	{
		falcon.setXPos(newXPos);
	}
	
	public void setFalconYPos(float newYPos)
	{
		falcon.setYPos(newYPos + Falcon.HEIGHT);
	}
	
	public void draw(SpriteBatch batch)
	{
		super.draw(batch);
		falcon.draw(batch);
	}
}