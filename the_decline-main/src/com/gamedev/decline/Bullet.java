package com.gamedev.decline;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

/*
 * The Bullet class that represents bullet objects
 */
public class Bullet extends Unit
{
	private GlobalSingleton gs = GlobalSingleton.getInstance();
	private int bulletFacing;
	private int bulletSpeed;
	private static final int BULLET_WIDTH = 30;
	private static final int BULLET_HEIGHT = 30;
	
    /*
     * The default constructor for the class
     * Determines where the bullets start in the screen.
     */
	public Bullet(Texture texture, int bulletFacing) 
	{
		super(texture, 1000, 120, 70);
		this.bulletFacing = bulletFacing;
		bulletSpeed = 1000;
	}
	
	public void update(){
		if(bulletFacing == gs.RIGHT){
			if(speed <= bulletSpeed){
				speed = bulletSpeed - (int)gs.getHeroMovement();
			}
			moveRight();
		}
		else if(bulletFacing == gs.LEFT){
			if(speed >= bulletSpeed){
				speed = bulletSpeed + (int)gs.getHeroMovement();
			}
			moveLeft();
		}
		else{
			speed = bulletSpeed;
		}
	}
	
	@Override
	public void draw(SpriteBatch batch){
		super.draw(batch, BULLET_WIDTH, BULLET_HEIGHT);
	}
}
