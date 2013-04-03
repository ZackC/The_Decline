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
 * @version : 2.0 Last Update : 3/25/2013 Update By : Ian Middleton
 * 
 *          Source code for the Hero class. The Hero class represents the Hero
 *          object in the game. It extends and uses the Unit class.
 * 
 */
public class Hero extends Unit {

	// Global Singleton //
	private GlobalSingleton gs = GlobalSingleton.getInstance();
	Sound dyingSound;

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
	public static final int STARTING_ROCK_HIDING_POWER_COUNT = 3;
	public static final int LIVES_DISPLAY_Y_POSITION = Gdx.graphics.getHeight() - 70;
	public static final int LIVES_DISPLAY_X_POSITION = AMMO_DISPLAY_X_POSITION + 150;
	public static final int LIVES_IMAGE_SIZE = 30;
	public static final int ROCK_POWER_X_POSITION = LIVES_DISPLAY_X_POSITION + (4 * LIVES_IMAGE_SIZE);
	public static final int ROCK_POWER_Y_POSITION = LIVES_DISPLAY_Y_POSITION;
	public static final int ROCK_POWER_SIZE = LIVES_IMAGE_SIZE;
	
	int rockPowers = STARTING_ROCK_HIDING_POWER_COUNT;
	int lives = STARTING_HERO_LIVES;
	final Texture standingTexture;
	final Texture hidingTexture;
	final Texture rockTexture;
	final Texture heartTexture;

	// Internal Variables //
	private float posChange = 0;
	private int ammo = MAX_AMMO / 2;

	/**
	 * Instantiates a new Hero object by calling the super constructor (Unit)
	 * 
	 * @param newStandingTexture
	 *            : The image to be used for when the hero is standing.
	 * @param newHidingTexture
	 *            : The image to be used for when the hero is hiding;
	 */
	public Hero(Texture newStandingTexture, Texture newHidingTexture, Texture newHeartTexture, 
	    Texture newRockTexture, Sound newDyingSound) {
		super(newStandingTexture, SPEED, START_XDRAW, START_YDRAW);
		standingTexture = newStandingTexture;
		hidingTexture = newHidingTexture;
		rockTexture = newRockTexture;
		setJumpSpeed(JUMP_SPEED);
		gs.setHeroHeight(getHeight());
		gs.setHeroWidth(getWidth());
		gs.setHeroYPos(START_YDRAW);
		setIsAlive(true);
		heartTexture = newHeartTexture;
                dyingSound = newDyingSound;
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
	
	@Override
	public void moveRight(){
		posChange = speed * Gdx.graphics.getDeltaTime();
		setXPos(getXPos() + posChange);
		setPosition(getXPos() - gs.getWorldXPos(), getYPos());
		gs.setHeroXDraw(getXPos() - gs.getWorldXPos());
		if (isFlipX() == true)
			flip(true, false);
	}
	
	@Override
	public void moveLeft(){
		posChange = -(speed * Gdx.graphics.getDeltaTime());
		setXPos(getXPos() + posChange);
		setPosition(getXPos() - gs.getWorldXPos(), getYPos());
		gs.setHeroXDraw(getXPos() - gs.getWorldXPos());
		if (isFlipX() == false)
			flip(true, false);
	}
	
	public void moveRightScroll() {
		posChange = speed * Gdx.graphics.getDeltaTime();
		setXPos(getXPos() + posChange);
		gs.setWorldXPos(gs.getWorldXPos() + posChange);
		if (isFlipX() == true)
			flip(true, false);
	} // end moveRight()

	public void moveLeftScroll() {
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

	

	/***
	 * The function to make the hero hide.
	 */
	public void hide(Array<Bush> bushArray) {
	        boolean isBehindBush = false;
	        for(int i = 0; i < bushArray.size; i++)
	        {
	          if(Intersector.intersectRectangles(getBoundingRectangle(),
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

	/***
	 * The function to make the hero stand.
	 */
	public void stand() {
		gs.setIsHeroHiding(false);
		gs.setIsHeroTryingToHide(false);
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
	
	public void drawRockPower(SpriteBatch batch)
	{
	  for(int i = 0; i < rockPowers; i++)
	  {
	    batch.draw(rockTexture,ROCK_POWER_X_POSITION + i * ROCK_POWER_SIZE, ROCK_POWER_Y_POSITION,
		ROCK_POWER_SIZE, ROCK_POWER_SIZE);
	  }
	}
	
	/***
	 * Returns the max health of the hero
	 * 
	 * @return: the hero's max health
	 */
	@Override
	public int getMaxHealth()
	{
	  return MAX_HEALTH;
	}
	
	@Override
	public void jump(){
		yPosChange = jumpSpeed; // * Gdx.graphics.getDeltaTime();
		setYPos(getYPos() + yPosChange);
		if (getYPos() >= Hero.JUMP_DISTANCE) {
			setYPos(Hero.JUMP_DISTANCE);
			jumpSpeed = -Hero.JUMP_SPEED;
		} else if (getYPos() < Hero.START_YDRAW) {
			setYPos(Hero.START_YDRAW);
			gs.setIsHeroJumping(false);
			jumpSpeed = Hero.JUMP_SPEED;
		}
		gs.setHeroYPos(getYPos());
		setPosition(getX(), getYPos());
		gs.setHeroYDraw(getYPos());
	}
	
	/***
	 * Handles the hero dying. 
	 */
	@Override
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
