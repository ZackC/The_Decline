// Package Declaration //
package com.gamedev.decline;

// Java Package Support //


// Badlogic Package Support //
import java.util.Iterator;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Array;

/**
 * 
 * com/gamedev/decline/EnemyManager.java
 * 
 * @author(s) : Ian Middleton, Zach Coker, Zach Ogle
 * @version : 2.0 Last Update : 3/25/2013 Update By : Ian Middleton
 * 
 *          Source code for the EnemyManager class. The EnemyManager class takes
 *          care of creating, updating, drawing, and reallocating Enemy objects.
 * 
 */
public class BossManager {
	// Global Singleton //
	private GlobalSingleton gs = GlobalSingleton.getInstance();
	// Constants //
	private static final int MAX_FIREBALLS = 10;
	
	// Internal Variables //
	private Boss boss;
	private Fireball[] fireballs = new Fireball[MAX_FIREBALLS];
	private Array<Fireball> currentFireballs = new Array<Fireball>();
	private int currentFireballNumber = 0;
	private Fireball currentFireball;
	private Iterator<Fireball> fireballIter;
	
	public BossManager(Texture bossTexture, Texture fireballTexture){
		boss = new Boss(bossTexture);
		for (int i = 0; i < MAX_FIREBALLS; i++) {
			fireballs[i] = new Fireball(fireballTexture);
		}
		boss.setToInitialDrawPosition();
		boss.flipOrientation();
	}
	
	public Boss getBoss(){
		return boss;
	}
	
	public Array<Fireball> getActiveFireballs(){
		return currentFireballs;
	}
	
	public void bossDamagedEvent(int damage){
		if(!boss.getHasHealthBar()){
			gs.getHealthBarManager().add(boss);
			boss.setHasHealthBar(true);
		}
		boss.setHealth(boss.getHealth() - damage);
	}
	
	public void removeActiveFireball(int index){
		currentFireballs.removeIndex(index);
	}
	
	public void shootFireball(){
		currentFireball = fireballs[currentFireballNumber % MAX_FIREBALLS];
		if (boss.getOrientation() == GlobalSingleton.RIGHT) 
		{
		   currentFireball.setOrientation(GlobalSingleton.RIGHT);
		} // end if
		else {
			currentFireball.setOrientation(GlobalSingleton.LEFT);
		} // end else
		currentFireball.setToInitialDrawPosition(boss.getX(), boss.getY());
        currentFireball.setIsAlive(true);
		currentFireballs.add(currentFireball);
		currentFireballNumber++;
	}
	
	public void update(){
		boss.update();
		
		fireballIter = currentFireballs.iterator();
		while (fireballIter.hasNext()) {
			currentFireball = fireballIter.next();
			currentFireball.update();
			if (currentFireball.getX() > Gdx.graphics.getWidth()) {
				fireballIter.remove();
			} // end if
			else if (currentFireball.getX() < 0) {
				fireballIter.remove();
			} // end else if
		}
	}
	
	public void draw(SpriteBatch batch){
		boss.draw(batch);
		
		fireballIter = currentFireballs.iterator();
		while(fireballIter.hasNext()){
			currentFireball = fireballIter.next();
			currentFireball.draw(batch);
		}
	}
}