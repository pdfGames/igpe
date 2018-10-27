package com.unicalday.gui;

import java.awt.image.BufferedImage;

public class Texture {
	
	SpriteSheet ps, bs, es, cs, ms;
	
	public BufferedImage[] menuSheet = new BufferedImage[2];

	private BufferedImage playerSheet = null;
	private BufferedImage blockSheet = null;
	private BufferedImage enemySheet1 = null;
	private BufferedImage coinSheet = null;
	
	public BufferedImage[] playerIdle = new BufferedImage[20];
	public BufferedImage[] playerWalk = new BufferedImage[18];
	public BufferedImage[] playerJump = new BufferedImage[20];

	public BufferedImage[] block = new BufferedImage[12];

	public BufferedImage[] enemy1 = new BufferedImage[16];
	
	public BufferedImage[] coin = new BufferedImage[8];
	
	BufferedImageLoader loader = new BufferedImageLoader();
	
	public Texture(String id) {
		if (id == "Menu") {
			
			try {
				menuSheet[0] = loader.loadBufferedImage("/menubg.png");
				menuSheet[1] = loader.loadBufferedImage("/button.png");

			}
			catch(Exception e) {
				e.printStackTrace();
			}
			getMenuTextures();
		}
		else {	
			try {
				playerSheet = loader.loadBufferedImage("/player_sheet.png");
				blockSheet = loader.loadBufferedImage("/block_sheet.png");
				enemySheet1 = loader.loadBufferedImage("/enemy1_sheet.png");
				coinSheet = loader.loadBufferedImage("/coin_sheet.png");
			}
			catch(Exception e) {
				e.printStackTrace();
			}
			ps = new SpriteSheet(playerSheet);
			bs = new SpriteSheet(blockSheet);
			es = new SpriteSheet(enemySheet1);
			cs = new SpriteSheet(coinSheet);
		
			getGameTextures();
		}
	}

	private void getMenuTextures() {

	}

