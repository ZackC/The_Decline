// Package Declaration //
package com.gamedev.decline;

// Java Package Support //
// { Not Applicable }

// Badlogic Package Support //
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;

/**
 * 
 * com/gamedev/decline/Hero.java
 * 
 * @author(s) 	: Ian Middleton, Zach Coker, Zach Ogle
 * @version 	: 2.0
 * Last Update	: 3/25/2013
 * Update By	: Ian Middleton
 * 
 * Source code for the Hero class. The Hero class represents the Hero object
 * 	in the game. It extends and uses the Unit class.
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
	final Texture standingTexture;
	final Texture hidingTexture;
	
	// Internal Variables //
	private float posChange = 0;
	private int ammo = MAX_AMMO / 2;
	private int health = MAX_HEALTH;
	
	/**
	 * Instantiates a new Hero object by calling the super constructor (Unit)
	 * 
	 * @param texture	: The image to be used for the hero.
	 */
	public Hero(Texture newStandingTexture, Texture newHidingTexture) {
		super(newStandingTexture, SPEED, START_XDRAW, START_YDRAW);
		standingTexture = newStandingTexture;
		hidingTexture = newHidingTexture;
		setJumpSpeed(JUMP_SPEED);
		
	}// end Hero()
	
	public void setToInitialDrawPosition(){
		setPosition(START_XDRAW, START_YDRAW);
	}// end setToInitialDrawPosition()
	
	/**
	 * The update function is called every global update. 
     *
     * ***PROBLEM*** duplicate problem
	 */
	public void update(){
		gs.setHeroXPos(xPos);
		if(gs.getIsHeroJumping())
		{
			jump();
		}
	} // end update()
	
	/**
	 * Overrides the moveRight function of Unit to add special functionality for Hero.
	 */
	@Override
	public void moveRight(){
		posChange = speed * Gdx.graphics.getDeltaTime();
		setXPos(getXPos() + posChange);
		gs.setWorldXPos(gs.getWorldXPos() + posChange);
		if(isFlipX() == true)
			flip(true, false);
	} // end moveRight()
	
    /**
	 * Overrides the moveLeft function of Unit to add special functionality for Hero.
	 */
	@Override
	public void moveLeft(){
		posChange = -(speed * Gdx.graphics.getDeltaTime());
		setXPos(getXPos() + posChange);
		gs.setWorldXPos(gs.getWorldXPos() + posChange);
		if(isFlipX() == false)
			flip(true, false);
	}// end moveLeft()

	/**
	 * Gets the hero's current ammo count.
	 * 
	 * @return	: The hero's current ammo count.
	 */
	public int getAmmo()
	{
		return ammo;
	} // end getAmmo()
	
	/**
	 * Gets the hero's current health.
	 * 
	 * @return	: The hero's current health.
	 */
	public int getHealth()
	{
		return health;
	}// end getHealth()
	
	/**
	 * Sets the hero's current ammo count.
	 * 
	 * @param ammo	: The hero's new ammo count.
	 */
	public void setAmmo(int ammo)
	{
		this.ammo = ammo;
	}// end setAmmo()
	
	/**
	 * Sets the hero's current health.
	 * 
	 * @param health	: The hero's new health.
	 */
	public void setHealth(int health)
	{
		this.health = health;
	}// end setHealth()
	
	/***
	 * The function to make the hero hide.
	 */
	public void hide()
	{
		gs.setIsHeroHiding(true);
		setTexture(hidingTexture);
		setSize(hidingTexture.getWidth(), hidingTexture.getHeight());
	}	
	
	/***
	 * The function to make hero stand. 
	 */
	public void stand()
	{
		gs.setIsHeroHiding(false);
		setTexture(standingTexture);
		setSize(standingTexture.getWidth(), standingTexture.getHeight());
	}
	
}
