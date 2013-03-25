package com.gamedev.decline;

//badlogic package support
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;

/**
 * 
 * com/gamedev/decline/HealthBar.java
 * 
 * @author(s) 	: Ian Middleton, Zack Coker, Zach Ogle
 * @version 	: 2.0
 * Last Update	: 3/25/2013
 * Update By	: Ian Middleton
 * 
 * Source code for the Healthbar class.  This class manages displaying
 * a health bar in the game.
 *
 */
public class HealthBar 
{
  //only implemented for the hero since the hero is 
  //the only one that has health at this point in 
  //the game.	
   Hero hero; 
   ShapeRenderer shapeRender = new ShapeRenderer();

   final int MAX_WIDTH = 50;
   final int MAX_HEIGHT = 5;
   final int DISTANCE_OVER_OBBJECT = 10;
   final int DISTANCE_FROM_SIDE_OF_OBJECT = 30;
	
   /***
    * The constructor for the health bar class
    * 
    * @param newHero - the hero object that the health bar is representing
    */
   public HealthBar(Hero newHero)
   {
	   hero = newHero;
   }
   
   /***
    * The function that draws the health bar
    */
   public void draw()
   {
	   shapeRender.begin(ShapeType.FilledRectangle);
	   shapeRender.setColor(Color.RED);
	   shapeRender.filledRect(hero.getX() + DISTANCE_FROM_SIDE_OF_OBJECT, hero.getYPos() + hero.getHeight() + DISTANCE_OVER_OBBJECT, 
			   MAX_WIDTH, MAX_HEIGHT);
	   shapeRender.setColor(Color.GREEN);
	   shapeRender.filledRect(hero.getX() + DISTANCE_FROM_SIDE_OF_OBJECT, hero.getYPos() + hero.getHeight() + DISTANCE_OVER_OBBJECT, 
			   (float)hero.getHealth()/Hero.MAX_HEALTH * MAX_WIDTH, MAX_HEIGHT);
	   shapeRender.end();
   }
   
   
}
