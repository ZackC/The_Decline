package com.gamedev.decline;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.FPSLogger;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
/*
 * The main class of the game.
 */
public class Decline implements ApplicationListener {
	// the camera of the game which determines the game view
	private OrthographicCamera camera;
	//the object that draws the objects in the game
	private SpriteBatch batch;
	//the main character
	private Hero character;
	//the frames per second logger
	private FPSLogger fps = new FPSLogger();
	// the background of the game
	private RepeatingBackground background;
	//the singleton that holds the global variables for the game
	private GlobalSingleton gs = GlobalSingleton.getInstance();
	//the x position of the hero
	private int heroXPos = gs.getStartingHeroXPos();
	//the y position of the hero
	private int heroYPos = gs.getStartingHeroYPos();
	//the bullet manager of the game
	private BulletManager bm;
	

	/*
	 * Creates the objects of the game and logs information.
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
		
		character = new Hero(new Texture(Gdx.files.internal("hero_weapon.png")),bm);
		character.setOrigin(character.getWidth()/2, character.getHeight()/2);
		character.setPosition(gs.getStartingHeroXPos(), gs.getStartingHeroYPos());

		Gdx.app.log("Character Width", String.valueOf(character.getWidth()));
		Gdx.app.log("Character Height", String.valueOf(character.getHeight()));
		
		background = new RepeatingBackground(new Texture(Gdx.files.internal("data/cave.jpg")));

	}

	/*
	 * Used to get rid of objects after the game has finished
	 * 
	 * (non-Javadoc)
	 * @see com.badlogic.gdx.ApplicationListener#dispose()
	 */
	@Override
	public void dispose() {
		batch.dispose();
	}

	/*
	 * draws the objects in their current state and then updates them
	 * behind the scenes
	 * 
	 * (non-Javadoc)
	 * @see com.badlogic.gdx.ApplicationListener#render()
	 */
	@Override
	public void render() {		
		Gdx.gl.glClearColor(1, 1, 1, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		batch.setProjectionMatrix(camera.combined);
		batch.begin();
		background.draw(batch,character.getXPos());
		character.draw(batch);
		bm.draw(batch);
		batch.end();

		update();
		//fps.log();
		
	}

	/*
	 * updates the scene
	 */
	private void update() {
		
		character.update();
		bm.update();
	}

	/*
	 * used for resizing the game
	 * 
	 * (non-Javadoc)
	 * @see com.badlogic.gdx.ApplicationListener#resize(int, int)
	 */
	@Override
	public void resize(int width, int height) {
	}

	/*
	 * used for pausing the game
	 * 
	 * (non-Javadoc)
	 * @see com.badlogic.gdx.ApplicationListener#pause()
	 */
	@Override
	public void pause() {
	}

	/*
	 * used for resuming the game
	 * 
	 * (non-Javadoc)
	 * @see com.badlogic.gdx.ApplicationListener#resume()
	 */
	
	@Override
	public void resume() {
	}
}
