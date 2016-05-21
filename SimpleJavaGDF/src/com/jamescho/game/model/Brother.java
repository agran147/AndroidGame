package com.jamescho.game.model;
import java.awt.*;
import com.jamescho.game.state.*;
import com.jamescho.game.main.GameMain;
public class Brother {
    private int x,y,width,height,velX,velY;
    private Rectangle rect;
    private final static int MOVE_SPEED = 4;
    private int brotherLine ; //it is the line location  1,2,3 means up middle and down
    
    public Brother(int x,int y,int width,int height){
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        rect = new Rectangle(x,y,width,height);
        velX = 0;
        velY = 0;
        brotherLine=2;
        updateRect();
    }
    public void update(){
        
        x += velX;
        y += velY;
        if(x < 0){ //to prevent from getting out of screen when go back
            x = 0;
        }
        if(velY!=0 && brotherLine==2){
            if(y==380){
                velY=0;
            }
        }
        if(y<280){
            velY=0;
        }
        else if(y>480){
            velY=0;
        }
        
        
        updateRect();
    }
    private void updateRect(){
        rect.setBounds(x,y,width,height);
    }
    public int getbrotherLine(){
        return brotherLine;
    }
    public void brotherLineUp(){
        brotherLine--;
    }
    public void brotherLineDown(){
        brotherLine++;
    }
    public void brotherGoUp(){
        velY=-4;//*Backpack.checkweight();
        
    }
    
    public void brotherGoDown(){
        velY=4;//*Backpack.checkweight();
    }
    public void accelForward(){ //go forward
        
        velX = MOVE_SPEED;//*Backpack.checkweight();
        
    }
    public void accelBack(){ // go back
        velX = -MOVE_SPEED;//*Backpack.checkweight();
    }
    public void stop(){
        velX = 0;
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
    
}
