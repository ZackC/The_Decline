package com.gamedev.decline;

import com.badlogic.gdx.math.Rectangle;
/*
 * The Bullet class that represents bullet objects
 */
public class Bullet extends Rectangle
{
	private GlobalSingleton gs = GlobalSingleton.getInstance();
    private float xPos;
    private int yPos;
	
    /*
     * The default constructor for the class
     * Determines where the bullets start in the screen.
     */
	public Bullet() 
	{
		//For some reason bullets are being created before the hero size is
		//determined.  Will have to look into that later
		//xPos = gs.getStartingHeroXPos() + gs.getHeroWidth() + 10;
	    //yPos = gs.getStartingHeroYPos() + gs.getHeroHeight()/2;
		
		xPos = gs.getStartingHeroXPos() + 70;
		yPos = gs.getStartingHeroYPos() + 50;
	    
	    //System.out.println("Starting bullet xPos: "+xPos);
	    //System.out.println("Starting bullet yPos: "+yPos);
	}
	
	/*
	 * sets the x position of the bullet to newPos
	 * 
	 * newPos - the new x position of the bullet
	 */
	public void setXPos(float newPos)
	{
		xPos = newPos;
	}
	
	/*
	 * sets the x position of the bullet to its original starting position
	 * needed for when a bullet disappears
	 */
	public void setXPosToStart()
	{
		xPos = gs.getStartingHeroXPos() + 70;
	}

	/*
	 * returns the x position of the bullet
	 */
	public float getXPos()
	{
	  return xPos;
	}
	
	/*
	 * sets the y position of the bullet
	 */
	public void setYPos(int newPos)
	{
		yPos = newPos;
	}

    /*
     * returns the y position of the bullet
     */
	int getYPos()
	{
		return yPos;
	}
	
	/*
	 * the function that checks if a bullet collides with an enemy
	 * doesn't work at the moment!!!
	 */
	public boolean hasCollision(Rectangle rect)
	{
		return overlaps(rect);
	}
}
