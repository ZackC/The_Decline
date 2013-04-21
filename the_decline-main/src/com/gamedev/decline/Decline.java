// Package Declaration //
package com.gamedev.decline;

// Java Package Support //
import java.util.Iterator;

// Badlogic Package Support //
import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.TimeUtils;

/**
 * com/gamedev/decline/Decline.java
 * 
 * @author(s) : Ian Middleton, Zach Coker, Zach Ogle
 * @version : 2.0 Last Update : 3/25/2013 Update By : Ian Middleton
 * 
 * Source code for the Decline class. The Decline class takes care of
 *	essentially "running" the entire game. All high-level game elements
 *	in addition to the camera, background, sounds and batch are instantiated
 *	and executed here.
 * 
 */
public class Decline implements ApplicationListener
{
	// Global Singleton //
	private GlobalSingleton gs = GlobalSingleton.getInstance();

	// Constants //
	public static final int HEALTH_PACK = 5;
	public static final int ENEMY_DAMAGE = 20;
	public static final int ENEMY_SHOT_DAMAGE = 10;
	public static final int LIGHT_TIME_SECONDS = 40;
	public static final int LIGHT_TRANSITION_SECONDS = 5;
	public static final int TIME_TILL_NEXT_HORN = 4;
	public static final long TIME_BETWEEN_ENEMIES = 5 * 100000000L;

	// Internal Variables //
	private OrthographicCamera camera;
	private SpriteBatch batch;
	private RepeatingBackground background;
	private Hero hero;
	private BulletManager bm;
	private EnemyManager em;
	private ItemManager im;
	private BossManager bsm;
	private boolean shoot = false, ableToShoot = true, bossFight = false;
	private boolean isNight = false, isTransitioning = false;
	private Music jungleMusic, tribalMusic;
	private Sound heroHitSound, enemyHitSound, bulletShotSound, itemPickUpSound;
	private Sound heroDyingSound, gameOverSound, hornSound;
	private Sprite gameOverSprite, gameWinSprite;
	private long lastDayLightChangeTime = TimeUtils.nanoTime(), lastHornTime = 0;
	private long timeSinceLastEnemyAppeared = 0;
	private int enemiesToAppear = 0;
	

	/**
	 * Function run when the game is started. Basically a high-level constructor
	 * for the game.
	 * 
	 * @see com.badlogic.gdx.ApplicationListener#create()
	 */
	@Override
	public void create()
	{
		float width = Gdx.graphics.getWidth();
		float height = Gdx.graphics.getHeight();

		Gdx.app.log("Screen Width", String.valueOf(width));
		Gdx.app.log("Screen Height", String.valueOf(height));

		camera = new OrthographicCamera(width, height);
		camera.setToOrtho(false);

		jungleMusic = Gdx.audio.newMusic(Gdx.files.internal("jungle_noise.mp3"));
		jungleMusic.setLooping(true);
		jungleMusic.setVolume((float) 0.1);
		jungleMusic.play();
		
		tribalMusic = Gdx.audio.newMusic(Gdx.files.internal("tribal-noises.wav"));
		tribalMusic.setLooping(true);
		tribalMusic.setVolume(1);
		tribalMusic.play();
		

		heroHitSound = Gdx.audio.newSound(Gdx.files.internal("hero_hit.mp3"));
		enemyHitSound = Gdx.audio.newSound(Gdx.files.internal("enemy_hit.wav"));
		bulletShotSound = Gdx.audio.newSound(Gdx.files.internal("shotgun.wav"));
		itemPickUpSound = Gdx.audio.newSound(Gdx.files.internal("bloop.wav"));
		heroDyingSound = Gdx.audio.newSound(Gdx.files.internal("dying-noise.wav"));
		gameOverSound = Gdx.audio.newSound(Gdx.files.internal("game-over-sad.wav"));
        hornSound = Gdx.audio.newSound(Gdx.files.internal("warning-horn.wav"));
		
		batch = new SpriteBatch();

		hero = new Hero(new Texture(Gdx.files.internal("hero_weapon.png")), new Texture(Gdx.files.internal("hero_crouch.png")),
				new Texture(Gdx.files.internal("heart.png")), new Texture(Gdx.files.internal("hero_rock.png")), heroDyingSound);
		hero.setOrigin(hero.getWidth() / 2, hero.getHeight() / 2);
		hero.setToInitialDrawPosition();

		bm = new BulletManager(new Texture(Gdx.files.internal("bullets.png")));

		em = new EnemyManager(new Texture(Gdx.files.internal("enemy.png")), new Texture(Gdx.files.internal("enemy_falconeer.png")), new Texture(Gdx.files.internal("enemy_falconeer_noFalcon.png")), new Texture(Gdx.files.internal("hawk.png")));

		bsm = new BossManager(new Texture(Gdx.files.internal("boss.png")), new Texture(Gdx.files.internal("fireball.png")));
		
		gameOverSprite = new Sprite(new Texture(Gdx.files.internal("game_over.png")));
		gameWinSprite = new Sprite(new Texture(Gdx.files.internal("Win_screen.png")));
		
		background = new RepeatingBackground(new Texture(Gdx.files.internal("background.png")));
		im = new ItemManager(new Texture(Gdx.files.internal("ammoBox.png")), new Texture(Gdx.files.internal("healthKit.png")),
				new Texture(Gdx.files.internal("bush.png")));
		gs.setHealthBarManager(new HealthBarManager());
		gs.getHealthBarManager().add(hero);
	}

