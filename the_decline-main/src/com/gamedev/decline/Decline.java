// Package Declaration //
package com.gamedev.decline;

// Java Package Support //
// { Not Applicable }

// Badlogic Package Support //
import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Array;

/**
 * 
 * com/gamedev/decline/Decline.java
 * 
 * @author(s) 	: Ian Middleton, Zach Coker, Zach Ogle
 * @version 	: 2.0
 * Last Update	: 3/23/2013
 * Update By	: Ian Middleton
 * 
 * Source code for the Decline class. The Decline class takes care of essentially
 * 	"running" the entire game. All high-level game elements in addition to the camera, background,
 * 	and batch are instantiated and executed here.
 *
 */
public class Decline implements ApplicationListener {
	
	// Global Singleton //
	private GlobalSingleton gs = GlobalSingleton.getInstance();
	
	// Constants for the Objects //
	public static final int HEALTH_PACK = 5;
	public static final int ENEMY_DAMAGE = 10;
	
	// Internal Variables //
	private OrthographicCamera camera;
	private SpriteBatch batch;
	private RepeatingBackground background;
	private Hero hero;
	private BulletManager bm;
	private EnemyManager em;
	private ItemManager im;
	boolean shoot = false;
	boolean ableToShoot = true;	

	/**
	 * Function run when the game is started. Basically a high-level constructor for the game.
	 * 
	 * (non-Javadoc)
	 * @see com.badlogic.gdx.ApplicationListener#create()
	 */
	@Override
	public void create() {		
		float width = Gdx.graphics.getWidth();
		float height = Gdx.graphics.getHeight();

		Gdx.app.log("Screen Width", String.valueOf(width));
		Gdx.app.log("Screen Height", String.valueOf(height));

		camera = new OrthographicCamera(width, height);
		camera.setToOrtho(false);
		batch = new SpriteBatch();
		
		bm = new BulletManager(new Texture(Gdx.files.internal("data/bullet.jpg")));
		
		em = new EnemyManager(new Texture(Gdx.files.internal("data/enemy.gif")));
		
		hero = new Hero(new Texture(Gdx.files.internal("hero_weapon.png")), 
				new Texture(Gdx.files.internal("hero_crouch.png")));
		hero.setOrigin(hero.getWidth()/2, hero.getHeight()/2);
		hero.setToInitialDrawPosition();
		
		background = new RepeatingBackground(new Texture(Gdx.files.internal("data/cave.jpg")));
        im = new ItemManager(new Texture(Gdx.files.internal("ammoBox.png")), new Texture(Gdx.files.internal("data/healthpack.jpg")));
	}

	/**
	 * Cleans up all resources when the game is closed.
	 * 
	 * (non-Javadoc)
	 * @see com.badlogic.gdx.ApplicationListener#dispose()
	 * 
	 * *** INCOMPLETE ***
	 */
	@Override
	public void dispose() {
		batch.dispose();
	}

