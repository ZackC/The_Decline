// Package Declaration //
package com.gamedev.decline;

// Java Package Support //
// { Not Applicable }

import com.badlogic.gdx.Gdx;
// Badlogic Package Support //
import com.badlogic.gdx.graphics.Texture;

/**
 * 
 * com/gamedev/decline/Hero.java
 * 
 * @author(s) 	: Ian Middleton, Zach Coker, Zach Ogle
 * @version 	: 2.0
 * Last Update	: 3/22/2013
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
	public static final int JUMP_SPEED = 40;
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
	 * Instantiates a new Hero object by calling the super constructor (Unit) and
	 * 	setting the managers up in addition to other internal variables.
	 * 
	 * @param texture	: The image to be used for the hero.
	 * @param newBm		: The manager for the bullets.
	 * @param newEm		: The manager for the enemies.
	 */
	public Hero(Texture newStandingTexture, Texture newHidingTexture) {
		super(newStandingTexture, SPEED, START_XDRAW, START_YDRAW);
		standingTexture = newStandingTexture;
		hidingTexture = newHidingTexture;
		
	}
	
	public void setToInitialDrawPosition(){
		setPosition(START_XDRAW, START_YDRAW);
	}
	
	/**
	 * The update function is called every global update. It checks for key presses and updates the hero
	 * 	accordingly. It then also updates the GlobalSingleton with new values if needed. Update functions
	 * 	for the managers are also called here.
	 */
	public void update(){
		gs.setHeroXPos(xPos);
	}
	
	@Override
	public void moveRight(){
		posChange = speed * Gdx.graphics.getDeltaTime();
		setXPos(getXPos() + posChange);
		gs.setWorldXPos(gs.getWorldXPos() + posChange);
		if(isFlipX() == true)
			flip(true, false);
	}
	
	@Override
	public void moveLeft(){
		posChange = -(speed * Gdx.graphics.getDeltaTime());
		setXPos(getXPos() + posChange);
		gs.setWorldXPos(gs.getWorldXPos() + posChange);
		if(isFlipX() == false)
			flip(true, false);
	}

	/**
	 * Gets the hero's current ammo count.
	 * 
	 * @return	: The hero's current ammo count.
	 */
	public int getAmmo()
	{
		return ammo;
	}
	
	/**
	 * Gets the hero's current health.
	 * 
	 * @return	: The hero's current health.
	 */
	public int getHealth()
	{
		return health;
	}
	
	/**
	 * Sets the hero's current ammo count.
	 * 
	 * @param ammo	: The hero's new ammo count.
	 */
	public void setAmmo(int ammo)
	{
		this.ammo = ammo;
	}
	
	/**
	 * Sets the hero's current health.
	 * 
	 * @param health	: The hero's new health.
	 */
	public void setHealth(int health)
	{
		this.health = health;
	}
	
	
	public void hide()
	{
		gs.setIsHeroHiding(true);
		setTexture(hidingTexture);
	}	
	
	public void stand()
	{
		gs.setIsHeroHiding(false);
		setTexture(standingTexture);
	}
	
}
