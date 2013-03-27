// Package Declaration //
package com.gamedev.decline;

// Java Package Support //
// { Not Applicable }

// Badlogic Package Support //
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

/**
 * 
 * com/gamedev/decline/Hero.java
 * 
 * @author(s) : Ian Middleton, Zach Coker, Zach Ogle
 * @version : 2.0 Last Update : 3/25/2013 Update By : Ian Middleton
 * 
 *          Source code for the Hero class. The Hero class represents the Hero
 *          object in the game. It extends and uses the Unit class.
 * 
 */
public class Hero extends Unit {

	// Global Singleton //
	private GlobalSingleton gs = GlobalSingleton.getInstance();

	// Constants of the Object //
	public static final int START_XDRAW = 120;
	public static final int START_YDRAW = 20;
	public static final int SPEED = 200;
	public static final int JUMP_SPEED = 5;
	public static final int JUMP_DISTANCE = 160;
	public static final int MAX_AMMO = 50;
	public static final int MAX_HEALTH = 100;
	public static final int AMMO_DISPLAY_X_POSITION = 30;
	public static final int AMMO_DISPLAY_Y_POSITION = Gdx.graphics.getHeight() - 50;
	public static final int STARTING_HERO_LIVES = 3;
	public static final int LIVES_DISPLAY_Y_POSITION = Gdx.graphics.getHeight() - 70;
	public static final int LIVES_DISPLAY_X_POSITION = AMMO_DISPLAY_X_POSITION + 150;
	public static final int LIVES_IMAGE_SIZE = 30;
	
	int lives = STARTING_HERO_LIVES;
	final Texture standingTexture;
	final Texture hidingTexture;
	final Texture heartTexture;

	// Internal Variables //
	private float posChange = 0;
	private int ammo = MAX_AMMO / 2;
	private int health = MAX_HEALTH;

	/**
	 * Instantiates a new Hero object by calling the super constructor (Unit)
	 * 
	 * @param newStandingTexture
	 *            : The image to be used for when the hero is standing.
	 * @param newHidingTexture
	 *            : The image to be used for when the hero is hiding;
	 */
	public Hero(Texture newStandingTexture, Texture newHidingTexture, Texture newHeartTexture) {
		super(newStandingTexture, SPEED, START_XDRAW, START_YDRAW);
		standingTexture = newStandingTexture;
		hidingTexture = newHidingTexture;
		setJumpSpeed(JUMP_SPEED);
		gs.setHeroHeight(getHeight());
		gs.setHeroWidth(getWidth());
		gs.setHeroYPos(START_YDRAW);
		heartTexture = newHeartTexture;

	}// end Hero()

	/***
	 * Sets the hero to the initial draw position.
	 */
	public void setToInitialDrawPosition() {
		setPosition(START_XDRAW, START_YDRAW);
	}// end setToInitialDrawPosition()

	/**
	 * The update function is called every global update.
	 * 
	 * ***PROBLEM*** duplicate problem
	 */
	public void update() {
		gs.setHeroXPos(xPos);
		if (gs.getIsHeroJumping()) {
			jump();
		}
	} // end update()

	/**
	 * Overrides the moveRight function of Unit to add special functionality for
	 * Hero.
	 */
	@Override
	public void moveRight() {
		posChange = speed * Gdx.graphics.getDeltaTime();
		setXPos(getXPos() + posChange);
		gs.setWorldXPos(gs.getWorldXPos() + posChange);
		if (isFlipX() == true)
			flip(true, false);
	} // end moveRight()

	/**
	 * Overrides the moveLeft function of Unit to add special functionality for
	 * Hero.
	 */
	@Override
	public void moveLeft() {
		posChange = -(speed * Gdx.graphics.getDeltaTime());
		setXPos(getXPos() + posChange);
		gs.setWorldXPos(gs.getWorldXPos() + posChange);
		if (isFlipX() == false)
			flip(true, false);
	}// end moveLeft()

	/**
	 * Gets the hero's current ammo count.
	 * 
	 * @return : The hero's current ammo count.
	 */
	public int getAmmo() {
		return ammo;
	} // end getAmmo()

	/**
	 * Gets the hero's current health.
	 * 
	 * @return : The hero's current health.
	 */
	public int getHealth() {
		return health;
	}// end getHealth()

	/**
	 * Sets the hero's current ammo count.
	 * 
	 * @param ammo
	 *            : The hero's new ammo count.
	 */
	public void setAmmo(int ammo) {
		this.ammo = ammo;
	}// end setAmmo()

	/**
	 * Sets the hero's current health.
	 * 
	 * @param health
	 *            : The hero's new health.
	 */
	public void setHealth(int newHealth) {
		health = newHealth;
		if (health < 1) 
		{
		  health = MAX_HEALTH;
		  if(lives < 1)
		  {
			gs.setIsHeroAlive(false);
		  }
		  lives--;
		}
	}// end setHealth()

	/***
	 * The function to make the hero hide.
	 */
	public void hide() {
		gs.setIsHeroHiding(true);
		setTexture(hidingTexture);
		setSize(hidingTexture.getWidth(), hidingTexture.getHeight());
	}

	/***
	 * The function to make the hero stand.
	 */
	public void stand() {
		gs.setIsHeroHiding(false);
		setTexture(standingTexture);
		setSize(standingTexture.getWidth(), standingTexture.getHeight());
	}

	/**
	 * The function to display the ammo count
	 * @param batch:  the batch object that draws the text
	 */
	public void drawAmmoCount(SpriteBatch batch) {
		BitmapFont font = new BitmapFont();
		font.draw(batch, "Ammo Left: " + new Integer(getAmmo()).toString(),
				AMMO_DISPLAY_X_POSITION, AMMO_DISPLAY_Y_POSITION);
	}
	
	/***
	 * This function draws the lives for the hero.
	 * @param batch: the batch object that draws the lives images.
	 */
	public void drawLives(SpriteBatch batch)
	{
	  for(int i = 0; i < lives; i++)
	  {
	    batch.draw(heartTexture,LIVES_DISPLAY_X_POSITION + i * LIVES_IMAGE_SIZE,LIVES_DISPLAY_Y_POSITION,
		LIVES_IMAGE_SIZE,LIVES_IMAGE_SIZE);
	  }
	}
}