	private void getGameTextures() {
		//IDLERIGHT
		playerIdle[0] = ps.subImage(1, 1, 84, 128);
		playerIdle[1] = ps.subImage(2, 1, 84, 128);
		playerIdle[2] = ps.subImage(3, 1, 84, 128);
		playerIdle[3] = ps.subImage(4, 1, 84, 128);
		playerIdle[4] = ps.subImage(5, 1, 84, 128);
		playerIdle[5] = ps.subImage(6, 1, 84, 128);
		playerIdle[6] = ps.subImage(7, 1, 84, 128);
		playerIdle[7] = ps.subImage(8, 1, 84, 128);
		playerIdle[8] = ps.subImage(9, 1, 84, 128);
		playerIdle[9] = ps.subImage(10, 1, 84, 128);
		
		//IDLE LEFT
		playerIdle[10] = ps.subImage(1, 2, 84, 128);
		playerIdle[11] = ps.subImage(2, 2, 84, 128);
		playerIdle[12] = ps.subImage(3, 2, 84, 128);
		playerIdle[13] = ps.subImage(4, 2, 84, 128);
		playerIdle[14] = ps.subImage(5, 2, 84, 128);
		playerIdle[15] = ps.subImage(6, 2, 84, 128);
		playerIdle[16] = ps.subImage(7, 2, 84, 128);
		playerIdle[17] = ps.subImage(8, 2, 84, 128);
		playerIdle[18] = ps.subImage(9, 2, 84, 128);
		playerIdle[19] = ps.subImage(10, 2, 84, 128);
		
		//RIGHT
		playerWalk[0] = ps.subImage(1, 3, 84, 128);
		playerWalk[1] = ps.subImage(2, 3, 84, 128);
		playerWalk[2] = ps.subImage(3, 3, 84, 128);
		playerWalk[3] = ps.subImage(4, 3, 84, 128);
		playerWalk[4] = ps.subImage(5, 3, 84, 128);
		playerWalk[5] = ps.subImage(6, 3, 84, 128);
		playerWalk[6] = ps.subImage(7, 3, 84, 128);
		playerWalk[7] = ps.subImage(8, 3, 84, 128);
		playerWalk[8] = ps.subImage(9, 3, 84, 128);
		
		//LEFT
		playerWalk[9] = ps.subImage(1, 4, 84, 128);
		playerWalk[10] = ps.subImage(2, 4, 84, 128);
		playerWalk[11] = ps.subImage(3, 4, 84, 128);
		playerWalk[12] = ps.subImage(4, 4, 84, 128);
		playerWalk[13] = ps.subImage(5, 4, 84, 128);
		playerWalk[14] = ps.subImage(6, 4, 84, 128);
		playerWalk[15] = ps.subImage(7, 4, 84, 128);
		playerWalk[16] = ps.subImage(8, 4, 84, 128);
		playerWalk[17] = ps.subImage(9, 4, 84, 128);
		
		//JUMPRIGHT
		playerJump[0] = ps.subImage(1, 5, 84, 128);
		playerJump[1] = ps.subImage(2, 5, 84, 128);
		playerJump[2] = ps.subImage(3, 5, 84, 128);
		playerJump[3] = ps.subImage(4, 5, 84, 128);
		playerJump[4] = ps.subImage(5, 5, 84, 128);
		
		//JUMPLEFT
		playerJump[5] = ps.subImage(1, 6, 84, 128);
		playerJump[6] = ps.subImage(2, 6, 84, 128);
		playerJump[7] = ps.subImage(3, 6, 84, 128);
		playerJump[8] = ps.subImage(4, 6, 84, 128);
		playerJump[9] = ps.subImage(5, 6, 84, 128);
		
		/////////////////////////////////////////////
		
		//BLOCK 1
		block[0] = bs.subImage(1, 1, 128, 128);
		block[1] = bs.subImage(2, 1, 128, 128);
		block[2] = bs.subImage(3, 1, 128, 128);
		
		block[3] = bs.subImage(1, 2, 128, 128);
		block[4] = bs.subImage(2, 2, 128, 128);
		block[5] = bs.subImage(3, 2, 128, 128);
		
		block[6] = bs.subImage(1, 3, 128, 128);
		block[7] = bs.subImage(2, 3, 128, 128);
		block[8] = bs.subImage(3, 3, 128, 128);
		
		//BLOCK2
		block[9] = bs.subImage(1, 4, 128, 128);
		block[10] = bs.subImage(2, 4, 128, 128);
		block[11] = bs.subImage(3, 4, 128, 128);
		
		//ENEMY1RIGHT
		enemy1[0] = es.subImage(1, 1, 91, 128);
		enemy1[1] = es.subImage(2, 1, 91, 128);
		enemy1[2] = es.subImage(3, 1, 91, 128);
		enemy1[3] = es.subImage(4, 1, 91, 128);
		enemy1[4] = es.subImage(5, 1, 91, 128);
		enemy1[5] = es.subImage(6, 1, 91, 128);
		enemy1[6] = es.subImage(7, 1, 91, 128);
		enemy1[7] = es.subImage(8, 1, 91, 128);
		
		//ENEMY1LEFT
		enemy1[8] = es.subImage(1, 2, 91, 128);
		enemy1[9] = es.subImage(2, 2, 91, 128);
		enemy1[10] = es.subImage(3, 2, 91, 128);
		enemy1[11] = es.subImage(4, 2, 91, 128);
		enemy1[12] = es.subImage(5, 2, 91, 128);
		enemy1[13] = es.subImage(6, 2, 91, 128);
		enemy1[14] = es.subImage(7, 2, 91, 128);
		enemy1[15] = es.subImage(8, 2, 91, 128);
		
		//COIN
		coin[0] = cs.subImage(1, 1, 41, 42);
		coin[1] = cs.subImage(2, 1, 41, 42);
		coin[2] = cs.subImage(3, 1, 41, 42);
		
		coin[3] = cs.subImage(4, 1, 41, 42);
		coin[4] = cs.subImage(5, 1, 41, 42);
		coin[5] = cs.subImage(6, 1, 41, 42);

		coin[6] = cs.subImage(7, 1, 41, 42);
		coin[7] = cs.subImage(8, 1, 41, 42);		
	}

}
