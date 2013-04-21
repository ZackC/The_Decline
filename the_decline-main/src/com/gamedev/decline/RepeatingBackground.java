// Package Declaration //
package com.gamedev.decline;

// Java Package Support //
// { Not applicable }

// Badlogic Package Support //
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

/**
 * com/gamedev/decline/RepeatingBackground.java
 * 
 * @author(s) : Ian Middleton, Zach Coker, Zach Ogle
 * @version : 2.0 Last Update : 4/21/2013 Update By : Zach Ogle
 * 
 * Source code for the RepeatingBackground class. The RepeatingBackground
 *	class represents a RepeatingBackground object in the game.
 * 
 */
public class RepeatingBackground
{
	// Global Singleton //
	private GlobalSingleton gs = GlobalSingleton.getInstance();
	
	// Constants //
	public static final int BP_SIZE = 2;
	
	// Internal Variables //
	private BackgroundPanel[] bp = new BackgroundPanel[BP_SIZE];

	/**
	 * Instantiates a new RepeatingBackground and sets the textures.
	 * 
	 * @param newTexture : The image for the RepeatingBackground.
	 */
	public RepeatingBackground(Texture newTexture)
	{
		for (int i = 0; i < BP_SIZE; i++)
		{
			bp[i] = new BackgroundPanel(newTexture);
		}
	}

	/**
	 * Draws the background panels.
	 * 
	 * @param batch : The SpriteBatch object that will draw the RepeatingBackground.
	 */
	public void draw(SpriteBatch batch)
	{
		float backgroundPos = (gs.getWorldXPos() + GlobalSingleton.HERO_XDRAW) % bp[0].getWidth();
		if (gs.getHeroXPos() % bp[0].getWidth() < GlobalSingleton.HERO_XDRAW)
		{
			bp[0].setXPos(-1 * (backgroundPos));
			bp[1].setXPos(-1 * backgroundPos - bp[0].getWidth());
			bp[0].draw(batch);
			bp[1].draw(batch);
		}
		else if (gs.getHeroXPos() % bp[0].getWidth() > bp[0].getWidth() - Gdx.graphics.getWidth())
		{
			bp[0].setXPos(-1 * backgroundPos);
			bp[1].setXPos(-1 * backgroundPos + bp[0].getWidth());
			bp[0].draw(batch);
			bp[1].draw(batch);
		}
		else
		{
			bp[0].setXPos(-1 * (backgroundPos % bp[0].getWidth()));
			bp[0].draw(batch);
		}
	}
}