package com.jamescho.game.state;

import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

import com.jamescho.game.main.GameMain;
import com.jamescho.game.main.Resources;

public class IntroState extends State {

	@Override
	public void init() {
		System.out.println("Entered MenuState");
		Resources.introVedio.play();
	}

	@Override
	public void update() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update(float delta) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void render(Graphics g) {
		g.setColor(Resources.white);
		g.fillRect(0, 0, 1280, 720);
        g.drawImage(Resources.introback, 130, 80, null);
 		g.drawImage(Resources.intro,340 ,150, null);
 		g.drawImage(Resources.introplayicon,420 ,500, null);
	}

	@Override
	public void onClick(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onKeyPress(KeyEvent e) {
		int key = e.getKeyCode();
		if (key == KeyEvent.VK_SPACE || key == KeyEvent.VK_ENTER) {
				setCurrentState(new PlayState()); 
		}
			
	}

	@Override
	public void onKeyRelease(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

}
