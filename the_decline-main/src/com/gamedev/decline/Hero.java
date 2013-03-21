package com.gamedev.decline;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
/*
 * The hero class;  this class controls the main character
 */
public class Hero extends Unit {
	// the hero's movement speed
	final static int SPEED = 200;
	// the bullet manager
	private BulletManager bm;
	
	/*
	 * The hero constructor using a texture
	 * 
	 * texture - the image for the hero
	 * newBm - the bulletManager
	 */
	public Hero(Texture texture,BulletManager newBm) {
		super(texture, SPEED, 0, 0);
		bm = newBm;
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
		}
		else if(Gdx.input.isKeyPressed(Keys.RIGHT)) {
			moveRight();
		}
		else
		{
			gs.setHeroMovement(0);
		}
		if(Gdx.input.isKeyPressed(Keys.SPACE))
		{
		  bm.shootBullet();
		}
		
	}

}
