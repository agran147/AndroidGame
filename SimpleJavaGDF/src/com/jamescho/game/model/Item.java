package com.jamescho.game.model;

import java.awt.Rectangle;
import com.jamescho.game.model.*;

public class Item {
	private float x, y;
	private int width, height;
	private Rectangle rect;
	private boolean visible;
	private int itemId;

	public Item(float x, float y, int width, int height, int itemId) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.itemId = itemId;
		rect = new Rectangle((int) x, (int) y, width, height);
		visible = true;
	}

	// Note: Velocity value will be passed in from PlayState!
	public void update(float velX) {
		visible = true;
		x += velX;
		if (x <= -50) {
			visible = false;
		}
		updateRect();
	}

	public void updateRect() {
		rect.setBounds((int) x, (int) y, width, height);
	}

	public void onCollide(Brother b) {
		visible = false;
	}

	public float getX() {
		return x;
	}
	public float getY() {
		return y;
	}
	public int getItemId(){
		return itemId;
	}

	public boolean isVisible() {
		return visible;
	}

	public Rectangle getRect() {
		return rect;
	}
}