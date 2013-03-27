package com.gamedev.decline;

//badlogic package support
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;

/**
 * 
 * com/gamedev/decline/HealthBar.java
 * 
 * @author(s) : Ian Middleton, Zack Coker, Zach Ogle
 * @version : 2.0 Last Update : 3/25/2013 Update By : Ian Middleton
 * 
 *          Source code for the Healthbar class. This class manages displaying a
 *          health bar in the game.
 * 
 */
public class HealthBar {
	// only implemented for the hero since the hero is
	// the only one that has health at this point in
	// the game.
	Unit unit;
	

	final int MAX_WIDTH = 50;
	final int MAX_HEIGHT = 5;
	final int DISTANCE_OVER_OBBJECT = 10;
	final int DISTANCE_FROM_SIDE_OF_OBJECT = 30;

	/***
	 * The constructor for the health bar class
	 */
	public HealthBar() {
		
	}

	/***
	 * The function that draws the health bar
	 */
	public void draw(ShapeRenderer shapeRender) {
		
		shapeRender.setColor(Color.RED);
		shapeRender.filledRect(unit.getX() + DISTANCE_FROM_SIDE_OF_OBJECT,
				unit.getYPos() + unit.getHeight() + DISTANCE_OVER_OBBJECT,
				MAX_WIDTH, MAX_HEIGHT);
		shapeRender.setColor(Color.GREEN);
		shapeRender.filledRect(unit.getX() + DISTANCE_FROM_SIDE_OF_OBJECT,
				unit.getYPos() + unit.getHeight() + DISTANCE_OVER_OBBJECT,
				(float) unit.getHealth() / unit.getMaxHealth() * MAX_WIDTH,
				MAX_HEIGHT);
	}
	
	/***
	 * Updates the health bar manager by letting it know if the health bar 
	 * should be drawn and is necessary.
	 * @return: returns true if the health bar is still in the game and false otherwise
	 */
	public boolean update()
	{
	  return unit.getIsAlive();
	}
	
	/***
	 * Sets the unit for the health bar
	 * @param newUnit: the unit that the health bar is representing
	 */
	public void setUnit(Unit newUnit)
	{
	  unit = newUnit;
	}

}
