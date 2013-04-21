// Package Declaration //
package com.gamedev.decline;

// Java Package Support //
// { Not Applicable }

// Badlogic Package Support //
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.utils.Array;

/**
 * 
 * com/gamedev/decline/Hero.java
 * 
 * @author(s) : Ian Middleton, Zach Coker, Zach Ogle
 * @version : 2.0 Last Update : 4/21/2013 Update By : Zach Ogle
 * 
 * Source code for the Hero class. The Hero class represents the Hero
 *	object in the game. It extends and uses the Unit class.
 * 
 */
public class Hero extends Unit
{
	// Global Singleton //
	private GlobalSingleton gs = GlobalSingleton.getInstance();

	// Constants of the Object //
	public static final int START_XDRAW = 120;
	public static final int START_YDRAW = 20;
	public static final int SPEED = 200;
	public static final int JUMP_SPEED = 5;
	public static final int JUMP_DISTANCE = 190;
	public static final int MAX_AMMO = 50;
	public static final int MAX_HEALTH = 100;
	public static final int AMMO_DISPLAY_X_POSITION = 30;
	public static final int AMMO_DISPLAY_Y_POSITION = Gdx.graphics.getHeight() - 50;
	public static final int STARTING_HERO_LIVES = 3;
	public static final int STARTING_ROCK_POWER_COUNT = 3;
	public static final int LIVES_DISPLAY_Y_POSITION = Gdx.graphics.getHeight() - 70;
	public static final int LIVES_DISPLAY_X_POSITION = AMMO_DISPLAY_X_POSITION + 150;
	public static final int LIVES_IMAGE_SIZE = 30;
	public static final int ROCK_POWER_X_POSITION = LIVES_DISPLAY_X_POSITION + (4 * LIVES_IMAGE_SIZE);
	public static final int ROCK_POWER_Y_POSITION = LIVES_DISPLAY_Y_POSITION;
	public static final int ROCK_POWER_SIZE = LIVES_IMAGE_SIZE;

	// Internal Variables //
	private int rockPowers = STARTING_ROCK_POWER_COUNT, lives = STARTING_HERO_LIVES, ammo = MAX_AMMO / 2;
	private float posChange = 0;
	private BitmapFont font = new BitmapFont();
	private Sound dyingSound;
	private Texture standingTexture, hidingTexture, rockTexture, heartTexture;

	/**
	 * Instantiates a new Hero object by calling the super constructor (Unit) and
	 *	saving the different textures and sounds, setting his jump speed, and setting
	 *	his size and position in world coordinates.
	 * 
	 * @param newStandingTexture : The image for when the Hero is standing.
	 * @param newHidingTexture : The image for when the Hero is hiding.
	 * @param newHeartTexture : The image for the Hero's lives display.
	 * @param newRockTexture : The image for the Hero's rock powers and when the Hero uses
	 *	a rock power.
	 * @param newDyingSound : The sound that will play when the Hero dies.
	 */
	public Hero(Texture newStandingTexture, Texture newHidingTexture, Texture newHeartTexture, 
	    Texture newRockTexture, Sound newDyingSound)
	{
		super(newStandingTexture, SPEED, START_XDRAW, START_YDRAW);
		standingTexture = newStandingTexture;
		hidingTexture = newHidingTexture;
		rockTexture = newRockTexture;
		setJumpSpeed(JUMP_SPEED);
		gs.setHeroHeight(getHeight());
		gs.setHeroWidth(getWidth());
		setIsAlive(true);
		heartTexture = newHeartTexture;
        dyingSound = newDyingSound;
	}

	/**
	 * Sets the Hero to his initial draw position.
	 */
	public void setToInitialDrawPosition()
	{
		setPosition(START_XDRAW, START_YDRAW);
	}

	/**
	 * Updates the Hero's x position in world coordinates of the Global Singleton
	 *	and handles any jumping that occurs.
	 */
	public void update()
	{
		gs.setHeroXPos(xPos);
		if (gs.getIsHeroJumping())
		{
			jump();
		}
	}
	
	/**
	 * Moves the Hero right and updates his x position in world coordinates in the
	 *	Global Singleton. Used in the Boss fight.
	 */
	public void moveRight()
	{
		super.moveRight();
		gs.setHeroXDraw(getXPos() - gs.getWorldXPos());
	}
	
	/**
	 * Moves the Hero left and updates his x position in world coordinates in the
	 *	Global Singleton. Used in the Boss fight.
	 */
	public void moveLeft()
	{
		super.moveLeft();
		gs.setHeroXDraw(getXPos() - gs.getWorldXPos());
	}
	
	/**
	 * Moves the Hero right while the screen is scrolling.
	 */
	public void moveRightScroll()
	{
		posChange = speed * Gdx.graphics.getDeltaTime();
		setXPos(getXPos() + posChange);
		gs.setWorldXPos(gs.getWorldXPos() + posChange);
		if (isFlipX() == true)
		{
			flip(true, false);
		}
	}

