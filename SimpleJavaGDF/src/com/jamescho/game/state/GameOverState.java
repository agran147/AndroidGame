package com.jamescho.game.state;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

import com.jamescho.game.main.GameMain;
import com.jamescho.game.main.Resources;

public class GameOverState extends State {
	private Font font;
	
	public GameOverState(){
		font = new Font("SansSerif",Font.BOLD,50);
	}

	@Override
	public void init() {
		//System.out.println("Entered MenuState");
	}

	@Override
	public void update() {
		// Do Nothing
	}

	@Override
	public void render(Graphics g) {
		// Draws Resoruces.welcome to the screen at x = 0, y = 0
		g.setColor(Color.BLACK);
		g.fillRect(0, 0, GameMain.GAME_WIDTH, GameMain.GAME_HEIGHT);
		g.setColor(Color.WHITE);
		g.setFont(font);
		g.drawString("GAME OVER", 514, 350);
		g.drawString("Press any key", 490, 430);
	}

	@Override
	public void onClick(MouseEvent e) {
		//setCurrentState(new PlayState());
	}

	@Override
	public void onKeyPress(KeyEvent e) {
		setCurrentState(new PlayState());
	}

	@Override
	public void onKeyRelease(KeyEvent e) {
		//System.out.println("On KeyRelease!");
	}

	@Override
	public void update(float delta) {
		// TODO Auto-generated method stub
		
	}

}
