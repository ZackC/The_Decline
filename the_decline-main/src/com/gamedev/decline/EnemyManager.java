// Package Declaration //
package com.gamedev.decline;

// Java Package Support //
import java.util.Iterator;
import java.util.Random;

// Badlogic Package Support //
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Array;

/**
 * com/gamedev/decline/EnemyManager.java
 * 
 * @author(s) : Ian Middleton, Zach Coker, Zach Ogle
 * @version : 2.0 Last Update : 4/19/2013 Update By : Zach Ogle
 * 
 * Source code for the EnemyManager class. The EnemyManager class takes
 *	care of creating, updating, drawing, and removing Enemy objects.
 * 
 */
public class EnemyManager
{
	// Global Singleton //
	private GlobalSingleton gs = GlobalSingleton.getInstance();

	// Constants for the Object //
	public static final int MAX_FALCONERS = 8;

	// Internal Variables //
	private Array<Enemy> currentEnemies = new Array<Enemy>();
	private Array<Falconer> currentFalconers = new Array<Falconer>();
	private Iterator<Enemy> enemyIter;
	private Iterator<Falconer> falconerIter;
	private Enemy currentEnemy;
	private Falconer currentFalconer;
	private Random rand = new Random();
	private int newEnemyXPosition = Gdx.graphics.getWidth() / 2 + rand.nextInt(100);
	private int newFalconerXPosition = Gdx.graphics.getWidth() / 2 + rand.nextInt(400);
	Texture enemyTexture, falconerTexture, falconerWithoutFalconTexture, falconTexture;

	/**
	 * Instantiates a new EnemyManager object. The EnemyManager fills an array
	 * of new Enemy objects with the given Texture to be used in the game. This
	 * is done to create a buffer of Enemy objects.
	 * 
	 * @param texture
	 *            : The image to be used for the Enemy objects.
	 */
	public EnemyManager(Texture enemyTexture, Texture falconerTexture, Texture falconerWithoutFalconTexture, Texture falconTexture)
	{
		this.enemyTexture = enemyTexture;
		this.falconerTexture = falconerTexture;
		this.falconerWithoutFalconTexture = falconerWithoutFalconTexture;
		this.falconTexture = falconTexture;
	}

	/**
	 * Gets the Array of active Enemies.
	 * 
	 * @return : An Array of active Enemies.
	 */
	public Array<Enemy> getActiveEnemies()
	{
		return currentEnemies;
	}
	
	/**
	 * Gets the Array of active Falconers.
	 * 
	 * @return : An Array of active Falconers.
	 */
	public Array<Falconer> getActiveFalconers()
	{
		return currentFalconers;
	}

	/**
	 * Handles an Enemy being damaged.
	 * 
	 * @param enemy : The Enemy to calculate damage on.
	 * @param damage : The amount of damage inflicted.
	 */
	public boolean enemyDamagedEvent(Enemy enemy, int damage)
	{
		currentEnemy = enemy;
		if(!currentEnemy.getHasHealthBar())
		{
			gs.getHealthBarManager().add(currentEnemy);
			currentEnemy.setHasHealthBar(true);
		}
		currentEnemy.setHealth(currentEnemy.getHealth() - damage);
		if(!currentEnemy.getIsAlive())
		{
			return true;
		}
		else
		{
			return false;
		}
	}
	
	/**
	 * Handles a Falconer being damaged.
	 * 
	 * @param falconer : The Falconer to calculate damage on.
	 * @param damage : The amount of damage inflicted.
	 */
	public boolean falconerDamagedEvent(Falconer falconer, int damage)
	{
		currentFalconer = falconer;
		if (!currentFalconer.getHasHealthBar())
		{
			gs.getHealthBarManager().add(currentFalconer);
			currentFalconer.setHasHealthBar(true);
		}
		currentFalconer.setHealth(currentFalconer.getHealth() - damage);
		if (!currentFalconer.getIsAlive())
		{
			return true;
		}
		else
		{
			return false;
		}
	}

