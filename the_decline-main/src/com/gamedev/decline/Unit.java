// Package Declaration //
package com.gamedev.decline;

// Java Package Support //
// { Not Applicable }

// Badlogic Package Support // 
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;

/**
 * 
 * com/gamedev/decline/Unit.java
 * 
 * @author(s) : Ian Middleton, Zach Coker, Zach Ogle
 * @version : 1.0 Last Update : 3/23/2013 Update By : Ian Middleton
 * 
 *          Source code for the Unit class. The Unit class is an abstract class
 *          that is meant to be extended for specific Units within the game.
 * 
 */
public abstract class Unit extends CollidableObject {

	// Global Singleton //
	protected GlobalSingleton gs = GlobalSingleton.getInstance();

	// Constants of the Object //
	// { Not Applicable }

	// Internal Variables //
	protected int speed;
	private float xPosChange;
	private float yPosChange;
	private int jumpSpeed;

	/**
	 * Constructor for all Units in the game. Must be called by all classes
	 * extending Unit.
	 * 
	 * @param texture
	 *            : The image for this specific Unit.
	 * @param speed
	 *            : The speed of this specific Unit.
	 * @param xPos
	 *            : The xPos of this specific Unit.
	 * @param yPos
	 *            : The yPos of this specific Unit.
	 */
	public Unit(Texture texture, int speed, float xPos, float yPos) {
		super(texture, xPos, yPos);
		this.speed = speed;
	}

	/***
	 * Sets the unit to the initial draw position
	 */
	public abstract void setToInitialDrawPosition();

	/**
	 * Sets the speed of the Unit.
	 * 
	 * @param newSpeed
	 *            : New speed of the Unit.
	 */
	public void setSpeed(int newSpeed) {
		speed = newSpeed;
	}

	/**
	 * Gets the speed of the Unit.
	 * 
	 * @return : The speed of the unit.
	 */
	public int getSpeed() {
		return speed;
	}

	/**
	 * Sets the speed of the Unit.
	 * 
	 * @param newSpeed
	 *            : New speed of the Unit.
	 */
	public void setJumpSpeed(int newSpeed) {
		jumpSpeed = newSpeed;
	}

	/**
	 * Gets the speed of the Unit.
	 * 
	 * @return : The speed of the unit.
	 */
	public int getJumpSpeed() {
		return jumpSpeed;
	}

	/**
	 * Abstract method that must be implemented by all extending classes.
	 */
	public abstract void update();

	/**
	 * Moves the unit right by the amount designated by speed.
	 */
	public void moveRight() {
		xPosChange = speed * Gdx.graphics.getDeltaTime();
		setXPos(getXPos() + xPosChange);
		setPosition(getXPos() - gs.getWorldXPos(), getYPos());
		if (isFlipX() == true)
			flip(true, false);
	}

	/**
	 * Moves the unit left by the amount designated by speed.
	 */
	public void moveLeft() {
		xPosChange = -(speed * Gdx.graphics.getDeltaTime());
		setXPos(getXPos() + xPosChange);
		setPosition(getXPos() - gs.getWorldXPos(), getYPos());
		if (isFlipX() == false)
			flip(true, false);
	}

	/**
	 * Makes the Unit jump. At the moment, this is only implemented for the
	 * hero. So we will have to refactor if we want it for other units.
	 */
	public void jump() {

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
	}
}