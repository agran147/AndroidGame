package com.jamescho.game.model;
import java.awt.*;

import com.jamescho.game.main.Resources;
import com.jamescho.game.model.Brother;
public class Monster {
    private int x,y,width,height,velX,velY;
    private Rectangle rect;
    private final static int MOV_SPEED = 4;
    private Font monsterFont; //the talk of monster
    private boolean angry = false;
    
    public Monster(int x,int y,int width,int height){
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        rect = new Rectangle(x,y,width,height);
        velX=0;
        velY=0;
        monsterFont = new Font("SansSerif",Font.PLAIN,20);
    }
    public void update(Brother b){
    	x += velX;
        y += velY;
        if(y + height <= 400)
            velY = 0;
        if(angry && b.getY() + b.getHeight() != y + height){
            y = b.getY() + b.getHeight() - height;
        }
        updateRect();
    }
    
    /*Monster ask the brother*/
    public void ask(Graphics g){
        g.setColor(Color.BLACK);
        g.setFont(monsterFont);
        g.drawString("Do you have something to give me?", 400, 140);
    }
    
    private void updateRect(){
        rect.setBounds(x,y,width,height);
    }
    public int getX(){
        return x;
    }
    public int getY(){
        return y;
    }
    public int getWidth(){
        return width;
    }
    public int getHeight(){
        return height;
    }
    public Rectangle getRect(){
        return rect;
    }
    public void angry(){
        angry = true;
        velX = -4;
    }
    public void happy(){
        velY = -4;
    }
    public void stop(){
        velX = 0;
        velY = 0;
    }
    public void receive(int i){
        if(i == 1) happy();
        else angry();
    }
    
}
