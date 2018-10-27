package com.unicalday.core;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;

import com.unicalday.gui.Animation;
import com.unicalday.gui.Texture;
import com.unicalday.gui.TileMap;
import com.unicalday.logic.abstrclass.Direction;
import com.unicalday.logic.entity.Player;

public class GameState extends State {
		
	private Texture tex;
	
	private Animation playerIdleRight;
	private Animation playerIdleLeft;
	private Animation playerWalkRight;
	private Animation playerWalkLeft;
	private Animation playerJumpRight;
	private Animation playerJumpLeft;
	
	private Animation enemyWalkRight;
	private Animation enemyWalkLeft;
	
	private Animation coin;
	
	private TileMap tileMap;
	private Handler handler;

	private Player player;
	
	private Camera camera;
	
	private int coinCounter = 0;
	
	private String levelPath;
	
	public GameState(String path) {
		
		levelPath = path;
		
		//CREO LE TEXTURE
		tex = new Texture("Game");
		
		//CREO LE ANIMATIONS
		playerIdleRight = new Animation(3, tex.playerIdle[0], tex.playerIdle[1], tex.playerIdle[2], tex.playerIdle[3], tex.playerIdle[4], tex.playerIdle[5], tex.playerIdle[6], tex.playerIdle[7], tex.playerIdle[8], tex.playerIdle[9]);
		playerIdleLeft = new Animation(3, tex.playerIdle[10], tex.playerIdle[11], tex.playerIdle[12], tex.playerIdle[13], tex.playerIdle[14], tex.playerIdle[15], tex.playerIdle[16], tex.playerIdle[17], tex.playerIdle[18], tex.playerIdle[19]);
		
		playerWalkRight = new Animation(3, tex.playerWalk[0], tex.playerWalk[1], tex.playerWalk[2], tex.playerWalk[3], tex.playerWalk[4], tex.playerWalk[5], tex.playerWalk[6], tex.playerWalk[7], tex.playerWalk[8]);
		playerWalkLeft = new Animation(3, tex.playerWalk[9], tex.playerWalk[10], tex.playerWalk[11], tex.playerWalk[12], tex.playerWalk[13], tex.playerWalk[14],  tex.playerWalk[15],  tex.playerWalk[16],  tex.playerWalk[17]);
		
		playerJumpRight = new Animation(6, tex.playerJump[0], tex.playerJump[1], tex.playerJump[2], tex.playerJump[3], tex.playerJump[4]);
		playerJumpLeft = new Animation(6, tex.playerJump[5], tex.playerJump[6], tex.playerJump[7], tex.playerJump[8], tex.playerJump[9]);
		
		enemyWalkRight = new Animation(6, tex.enemy1[0], tex.enemy1[1], tex.enemy1[2], tex.enemy1[3], tex.enemy1[4], tex.enemy1[5], tex.enemy1[6], tex.enemy1[7]);
		enemyWalkLeft = new Animation(6, tex.enemy1[8], tex.enemy1[9], tex.enemy1[10], tex.enemy1[11], tex.enemy1[12], tex.enemy1[13], tex.enemy1[14], tex.enemy1[15]);

		coin = new Animation(5, tex.coin[0], tex.coin[1], tex.coin[2], tex.coin[3], tex.coin[4], tex.coin[5], tex.coin[6], tex.coin[7]);
		
		tileMap = new TileMap(levelPath, 128);
		handler = new Handler();
		player = tileMap.loadLevel(handler, player);

		handler.setPlayer(player);
		
		camera = new Camera(player.getX(), player.getY());
	}

