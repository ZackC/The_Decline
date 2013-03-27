package com.gamedev.decline;

import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.utils.Array;

public class HealthBarManager
{
   static final int INITIAL_NUMBER_OF_HEALTH_BARS = 20;
   HealthBar[] initializedHealthBars = new HealthBar[INITIAL_NUMBER_OF_HEALTH_BARS];
   Array<HealthBar> healthBarDisplayed = new Array<HealthBar>();
   ShapeRenderer shapeRender;
   HealthBar currentHealthBar;
   int currentHealthBarNumber = 0;
   
   public HealthBarManager()
   {
     for(int i = 0; i < INITIAL_NUMBER_OF_HEALTH_BARS; i++)
     {
       initializedHealthBars[i] = new HealthBar();
     }
     shapeRender = new ShapeRenderer();
   }
   
   public void draw()
   {
     //System.out.println("Number of health bars drawing: "+healthBarDisplayed.size);
     shapeRender.begin(ShapeType.FilledRectangle);
     for(int i = 0; i < healthBarDisplayed.size; i++)
     {
       healthBarDisplayed.get(i).draw(shapeRender);
     }
     shapeRender.end();
   }
   
   public void update()
   {
     //System.out.println("Number of health bars updated: "+healthBarDisplayed.size);
     for(int i = 0; i < healthBarDisplayed.size; i++)
     {
       currentHealthBar = healthBarDisplayed.get(i);
       if(!currentHealthBar.update())
       {
         healthBarDisplayed.removeIndex(i);
       }
     }
   }
   
   public void add(Unit unit)
   {
     currentHealthBar = initializedHealthBars[currentHealthBarNumber % INITIAL_NUMBER_OF_HEALTH_BARS];
     currentHealthBar.setUnit(unit);
     healthBarDisplayed.add(currentHealthBar);
     currentHealthBarNumber++;
   }
}
