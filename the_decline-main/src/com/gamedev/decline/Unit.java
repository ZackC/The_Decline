// Package Declaration //
package com.gamedev.decline;

// Badlogic Imports // 
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public abstract class Unit extends Sprite{
	
	protected GlobalSingleton gs = GlobalSingleton.getInstance();
	protected double xPos;
	protected double yPos;
	protected int speed;
	protected int height;
	protected int width;
	
	public Unit(Texture texture, int speed, double xPos, double yPos){
		super(texture);
		height = texture.getHeight();
		width = texture.getWidth();
	}
	
	public void setSpeed(int newSpeed){
		speed = newSpeed;
	}
	
	public int getSpeed(){
		return speed;
	}
	
	public void setXPos(double newXPos)
	{
		xPos = newXPos;
	}
	
	public double getXPos()
	{
		return xPos;
	}
	
	public void setYPos(double newYPos){
		yPos = newYPos;
	}
	
	public double getYPos(){
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
	
}