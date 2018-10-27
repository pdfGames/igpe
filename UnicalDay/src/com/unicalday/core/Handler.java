package com.unicalday.core;

import java.awt.Graphics;
import java.util.LinkedList;

import com.unicalday.logic.abstrclass.AbstractStaticObject;
import com.unicalday.logic.entity.Coin;
import com.unicalday.logic.entity.Enemy;
import com.unicalday.logic.entity.Player;

public class Handler {
	
	//BLOCK
	public LinkedList<AbstractStaticObject> blockList = new LinkedList<AbstractStaticObject>();
	//ENEMY
	public LinkedList<Enemy> enemyList = new LinkedList<Enemy>();
	//COIN
	public LinkedList<AbstractStaticObject> coinList = new LinkedList<AbstractStaticObject>();
	//PLAYER
	private Player player;
	
	public Handler() { }
	
	public LinkedList<AbstractStaticObject> getBlockList() { return blockList; }
	public LinkedList<Enemy> getEnemyList() { return enemyList; }
	public LinkedList<AbstractStaticObject> getCoinList() { return coinList; }
	public void setPlayer(Player player) { this.player = player; }
	
	public void tick() {
		player.tick();
		for (int i = 0; i < enemyList.size(); i++) {
			enemyList.get(i).tick();
		}
		//COLLISIONE DEL PLAYER CON I BLOCK
		for(int i = 0; i < blockList.size(); i++) {					
			if (player.getBounds().intersects(blockList.get(i).getBounds())) {
				player.setY(blockList.get(i).getY() - player.getHeight());
				player.setVelY(0);
				player.setFalling(false);
				player.setJumping(false);
			}
			else {
				player.setFalling(true); 
			}
			if (player.getBoundsTop().intersects(blockList.get(i).getBounds())) {
				player.setY(blockList.get(i).getY() + blockList.get(i).getHeight());
				player.setVelY(0);
				removeBlockObject(blockList.get(i));
			}
			if (player.getBoundsRight().intersects(blockList.get(i).getBounds())) {
				player.setX(blockList.get(i).getX() - player.getWidth());
			}
			if (player.getBoundsLeft().intersects(blockList.get(i).getBounds())) {
				player.setX(blockList.get(i).getX() + blockList.get(i).getWidth() + 3);
			}
		}
		//COLLISIONE DEL PLAYER CON GLI ENEMYES
		for (int i = 0; i < enemyList.size(); i++) {
			if (player.getBoundsTop().intersects(enemyList.get(i).getBounds())) {
				//player.setY(enemy.get(i).getY() + enemy.get(i).getHeight());
				//player.setVelY(0);
				//removeStaticblockList(enemy.get(i));
			}
			if (player.getBoundsLeft().intersects(enemyList.get(i).getBoundsRight())) {
				enemyList.get(i).setVelX(-3);
			}
			if (player.getBoundsRight().intersects(enemyList.get(i).getBoundsLeft())) {
				enemyList.get(i).setVelX(3);
			}
			if (player.getBounds().intersects(enemyList.get(i).getBoundsTop())) {
				//player.setY(enemy.get(i).getY() - player.getHeight());
				player.setVelY(-3);
				enemyList.remove(i);
			}
		}
		//COLLISIONE DEGLI ENEMYES CON I BLOCK
		for(int i = 0; i < blockList.size(); i++) {
			for (int j = 0; j < enemyList.size(); j++) {
				if (enemyList.get(j).getBounds().intersects(blockList.get(i).getBounds())) {
					enemyList.get(j).setY(blockList.get(i).getY() - player.getHeight());
					enemyList.get(j).setVelY(0);

					enemyList.get(j).setFalling(false);
					enemyList.get(j).setJumping(false);
					//SE I BLOCCHI CON IL QUALE IL NEMICO COLLIDE CORRISPONDONO AI CODICI VIENE CAMBIATA LA DIREZIONE E VICEVERSA
					if (blockList.get(i).getCode() == 1 || blockList.get(i).getCode() == 10 )
						enemyList.get(j).setVelX(3);
					else if (blockList.get(i).getCode() == 3 || blockList.get(i).getCode() == 12)
						enemyList.get(j).setVelX(-3);
				}
				else{
					enemyList.get(j).setFalling(true);
				}
				
				if (enemyList.get(j).getBoundsRight().intersects(blockList.get(i).getBounds())) {
					enemyList.get(j).setVelX(-3);		
				}
				if (enemyList.get(j).getBoundsLeft().intersects(blockList.get(i).getBounds())) {
					enemyList.get(j).setVelX(3);
				}
				if (enemyList.get(j).getBounds().intersects(player.getBounds())) {
					//enemy.get(j).setVelX(0);
					//Eliminare una vita
				}
			}
		}
	}
	
	public void render(Graphics g) {
		for (int i = 0; i < blockList.size(); i++) {
			//tempObject = object.get(i);
			//tempObject.render(g);
		//	if (camera.getX() >= 0 && camera.getX() <= 800)
//			if ((object.get(i).getX() >= player.getX() - 700 && object.get(i).getX() <= player.getX() + 700) && 
//					(object.get(i).getY() >= player.getY() - 700 && object.get(i).getX() <= player.getX() + 700))
//			{
//				if (object.get(i) instanceof Coin)
//					g.setColor(Color.white);
//					g.drawRect((int) object.get(i).getX(), (int) object.get(i).getY(), (int) object.get(i).getWidth(), (int) object.get(i).getHeight());
//			}
				//object.get(i).render(g);
		}
	}
	
	public void addBlockObject(AbstractStaticObject object) {
		this.blockList.add(object);
	}
	
	public void addEnemyObject(Enemy object) {
		this.enemyList.add(object);
	}
	
	public void addCoinObject(Coin object) {
		this.coinList.add(object);
	}

	public void removeBlockObject(AbstractStaticObject object) {
		this.blockList.remove(object);
	}
	
	public void removeEnemyObject(Enemy object) {
		this.enemyList.remove(object);
	}
	
	public void removeCoinObject(AbstractStaticObject object) {
		this.coinList.remove(object);
	}
	
}
