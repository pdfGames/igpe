package com.unicalday.core;

import java.awt.Graphics;

import com.unicalday.gui.Window;


public class GameManager implements Runnable {

	private boolean running = false;
	private final double UPDATE_CAP = 1.0/60.0;
	
	private Window window;
	private State menuState;
	
	private int FPS = 60;
	private long targetTime = 1000 / FPS;
	
	public void start() {
		menuState = new MenuState();
		State.setState(menuState);
		window = new Window("Ciao !", GameConfig.WIDTH, GameConfig.HEIGHT, GameConfig.SCALE, State.getKeyboardInput(), State.getMouseInput(), this);
		this.run();
	}
	
//	public void run() {
//		running = true;
//		long start;
//		long elapsed;
//		long wait;
//		while(running) {
//			start = System.nanoTime();
//			
//			tick();
//			window.getPanel().repaint();
//			
//			elapsed = System.nanoTime() - start;
//			wait = targetTime - elapsed / 1000000;
//			
//			if(wait < 0) wait = 5;
//			try {
//				Thread.sleep(wait);
//			}
//			catch(Exception e) {
//				e.printStackTrace();
//			}	
//		}
//	}
	
	@Override
	public void run() {
		running = true;
		boolean render = false;

		double firstTime = 0;
		double lastTime = System.nanoTime() / 1000000000.0;
		double passedTime =  0;
		double unprocessedTime = 0;		
		
		double frameTime = 0;
		int frames = 0;
		int fps = 0;
		
		while(running)
		{
			render = false;
			firstTime = System.nanoTime() / 1000000000.0;
			passedTime = firstTime - lastTime;
			lastTime = firstTime;
			unprocessedTime += passedTime;
			frameTime += passedTime;
			
			while(unprocessedTime >= UPDATE_CAP)
			{
				unprocessedTime -= UPDATE_CAP;
				render = true;

				tick();

				if (frameTime >= 1.0)
				{
					frameTime = 0;
					fps = frames;
					frames = 0;
					System.out.println("FPS: " + fps);
				}
			}
			if (render) {	
				window.getPanel().repaint();
				frames++;
			}
		}
	}

	public void tick() {
		State.getKeyboardInput().tick();
		State.getMouseInput().tick();
		if (State.getState() != null)
			State.getState().tick();
	}
	
	public void render(Graphics g) {
		if (State.getState() != null)
			State.getState().render(g);
	}
	
	public static void main(String[] args) {
		GameManager gm = new GameManager();
		gm.start();
	}

}
