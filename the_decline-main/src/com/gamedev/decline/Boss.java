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
 * 
 * com/gamedev/decline/Boss.java
 * 
 * @author(s) 	: Ian Middleton, Zach Coker, Zach Ogle
 * @version 	: 2.0 
 * Last Update 	: 4/09/2013 
 * Update By 	: Ian Middleton
 * 
 *          Source code for the Boss class. The Boss class represents a Boss
 *          object in the game. It extends and uses the Unit class.
 * 
 */
public class Boss extends Unit {

	// Global Singleton //

	// Constants //
	public static final int INITIAL_SPEED = 0;
	public static final int WIDTH = 60;
	public static final int HEIGHT = 135;
	public static final int START_XDRAW = Gdx.graphics.getWidth() - (WIDTH*2);
	public static final int START_YDRAW = 800;
	public static final int MAX_HEALTH = 100;

	// Internal Variables //
	int orientation = GlobalSingleton.RIGHT;
	Fireball fireball;
	boolean fireballMode = false;
	boolean goingUp = false;

	/**
	 */
	public Boss(Texture texture, Fireball fireball) {
		super(texture, INITIAL_SPEED, 0, 0);
		setSize(WIDTH, HEIGHT);
		this.fireball = fireball;
	} // end Enemy()

	/**
	 */
	public void setToInitialDrawPosition() {
	    health = getMaxHealth();
		setPosition(START_XDRAW-50, START_YDRAW);
		setXPos(START_XDRAW-50);
		setYPos(START_YDRAW);
	}// end setToInitialDrawPosition()
	
	public void setToGroundDrawPosition(){
		setPosition(START_XDRAW+25, 20);
	}

	public void flipOrientation(){
		if(orientation == GlobalSingleton.RIGHT){
			orientation = GlobalSingleton.LEFT;
		}else{
			orientation = GlobalSingleton.RIGHT;
		}
		flip(true, false);
	}
	
	public void setFireballMode(boolean mode){
		fireballMode = mode;
	}
	
	public void setFireballDirection(boolean goingUp){
		this.goingUp = goingUp;
	}
	
	public int getOrientation(){
		return orientation;
	}
	
	/**
	 */
	public void update() {
	} // end update()

	/**
	 */
	@Override
	public int getMaxHealth()
	{
	  return MAX_HEALTH;
	}

	/**
	 */
	@Override
	public void die()
	{
	  setIsAlive(false);
	  setHasHealthBar(false);
	}
	
	public void moveUp() {
		yPosChange = 600 * Gdx.graphics.getDeltaTime();
		setYPos(getYPos() + yPosChange);
		setPosition(getXPos(), getYPos());
	}
	
	public void moveDown() {
		yPosChange = -600 * Gdx.graphics.getDeltaTime();
		setYPos(getYPos() + yPosChange);
		setPosition(getXPos(), getYPos());
	}
	
	@Override
	public void draw(SpriteBatch batch){
		if(fireballMode){
			if(goingUp == true){
				batch.draw(new TextureRegion(fireball.getTexture()), getX(), getY(), 200/2f, 200/2f, 200f, 200f, 1f, 1f, 0f, true); 
			}else{
				batch.draw(new TextureRegion(fireball.getTexture()), getX(), getY(), 200/2f, 200/2f, 200f, 200f, 1f, 1f, 180f, true);
			}		
		}else{
			super.draw(batch);
		}
	}
	
}// end Enemy class
