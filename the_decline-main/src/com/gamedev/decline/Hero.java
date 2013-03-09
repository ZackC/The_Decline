package com.gamedev.decline;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
/*
 * The hero class;  this class controls the main character
 */
public class Hero extends Sprite {
    
	// the global variables of the game
	private GlobalSingleton gs = GlobalSingleton.getInstance();
	// the current x position of the hero
	private float xPos;
	// the hero's movement speed
	private int heroSpeed = 200;
	// the bullet manager
	private BulletManager bm;
	
	/*
	 * The default constructor for the hero class
	 */
	private Hero() 
	{

	}

	/*
	 * The hero constructor using a texture
	 * 
	 * texture - the image for the hero
	 * newBm - the bulletManager
	 */
	public Hero(Texture texture,BulletManager newBm) {
		super(texture);
		bm = newBm;
		initialize(texture.getWidth(), texture.getHeight());
	}

	/*
	 * The hero constructor using a region
	 * 
	 * region - the region for the hero
	 * newBm - the bulletManager
	 */
	public Hero(TextureRegion region,BulletManager newBm) {
		super(region);
		bm = newBm;
		initialize(region.getRegionWidth(),region.getRegionHeight());
	}

	/*
	 * The hero constructor using a sprite
	 * 
	 * sprite - the sprite for the hero
	 * newBm - the bulletManager
	 */
	public Hero(Sprite sprite,BulletManager newBm) {
		super(sprite);
		bm = newBm;
		initialize(sprite.getRegionWidth(),sprite.getRegionHeight());
	}

	/*
	 * The hero constructor using a texture to get a texture region
	 * 
	 * texture - the image for the hero
	 * srcWidth - the width you want out of the image 
	 * srcHeight - the height of the region you want out of the image
	 * newBm - the bulletManager
	 */
	public Hero(Texture texture, int srcWidth, int srcHeight,BulletManager newBm) {
		super(texture, srcWidth, srcHeight);
		bm = newBm;
		initialize(srcWidth, srcHeight);
	}

	/*
	 * Note: Haven't tried this one
	 * 
	 * The hero constructor using a texture to get a texture region
	 * 
	 * texture - the image for the hero
	 * srcX - (not sure and not on website) I think this is the staring x position of the region 
	 *    in the image
	 * srcY - (not sure and not on website) I think this is the staring y position of the region 
	 *   in the image    
	 * srcWidth - the width you want out of the image 
	 * srcHeight - the height of the region you want out of the image
	 * newBm - the bulletManager
	 */
	public Hero(Texture texture, int srcX, int srcY, int srcWidth,
			int srcHeight,BulletManager newBm) {
		super(texture, srcX, srcY, srcWidth, srcHeight);
		bm = newBm;
		initialize(srcWidth, srcHeight);
	}
	
	/*
	 * Note: Haven't tried this one
	 * 
	 * The hero constructor using a region to get another texture region
	 * 
	 * texture - the image for the hero
	 * srcX - (not sure and not on website) I think this is the staring x position of the region 
	 *    in the image
	 * srcY - (not sure and not on website) I think this is the staring y position of the region 
	 *   in the image    
	 * srcWidth - the width you want out of the image 
	 * srcHeight - the height of the region you want out of the image
	 * newBm - the bulletManager
	 */
	public Hero(TextureRegion region, int srcX, int srcY, int srcWidth,
			int srcHeight,BulletManager newBm) {
		super(region, srcX, srcY, srcWidth, srcHeight);
		bm = newBm;
		initialize(srcWidth,srcHeight);
	}

	/*
	 * announces the width and height of the hero to the global singleton
	 */
	public void initialize(int width, int height)
	{
		gs.setHeroWidth(width);
		gs.setHeroHeight(height);
		
		//System.out.println("Width: "+width);
		//System.out.println("Height: "+height);
	}
	
	/*
	 * sets the current x position of the hero and alerts the global singleton
	 */
	public void setXPos(float newXPos)
	{
		xPos = newXPos;
		gs.setHeroXPos(xPos);
	}
	
	/* 
	 * returns the current x position of the hero
	 */
	public float getXPos()
	{
		return xPos;
	}
	
	/*
	 * updates the hero
	 * 
	 * at the moment - this method is used to move the hero and have the
	 *    hero shoot bullets
	 */
	public void update()
	{
		//if(character.getX() > 0)
		if(Gdx.input.isKeyPressed(Keys.LEFT)) {
			setXPos(getXPos() - heroSpeed * Gdx.graphics.getDeltaTime());
			gs.setHeroMovement(-1 * (heroSpeed * Gdx.graphics.getDeltaTime()));
			if(isFlipX() == false)
				flip(true, false);
		}
	//if(character.getX()+character.getWidth() < Gdx.graphics.getWidth())
	//	System.out.println("Graphics width: "+Gdx.graphics.getWidth());
		else if(Gdx.input.isKeyPressed(Keys.RIGHT)) {
			setXPos(getXPos() + heroSpeed * Gdx.graphics.getDeltaTime());
			gs.setHeroMovement(heroSpeed * Gdx.graphics.getDeltaTime());
			if(isFlipX() == true)
				flip(true, false);
		}
		else
		{
			gs.setHeroMovement(0);
		}
		if(Gdx.input.isKeyPressed(Keys.SPACE))
		{
		  bm.shootBullet();
		}
		
	}

}
