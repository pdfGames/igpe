package com.unicalday.core;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

import com.unicalday.gui.Button;
import com.unicalday.gui.Fonts;
import com.unicalday.gui.Texture;


public class MenuState extends State {
	
	private final Button[] options;
	private int currentSelection = 0;
	
	private State gameState;
	
	private Texture tex;
	
	public MenuState() {
		gameState = new GameState("level1.txt");
		
		tex = new Texture("Menu");

		options = new Button[4];
        options[0] = new Button("Play", 200 + 0 * 110, 
        		new Font("Arial", Font.PLAIN, 32), new Font("Arial", Font.BOLD, 48),
        		Color.WHITE, Color.YELLOW, tex.menuSheet[1]);
        options[1] = new Button("Options", 200 + 1 * 110, 
        		new Font("Arial", Font.PLAIN, 32), new Font("Arial", Font.BOLD, 48),
        		Color.WHITE, Color.YELLOW,  tex.menuSheet[1]);
        options[2] = new Button("Exit", 200 + 2 * 110, 
        		new Font("Arial", Font.PLAIN, 32), new Font("Arial", Font.BOLD, 48),
        		Color.WHITE, Color.YELLOW,  tex.menuSheet[1]);
        options[3] = new Button("Exit", 200 + 3 * 110, 
        		new Font("Arial", Font.PLAIN, 32), new Font("Arial", Font.BOLD, 48),
        		Color.WHITE, Color.YELLOW,  tex.menuSheet[1]);
	}
	
	@Override
	public void tick() {
		boolean clicked = false;
		for (int i = 0; i < options.length; i++) {
			if (options[i].intersects(new Rectangle(State.getMouseInput().getX(), State.getMouseInput().getY(), 10, 10))) {
				currentSelection = i;
				clicked = State.getMouseInput().isButton(MouseEvent.BUTTON1);
			}
		}
		
		if (State.getKeyboardInput().isKey(KeyEvent.VK_UP) || State.getKeyboardInput().isKey(KeyEvent.VK_W)) {
			currentSelection--;
			if (currentSelection < 0) currentSelection = options.length -1;
		}
		
		if (State.getKeyboardInput().isKey(KeyEvent.VK_DOWN) || State.getKeyboardInput().isKey(KeyEvent.VK_S)) {
			currentSelection++;
			if (currentSelection > options.length) currentSelection = 0;
		}
		
		if (clicked || State.getKeyboardInput().isKey(KeyEvent.VK_ENTER))
			select();
	}
	
	private void select() {
		switch(currentSelection) {
		case 0: 
				System.out.println("Play");
				State.setState(gameState);
				break;
		case 1: 
				System.out.println("Options");
				break;
		case 2: 
				System.out.println("Exit");
				break;
		}
	}

	@Override
	public void render(Graphics g) {
		g.setColor(Color.BLACK);
		g.drawImage(tex.menuSheet[0], 0, 0, null);

		Fonts.drawString(g, new Font("Arial", Font.BOLD, 72), Color.ORANGE, "Ciao", 80);
	
		for (int i = 0; i < options.length; i++) {
			if (i == currentSelection)
				options[i].setSelected(true);
			else
				options[i].setSelected(false);
		
			options[i].render(g);
		}
	}

}