	/**
	 * Calls the draw functions for all objects and then calls the update function. 
	 * 
	 * (non-Javadoc)
	 * @see com.badlogic.gdx.ApplicationListener#render()
	 */
	@Override
	public void render() {
		handleEvent();
		handleCollision();
		update();
		
		Gdx.gl.glClearColor(1, 1, 1, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		batch.setProjectionMatrix(camera.combined);
		batch.begin();
		background.draw(batch,hero.getXPos());
		hero.draw(batch);
		bm.draw(batch);
		em.draw(batch);
		im.draw(batch);
		batch.end();
	}
	
	private void handleEvent(){
		//if hero is hiding
		if(gs.getIsHeroHiding())
		{
			//and the user is not trying to hide
			//make the hero stand up
			if(!Gdx.input.isKeyPressed(Keys.DOWN))
			{
			  hero.stand();
			}
			//else set the hero movement to zero
			else
			{
				gs.setHeroMovement(0);	
			} 
		}
		//if the hero is not hiding
		else
		{
		  //if left is pressed move left	
		  if(Gdx.input.isKeyPressed(Keys.LEFT)) {
			hero.moveLeft();
			  if(gs.getHeroOrientation() == GlobalSingleton.RIGHT){
			  	gs.setHeroOrientation(GlobalSingleton.LEFT);
			  }
			  gs.setHeroMovement(-Hero.SPEED);
		  }
		  else if(Gdx.input.isKeyPressed(Keys.RIGHT)) {
		 	  hero.moveRight();
			  if(gs.getHeroOrientation() == GlobalSingleton.LEFT){
				  gs.setHeroOrientation(GlobalSingleton.RIGHT);
			  }
			  gs.setHeroMovement(Hero.SPEED);
		  }
		  else
		  {
			  gs.setHeroMovement(0);
		  }
		  if(Gdx.input.isKeyPressed(Keys.UP) && !gs.getIsHeroJumping()){
			  gs.setIsHeroJumping(true);
		  }
		  if(Gdx.input.isKeyPressed(Keys.DOWN))
		  {
			  hero.hide();
		  }
		}
		
		if(Gdx.input.isKeyPressed(Keys.SPACE) && ableToShoot)
		{
			shoot = true;
			ableToShoot = false;
		}
		
		if(shoot == true){
			bm.shootBullet();
			hero.setAmmo(hero.getAmmo() - 1);
			if (hero.getAmmo() == 0)
			{
				ableToShoot = false;
			}
			shoot = false;
		}
		
		if(!(Gdx.input.isKeyPressed(Keys.SPACE)) && hero.getAmmo() != 0){
			ableToShoot = true;
		}
	}
	
	private void handleCollision(){
		Array<Bullet> activeBullets = bm.getActiveBullets();
		Array<Enemy> activeEnemies = em.getActiveEnemies();
		Array<Ammo> activeAmmo = im.getActiveAmmo();
		Array<HealthPack> activeHealthPacks = im.getActiveHealthPacks();
		
		for(int i = 0; i < activeBullets.size; i++){
			for(int j = 0; j < activeEnemies.size; j++){
				if(activeBullets.get(i).collidesWith(activeEnemies.get(j))){
					bm.removeActiveBullet(i);
					em.removeActiveEnemy(j);
					break;
				}
			}
		}
		if(!gs.getIsHeroHiding())
		{	
		  for(int i=0; i < activeEnemies.size; i++){
			  if(hero.collidesWith(activeEnemies.get(i))){
				  hero.setHealth(hero.getHealth() - ENEMY_DAMAGE);
				  if (hero.getHealth() < 0)
				  {
					  hero.setHealth(0);
				  }
				  em.removeActiveEnemy(i);
			  }
		  }
		}
		
		for(int i=0; i < activeAmmo.size; i++){
			if(hero.collidesWith(activeAmmo.get(i))){
				hero.setAmmo(hero.getAmmo() + im.getActiveAmmo().get(i).getAmountOfAmmoStored());
				ableToShoot = true;
				if (hero.getAmmo() > Hero.MAX_AMMO)
				{
					hero.setAmmo(Hero.MAX_AMMO);
				}
				im.removeActiveAmmo(i);
			}
		}
		
		for(int i=0; i < activeHealthPacks.size; i++){
			if(hero.collidesWith(activeHealthPacks.get(i))){
				hero.setHealth(hero.getHealth() + HEALTH_PACK);
				if (hero.getHealth() > Hero.MAX_HEALTH)
				{
					hero.setHealth(Hero.MAX_HEALTH);
				}
				im.removeActiveHealthPack(i);
			}
		}
	}

	/**
	 * Calls the update functions for all objects.
	 */
	private void update() {
		hero.update();
		bm.update();
		em.update();
		im.update();
	}

	/**
	 * Function used for resizing of the game.
	 * 
	 * (non-Javadoc)
	 * @see com.badlogic.gdx.ApplicationListener#resize(int, int)
	 * 
	 * *** INCOMPLETE ***
	 */
	@Override
	public void resize(int width, int height) {
	}

	/**
	 * Function used for pausing of the game.
	 * 
	 * (non-Javadoc)
	 * @see com.badlogic.gdx.ApplicationListener#pause()
	 * 
	 * *** INCOMPLETE ***
	 */
	@Override
	public void pause() {
	}

	/**
	 * Function used for resuming of the game
	 * 
	 * (non-Javadoc)
	 * @see com.badlogic.gdx.ApplicationListener#resume()
	 * 
	 * *** INCOMPLETE ***
	 */
	@Override
	public void resume() {
	}
}
