package com.AGameStudio.NewMech.util;

import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.List;

import com.AGameStudio.NewMech.Entity.environment.Entity;
import com.AGameStudio.NewMech.level.Level;

public class Physics {
	
	public static List<Entity> rectangleCollide(int x, int y, int width, int height, Level level) {
		List<Entity> collisions = new ArrayList<Entity>();
		
		Rectangle collider = new Rectangle(x, y, width, height);
		
		for(Entity e : level.getEntities()) {
			if(checkCollisions(collider, e) != null) collisions.add(e);
		}
		
		return collisions;
	}
	
	public static Entity checkCollisions(Entity e1, Entity e2) {
		return checkCollisions(new Rectangle(e1.getX(), e1.getY(), e1.getWidth(), e1.getHeight()), e2);
	}
	
	public static Entity checkCollisions(Rectangle r1, Entity e1) {
		Rectangle r2 = new Rectangle(e1.getX(), e1.getY(), e1.getWidth(), e1.getHeight());
		
		boolean results = r1.intersects(r2);
		
		if(results) return e1;
		return null;
	}
	
	public static int getDistance(int x1, int y1, int x2, int y2) {
		if(x2 - x1 != 0) return (y2 - y1) / (x2 - x1);
		return 0;
	}
}
