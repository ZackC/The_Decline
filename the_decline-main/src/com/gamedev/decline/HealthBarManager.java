// Package Declaration //
package com.gamedev.decline;

// Java Package Support //
// { Not applicable }

// Badlogic Package Support //
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.utils.Array;

/**
 * com/gamedev/decline/HealthBarManager.java
 * 
 * @author(s) : Ian Middleton, Zach Coker, Zach Ogle
 * @version : 2.0 Last Update : 4/21/2013 Update By : Zach Ogle
 * 
 * Source code for the HealthBarManager class. The HealthBarManager class manages
 *	HealthBar objects in the game.
 * 
 */
public class HealthBarManager
{
	// Global Singleton //
	// { Not applicable }
	
	// Constants //
	// { Not applicable }
   
	// Internal Variables //
	private Array<HealthBar> healthBarDisplayed = new Array<HealthBar>();
	private ShapeRenderer shapeRender;
	private HealthBar currentHealthBar;
   
	/**
	 * Creates a new ShapeRenderer to draw HealthBars.
	 */
	public HealthBarManager()
	{
		shapeRender = new ShapeRenderer();
	}
	
	/**
	 * Draws all HealthBars currently displayed.
	 */
	public void draw()
	{
		shapeRender.begin(ShapeType.FilledRectangle);
		for(int i = 0; i < healthBarDisplayed.size; i++)
		{
			healthBarDisplayed.get(i).draw(shapeRender);
		}
		shapeRender.end();
	}
	
	/**
	 * Removes any HealthBars that aren't currently displayed.
	 */
	public void update()
	{
		for(int i = 0; i < healthBarDisplayed.size; i++)
		{
			currentHealthBar = healthBarDisplayed.get(i);
			if(!currentHealthBar.update())
			{
				healthBarDisplayed.removeIndex(i);
			}
		}
	}
	
	/**
	 * Adds a HealthBar for a Unit.
	 * 
	 * @param unit : The Unit to add the HealthBar to.
	 */
	public void add(Unit unit)
	{
		currentHealthBar = new HealthBar();
		currentHealthBar.setUnit(unit);
		healthBarDisplayed.add(currentHealthBar);
	}
}