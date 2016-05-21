package com.jamescho.game.state;
import java.awt.*;
import java.awt.event.*;

import com.jamescho.game.main.GameMain;
import com.jamescho.game.main.Resources;

//import javax.swing.JButton;
//import javax.swing.JFrame;
//import javax.swing.JPanel;

import com.jamescho.game.model.*;

import com.jamescho.game.model.Brother;
public class PlayState extends State{
	private Brother brother;
	private Bag bag1,bag2;
	private static final int BRO_WIDTH = 60;
	private static final int BRO_HEIGHT = 90;
	private Monster monster;
	private int flag = 0; //show whether brother arrives to the middle of the screen
	private int monsterSay = 0; //show whether monster should speak 
	private int brotherGo = 0; //show whether animation should play
	
	@Override
	public void init() {
		brother = new Brother(20,260,BRO_WIDTH,BRO_HEIGHT);
		monster = new Monster(700,150,100,200);
		bag1 = new Bag(20,400,40,40);
		bag2 = new Bag(70,400,40,40);
	}

	@Override
	public void update() {
		
	}

	@Override
	public void render(Graphics g) {
		//draw sky
		g.setColor(Resources.skyBlue);
		g.fillRect(0, 0, 800, 450);
		//draw road
		g.setColor(Color.GRAY);
		g.fillRect(0, 250, 800, 200);
		//draw brother
		/*g.setColor(Color.BLUE);
		g.fillRect(brother.getX(), brother.getY(), brother.getWidth(), brother.getHeight());*/
		
		if(brotherGo == 0){
			g.drawImage(Resources.run2, brother.getX(), brother.getY(), brother.getWidth(), brother.getHeight(), null);
		} else if(brotherGo == 1){
			Resources.runAnim.render(g, brother.getX(), brother.getY(), brother.getWidth(), brother.getHeight());
		}
		//draw monster
		g.setColor(Color.RED);
		g.fillRect(monster.getX(), monster.getY(), monster.getWidth(), monster.getHeight());
		//draw bag
		g.setColor(Color.GREEN);
		g.fillRect(bag1.getX(), bag1.getY(), bag1.getWidth(), bag1.getHeight());
		g.fillRect(bag2.getX(), bag2.getY(), bag2.getWidth(), bag2.getHeight());
		//monster say
		if(monsterSay == 1){
			monster.ask(g);
		}
	}

	@Override
	public void onClick(MouseEvent e) {
		//give right thing
		if(flag == 0 && e.getButton() == MouseEvent.BUTTON1 && (e.getX() >= bag1.getX() && e.getX() <= (bag1.getX() + bag1.getWidth()) && e.getY() >= bag1.getY() && e.getY() <= (bag1.getY() + bag1.getHeight()))){
			monster.happy();
			flag = 1;
			monsterSay = 0;
		}
		//give wrong thing
		else if(flag == 0 && e.getButton() == MouseEvent.BUTTON1 && (e.getX() >= bag2.getX() && e.getX() <= (bag2.getX() + bag2.getWidth()) && e.getY() >= bag2.getY() && e.getY() <= (bag2.getY() + bag2.getHeight()))){
			monster.angry();
			flag = 2;
			monsterSay = 0;
		}
		
	}

	@Override
	public void onKeyPress(KeyEvent e) {
		/*if(e.getKeyCode() == KeyEvent.VK_LEFT){
			brother.accelBack();
			
		} else */
		
		//brother arrives middle
		if (e.getKeyCode() == KeyEvent.VK_RIGHT && flag == 0 && brother.getX() >= GameMain.GAME_WIDTH/2){
			brother.stop();
			brotherGo = 0;
			monsterSay = 1;
		} 
		//brother hasn't arrive middle
		else if (e.getKeyCode() == KeyEvent.VK_RIGHT && (flag != 0 || brother.getX() < GameMain.GAME_WIDTH/2)){
			brotherGo = 1;
			brother.accelForward();
		}
		
	}

	@Override
	public void onKeyRelease(KeyEvent e) {
		if(/*e.getKeyCode() == KeyEvent.VK_LEFT ||*/ e.getKeyCode() == KeyEvent.VK_RIGHT){
			brother.stop();
			brotherGo = 0;
		}
		
	}
	private boolean monsterCollides(Brother b){
		return monster.getRect().intersects(b.getRect());
	}
	
	public int getFlag(){
		return flag;
	}

	@Override
	public void update(float delta) {
		Resources.runAnim.update(delta);
		brother.update();
		monster.update(brother);
		
		if(monsterCollides(brother)){
			monster.stop();
			setCurrentState(new GameOverState());
		}
	}

}