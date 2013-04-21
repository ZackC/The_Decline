// Package Declaration //
package com.gamedev.decline;

// Java Package Support //
// { Not applicable }

// Badlogic Package Support //
import com.badlogic.gdx.graphics.Texture;

/**
 * com/gamedev/decline/Bush.java
 * 
 * @author(s) : Ian Middleton, Zach Coker, Zach Ogle
 * @version : 2.0 Last Update : 4/9/2013 Update By : Zach Ogle
 * 
 * Source code for the Bush class. The Bush class represents a Bush
 *	object in the game. It extends and uses the Item class.
 */
public class Bush extends Item
{
	// Global Singleton //
	// { Not applicable }
	
	// Constants //
	public static final int BUSH_SIZE = 100;
	
	// Internal Variables //
	// { Not applicable }
	
	/**
	 * Instantiates a new Bush object by calling the super constructor (Item).
	 * 
	 * @param texture : The image of the Bush.
	 */
	public Bush(Texture texture)
	{
		super(texture, 0, 0,BUSH_SIZE);
	}
}