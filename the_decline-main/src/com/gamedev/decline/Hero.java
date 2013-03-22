// Package Declaration //
package com.gamedev.decline;

// Java Package Support //
// { Not Applicable }

// Badlogic Package Support //
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
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
	public static final int STARTINGHEROXPOS = 120;
	public static final int STARTINGHEROYPOS = 20;
	public final static int SPEED = 200;
	
	// Internal Variables //
	private BulletManager bm;
	private EnemyManager em;
	private boolean shoot;
	private boolean ableToShoot;
	
	/**
	 * Instantiates a new Hero object by calling the super constructor (Unit) and
	 * 	setting the managers up in addition to other internal variables.
	 * 
	 * @param texture	: The image to be used for the hero.
	 * @param newBm		: The manager for the bullets.
	 * @param newEm		: The manager for the enemies.
	 */
	public Hero(Texture texture, BulletManager newBm, EnemyManager newEm) {
		super(texture, SPEED, STARTINGHEROXPOS, STARTINGHEROYPOS);
		
		bm = newBm;
		em = newEm;
		
		shoot = false;
		ableToShoot = true;
	}
	
	/**
	 * The update function is called every global update. It checks for key presses and updates the hero
	 * 	accordingly. It then also updates the GlobalSingleton with new values if needed. Update functions
	 * 	for the managers are also called here.
	 */
	public void update()
	{
		if(Gdx.input.isKeyPressed(Keys.LEFT)) {
			moveLeft();
			if(gs.getHeroOrientation() == gs.RIGHT){
				gs.setHeroOrientation(gs.LEFT);
			}
			gs.setHeroMovement(-speed);
		}
		else if(Gdx.input.isKeyPressed(Keys.RIGHT)) {
			moveRight();
			if(gs.getHeroOrientation() == gs.LEFT){
				gs.setHeroOrientation(gs.RIGHT);
			}
			gs.setHeroMovement(speed);
		}
		else
		{
			gs.setHeroMovement(0);
		}
		
		gs.setHeroXPos(xPos);
		
		if(Gdx.input.isKeyPressed(Keys.SPACE) && ableToShoot)
		{
			shoot = true;
			ableToShoot = false;
		}
		
		if(shoot == true){
			bm.shootBullet();
			shoot = false;
		}
		
		if(!(Gdx.input.isKeyPressed(Keys.SPACE))){
			ableToShoot = true;
		}
		
		em.update();
	}

}
