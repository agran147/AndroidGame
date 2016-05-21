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
    private static final int BRO_WIDTH = 150;
    private static final int BRO_HEIGHT = 180;
    private Monster monster;
    private int middle = 0; //show whether brother arrives to the middle of the screen
    private int monsterSay = 0; //show whether monster should speak
    private int brotherGo = 0; //show whether animation should play
    private int brotherSwitch = 0; // show whether switch animation should play
    private int item = 0;// item-id
    private int monsterGo = 0;// show if monster move

    public void init() {
        brother = new Brother(20,380,BRO_WIDTH,BRO_HEIGHT);
        monster = new Monster(1200,460,72,97);
        bag1 = new Bag(20,660,40,40);
        bag2 = new Bag(70,660,40,40);
    }
    
    @Override
    public void update() {
        
    }
    
    @Override
    public void render(Graphics g) {
        //draw sky
        //g.setColor(Resources.skyBlue);
        g.drawImage(Resources.background1,0,0,null);
        //g.fillRect(0, 0, 800, 450);
        //draw road
        //g.setColor(Color.GRAY);
        //g.fillRect(0, 250, 800, 200);
        //draw brother
        /*g.setColor(Color.BLUE);
         g.fillRect(brother.getX(), brother.getY(), brother.getWidth(), brother.getHeight());*/
        
        if(monsterGo == 1)Resources.runAnim2.render(g, monster.getX(), monster.getY() - monster.getHeight() * 3, monster.getWidth() * 4, monster.getHeight() * 4);
        else if(monsterGo == 2 && monster.getY() + monster.getHeight() > 400)Resources.runAnim2.render(g, monster.getX(), monster.getY(), monster.getWidth(), monster.getHeight());
        else
        	g.drawImage(Resources.mrun1,monster.getX(),monster.getY(),monster.getWidth(),monster.getHeight(),null);
       
        if(brotherGo == 0 && brotherSwitch==0){
            g.drawImage(Resources.stop, brother.getX(), brother.getY(), brother.getWidth(), brother.getHeight(), null);
        }
        else if(brotherSwitch == 0 && brotherGo == 1){
            Resources.runAnim.render(g, brother.getX(), brother.getY(), brother.getWidth(), brother.getHeight());
        }
        else if(brotherSwitch ==1 && brotherGo == 0 ){
            Resources.runAnim.render(g, brother.getX(), brother.getY(), brother.getWidth(), brother.getHeight());
        }
        else{
            Resources.runAnim.render(g, brother.getX(), brother.getY(), brother.getWidth(), brother.getHeight());
            
        }
        //draw monster
        //g.setColor(Color.RED);
        //g.fillRect(monster.getX(), monster.getY(), monster.getWidth(), monster.getHeight());
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
        if(middle == 1 && e.getButton() == MouseEvent.BUTTON1 && (e.getX() >= bag1.getX() && e.getX() <= (bag1.getX() + bag1.getWidth()) && e.getY() >= bag1.getY() && e.getY() <= (bag1.getY() + bag1.getHeight()))){
            //monster.happy();
            item = 1;
            monsterSay = 0;
            monsterGo = 2;
            monster.receive(item);
        }
        //give wrong thing
        else if(middle == 1 && e.getButton() == MouseEvent.BUTTON1 && (e.getX() >= bag2.getX() && e.getX() <= (bag2.getX() + bag2.getWidth()) && e.getY() >= bag2.getY() && e.getY() <= (bag2.getY() + bag2.getHeight()))){
            //monster.angry();
            item = 2;
            monsterSay = 0;
            monsterGo = 1;
            monster.receive(item);
        }
        
    }
    
    @Override
    public void onKeyPress(KeyEvent e) {
        /*if(e.getKeyCode() == KeyEvent.VK_LEFT){
         brother.accelBack();
         
         } else */
        
        //brother arrives middle
        if (e.getKeyCode() == KeyEvent.VK_RIGHT && middle == 0 && brother.getX() >= GameMain.GAME_WIDTH/2){
            brother.stop();
            middle = 1;
            brotherGo = 0;
            monsterSay = 1;
        }
        //brother hasn't arrived middle
        else if (e.getKeyCode() == KeyEvent.VK_RIGHT && (item != 0 || brother.getX() < GameMain.GAME_WIDTH/2)){
            brotherGo = 1;
            brother.accelForward();
        }
        if(e.getKeyCode() == KeyEvent.VK_UP && brother.getbrotherLine()!=1&&(item != 0 || brother.getX() < GameMain.GAME_WIDTH/2))
        {
            brotherSwitch = 1;
            brother.brotherLineUp();
            brother.brotherGoUp();
        }
        if(e.getKeyCode() == KeyEvent.VK_DOWN && brother.getbrotherLine()!=3&&(item != 0 || brother.getX() < GameMain.GAME_WIDTH/2))
        {
            brotherSwitch = 1;
            brother.brotherLineDown();
            brother.brotherGoDown();
        }
        
    }
    
    @Override
    public void onKeyRelease(KeyEvent e) {
        if(/*e.getKeyCode() == KeyEvent.VK_LEFT ||*/ e.getKeyCode() == KeyEvent.VK_RIGHT){
            brother.stop();
            brotherGo = 0;
        }
        brotherSwitch=0;
        
    }
    private boolean monsterCollides(Brother b){
        return monster.getRect().intersects(b.getRect());
    }
    
    public int getFlag(){
        return middle;
    }
    
    @Override
    public void update(float delta) {
        Resources.runAnim.update(delta);
        Resources.runAnim2.update(delta);
        brother.update();
        monster.update(brother);
        
        if(monsterGo == 1 && monsterCollides(brother)){
            //monster.stop();
            setCurrentState(new GameOverState());
        }
    }
    
}