	/**
	 * Creates a new Enemy and sets the initial values for this specific Enemy.
	 *	This Enemy is then added to the Array of active Enemies.
	 */
	public void makeEnemyAppear()
	{
		currentEnemy = new Enemy(enemyTexture);
		currentEnemy.setToInitialDrawPosition();
		currentEnemy.setIsAlive(true);
		currentEnemy.setXPos(gs.getWorldXPos() + Enemy.START_XDRAW);
		currentEnemy.setYPos(Enemy.START_YDRAW);
		currentEnemies.add(currentEnemy);
	}
	
	/**
	 * Creates a new Falconer and sets the initial values for this specific Falconer.
	 *	This Falconer is then added to the Array of active Falconers.
	 */
	public void makeFalconerAppear()
	{
		if (currentFalconers.size < MAX_FALCONERS)
		{
			currentFalconer = new Falconer(falconerTexture, falconerWithoutFalconTexture, falconTexture);
			currentFalconer.setToInitialDrawPosition();
			currentFalconer.setIsAlive(true);
			currentFalconer.setXPos(gs.getWorldXPos() + Falconer.START_XDRAW);
			currentFalconer.setYPos(Falconer.START_YDRAW);
			currentFalconer.setFalconXPos(gs.getWorldXPos() + Falconer.START_XDRAW);
			currentFalconer.setFalconYPos(Falconer.START_YDRAW);
			currentFalconers.add(currentFalconer);
		}
	}

	/**
	 * Checks to see if the Hero has moved farther than the random amount required for a new
	 *	Enemy or Falconer to be spawned. If the Hero has moved this distance then a new Enemy
	 *	or Falconer is created. Afterwards, the function iterates through all Enemies and Falconers
	 *	on the screen and calls their update method. If an Enemy or Falconer has traveled off screen
	 *	after updating then that Enemy or Falconer is removed.
	 */
	public void update()
	{
		if(gs.getHeroXPos() < 9000)
		{
			if (gs.getHeroXPos() > newEnemyXPosition)
			{
				makeEnemyAppear();
				newEnemyXPosition += rand.nextInt(400) + 50;
			}
		}
		enemyIter = currentEnemies.iterator();
		while (enemyIter.hasNext())
		{
			currentEnemy = enemyIter.next();
			currentEnemy.update();
			if (currentEnemy.getX() < -1 * currentEnemy.getWidth()) 
			{
			   currentEnemy.setIsAlive(false);
			}
            if(!currentEnemy.getIsAlive())
            {
            	enemyIter.remove();                          
            }
		}
		if (gs.getHeroXPos() < 9000)
		{
			if (gs.getHeroXPos() > newFalconerXPosition)
			{
				makeFalconerAppear();
				newFalconerXPosition += rand.nextInt(500) + 300;
			}
		}
		falconerIter = currentFalconers.iterator();
		while (falconerIter.hasNext())
		{
			currentFalconer = falconerIter.next();
			currentFalconer.update();
			if (currentFalconer.getX() < -1 * currentFalconer.getWidth())
			{
				currentFalconer.setIsAlive(false);
			}
			if (!currentFalconer.getIsAlive())
			{
				falconerIter.remove();
			}
		}
	}

	/**
	 * Iterates through the Arrays of Enemies and Falconers to be drawn
	 *	to the screen and calls the draw function for each Enemy and Falconer.
	 * 
	 * @param batch : The SpriteBatch object that draws the Enemies and Falconers.
	 */
	public void draw(SpriteBatch batch)
	{
		enemyIter = currentEnemies.iterator();
		while (enemyIter.hasNext())
		{
			currentEnemy = enemyIter.next();
			currentEnemy.draw(batch);
		}
		falconerIter = currentFalconers.iterator();
		while (falconerIter.hasNext())
		{
			currentFalconer = falconerIter.next();
			currentFalconer.draw(batch);
		}
	}
}