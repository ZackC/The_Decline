// Package Declaration //
package com.gamedev.decline;

// Java Package Support //
// { Not Applicable }

// Badlogic Package Support //
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;

/**
 * 
 * com/gamedev/decline/Item.java
 * 
 * @author(s) : Ian Middleton, Zach Coker, Zach Ogle
 * @version : 2.0 Last Update : 3/22/2013 Update By : Zach Ogle
 * 
 *          Source code for the Item class. The Item class is an abstract class
 *          that is meant to be extended for specific Items within the game.
 * 
 *          ***START HERE*** ian
 */
public abstract class Item extends CollidableObject {
	// Global Singleton //
	private GlobalSingleton gs = GlobalSingleton.getInstance();

	// Constants of the Object //
	public static final int ITEM_SIZE = 50;
	public static final int START_XDRAW = Gdx.graphics.getWidth() - ITEM_SIZE
			/ 2;
	public static final int START_YDRAW = 20;

	// Internal Variables

	/**
	 * Constructor for all Items in the game. Must be called by all classes
	 * extending Item.
	 * 
	 * @param texture
	 *            : The image for this specific Item.
	 * @param xPos
	 *            : The xPos of this specific Item.
	 * @param yPos
	 *            : The yPos of this specific Item.
	 */
	public Item(Texture texture, float xPos, float yPos, int itemSize) {
		super(texture, xPos, yPos);
		if(itemSize == 0)
		{
		  setSize(ITEM_SIZE, ITEM_SIZE);
		}
		else
		{
		  setSize(itemSize,itemSize);
		}
	}

	/**
	 * Sets the position of the Item to its starting position.
	 */
	public void setToInitialDrawPosition() {
		setPosition(START_XDRAW, START_YDRAW);
	}

	/**
	 * Updates the position of Items.
	 */
	public void update() {
		setPosition(
				getX() - (gs.getHeroMovement() * Gdx.graphics.getDeltaTime()),
				getY());
	}
}
