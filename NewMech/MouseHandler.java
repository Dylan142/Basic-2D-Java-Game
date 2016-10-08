package com.AGameStudio.NewMech;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

public class MouseHandler implements MouseListener, MouseMotionListener{
	
	public int x, y;
	public int button;
	
	// 1 == left-click
	// 3 == right-click
	
	public MouseHandler(Game game) {
		game.addMouseListener(this);
		game.addMouseMotionListener(this);
	}
	
	public void tick() {
		
	}
	
	public void mouseClicked(MouseEvent e) {
		
	}
	
	public void mouseEntered(MouseEvent e) {
		x = e.getX();
		y = e.getY();
	}
	
	public void mouseExited(MouseEvent e) {
		x = -1;
		y = -1;
	}
	
	public void mousePressed(MouseEvent e) {
		button = e.getButton();
	}
	
	public void mouseReleased(MouseEvent e) {
		button = -1;
	}
	
	public void mouseDragged(MouseEvent e) {
		x = e.getX();
		y = e.getY();
	}
	
	public void mouseMoved(MouseEvent e) {
		x = e.getX();
		y = e.getY();
	}

}
