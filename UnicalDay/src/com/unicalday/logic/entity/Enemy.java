package com.unicalday.logic.entity;

import com.unicalday.logic.abstrclass.AbstractDynamicObject;
import com.unicalday.logic.abstrclass.Direction;
import com.unicalday.logic.abstrclass.ObjectID;

public class Enemy extends AbstractDynamicObject {
	
	public Enemy(float x, float y, float width, float height, ObjectID id, Direction dir){
		super(x, y, width, height, id, dir);
		velX = 3;
	}

}
