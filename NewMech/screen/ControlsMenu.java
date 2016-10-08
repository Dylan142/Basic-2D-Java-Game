package com.AGameStudio.NewMech.screen;

import com.AGameStudio.NewMech.Game;
import com.AGameStudio.NewMech.InputHandler;
import com.AGameStudio.NewMech.gfx.Font2x;
import com.AGameStudio.NewMech.gfx.Screen;

public class ControlsMenu extends Menu {
	
	private Font2x font = new Font2x();
	
	private static final String[] controls = {"W- Move Up", "S- Move Down", "A- Move Left", "D- Move Right", "\n",
		"Enter- Select and Equip Items", "Space- Attack", "I- Open Inventory", "\n", "Up- Select Up", "Down- Selected Down",
		"Left- Select Left", "Right- Select Right"};
	
	public ControlsMenu(Game game, InputHandler input) {
		super(game, input);
	}
	
	public void tick() {
		if(input.up.clicked) selected--;
		if(input.down.clicked) selected++;
		int length = controls.length;
		if(selected < 0) selected += length;
		if(selected >= length) selected -= length;
		if(input.enter.clicked) game.setMenu(new TitleMenu(game, input));
	}
	
	public void render(Screen screen) {
		screen.clear(0);
		String title = "Controls";
		font.render(screen, title, screen.xOffset + 440, screen.yOffset + 100, true);
		font.render(screen, "Press Enter", screen.xOffset + 80, screen.yOffset + 300, true);
		font.render(screen, "to return", screen.xOffset + 90, screen.yOffset + 332, true);
		for(int i = 0; i < controls.length; i++) {
			String msg = controls[i];
			font.render(screen, msg, screen.xOffset + 390, screen.yOffset + 200 + i * 32, true);
		}
	}
}
