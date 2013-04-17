// Package Declaration //
package com.gamedev.decline;

// Java Package Support //


// Badlogic Package Support //
import java.util.Iterator;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.TimeUtils;

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

	private Array<Fireball> currentFireballs = new Array<Fireball>();
	private Fireball currentFireball;
	private Iterator<Fireball> fireballIter;
	boolean intro = true;
	private float timeBetweenShots = 1.1f;
	private long lastShot;
	private Texture fireballTexture;
	
	public BossManager(Texture bossTexture, Texture fireballTexture){
		boss = new Boss(bossTexture, new Fireball(fireballTexture));
		boss.setToInitialDrawPosition();
		this.fireballTexture = fireballTexture;
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
		currentFireball = new Fireball(fireballTexture);
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
	}
	
	public void update(){
		if(intro){
			boss.setFireballMode(true);
			boss.setFireballDirection(false);
			boss.moveDown();
			if(boss.getYPos() < 20){
				boss.setFireballMode(false);
				intro = false;
				boss.setToGroundDrawPosition();
			}
		}else{
			if (TimeUtils.nanoTime() > lastShot + (timeBetweenShots * 1000000000L))
			{
			    System.out.println("1");
				shootFireball();
				lastShot = TimeUtils.nanoTime();
			}
			
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
			
			if(boss.getHealth() <= 0){
				gs.setIsGameOver(true);
			}
		}
	}
	
	public void draw(SpriteBatch batch){
		boss.draw(batch);
		
		fireballIter = currentFireballs.iterator();
		while(fireballIter.hasNext()){
			currentFireball = fireballIter.next();
			System.out.println(currentFireball.getX()+"-"+currentFireball.getY());
			currentFireball.draw(batch);
		}
	}
}