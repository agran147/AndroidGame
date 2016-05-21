package com.jamescho.game.model;
import java.awt.*;

import com.jamescho.game.main.GameMain;
public class Bag {
	private int x,y,width,height;
	private Rectangle rect;
	
	public Bag(int x,int y,int width,int height){
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		rect = new Rectangle(x,y,width,height);
	}
	public void update(){
		updateRect();
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
}
