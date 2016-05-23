package com.jamescho.game.model;

import java.awt.Rectangle;
import com.jamescho.game.state.PlayState;

public class WaterHole {
	private float x, y;
	private int width, height;
	private Rectangle rect;
	private boolean visible;
	
	public WaterHole(float x, float y, int width,int height){
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		rect = new Rectangle((int)x, (int)y, width, height);
		visible = false;
	}
	
	public void update(float delta, float velX) {
		visible = true;
		x += velX * delta;
		if (x <= -50) {
			visible = false;
		}
		updateRect();
	}

	public void updateRect() {
		rect.setBounds((int) x, (int) y, width, height);
	}
	

	public float getX() {
		return x;
	}

	public float getY() {
		return y;
	}

	public boolean isVisible() {
		return visible;
	}

	public Rectangle getRect() {
		return rect;
	}
}