	@Override
	public void tick() {
		
		playerIdleRight.runAnimnation();
		playerIdleLeft.runAnimnation();
		playerWalkRight.runAnimnation();
		playerWalkLeft.runAnimnation();
		playerJumpRight.runAnimnation();
		playerJumpLeft.runAnimnation();
		
		enemyWalkRight.runAnimnation();
		enemyWalkLeft.runAnimnation();
		
		coin.runAnimnation();
		
		camera.tick(player);
		
		handler.tick();
		
		//COLLISIONE DEL PLAYER CON I COIN
		for (int i = 0; i < handler.coinList.size(); i++) {
			if (player.getBounds().intersects(handler.coinList.get(i).getBounds())) {
				coinCounter++;
				handler.removeCoinObject(handler.coinList.get(i));
			}
		}
		
		//COMANDI DA TASTIERA
		if (State.getKeyboardInput().isKey(KeyEvent.VK_RIGHT) ) {
			player.setVelX(5);
		}
		else if (State.getKeyboardInput().isKey(KeyEvent.VK_LEFT)) {
			player.setVelX(-5);
		}
		else {
			player.setVelX(0);
		}
		if (State.getKeyboardInput().isKey(KeyEvent.VK_SPACE) && !player.isJumping() && player.getVelY() == 0)
		{
			player.setJumping(true);
			player.setVelY(-10);
		}
	}
	
	@Override
	public void render(Graphics g) {
		
		Graphics2D g2d = (Graphics2D) g;
		g2d.translate(camera.getX(), camera.getY());
			
			//for (int i = 0; i < bg.getWidth(null) * 5; i += bg.getWidth(null))
				//if(bg != null)
					//g.drawImage(bg, i, 0, null);
		
			//RENDER DEI BLOCK
			boolean blockRender = false;
			if (blockRender == false) {
				for (int row = 0; row < tileMap.getHeight(); row++) {
					for (int col = 0; col < tileMap.getWidth(); col++) {
						int rc = tileMap.getMap()[row][col];
						if (rc == 1 || rc == 2 || rc == 3 || rc == 4 || rc == 5 || rc == 6 || rc == 7 || rc == 8 || rc == 9 || rc == 10 || rc == 11 || rc == 12) {
							g.drawImage(tex.block[rc-1], col * 128, row * 128, 128, 128, null); 
						}
					}
				}
				blockRender = true;
			}
		
			//RENDER DEL PLAYER
			if (player.isJumping() == true) {
				if (player.getDirection() == Direction.IDLERIGHT)
					playerJumpRight.drawAnimation(g, (int) player.getX(), (int) player.getY());
				else if (player.getDirection() == Direction.IDLELEFT)
					playerJumpLeft.drawAnimation(g, (int) player.getX(), (int) player.getY());
			}	
			else {
				if (player.getVelX() != 0) {
					if (player.getDirection() == Direction.IDLERIGHT)
						playerWalkRight.drawAnimation(g, (int) player.getX(), (int) player.getY());
					else
						playerWalkLeft.drawAnimation(g, (int) player.getX(), (int) player.getY());
				}
				else {
					if (player.getDirection() == Direction.IDLERIGHT)
						playerIdleRight.drawAnimation(g, (int) player.getX(), (int) player.getY());
					else if (player.getDirection() == Direction.IDLELEFT)
						playerIdleLeft.drawAnimation(g, (int) player.getX(), (int) player.getY());
				}	
			}
		
			g.drawRect((int)player.getX(), (int) player.getY(), 84, 128);
			
			//RENDER ENEMYES
			for (int i = 0; i < handler.enemyList.size(); i++) {
				if ((handler.enemyList.get(i).getDirection() == Direction.IDLERIGHT))
					enemyWalkRight.drawAnimation(g, (int) handler.enemyList.get(i).getX(), (int) handler.enemyList.get(i).getY());
				else
					enemyWalkLeft.drawAnimation(g, (int) handler.enemyList.get(i).getX(), (int) handler.enemyList.get(i).getY());
			}
			
			//RENDER COIN
			for (int i = 0; i < handler.getCoinList().size(); i++) {
					coin.drawAnimation(g, (int) handler.getCoinList().get(i).getX(), (int) handler.getCoinList().get(i).getY());
			}
			
		g2d.translate(-camera.getX(), -camera.getY());
	}
	
}
