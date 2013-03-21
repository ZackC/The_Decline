package com.gamedev.decline;

import java.util.Iterator;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Array;

/*
 * The class that manages all of the bullet objects
 */
public class BulletManager {

	//the image of the bullet
	private Texture texture;
	//the number of bullets initialized at the beginning and the max number in
	//the game
	private int maxBulletNumber = 100;
	//the array of all of the bullet objects
	private Bullet[] bulletArray = new Bullet[maxBulletNumber];
	//the number of the current bullet fired; this number always increments
	private int currentBulletNumber = 0;
	//the array that stores the bullets currently on the screen
	private Array<Bullet> shotBullets = new Array<Bullet>();
	//the iterator of the shotBullets array
	private Iterator<Bullet> bulletIter;
	//the object for the currentBullet being looked at
	private Bullet currentBullet;
	//the speed of the bullet objects
	private int bulletSpeed = 10;
	//the EnemyManager
	private EnemyManager em;
	//the iterator for currently displayed enemy objects
	private Iterator<Enemy> enemyIter;
	//the current enemey being looked at
	private Enemy currentEnemy;
	//the size of the bullets in the x direction
	private int bulletXSize = 30;
	//the size of the bullets in the y direction
	private int bulletYSize = 30;
	//the singleton that holds all of the global variables
	private GlobalSingleton gs = GlobalSingleton.getInstance();
	
	/*
	 * The default constructor for the BulletManager class
	 * 
	 * newTexture - the image of the bullet
	 */
	public BulletManager(Texture newTexture) 
	{
	   texture = newTexture;
	   em = new EnemyManager(new Texture(Gdx.files.internal("data/enemy.gif")));
       for(int i = 0; i < maxBulletNumber; i++)
       {
    	   bulletArray[i] = new Bullet();
       }
	}
	
	/*
	 * The method for adding a bullet to the screen.  
	 * Adds the bullet to the shotBullet array and increments 
	 * the currentBulletNumber.
	 */
	public void shootBullet()
	{
		shotBullets.add(bulletArray[currentBulletNumber % maxBulletNumber]);
		currentBulletNumber++;
	}
	
	/*
	 * The method that updates the movements of the bullets and determines
	 * if they need to disappear 
	 * 
	 * this method also attempts to determine the bullet collisions but it does
	 * not work at the moment!!!
	 */
	public void update()
	{
		bulletIter = shotBullets.iterator();
		while(bulletIter.hasNext())
		{
			currentBullet = bulletIter.next();
			currentBullet.setXPos(currentBullet.getXPos()+ bulletSpeed-gs.getHeroMovement());
			enemyIter = em.getCurrentEnemiesIter();
			while(enemyIter.hasNext())
			{
			  currentEnemy = enemyIter.next();
			  //System.out.println("Checking for overlap");
			  if(currentBullet.hasCollision(currentEnemy.getBoundingRectangle()));
			  {
				//System.out.println("Had overlap");  
				enemyIter.remove();
				currentBullet.setXPosToStart();
				//System.out.println("shot bullets size: "+ shotBullets.size);
				bulletIter.remove();
				break;
			  }
		    }
			if(currentBullet.getXPos() > Gdx.graphics.getWidth())
			{
				currentBullet.setXPosToStart();
				bulletIter.remove();
			}
			else if(currentBullet.getXPos() < 0)
			{
				currentBullet.setXPosToStart();
				bulletIter.remove();
			}
			
		}
		em.update();
	}
	
	/*
	 * the method for drawing all of the bullet objects currently
	 * being displayed.
	 * 
	 * batch - the SpriteBatch for drawing all of the objects on screen
	 */
	public void draw(SpriteBatch batch)
	{
		bulletIter = shotBullets.iterator();
		while(bulletIter.hasNext())
		{
		  currentBullet = bulletIter.next();
		  batch.draw(texture,currentBullet.getXPos(),currentBullet.getYPos(),bulletXSize,bulletYSize);
		}
		em.draw(batch);
	}

}
