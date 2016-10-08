package com.AGameStudio.NewMech.screen;

import com.AGameStudio.NewMech.Game;
import com.AGameStudio.NewMech.InputHandler;
import com.AGameStudio.NewMech.gfx.Font2x;
import com.AGameStudio.NewMech.gfx.Screen;

public class PauseMenu extends Menu {
	
	private Font2x font = new Font2x();
	
	private String[] options = {"Resume", "Save", "Quit"};
	
	public PauseMenu(Game game, InputHandler input) {
		super(game, input);
	}
	
	public void tick() {
		if(input.up.clicked) selected--;
		if(input.down.clicked) selected++;
		int length = options.length;
		if(selected < 0) selected += length;
		if(selected >= length) selected -= length;
		if(input.enter.clicked) {
			if(selected == 0) game.setMenu(null);
			else if(selected == 1) game.save();
			else if(selected == 2) {
				game.save();
				System.exit(0);
			}
		}
	}
	
	public void render(Screen screen) {
		String title = "Paused";
		font.render(screen, title, screen.xOffset + 460, screen.yOffset + 100, true);
		for(int i = 0; i < options.length; i++) {
			String msg = options[i];
			if(selected == i) msg = "> " + msg;
			font.render(screen, msg, screen.xOffset + 460, screen.yOffset + 300 + i * 96, true);
		}
	}
}
