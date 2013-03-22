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

	private Bullet[] rightBullets = new Bullet[50];
	private Bullet[] leftBullets = new Bullet[50];
	//the number of bullets initialized at the beginning and the max number in
	//the game
	private int maxBulletNumber = 50;
	//the number of the current bullet fired; this number always increments
	private int currentRightBulletNumber = 0;
	private int currentLeftBulletNumber = 0;
	//the array that stores the bullets currently on the screen
	private Array<Bullet> shotBullets = new Array<Bullet>();
	//the iterator of the shotBullets array
	private Iterator<Bullet> bulletIter;
	//the object for the currentBullet being looked at
	private Bullet currentBullet;
	//the singleton that holds all of the global variables
	private GlobalSingleton gs = GlobalSingleton.getInstance();
	private Bullet bulletHolder;
	
	/*
	 * The default constructor for the BulletManager class
	 * 
	 * newTexture - the image of the bullet
	 */
	public BulletManager(Texture texture) 
	{
		for(int i = 0; i < 50; i++){
			rightBullets[i] = new Bullet(texture, gs.RIGHT);
		    leftBullets[i] = new Bullet(texture, gs.LEFT);
		}
	}
	
	/*
	 * The method for adding a bullet to the screen.  
	 * Adds the bullet to the shotBullet array and increments 
	 * the currentBulletNumber.
	 */
	public void shootBullet()
	{
		if(gs.getHeroOrientation() == gs.RIGHT){
			bulletHolder = rightBullets[currentRightBulletNumber % maxBulletNumber];
			bulletHolder.setXPos(gs.getStartingHeroXPos()+80);
			shotBullets.add(bulletHolder);
			currentRightBulletNumber++;
		}
		else{
			bulletHolder = leftBullets[currentLeftBulletNumber % maxBulletNumber];
			bulletHolder.setXPos(gs.getStartingHeroXPos());
			shotBullets.add(bulletHolder);
			currentLeftBulletNumber++;
		}
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
			currentBullet.update();
			if(currentBullet.getXPos() > Gdx.graphics.getWidth())
			{
				bulletIter.remove();
			}
			else if(currentBullet.getXPos() < 0)
			{
				bulletIter.remove();
			}
		}
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
		  currentBullet.draw(batch);
		}
	}

}
