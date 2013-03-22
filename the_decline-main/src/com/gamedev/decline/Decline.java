package com.gamedev.decline;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
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
	// the background of the game
	private RepeatingBackground background;
	//the singleton that holds the global variables for the game
	private GlobalSingleton gs = GlobalSingleton.getInstance();
	//the bullet manager of the game
	private BulletManager bm;
	// the manager of the ammo objects for the game
	private AmmoManager am;
	
	private EnemyManager em;
	

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
		
		em = new EnemyManager(new Texture(Gdx.files.internal("data/enemy.gif")));
		
		character = new Hero(new Texture(Gdx.files.internal("hero_weapon.png")),bm, em);
		character.setOrigin(character.getWidth()/2, character.getHeight()/2);
		character.setPosition(gs.getStartingHeroXPos(), gs.getStartingHeroYPos());
		
		background = new RepeatingBackground(new Texture(Gdx.files.internal("data/cave.jpg")));
        //am = new AmmoManager(new Texture(Gdx.files.internal("data/ammo.jpg")));
	}

	/*
	 * Used to get rid of objects after the game has finished
	 * 
	 * This method probably needs to be updated
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
		em.draw(batch);
		//am.draw(batch);
		batch.end();

		update();
		
	}

	/*
	 * updates the scene
	 */
	private void update() {
		
		character.update();
		//am.update();
		bm.update();
		em.update();
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
