package com.gamedev.decline;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Decline implements ApplicationListener {
	private OrthographicCamera camera;
	private SpriteBatch batch;
	private Sprite character;
	
	@Override
	public void create() {		
		float width = Gdx.graphics.getWidth();
		float height = Gdx.graphics.getHeight();
		
		Gdx.app.log("Screen Width", String.valueOf(width));
		Gdx.app.log("Screen Height", String.valueOf(height));
		
		camera = new OrthographicCamera(width, height);
		batch = new SpriteBatch();
		
		character = new Sprite(new Texture(Gdx.files.internal("hero_weapon.png")));
		character.setOrigin(character.getWidth()/2, character.getHeight()/2);
		character.setPosition(-character.getWidth()/2, -character.getHeight()/2);
		
		Gdx.app.log("Character Width", String.valueOf(character.getWidth()));
		Gdx.app.log("Character Height", String.valueOf(character.getHeight()));
		
	}

	@Override
	public void dispose() {
		batch.dispose();
	}

	@Override
	public void render() {		
		Gdx.gl.glClearColor(1, 1, 1, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
		batch.setProjectionMatrix(camera.combined);
		batch.begin();
		character.draw(batch);
		batch.end();
		
		update();
	}
	
	private void update() {
		
	}

	@Override
	public void resize(int width, int height) {
	}

	@Override
	public void pause() {
	}

	@Override
	public void resume() {
	}
}
