package com.gamedev.decline;

//bad logic package support
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

/**
 * 
 * com/gamedev/decline/AmmoCountDisplay.java
 * 
 * @author(s) : Ian Middleton, Zach Coker, Zach Ogle
 * @version : 2.0 Last Update : 3/25/2013 Update By : Ian Middleton
 * 
 *          Source code for the AmmoCountDisplay class. This class manages
 *          placing the ammo count on the screen.
 * 
 */
public class AmmoCountDisplay {
	Hero hero;
	BitmapFont font;
	final int xPostion = 30;
	final int yPosition = Gdx.graphics.getHeight() - 50;

	/**
	 * Creates a display for the ammo count
	 * 
	 * @param newHero
	 *            - the hero object whose ammo count is being displayed
	 */
	public AmmoCountDisplay(Hero newHero) {
		hero = newHero;
		font = new BitmapFont();
	}

	/**
	 * Draws the ammo count to the screen.
	 * 
	 * @param batch
	 *            - the sprite batch that draws the text object
	 */
	public void draw(SpriteBatch batch) {
		font.draw(batch,
				"Ammo Left: " + hero.getAmmo(),
				xPostion, yPosition);
	}
}