	/**
	 * Moves the Hero left while the screen is scrolling.
	 */
	public void moveLeftScroll()
	{
		posChange = -(speed * Gdx.graphics.getDeltaTime());
		setXPos(getXPos() + posChange);
		gs.setWorldXPos(gs.getWorldXPos() + posChange);
		if (isFlipX() == false)
		{
			flip(true, false);
		}
	}

	/**
	 * Gets the Hero's current Ammo count.
	 * 
	 * @return : The Hero's current Ammo count.
	 */
	public int getAmmo()
	{
		return ammo;
	}

	/**
	 * Gets the Hero's current health.
	 * 
	 * @return : The Hero's current health.
	 */
	public int getHealth()
	{
		return health;
	}

	/**
	 * Sets the Hero's current Ammo count.
	 * 
	 * @param ammo : The Hero's new Ammo count.
	 */
	public void setAmmo(int ammo)
	{
		this.ammo = ammo;
	}
	
	/**
	 * Makes the Hero hide if possible.
	 * 
	 * @param bushArray : The array of Bushes available to hide behind.
	 */
	public void hide(Array<Bush> bushArray)
	{
		boolean isBehindBush = false;
	    for (int i = 0; i < bushArray.size; i++)
	    {
	    	if (Intersector.intersectRectangles(getBoundingRectangle(),
	    			bushArray.get(i).getBoundingRectangle()))
	        {
	    		isBehindBush = true;
	            break;
	        }
	    }
	    if(isBehindBush)
	    {
	    	gs.setIsHeroHiding(true);
	        setTexture(hidingTexture);
	    }
	    else
	    {
	    	if(rockPowers > 0)
	    	{
	    		gs.setIsHeroHiding(true);
	    		setTexture(rockTexture);
	    		rockPowers = rockPowers - 1;;
	        }
	        else
	        {
	            gs.setIsHeroTryingToHide(true);
	            setTexture(hidingTexture);
	        }
	    }
		setSize(hidingTexture.getWidth(), hidingTexture.getHeight());
	}

	/**
	 * Makes the Hero stand up.
	 */
	public void stand()
	{
		gs.setIsHeroHiding(false);
		gs.setIsHeroTryingToHide(false);
		setTexture(standingTexture);
		setSize(standingTexture.getWidth(), standingTexture.getHeight());
	}

	/**
	 * Displays the Hero's Ammo count.
	 * @param batch : The SpriteBatch object which will draw the Ammo count.
	 */
	public void drawAmmoCount(SpriteBatch batch)
	{
		font.draw(batch, "Ammo Left: " + getAmmo(),	AMMO_DISPLAY_X_POSITION, AMMO_DISPLAY_Y_POSITION);
	}
	
	/**
	 * Displays the Hero's lives.
	 * @param batch : The SpriteBatch object which will draw the lives.
	 */
	public void drawLives(SpriteBatch batch)
	{
		for(int i = 0; i < lives; i++)
		{
			batch.draw(heartTexture,LIVES_DISPLAY_X_POSITION + i * LIVES_IMAGE_SIZE,LIVES_DISPLAY_Y_POSITION,
					LIVES_IMAGE_SIZE,LIVES_IMAGE_SIZE);
		}
	}
	
	/**
	 * Display's the Hero's rock powers.
	 * 
	 * @param batch : The SpriteBatch object which will draw the rock powers.
	 */
	public void drawRockPower(SpriteBatch batch)
	{
		for(int i = 0; i < rockPowers; i++)
		{
			batch.draw(rockTexture,ROCK_POWER_X_POSITION + i * ROCK_POWER_SIZE, ROCK_POWER_Y_POSITION,
					ROCK_POWER_SIZE, ROCK_POWER_SIZE);
		}
	}
	
	/**
	 * Gets the Hero's max health.
	 * 
	 * @return : The Hero's max health.
	 */
	public int getMaxHealth()
	{
		return MAX_HEALTH;
	}
	
	/**
	 * Makes the Hero jump.
	 */
	public void jump()
	{
		yPosChange = jumpSpeed;
		setYPos(getYPos() + yPosChange);
		if (getYPos() >= Hero.JUMP_DISTANCE)
		{
			setYPos(Hero.JUMP_DISTANCE);
			jumpSpeed = -Hero.JUMP_SPEED;
		}
		else if (getYPos() < Hero.START_YDRAW)
		{
			setYPos(Hero.START_YDRAW);
			gs.setIsHeroJumping(false);
			jumpSpeed = Hero.JUMP_SPEED;
		}
		gs.setHeroYPos(getYPos());
		setPosition(getX(), getYPos());
		gs.setHeroYDraw(getYPos());
	}
	
	/***
	 * Handles the Hero dying.
	 */
	public void die()
	{
		dyingSound.play();
		health = getMaxHealth();
		if(lives < 1)
		{
			setIsAlive(false);
			gs.setIsHeroAlive(false);
			gs.setIsGameOver(true);
		}
		lives--;
	}
}