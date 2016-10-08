package com.AGameStudio.NewMech.gfx;

import java.util.ArrayList;
import java.util.List;

public class Font2x {
	
	private static String chars = "" + "ABCDEFGHIJKLMNOPQRSTUVWXYZ  " + "0123456789.,!?'\"-+=/\\%()<>:;" + "";
	
	public static List<Sprite> characters = new ArrayList<Sprite>();
	public int size;
	public SpriteSheet sheet;
	
	public Font2x() {
		SpriteSheet sheet = SpriteSheet.font2x;
		int xt = 0;
		int yt = 0;
		for(int y = yt; y < sheet.height / 16; y++) {
			for(int x = xt; x < sheet.width / 16; x++) {
				characters.add(new Sprite(sheet, x, y, 16, 16));
			}
		}
	}
	
	public void render(Screen screen, String msg, int x, int y, boolean fixed) {
		msg = msg.toUpperCase();
		for(int i = 0; i < msg.length(); i++) {
			int index = chars.indexOf(msg.charAt(i));
			Sprite sprite = null;
			if(index >= 0) {
				if(index < characters.size()) sprite = characters.get(index);
			}
			if(msg.length() > 100) y = y + 32;
			if(msg.length() > 200) y = y + 32;
			if(sprite != null) screen.renderText(x + i * 16, y, sprite, fixed, 0);//2 * text size : for spacy look
		}
	}
	
	public void renderFrame(Screen screen, String title, int x0, int y0, int x1, int y1, boolean top) {
		for(int y = y0; y <= y1; y++) {
			for(int x = x0; x <= x1; x++) {
				if(x == x0 && y == y0) screen.renderSprite(x * 8, y * 8, Sprite.frame_corner,false, 0);
				else if(x == x1 && y == y0) screen.renderSprite(x * 8, y * 8, Sprite.frame_corner, false, 1);
				else if(x == x0 && y == y1) screen.renderSprite(x * 8, y * 8, Sprite.frame_corner, false, 2);
				else if(x == x1 && y == y1) screen.renderSprite(x * 8, y * 8, Sprite.frame_corner, false, 3);
				else if(y == y0 && top) screen.renderSprite(x * 8, y * 8, Sprite.frame_top,false, 0);
				else if(y == y1) screen.renderSprite(x * 8, y * 8, Sprite.frame_top, false, 2);
				else if(x == x0) screen.renderSprite(x * 8, y * 8, Sprite.frame_side, false, 0);
				else if(x == x1) screen.renderSprite(x * 8, y * 8, Sprite.frame_side, false, 1);
			}
		}
		if(!top) render(screen, title, x0 * 8 + 12, y0 * 8, false);
	}
}
