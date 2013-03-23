// Package Declaration //
package com.gamedev.decline;

// Java Package Support //
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;

// Badlogic Package Support //
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Array;

/**
 * 
 * com/gamedev/decline/EnemyManager.java
 * 
 * @author(s) 	: Ian Middleton, Zach Coker, Zach Ogle
 * @version 	: 2.0
 * Last Update	: 3/22/2013
 * Update By	: Ian Middleton
 * 
 * Source code for the EnemyManager class. The EnemyManager class takes care of creating,
 * 	updating, drawing, and reallocating Enemy objects.
 *
 */
public class EnemyManager {
	
	// Global Singleton //
	private GlobalSingleton gs = GlobalSingleton.getInstance();
	
	// Constants for the Object //
	public static final int MAX_ENEMIES = 15;
	
	// Internal Variables //
	private Enemy[] enemies = new Enemy[MAX_ENEMIES];
	private ArrayList<Enemy> currentEnemies = new ArrayList<Enemy>();
	private Iterator<Enemy> enemyIter;
	private Enemy currentEnemy;
	private int currentEnemyNumber = 0;
	private Random rand = new Random();
	private int newEnemyXPosition = Gdx.graphics.getWidth()/2 + rand.nextInt(100);

	
	/**
	 * Instantiates a new EnemyManager object. The EnemyManager fills an array of new Enemy objects
	 * 	with the given Texture to be used in the game. This is done to create a buffer of Enemy objects.
	 * 
	 * @param texture	: The image to be used for the Enemy objects.
	 */
	public EnemyManager(Texture texture) {
		for (int i = 0; i < MAX_ENEMIES; i++){
			enemies[i] = new Enemy(texture);
		} // end for
	} // end EnemyManager
	
	public ArrayList<Enemy> getActiveEnemies(){
		return currentEnemies;
	}
	
	public void removeActiveEnemy(int index){
		currentEnemies.remove(index);
	}
	
	/**
	 * Grabs a Enemy from the Enemy buffer created when the manager was constructed. This Enemy 
	 * 	is then added to the array of Enemy that are to be drawn to the screen and updated.
	 */
	public void makeEnemyAppear()
	{
		currentEnemy = enemies[currentEnemyNumber % MAX_ENEMIES];
		currentEnemy.setXPos(Enemy.START_XPOS);
		currentEnemies.add(currentEnemy);
		currentEnemyNumber++;
	}
	
	/**
	 * Checks to see if the hero has moved farther than the random amount required for a new enemy to be spawned.
	 * 	If the hero has moved this distance than a new enemy is created. Afterwards, the function iterates through
	 * 	all Enemies on the screen and calls their update method. If an Enemy has traveled off screen after updating
	 * 	then that Enemy is removed.
	 */
	public void update()
	{
		if(gs.getHeroXPos() > newEnemyXPosition)
		{
			makeEnemyAppear();
			newEnemyXPosition += rand.nextInt(400)+50;
		}
		enemyIter = currentEnemies.iterator();
		while(enemyIter.hasNext())
		{
			currentEnemy = enemyIter.next();
			currentEnemy.update();
			if(currentEnemy.getXPos() < -1 * currentEnemy.getWidth())
			{
				enemyIter.remove();
			}
			if(currentEnemy.getXPos() < 0)
			{
				enemyIter.remove();
			}
		}
		
	}
	
	/**
	 * Iterates through the array of Enemies to be drawn to the screen and calls 
	 * 	the draw function for each Enemy.
	 * 
	 * @param batch - The SpriteBatch object which will draw the Enemy objects.
	 */
	public void draw(SpriteBatch batch)
	{
		enemyIter = currentEnemies.iterator();
		while(enemyIter.hasNext())
		{
			currentEnemy = enemyIter.next();
			currentEnemy.draw(batch);
		}
	}
}
