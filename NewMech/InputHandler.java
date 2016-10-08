package com.AGameStudio.NewMech;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.List;

public class InputHandler implements KeyListener{
	
	public class Key {
		public int absorbs, presses;
		public boolean pressed, clicked;
		
		public Key() {
			keys.add(this);
		}
		
		public void toggle(boolean pressed) {
			if(pressed != this.pressed) this.pressed = pressed;
			if(pressed) presses++;
		}
		
		public void tick() {
			if(absorbs < presses) {
				absorbs++;
				clicked = true;
			}
			else clicked = false;
		}		
	}
	
	public List<Key> keys = new ArrayList<Key>();
	
	public Key up = new Key();
	public Key down = new Key();
	public Key left = new Key();
	public Key right = new Key();
	public Key w = new Key();
	public Key s = new Key();
	public Key a = new Key();
	public Key d = new Key();
	public Key space = new Key();
	public Key enter = new Key();
	public Key menu = new Key();
	public Key exit = new Key();
	public Key q = new Key();
	public Key r = new Key();
	public Key e = new Key();
	public Key i = new Key();
	
	public InputHandler(Game game) {
		game.addKeyListener(this);
	}
	
	public void tick() {
		for(int i = 0; i < keys.size(); i++) {
			keys.get(i).tick();
		}
	}
	
	public void toggle(KeyEvent key, boolean pressed) {
		if(key.getKeyCode() == KeyEvent.VK_UP) up.toggle(pressed);
		if(key.getKeyCode() == KeyEvent.VK_DOWN) down.toggle(pressed);
		if(key.getKeyCode() == KeyEvent.VK_LEFT) left.toggle(pressed);
		if(key.getKeyCode() == KeyEvent.VK_RIGHT) right.toggle(pressed);
		if(key.getKeyCode() == KeyEvent.VK_W) w.toggle(pressed);
		if(key.getKeyCode() == KeyEvent.VK_S) s.toggle(pressed);
		if(key.getKeyCode() == KeyEvent.VK_A) a.toggle(pressed);
		if(key.getKeyCode() == KeyEvent.VK_D) d.toggle(pressed);
		if(key.getKeyCode() == KeyEvent.VK_SPACE) space.toggle(pressed);
		if(key.getKeyCode() == KeyEvent.VK_ENTER) enter.toggle(pressed);
		if(key.getKeyCode() == KeyEvent.VK_TAB) menu.toggle(pressed);
		if(key.getKeyCode() == KeyEvent.VK_ESCAPE) exit.toggle(pressed);
		if(key.getKeyCode() == KeyEvent.VK_Q) q.toggle(pressed);
		if(key.getKeyCode() == KeyEvent.VK_R) r.toggle(pressed);
		if(key.getKeyCode() == KeyEvent.VK_E) e.toggle(pressed);
		if(key.getKeyCode() == KeyEvent.VK_I) i.toggle(pressed);
	}
	
	public void keyPressed(KeyEvent e) {
		toggle(e, true);
	}
	
	public void keyReleased(KeyEvent e) {
		toggle(e, false);
	}

	public void keyTyped(KeyEvent e) {
		
	}
}