	/**
	 * Cleans up all resources when the game is closed.
	 * 
	 * @see com.badlogic.gdx.ApplicationListener#dispose()
	 */
	@Override
	public void dispose()
	{
		batch.dispose();
		jungleMusic.dispose();
		enemyHitSound.dispose();
		heroHitSound.dispose();
		bulletShotSound.dispose();
		itemPickUpSound.dispose();
		tribalMusic.dispose();
		heroDyingSound.dispose();
		gameOverSound.dispose();
		hornSound.dispose();
	}

	/**
	 * Calls the draw functions for all objects and then calls the update
	 * function.
	 * 
	 * @see com.badlogic.gdx.ApplicationListener#render()
	 */
	@Override
	public void render()
	{
		if(!gs.getIsGameOver())
		{
			handleEvent();
			handleCollision();
			update();
		}

		Gdx.gl.glClearColor(1, 1, 1, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		batch.setProjectionMatrix(camera.combined);
		if(!gs.getIsGameOver())
		{
			if(isTransitioning)
			{
				if(TimeUtils.nanoTime() > (lastDayLightChangeTime + (LIGHT_TRANSITION_SECONDS * 1000000000L)))
				{
					isNight = !isNight;
					isTransitioning = false;
					lastDayLightChangeTime = TimeUtils.nanoTime();
				}
			}
			else
			{
				if(TimeUtils.nanoTime() > (lastDayLightChangeTime + (LIGHT_TIME_SECONDS * 1000000000L)))
				{
					isTransitioning = true;
					lastDayLightChangeTime = TimeUtils.nanoTime();
				}
			}
			
			batch.begin();
			if(isTransitioning)
			{
				batch.setColor(Color.LIGHT_GRAY);
			}
			else
			{
				if(isNight)
				{
					batch.setColor(Color.GRAY);
				}
				else
				{
					batch.setColor(Color.WHITE);
				}
			}
			background.draw(batch);
			if (gs.getIsHeroAlive())
			{
				hero.draw(batch);
			}
			bm.draw(batch);
			em.draw(batch);
			im.draw(batch);
			
			if(bossFight)
			{
				bsm.draw(batch);
			}
			
			hero.drawAmmoCount(batch);
			hero.drawLives(batch);
			hero.drawRockPower(batch);
			batch.end();
			gs.getHealthBarManager().draw();
		}
		else
		{
			handleGameOver();
		}
		
		if(gs.getIsGameWon())
		{
			handleGameWin();
		}
	}

	/**
	 * Handles all keyboard events in the game.
	 */
	private void handleEvent()
	{
		if (gs.getIsHeroHiding() || gs.getIsHeroTryingToHide())
		{
			if (!Gdx.input.isKeyPressed(Keys.DOWN))
			{
				hero.stand();
			}
			else
			{
				gs.setHeroMovement(0);
			}
		}
		else
		{
			if (Gdx.input.isKeyPressed(Keys.LEFT))
			{
				if(bossFight)
				{
					hero.moveLeft();
				}
				else
				{
					hero.moveLeftScroll();
				}
				if (gs.getHeroOrientation() == GlobalSingleton.RIGHT)
				{
					gs.setHeroOrientation(GlobalSingleton.LEFT);
				}
				gs.setHeroMovement(-Hero.SPEED);
			}
			else if (Gdx.input.isKeyPressed(Keys.RIGHT))
			{
				if(bossFight)
				{
					hero.moveRight();
				}
				else
				{
					hero.moveRightScroll();
				}
				if (gs.getHeroOrientation() == GlobalSingleton.LEFT)
				{
					gs.setHeroOrientation(GlobalSingleton.RIGHT);
				}
				gs.setHeroMovement(Hero.SPEED);
			}
			else
			{
				gs.setHeroMovement(0);
			}
			if (Gdx.input.isKeyPressed(Keys.UP) && !gs.getIsHeroJumping())
			{
				gs.setIsHeroJumping(true);
			}
			if (Gdx.input.isKeyPressed(Keys.DOWN))
			{
				hero.hide(im.getActiveBush());
			}
			if (Gdx.input.isKeyPressed(Keys.SPACE) && ableToShoot)
			{
				shoot = true;
				ableToShoot = false;
			}
		}

		if (shoot == true)
		{
			bm.shootBullet();
			bulletShotSound.play();
			hero.setAmmo(hero.getAmmo() - 1);
			if (hero.getAmmo() == 0)
			{
				ableToShoot = false;
			}
			shoot = false;
			if(!isNight)
			{
				if(TimeUtils.nanoTime() > lastHornTime + TIME_TILL_NEXT_HORN * 1000000000L)
				{
					hornSound.play();
					lastHornTime = TimeUtils.nanoTime();
					enemiesToAppear = 3;
				}
			}
		}

		if (!(Gdx.input.isKeyPressed(Keys.SPACE)) && hero.getAmmo() != 0)
		{
			ableToShoot = true;
		}
		
		if(!bossFight && gs.getHeroXPos() < 9000)
		{
			if(enemiesToAppear > 0 && TimeUtils.nanoTime() > timeSinceLastEnemyAppeared + TIME_BETWEEN_ENEMIES)
			{
				em.makeEnemyAppear();
				enemiesToAppear = enemiesToAppear - 1;
				timeSinceLastEnemyAppeared = TimeUtils.nanoTime();
			}
		}
	}

	/**
	 * Handles all collisions between CollidableObjects.
	 */
	private void handleCollision()
	{
		Array<Bullet> activeBullets = bm.getActiveBullets();
		Array<Enemy> activeEnemies = em.getActiveEnemies();
		Array<Falconer> activeFalconers = em.getActiveFalconers();
		Array<Ammo> activeAmmo = im.getActiveAmmo();
		Array<HealthPack> activeHealthPacks = im.getActiveHealthPacks();
		Array<Fireball> activeFireballs = bsm.getActiveFireballs();
		Boss boss = bsm.getBoss();
		
		if(bossFight)
		{
			int bulletCount = 0;
			boolean finished = false, collision = false;
			Iterator<Bullet> bulletIter;
			Bullet currentBullet;
			while (!finished)
			{
				bulletIter = activeBullets.iterator();
				for (int i = bulletCount; i > 0; i--)
				{
					bulletIter.next();
				}
				while (bulletIter.hasNext())
				{
					currentBullet = bulletIter.next();
					bulletCount++;
					if (currentBullet.collidesWith(boss))
					{
						bulletIter.remove();
						bulletCount--;
						bsm.bossDamagedEvent(ENEMY_SHOT_DAMAGE);
						enemyHitSound.play();
						collision = true;
						break;
					}
				}
				if (!collision && !bulletIter.hasNext())
				{
					finished = true;
				}
				collision = false;
			}
			
			if (!gs.getIsHeroHiding())
			{
				int fireballCount = 0;
				finished = false;
				Iterator<Fireball> fireballIter;
				Fireball currentFireball;
				while (!finished)
				{
					fireballIter = activeFireballs.iterator();
					for (int i = fireballCount; i > 0; i--)
					{
						fireballIter.next();
					}
					while (fireballIter.hasNext())
					{
						currentFireball = fireballIter.next();
						fireballCount++;
						if (hero.collidesWith(currentFireball))
						{
							fireballIter.remove();
							fireballCount--;
							hero.setHealth(hero.getHealth() - ENEMY_DAMAGE);
							if (hero.getHealth() < 0)
							{
								hero.setHealth(0);
							}
							heroHitSound.play();
							collision = false;
							break;
						}
					}
					if (!collision && !fireballIter.hasNext())
					{
						finished = true;
					}
					collision = false;
				}
			}
			if(hero.collidesWith(boss))
			{
				gs.setIsGameOver(true);
			}
		}
		else
		{
			int bulletCount = 0, enemyCount = 0;
			boolean finished = false, collision = false;
			Iterator<Bullet> bulletIter;
			Iterator<Enemy> enemyIter;
			Bullet currentBullet;
			Enemy currentEnemy;
			while (!finished)
			{
				bulletIter = activeBullets.iterator();
				enemyIter = activeEnemies.iterator();
				for (int i = bulletCount; i > 0; i--)
				{
					bulletIter.next();
				}
				for (int j = enemyCount; j > 0; j--)
				{
					enemyIter.next();
				}
				while (bulletIter.hasNext())
				{
					currentBullet = bulletIter.next();
					bulletCount++;
					while (enemyIter.hasNext())
					{
						currentEnemy = enemyIter.next();
						enemyCount++;
						if (currentBullet.collidesWith(currentEnemy))
						{
							bulletIter.remove();
							bulletCount--;
							if (em.enemyDamagedEvent(currentEnemy, ENEMY_SHOT_DAMAGE))
							{
								enemyIter.remove();
								enemyCount--;
							}
							collision = true;
							break;
						}
					}
					if (collision)
					{
						break;
					}
					if (!enemyIter.hasNext())
					{
						enemyIter = activeEnemies.iterator();
						enemyCount = 0;
					}
				}
				if (!collision && !bulletIter.hasNext())
				{
					finished = true;
				}
				collision = false;
			}
			
			int falconerCount = 0;
			bulletCount = 0;
			Iterator<Falconer> falconerIter = activeFalconers.iterator();
			Falconer currentFalconer;
			finished = false;
			while (!finished)
			{
				bulletIter = activeBullets.iterator();
				falconerIter = activeFalconers.iterator();
				for (int i = bulletCount; i > 0; i--)
				{
					bulletIter.next();
				}
				for (int j = falconerCount; j > 0; j--)
				{
					falconerIter.next();
				}
				while (bulletIter.hasNext())
				{
					currentBullet = bulletIter.next();
					bulletCount++;
					while (falconerIter.hasNext())
					{
						currentFalconer = falconerIter.next();
						falconerCount++;
						if (currentBullet.collidesWith(currentFalconer))
						{
							bulletIter.remove();
							bulletCount--;
							if (em.falconerDamagedEvent(currentFalconer, ENEMY_SHOT_DAMAGE))
							{
								falconerIter.remove();
								falconerCount--;
							}
							collision = true;
							break;
						}
					}
					if (collision)
					{
						break;
					}
					if (!falconerIter.hasNext())
					{
						falconerIter = activeFalconers.iterator();
						falconerCount = 0;
					}
				}
				if (!collision && !bulletIter.hasNext())
				{
					finished = true;
				}
				collision = false;
			}
			
			if (!gs.getIsHeroHiding())
			{
				enemyCount = 0;
				finished = false;
				while (!finished)
				{

					enemyIter = activeEnemies.iterator();
					for (int i = enemyCount; i > 0; i--)
					{
						enemyIter.next();
					}
					while (enemyIter.hasNext())
					{
						currentEnemy = enemyIter.next();
						enemyCount++;
						if (hero.collidesWith(currentEnemy))
						{
							hero.setHealth(hero.getHealth() - ENEMY_DAMAGE);
							if (hero.getHealth() < 0)
							{
								hero.setHealth(0);
							}
							em.enemyDamagedEvent(currentEnemy, currentEnemy.getMaxHealth());
							enemyIter.remove();
							enemyCount--;
							heroHitSound.play();
							collision = true;
							break;
						}
					}
					if (!collision && !enemyIter.hasNext())
					{
						finished = true;
					}
					collision = false;
				}
				
				falconerCount = 0;
				finished = false;
				while (!finished)
				{
					falconerIter = activeFalconers.iterator();
					for (int i = falconerCount; i > 0; i--)
					{
						falconerIter.next();
					}
					while (falconerIter.hasNext())
					{
						currentFalconer = falconerIter.next();
						falconerCount++;
						if (hero.collidesWith(currentFalconer))
						{
							hero.setHealth(hero.getHealth() - ENEMY_DAMAGE);
							if (hero.getHealth() < 0)
							{
								hero.setHealth(0);
							}
							em.falconerDamagedEvent(currentFalconer, currentFalconer.getMaxHealth());
							falconerIter.remove();
							falconerCount--;
							heroHitSound.play();
							collision = true;
							break;
						}
					}
					if (!collision && !falconerIter.hasNext())
					{
						finished = true;
					}
					collision = false;
				}
				
				finished = false;
				Falcon currentFalcon;
				falconerIter = activeFalconers.iterator();
				while (falconerIter.hasNext())
				{
					currentFalconer = falconerIter.next();
					currentFalcon = currentFalconer.getFalcon();
					if (!currentFalcon.getHasDamagedHero() && hero.collidesWith(currentFalcon))
					{
						hero.setHealth(hero.getHealth() - ENEMY_DAMAGE / 2);
						if (hero.getHealth() < 0)
						{
							hero.setHealth(0);
						}
						heroHitSound.play();
						currentFalcon.setHasDamagedHero(true);
					}
				}
			}
			
			int ammoCount = 0;
			Iterator<Ammo> ammoIter;
			Ammo currentAmmo;
			finished = false;
			while (!finished)
			{
				ammoIter = activeAmmo.iterator();
				for (int i = ammoCount; i > 0; i--)
				{
					ammoIter.next();
				}
				while (ammoIter.hasNext())
				{
					currentAmmo = ammoIter.next();
					ammoCount++;
					if (hero.collidesWith(currentAmmo))
					{
						hero.setAmmo(hero.getAmmo() + currentAmmo.getAmountOfAmmoStored());
						ableToShoot = true;
						if (hero.getAmmo() > Hero.MAX_AMMO)
						{
							hero.setAmmo(Hero.MAX_AMMO);
						}
						ammoIter.remove();
						ammoCount--;
						itemPickUpSound.play();
						collision = true;
						break;
					}
				}
				if (!collision && !ammoIter.hasNext())
				{
					finished = true;
				}
				collision = false;
			}
			
			int healthCount = 0;
			Iterator<HealthPack> packIter;
			HealthPack currentPack;
			finished = false;
			while (!finished)
			{
				packIter = activeHealthPacks.iterator();
				for (int i = healthCount; i > 0; i--)
				{
					packIter.next();
				}
				while (packIter.hasNext())
				{
					currentPack = packIter.next();
					healthCount++;
					if (hero.collidesWith(currentPack))
					{
						hero.setHealth(hero.getHealth() + currentPack.getAmountOfHealth());
						if (hero.getHealth() > Hero.MAX_HEALTH)
						{
							hero.setHealth(Hero.MAX_HEALTH);
						}
						packIter.remove();
						healthCount--;
						itemPickUpSound.play();
						collision = true;
						break;
					}
				}
				if (!collision && !packIter.hasNext())
				{
					finished = true;
				}
				collision = false;
			}
		}
	}

	/**
	 * Calls the update functions for all objects.
	 */
	private void update()
	{
		hero.update();
		bm.update();
		if(bossFight)
		{
			bsm.update();
		}
		else
		{
			em.update();
			im.update();
		}
		gs.getHealthBarManager().update();
		if(gs.getHeroXPos() > 10000)
		{
			bossFight = true;
			Array<Falconer> activeFalconers = em.getActiveFalconers();
			activeFalconers.clear();
		}
	}

	/**
	 * Function used for resizing of the game.
	 * 
	 * (non-Javadoc)
	 * 
	 * @see com.badlogic.gdx.ApplicationListener#resize(int, int)
	 * 
	 *      *** INCOMPLETE ***
	 */
	@Override
	public void resize(int width, int height)
	{
	}

	/**
	 * Function used for pausing of the game.
	 * 
	 * (non-Javadoc)
	 * 
	 * @see com.badlogic.gdx.ApplicationListener#pause()
	 * 
	 *      *** INCOMPLETE ***
	 */
	@Override
	public void pause()
	{
	}

	/**
	 * Function used for resuming of the game
	 * 
	 * (non-Javadoc)
	 * 
	 * @see com.badlogic.gdx.ApplicationListener#resume()
	 * 
	 *      *** INCOMPLETE ***
	 */
	@Override
	public void resume()
	{
	}
	
	/**
	 * Handles the game being over and the player losing.
	 */
	private void handleGameOver()
	{
		gameOverSound.play();
		batch.begin();
		background.draw(batch);
		batch.draw(gameOverSprite, 0, 0);
		batch.end();
	}
	
	/**
	 * Handles the game being over and the player winning.
	 */
	private void handleGameWin()
	{
		batch.begin();
		background.draw(batch);
		batch.draw(gameWinSprite, 0, 0);
		batch.end();
	}
}