package com.AGameStudio.NewMech.screen;

import com.AGameStudio.NewMech.Game;
import com.AGameStudio.NewMech.InputHandler;
import com.AGameStudio.NewMech.gfx.Font2x;
import com.AGameStudio.NewMech.gfx.Screen;


public class TitleMenu extends Menu {
	private Font2x font = new Font2x();
	
	private static final String[] options = { "Continue", "New Game", "Controls" };
	
	public TitleMenu(Game game, InputHandler input) {
		super(game, input);
	}
	
	public void tick() {
		if(input.up.clicked) selected--;
		if(input.down.clicked) selected++;
		int length = options.length;
		if(selected < 0) selected += length;
		if(selected >= length) selected -= length;
		
		if(input.enter.clicked) {
			if(selected == 0) {
				game.loadData = true;
				game.init();
				game.setMenu(null);
				game.load();
			}
			if(selected == 1) {
				game.loadData = false;
				game.init();
				game.setMenu(null);
			}
			else if(selected == 2) game.setMenu(new ControlsMenu(game, input));
		}
	}
	
	public void render(Screen screen) {
		screen.clear(0);
		String title = "GAME";
		String help = "Arrow Keys and Enter to navigate";
		font.render(screen, title, screen.xOffset + 450, screen.yOffset + 100, true);
		font.render(screen, help, screen.xOffset + 32, screen.yOffset + 650, true);
		for(int i = 0; i < options.length; i++) {
			String msg = options[i];
			if(selected == i) msg = "> " + msg;
			font.render(screen, msg, screen.xOffset + 420, screen.yOffset + 250 + i * 80, true);
		}
	}
}
