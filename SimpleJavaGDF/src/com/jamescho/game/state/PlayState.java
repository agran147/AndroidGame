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
    private Background background;
    private Bag bag;
    private static final int BRO_WIDTH = 150;
    private static final int BRO_HEIGHT = 180;
    private Monster monster;
    private int monsterAppear = 0; //show whether brother arrives to the end of the road
    private int monsterSay = 0; //show whether monster should speak
    private int brotherGo = 0; //show whether animation should play
    private int brotherSwitch = 0; // show whether switch animation should play
    private Item rock;
    private int itemId = 0;// item-id
    private int monsterGo = 0;// show if monster move
    private Font waterFont;
    private WaterHole waterHole;
    private Font scoreFont;
    private int playerScore = 0;
    private int appearX,appearY;
    
    public void init() {
        brother = new Brother(420,380,BRO_WIDTH,BRO_HEIGHT);
        background = new Background(-12,-12,3883,744);
        monster = new Monster(1200,460,72,97);
        bag = new Bag(20,160,100,400);
        waterFont = new Font ("SansSerif", Font.BOLD, 25);
        waterHole = new WaterHole(1200, 375, 150, 150);
        scoreFont = new Font("SansSerif", Font.BOLD, 25);
        rock = new Item(600, 600, 100, 100, 1);
    }
    
    @Override
    public void update() {
        
    }
    
    @Override
    public void render(Graphics g) {
        //brother control
    	if(brotherGo == 0 && brotherSwitch==0){
            g.drawImage(Resources.background1, background.getX(), background.getY(), background.getWidth(), background.getHeight(), null);
            g.drawImage(Resources.waterHole1, (int)waterHole.getX(),(int)waterHole.getY(), 150, 150, null);
            //draw rock
            if(rock.isVisible()){
            	g.drawImage(Resources.rock, (int)rock.getX(), (int)rock.getY(), 100, 100, null);
            }
            g.drawImage(Resources.stop, brother.getX(), brother.getY(), brother.getWidth(), brother.getHeight(), null);
        }
        else if(brotherSwitch == 0 && brotherGo == 1){
            g.drawImage(Resources.background1, background.getX(), background.getY(), background.getWidth(), background.getHeight(), null);
            g.drawImage(Resources.waterHole1, (int)waterHole.getX(),(int)waterHole.getY(), 150, 150, null);
            if(rock.isVisible()){
            	g.drawImage(Resources.rock, (int)rock.getX(), (int)rock.getY(), 100, 100, null);
            }
            Resources.runAnim.render(g, brother.getX(), brother.getY(), brother.getWidth(), brother.getHeight());
        }
        else if(brotherSwitch ==1 && brotherGo == 0 ){
            g.drawImage(Resources.background1, background.getX(), background.getY(), background.getWidth(), background.getHeight(), null);
            g.drawImage(Resources.waterHole1, (int)waterHole.getX(),(int)waterHole.getY(), 150, 150, null);
            if(rock.isVisible()){
            	g.drawImage(Resources.rock, (int)rock.getX(), (int)rock.getY(), 100, 100, null);
            }
        	Resources.runAnim.render(g, brother.getX(), brother.getY(), brother.getWidth(), brother.getHeight());
        }
        else{
            g.drawImage(Resources.background1, background.getX(), background.getY(), background.getWidth(), background.getHeight(), null);
            g.drawImage(Resources.waterHole1, (int)waterHole.getX(),(int)waterHole.getY(), 150, 150, null);
            if(rock.isVisible()){
            	g.drawImage(Resources.rock, (int)rock.getX(), (int)rock.getY(), 100, 100, null);
            }
            Resources.runAnim.render(g, brother.getX(), brother.getY(), brother.getWidth(), brother.getHeight());
        }
        
        //draw monster
        if(monsterGo == 1){
        	Resources.runAnim2.render(g, monster.getX(), monster.getY() - monster.getHeight() * 3, monster.getWidth() * 4, monster.getHeight() * 4);
        }
        else if(monsterGo == 2 && monster.getY() + monster.getHeight() > 400){
        	Resources.runAnim2.render(g, monster.getX() + background.getX() - appearX, monster.getY() + background.getY() - appearY, monster.getWidth(), monster.getHeight());
        }
        else if (monsterAppear == 1 || monsterGo == 2){
        	g.drawImage(Resources.mrun1,monster.getX() + background.getX() - appearX, monster.getY() + background.getY() - appearY,monster.getWidth(),monster.getHeight(),null);
        }
        
        //draw bag
        renderBag(g);
        
        //monster say
        if(monsterSay == 1){
            monster.ask(g);
            monsterSay = 0;
        }
        //draw water
        renderWater(g);
        g.drawImage(Resources.waterIcon, 10, 20, null);
        g.drawImage(Resources.HP, 150, 20, null);
        //draw score
        //renderScore(g);
        renderHP(g);
    }
    
    private void renderScore(Graphics g){
    	g.setFont(scoreFont);
    	g.setColor(Color.BLACK);
    	g.drawString("playscore:" + playerScore / 100, 100, 30);
    }
    
    private void renderWater(Graphics g){
		g.setFont(waterFont);
		g.setColor(Color.BLUE);
		g.drawString("" + brother.getWater() / 100, 50, 30);
	}
    
    private void renderHP(Graphics g){
    	g.setFont(waterFont);
    	g.setColor(Color.RED);
    	g.drawString("" + brother.getHP(), 190, 30);
    }
    
    private void renderBag(Graphics g){
    	g.drawImage(Resources.bag, bag.getX(), bag.getY(), 100, 400, null);
    	for(int i = 0; i < 4; i++){
    		if(bag.getBagRoom(i) == 1){
    			g.drawImage(Resources.rock, 20, 160 + i * 50, 100, 100,null);
    		}
    	}
    }
    
    @Override
    public void onClick(MouseEvent e) {
        //give right thing
        if(monsterAppear == 1 && e.getButton() == MouseEvent.BUTTON1 && (e.getX() >= 20 && e.getX() <= (20 + 100) && e.getY() >= 160 && e.getY() <= (160 + 100))){
            //monster.happy();
            itemId = 1;
            monsterGo = 2;
            monster.receive(itemId);
            monsterAppear = 0;
        }
        //give wrong thing
        else if(monsterAppear == 1 && e.getButton() == MouseEvent.BUTTON1 && (e.getX() >= 20 && e.getX() <= (20 + 100) && e.getY() >= 260 && e.getY() <= (260 + 100))){
            //monster.angry();
            itemId = 2;
            monsterGo = 1;
            monster.receive(itemId);
            monsterAppear = 0;
        }
        
    }
    
    @Override
    public void onKeyPress(KeyEvent e) {
    	
        //monster appear
        if ((e.getKeyCode() == KeyEvent.VK_RIGHT || e.getKeyCode() == KeyEvent.VK_UP || e.getKeyCode() == KeyEvent.VK_DOWN) && monsterAppear == 1){
        	background.stop();
            monsterSay = 1;
            brotherGo = 0;
        }
        
        //brother hasn't arrived middle
        else if (e.getKeyCode() == KeyEvent.VK_RIGHT && (itemId != 0 || brother.getX() < GameMain.GAME_WIDTH/2)){
            brotherGo = 1;
            background.accelForward();
        }
        if(e.getKeyCode() == KeyEvent.VK_UP && brother.getbrotherLine()!=1&&(itemId != 0 || brother.getX() < GameMain.GAME_WIDTH/2))
        {
            brotherSwitch = 1;
            brother.brotherLineUp();
            brother.brotherGoUp();
        }
        if(e.getKeyCode() == KeyEvent.VK_DOWN && brother.getbrotherLine()!=3&&(itemId != 0 || brother.getX() < GameMain.GAME_WIDTH/2))
        {
            brotherSwitch = 1;
            brother.brotherLineDown();
            brother.brotherGoDown();
        }
        
    }
    
    @Override
    public void onKeyRelease(KeyEvent e) {
        if(/*e.getKeyCode() == KeyEvent.VK_LEFT ||*/ e.getKeyCode() == KeyEvent.VK_RIGHT){
            background.stop();
            brotherGo = 0;
        }
        brotherSwitch=0;
        
    }
    private boolean monsterCollides(Brother b){
        return monster.getRect().intersects(b.getRect());
    }
    private boolean waterCollides(Brother b){
    	if(brotherGo == 0 && waterHole.getRect().intersects(b.getRect()))
    	{
    		return true;
    	}else{
    		return false;
    	}
    }
    private boolean rockCollides(Brother b){
    	if(brotherGo == 0 && rock.getRect().intersects(b.getRect()))
    	{
    		return true;
    	}else{
    		return false;
    	}
    }
    
    public int getFlag(){
        return monsterAppear;
    }
    
    @Override
    public void update(float delta) {
        Resources.runAnim.update(delta);
        Resources.runAnim2.update(delta);
        background.update();
        brother.update();
        //when monster appear, stopping water decrease
        if(monsterAppear == 0)brother.waterDecrease();
        else brother.waterHold();
        monster.update(brother);
        waterHole.update(background.getVelx());
        if(waterCollides(brother)){
        	brother.refill();
        }
        
        //update bag&item
        bag.update();
        rock.update(background.getVelx());
        if(rockCollides(brother)){
        	rock.onCollide(brother);
        	bag.addItem(rock.getItemId());
        }
        
        if((monsterGo == 1 && monsterCollides(brother)) || brother.getWater() <= 0){
            setCurrentState(new GameOverState());
        }
        if(brotherGo ==1 ) playerScore += 1;
        if(monsterGo == 0 && playerScore == 1000){
        	monsterAppear = 1;
        	appearX = background.getX();
        	appearY = background.getY();
        }
        if(monsterGo == 2 && (monster.getX() + background.getX() - appearX <= 0 || monster.getX() + background.getX() - appearX >= GameMain.GAME_WIDTH)){
        	setCurrentState(new MenuState());
        }
    }
    
}
