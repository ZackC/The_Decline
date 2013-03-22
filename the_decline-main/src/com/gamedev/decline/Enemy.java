// Package Declaration //
package com.gamedev.decline;

//Badlogic Imports // 
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

/*
 * The Enemy class for the game.
 */
public class Enemy extends Unit {
	// the enemy's movement speed
	public static final int SPEED = 2;
	public static final int WIDTH = 100;
	public static final int HEIGHT = 100;
	public static final int ENEMY_SPEED = 150;

	/*
	 * The enemy constructor that uses a texture
	 * 
	 * texture - the image of the enemy
	 */
	public Enemy(Texture texture) {
		super(texture, SPEED, 0, 0);
		setXPos(Gdx.graphics.getWidth()-width/2);
		setSize(WIDTH, HEIGHT);
	}
	
	/*
	 * This method moves the enemies.
	 */
	public void update()
	{
		if(gs.getHeroOrientation() == GlobalSingleton.RIGHT){
			if(speed <= ENEMY_SPEED){
				speed = ENEMY_SPEED - (int)gs.getHeroMovement();
			}
		}
		else if(gs.getHeroOrientation() == GlobalSingleton.LEFT){
			if(speed >= ENEMY_SPEED){
				speed = ENEMY_SPEED + (int)gs.getHeroMovement();
			}
		}
		else{
			speed = ENEMY_SPEED;
		}
		moveLeft();
	}
}
