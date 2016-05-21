package com.jamescho.game.model;
import java.awt.*;
import com.jamescho.game.state.*;
import com.jamescho.game.main.GameMain;
public class Brother {
	private int x,y,width,height,velX;
	private Rectangle rect;
	private final static int MOVE_SPEED = 4;
	
	public Brother(int x,int y,int width,int height){
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		rect = new Rectangle(x,y,width,height);
		velX = 0;
		updateRect();
	}
	public void update(){
		x += velX;
		if(x < 0){ //to prevent from getting out of screen when go back
			x = 0;
		} 
		updateRect();
	}
	private void updateRect(){
		rect.setBounds(x,y,width,height);
	}
	public void accelForward(){ //go forward
		
		velX = MOVE_SPEED;
		
	}
	public void accelBack(){ // go back
		velX = -MOVE_SPEED;
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
