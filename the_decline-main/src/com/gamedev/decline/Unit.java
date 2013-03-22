// Package Declaration //
package com.gamedev.decline;

// Badlogic Imports // 
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public abstract class Unit extends Sprite{
	
	protected GlobalSingleton gs = GlobalSingleton.getInstance();
	protected Texture texture;
	protected float xPos;
	protected float yPos;
	protected int speed;
	protected int height;
	protected int width;
	
	public Unit(Texture texture, int speed, float xPos, float yPos){
		super(texture);
		this.texture = texture;
		height = texture.getHeight();
		width = texture.getWidth();
		this.speed = speed;
		this.xPos = xPos;
		this.yPos = yPos;
	}
	
	public void setSpeed(int newSpeed){
		speed = newSpeed;
	}
	
	public int getSpeed(){
		return speed;
	}
	
	public void setXPos(float newXPos)
	{
		xPos = newXPos;
	}
	
	public float getXPos()
	{
		return xPos;
	}
	
	public void setYPos(float newYPos){
		yPos = newYPos;
	}
	
	public float getYPos(){
		return yPos;
	}
	
	public abstract void update();
	
	
	public void moveRight(){
		setXPos(getXPos() + speed * Gdx.graphics.getDeltaTime());
		gs.setHeroMovement(speed * Gdx.graphics.getDeltaTime());
		if(isFlipX() == true)
			flip(true, false);
	}
	
	public void moveLeft(){
		setXPos(getXPos() - speed * Gdx.graphics.getDeltaTime());
		gs.setHeroMovement(-1 * (speed * Gdx.graphics.getDeltaTime()));
		if(isFlipX() == false)
			flip(true, false);
	}
	
	public void jump(){
		
	}
	
	public void draw(SpriteBatch batch, int width, int height){
		batch.draw(texture, xPos, yPos, width, height);
	}
	
}