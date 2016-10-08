package com.AGameStudio.NewMech.screen;

import java.util.List;

import com.AGameStudio.NewMech.Game;
import com.AGameStudio.NewMech.InputHandler;
import com.AGameStudio.NewMech.gfx.Screen;

public class Menu {
	protected Game game;
	protected InputHandler input;
	
	protected int selected = 0;
	
	public Menu(Game game, InputHandler input) {
		this.game = game;
		this.input = input;
	}
	
	public void tick() {
		
	}
	
	public void render(Screen screen) {
		
	}
	
	public void renderList(Screen screen, List <?> list, int x, int y) {
		
	}
}
