package com.unicalday.logic.entity;

import java.awt.Rectangle;

import com.unicalday.logic.abstrclass.AbstractStaticObject;
import com.unicalday.logic.abstrclass.ObjectID;


public class Coin extends AbstractStaticObject {
	
	public Coin(float x, float y, float width, float height, ObjectID id) {
		super(x, y, width, height, id);
	}
	
	@Override
	public Rectangle getBounds() {
		return this.rectangle;
	}

}
