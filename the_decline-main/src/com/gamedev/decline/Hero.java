package com.gamedev.decline;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.Texture;
/*
 * The hero class;  this class controls the main character
 */
public class Hero extends Unit {
	// the hero's movement speed
	final static int SPEED = 200;
	// the bullet manager
	private BulletManager bm;
	private GlobalSingleton gs = GlobalSingleton.getInstance();
	public static final int STARTINGHEROXPOS = 120;
	public static final int STARTINGHEROYPOS = 20;
	private boolean shoot;
	private boolean ableToShoot;
	
	/*
	 * The hero constructor using a texture
	 * 
	 * texture - the image for the hero
	 * newBm - the bulletManager
	 */
	public Hero(Texture texture, BulletManager newBm, int xPos, int yPos) {
		super(texture, SPEED, STARTINGHEROXPOS, STARTINGHEROYPOS);
		bm = newBm;
		shoot = false;
		ableToShoot = true;
	}
	
	/*
	 * updates the hero
	 * 
	 * at the moment - this method is used to move the hero and have the
	 *    hero shoot bullets
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
		
	}

}
