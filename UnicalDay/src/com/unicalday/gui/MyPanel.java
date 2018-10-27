package com.unicalday.gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import javax.swing.JPanel;

import com.unicalday.core.GameManager;


public class MyPanel extends JPanel {
	
	private static final long serialVersionUID = 1L;
	GameManager gm;
	
	public MyPanel(int width, int height, int scale, GameManager gm) {
		this.gm = gm;
		Dimension s = new Dimension((int)width * scale, (int) height * scale);
		this.setBackground(Color.BLACK);
		this.setPreferredSize(s);
		this.setMaximumSize(s);
		this.setMinimumSize(s);
	}
	
	public void makePanelFocusable() {
		this.setFocusable(true);
        this.requestFocusInWindow();
	}
	
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		gm.render(g);
	}
	
}
