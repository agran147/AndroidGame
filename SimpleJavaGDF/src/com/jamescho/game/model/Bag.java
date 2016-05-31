package com.jamescho.game.model;
import java.awt.*;

import com.jamescho.game.main.GameMain;
public class Bag {
	private int x,y,width,height;
	private Rectangle rect;
	private int[] bagRoom = new int[4];
	
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
	
	//addItem
	public void addItem(int itemId){
		for(int i = 0; i < 4; i++){
			if(bagRoom[i] == 0){
				bagRoom[i] = itemId;
			}
			break;
		}
	}
	
	//dropOrGive
	
	//checkItem
	public boolean checkItem(int itemId){
		if(bagRoom[0] == itemId || bagRoom[1] == itemId || bagRoom[2] == itemId || bagRoom[3] == itemId){
			return true;
		}else{
			return false;
		}
	}
	
	//checkWeight
	public int checkWeight(){
		int bagWeight = 0;
		for(int i = 0; i < 4; i++){
			if(bagRoom[i] != 0){
				bagWeight++;
			}
		}
		return bagWeight;
	}
	
	public int getX(){
		return x;
	}
	public int getY(){
		return y;
	}
	public int getBagRoom(int i){
		return bagRoom[i];
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
