// Package Declaration //
package com.gamedev.decline;

// Badlogic Imports // 
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public abstract class Unit extends Sprite{
	
	private GlobalSingleton gs = GlobalSingleton.getInstance();
	private double xPos;
	private double yPos;
	private int speed;
	private int height;
	private int width;
	
	public Unit(){
		xPos = 0;
		yPos = 0;
		speed = 0;
		height = 0;
		width = 0;
	}
	
	public Unit(Texture texture){
		super(texture);
		height = texture.getHeight();
		width = texture.getWidth();
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
}