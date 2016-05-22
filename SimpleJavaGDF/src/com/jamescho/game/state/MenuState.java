package com.jamescho.game.state;

import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

import com.jamescho.game.main.GameMain;
import com.jamescho.game.main.Resources;

public class MenuState extends State {
	private int currentSelection = 0;

	@Override
	public void init() {
		System.out.println("Entered MenuState");
	}

	@Override
	public void update(float delta) {
		// Do Nothing
	}

	@Override
	public void render(Graphics g) {
		g.drawImage(Resources.welcome, 0, 0, null); 
		g.drawImage(Resources.title1,140,160, null); 
		g.drawImage(Resources.title2, 680, 320, null);
		g.drawImage(Resources.startBtn, 220, 431, null); 
		g.drawImage(Resources.exitBtn, 220, 491, null); 

		if (currentSelection == 0) {
			g.drawImage(Resources.selector, 180, 431, null); 
		} 
		else {
			g.drawImage(Resources.selector, 180, 491, null);
		}
	}
	@Override
	public void onClick(MouseEvent e) {
		System.out.println("On Click!");
	}

	@Override
	public void onKeyPress(KeyEvent e) {
		System.out.println("On KeyPress!");
		
		int key = e.getKeyCode();
		if (key == KeyEvent.VK_SPACE || key == KeyEvent.VK_ENTER) {
			if (currentSelection == 0) {
				setCurrentState(new IntroState()); 
			} 
			else if (currentSelection == 1) {
				GameMain.sGame.exit();
			}
		} 
		else if (key == KeyEvent.VK_UP) {
			currentSelection = 0;
		} 
		else if (key == KeyEvent.VK_DOWN) {
			currentSelection = 1;
		}
	}
	

	@Override
	public void onKeyRelease(KeyEvent e) {
		System.out.println("On KeyRelease!");
	}

	@Override
	public void update() {
		// TODO Auto-generated method stub
		
	}

}
