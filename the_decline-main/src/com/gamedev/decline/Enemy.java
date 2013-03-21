// Package Declaration //
package com.gamedev.decline;

//Badlogic Imports // 
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

/*
 * The Enemy class for the game.
 */
public class Enemy extends Unit {
	// the enemy's movement speed
	final static int SPEED = 2;

	/*
	 * The enemy constructor that uses a texture
	 * 
	 * texture - the image of the enemy
	 */
	public Enemy(Texture texture) {
		super(texture, SPEED, 0, 0);
		setXPos(Gdx.graphics.getWidth()-width/2);
	}
	
	/*
	 * This method moves the enemies.
	 */
	public void update()
	{
		moveLeft();
	}
}
