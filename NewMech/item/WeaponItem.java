package com.AGameStudio.NewMech.item;

import com.AGameStudio.NewMech.gfx.Screen;
import com.AGameStudio.NewMech.gfx.Sprite;

public class WeaponItem extends Item {
	public int damage = 0;
	protected int time = 0;

	public WeaponItem(String name) {
		super(name);

		switch (name) {
		case "axe":
			this.id = 10;
			this.sprite = Sprite.axe;
			this.animationTime = 25;
			this.range = 30;
			this.damage = 4;
			break;
		case "mace":
			this.id = 11;
			this.sprite = Sprite.mace;
			this.animationTime = 35;
			this.range = 35;
			this.damage = 7;
			break;
		case "sword":
			this.id = 12;
			this.sprite = Sprite.sword;
			this.animationTime = 20;
			this.range = 40;
			this.damage = 5;
			break;
		default:
			System.err.println("item id is invalid!");
			break;
		}
	}

	public void tick() {
		if (time > 0) time--;
	}

	public void renderIcon(Screen screen, int x, int y, int dir) {
		switch (name) {
		case "axe":
			switch (dir) {
			case 0:
				screen.renderSprite(x + 32, y - 8, sprite, true, 0);
				break;
			case 1:
				screen.renderSprite(x + 4, y, sprite, true, 0);
				break;
			case 2:
				screen.renderSprite(x, y, sprite, true, 0);
				break;
			case 3:
				screen.renderSprite(x, y - 2, sprite, true, 0);
				break;
			default:
				System.err.println("item dir is invalid!");
				break;
			}
			break;
		case "mace":
			switch(dir) {
			case 0:
				screen.renderSprite(x + 46, y + 24, sprite, true, 0);
				break;
			case 1:
				screen.renderSprite(x - 11, y + 24, sprite, true, 1);
				break;
			case 2:
				screen.renderSprite(x - 13, y + 24, sprite, true, 1);
				break;
			case 3:
				screen.renderSprite(x + 43, y + 24, sprite, true, 0);
				break;
			default:
				System.err.println("item dir is invalid!");
				break;
			}
		case "sword":
			switch(dir) {
			case 0:
				screen.renderSprite(x + 32, y - 14, sprite, true, 0);
				break;
			case 1:
				screen.renderSprite(x + 3, y - 14, sprite, true, 0);
				break;
			case 2:
				screen.renderSprite(x, y - 14, sprite, true, 0);
				break;
			case 3:
				screen.renderSprite(x + 3, y - 14, sprite, true, 0);
				break;
			default:
				System.err.println("item dir is invalid!");
				break;
			}
		}
	}
	
	public void renderInventory(Screen screen, int x, int y) {
		font.render(screen, getName(), x + 16, y - 2, true);
	}

	public boolean canAttack() {
		if (time <= 0) {
			time = animationTime + 15;
			return true;
		}
		return false;
	}

	public int critical() {
		double crit = animationTime / damage;
		if (crit < 0) crit = 1;
		if (random.nextInt(90) <= crit) return (int) (damage * 2.2);
		if (random.nextInt(4) == 0) return 1;
		return 0;
	}
}
