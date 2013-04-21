// Package Declaration //
package com.gamedev.decline;

// Java Package Support //
// { Not applicable }

// Badlogic Package Support
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

/**
 * com/gamedev/decline/HealthBar.java
 * 
 * @author(s) : Ian Middleton, Zack Coker, Zach Ogle
 * @version : 2.0 Last Update : 4/21/2013 Update By : Zach Ogle
 * 
 * Source code for the Healthbar class. This class manages displaying a
 *	HealthBar in the game.
 * 
 */
public class HealthBar
{
	// Global Singleton //
	// { Not applicable }
	
	// Constants //
	public static final int MAX_WIDTH = 50;
	public static final int MAX_HEIGHT = 5;
	public static final int DISTANCE_OVER_OBJECT = 10;
	public static final int DISTANCE_FROM_SIDE_OF_OBJECT = 30;
	
	// Internal Variables //
	private Unit unit = null;
	
	/**
	 * The constructor for the HealthBar class
	 */
	public HealthBar()
	{
	}

	/**
	 * Draws the Unit's HealthBar.
	 * 
	 * @param shapeRender : The ShapeRenderer which will draw the HealthBar.
	 */
	public void draw(ShapeRenderer shapeRender)
	{
		shapeRender.setColor(Color.RED);
		shapeRender.filledRect(unit.getX() + DISTANCE_FROM_SIDE_OF_OBJECT,
				unit.getYPos() + unit.getHeight() + DISTANCE_OVER_OBJECT, MAX_WIDTH, MAX_HEIGHT);
		shapeRender.setColor(Color.GREEN);
		shapeRender.filledRect(unit.getX() + DISTANCE_FROM_SIDE_OF_OBJECT,
				unit.getYPos() + unit.getHeight() + DISTANCE_OVER_OBJECT,
				(float) unit.getHealth() / unit.getMaxHealth() * MAX_WIDTH, MAX_HEIGHT);
	}
	
	/**
	 * Determines whether the HealthBar should be drawn based on whether the Unit
	 *	is alive.
	 *
	 * @return: Whether the Unit is alive.
	 */
	public boolean update()
	{
		return unit.getIsAlive();
	}
	
	/**
	 * Sets the Unit for the HealthBar.
	 * 
	 * @param newUnit: The Unit for the HealthBar.
	 */
	public void setUnit(Unit newUnit)
	{
		unit = newUnit;
	}
	
	/**
	 * Gets the Unit for the HealthBar.
	 * 
	 * @return: The Unit for the HealthBar.
	 */
	public Unit getUnit()
	{
		return unit;
	}
}