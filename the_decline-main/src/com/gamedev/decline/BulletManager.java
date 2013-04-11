// Package Declaration //
package com.gamedev.decline;

//Java Package Support //
import java.util.Iterator;
import java.util.ArrayList;

//Badlogic Package Support //
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Array;

/**
 * 
 * com/gamedev/decline/BulletManager.java
 * 
 * @author(s) : Ian Middleton, Zach Coker, Zach Ogle
 * @version : 2.0 Last Update : 3/25/2013 Update By : Ian Middleton
 * 
 *          Source code for the BulletManager class. The BulletManager class
 *          takes care of creating, updating, drawing, and reallocating Bullet
 *          objects.
 * 
 */
public class BulletManager {

	// Global Singleton //
	private GlobalSingleton gs = GlobalSingleton.getInstance();

	// Constants //
	public static final int MAX_BULLET_NUMBER = 20;

	// Internal Variables //
	private Bullet[] bullets = new Bullet[MAX_BULLET_NUMBER];
	private Array<Bullet> shotBullets = new Array<Bullet>();
	private Iterator<Bullet> bulletIter;
	private int currentBulletNumber = 0;
	private Bullet currentBullet;
	private Texture texture;

	/**
	 * Instantiates a new BulletManager object. The BulletManager fills an array
	 * of new Bullet objects with the given Texture to be used in the game. This
	 * is done to create a buffer of Bullet objects.
	 * 
	 * @param texture
	 *            : The image to be used for the Bullet objects.
	 */
	public BulletManager(Texture texture) {
		this.texture = texture;
		for (int i = 0; i < MAX_BULLET_NUMBER; i++) {
			bullets[i] = new Bullet(texture);
		} // end for
	} // end BulletManager()

	/**
	 * Returns the active Bullet objects in the game.
	 * 
	 * @return : A Badlogic Array of the Bullet objects.
	 */
	public Array<Bullet> getActiveBullets() {
		return shotBullets;
	}// end getActiveBullets()

	/**
	 * Removes the active Bullet located at the specified index.
	 * 
	 * @param index
	 *            : The index of the Bullet to be removed.
	 */
	public void removeActiveBullet(int index) {
		shotBullets.removeIndex(index);
		System.out.println("Removing bullet due to collision.");
	}// end removeActiveBullet

	/**
	 * Grabs a Bullet from the Bullet buffer created when the manager was
	 * constructed. This Bullet is then given a direction and a starting
	 * position based on which way the Hero object is oriented. This Bullet is
	 * then added to the array of active Bullets that are to be drawn to the
	 * screen and updated.
	 */
	public void shootBullet() 
	{
	  //currentBullet = bullets[currentBulletNumber % MAX_BULLET_NUMBER];
		currentBullet = new Bullet(texture);
		if (gs.getHeroOrientation() == GlobalSingleton.RIGHT) 
		{
		   currentBullet.setOrientation(GlobalSingleton.RIGHT);
		} // end if
		else {
			currentBullet.setOrientation(GlobalSingleton.LEFT);
		} // end else
		currentBullet.setToInitialDrawPosition();
                currentBullet.setIsAlive(true);
		shotBullets.add(currentBullet);
		System.out.println("Drawing Bullet number: "+currentBulletNumber % MAX_BULLET_NUMBER);
		currentBulletNumber++;
	} // end shootBullet()

	/**
	 * Iterates through the array of Bullets to be drawn to the screen and calls
	 * the update function for each Bullet. Also removes Bullets from the active
	 * array when they travel off of the viewable area.
	 */
	public void update() {
		bulletIter = shotBullets.iterator();
		while (bulletIter.hasNext()) {
			currentBullet = bulletIter.next();
			currentBullet.update();
			if (currentBullet.getX() > Gdx.graphics.getWidth()) {
				bulletIter.remove();
				System.out.println("Removing bullet due to past screen.");
			} // end if
			else if (currentBullet.getX() < 0) {
			        System.out.println("Removing bullet due to leaving front of screen.");
				bulletIter.remove();
			} // end else if
		} // end while
	} // end update()

	/**
	 * Iterates through the array of Bullets to be drawn to the screen and calls
	 * the draw function for each Bullet.
	 * 
	 * @param batch
	 *            - The SpriteBatch object which will draw the Bullet objects.
	 */
	public void draw(SpriteBatch batch) {
		bulletIter = shotBullets.iterator();
		while (bulletIter.hasNext()) {
			currentBullet = bulletIter.next();
			//System.out.println("Drawing bullet at x pos: "+currentBullet.getX()+ "and y pos: "+currentBullet.getY()+ 
			//    " at x world position: "+currentBullet.getXPos());
			currentBullet.draw(batch);
		} // end while
	} // end draw
} // end BulletManager class
