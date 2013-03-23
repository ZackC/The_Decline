// Package Declaration //
package com.gamedev.decline;

//Java Package Support //
import java.util.Iterator;

//Badlogic Package Support //
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Array;

/**
 * 
 * com/gamedev/decline/BulletManager.java
 * 
 * @author(s) 	: Ian Middleton, Zach Coker, Zach Ogle
 * @version 	: 2.0
 * Last Update	: 3/22/2013
 * Update By	: Ian Middleton
 * 
 * Source code for the BulletManager class. The BulletManager class takes care of creating,
 * 	updating, drawing, and reallocating Bullet objects.
 *
 */
public class BulletManager {

	// Global Singleton //
	private GlobalSingleton gs = GlobalSingleton.getInstance();
	
	// Constants for the Object //
	public static final int MAX_BULLET_NUMBER = 100;
	
	// Internal Variables //
	private Bullet[] bullets = new Bullet[100];
	private Array<Bullet> shotBullets = new Array<Bullet>();
	private Iterator<Bullet> bulletIter;
	private int currentBulletNumber = 0;
	private Bullet currentBullet;
	
	/**
	 * Instantiates a new BulletManager object. The BulletManager fills an array of new Bullet objects
	 * 	with the given Texture to be used in the game. This is done to create a buffer of Bullet objects.
	 * 
	 * @param texture	: The image to be used for the Bullet objects.
	 */
	public BulletManager(Texture texture) {
		for(int i = 0; i < 100; i++){
			bullets[i] = new Bullet(texture);
		} // end for
	} // end BulletManager()
	
	public Array<Bullet> getActiveBullets(){
		return shotBullets;
	}
	
	public void removeActiveBullet(int index){
		shotBullets.removeIndex(index);
	}
	
	/**
	 * Grabs a Bullet from the Bullet buffer created when the manager was constructed. This Bullet is then 
	 * 	given a direction and a starting position based on which way the Hero object is oriented. This Bullet 
	 * 	is then added to the array of Bullets that are to be drawn to the screen and updated.
	 */
	public void shootBullet(){
		if(gs.getHeroOrientation() == GlobalSingleton.RIGHT){
			currentBullet = bullets[currentBulletNumber % MAX_BULLET_NUMBER];
			
			currentBullet.setOrientation(GlobalSingleton.RIGHT);
			currentBullet.setXPos(Bullet.START_XPOS);
			currentBullet.setYPos(Bullet.START_YPOS);
			
			shotBullets.add(currentBullet);
			currentBulletNumber++;
		} // end if
		else{
			currentBullet = bullets[currentBulletNumber % MAX_BULLET_NUMBER];
			
			currentBullet.setOrientation(GlobalSingleton.LEFT);
			currentBullet.setXPos(Bullet.START_XPOS);
			currentBullet.setYPos(Bullet.START_YPOS);
			
			shotBullets.add(currentBullet);
			currentBulletNumber++;
		} // end else
	} // end shootBullet()
	
	/**
	 * Iterates through the array of Bullets to be drawn to the screen and calls the update function
	 * 	for each Bullet. Also removes Bullets from the drawing array when they travel off of the viewable 
	 * 	area.
	 */
	public void update(){
		bulletIter = shotBullets.iterator();
		while(bulletIter.hasNext()){
			currentBullet = bulletIter.next();
			currentBullet.update();
			if(currentBullet.getXPos() > Gdx.graphics.getWidth()){
				bulletIter.remove();
			} // end if
			else if(currentBullet.getXPos() < 0){
				bulletIter.remove();
			} // end else if
		} // end while
	} // end update()
	
	/**
	 * Iterates through the array of Bullets to be drawn to the screen and calls the draw function
	 * 	for each Bullet.
	 * 
	 * @param batch - The SpriteBatch object which will draw the Bullet objects.
	 */
	public void draw(SpriteBatch batch){
		bulletIter = shotBullets.iterator();
		while(bulletIter.hasNext()){
		  currentBullet = bulletIter.next();
		  currentBullet.draw(batch);
		} // end while
	} // end draw
} // end BulletManager class
