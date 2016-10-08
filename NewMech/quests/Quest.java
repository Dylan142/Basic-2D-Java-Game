package com.AGameStudio.NewMech.quests;

import com.AGameStudio.NewMech.XML;
import com.AGameStudio.NewMech.item.Item;
import com.AGameStudio.NewMech.item.WeaponItem;

public class Quest {
	
	private final String name;
	private final String objective;
	private String target;
	private String title;
	private final int type;
	private final int amount;
	private final Item reward;
	private int progress;
	private final int exp;
	private boolean rewarded;
	
	public Quest(String name) {
		this.name = name;
		this.type = Integer.parseInt(XML.read("//quest[@name='" + name + "']/type", "quest"));
		this.objective = XML.read("//quest[@name='" + name + "']/objective", "quest");
		this.amount = Integer.parseInt(XML.read("//quest[@name='" + name + "']/amount", "quest"));
		String rewardName = XML.read("//quest[@name='" + name + "']/reward", "quest");
		this.exp = Integer.parseInt(XML.read("//quest[@name='" + name + "']/experience", "quest"));
		this.title = objective + " " + amount + " ";
		this.target = "";
		if(this.amount > 1) {
			switch(type) {
			case 3:
				target = "enemies";
				break;
			case 17:
				target = "bandits";
				break;
			default:
				System.err.println("quest type invalid!");
				break;
			}
		}
		else {			
			switch(type) {
			case 3:
				target = "enemy";
				break;
			case 17:
				target = "bandit";
				break;
			default:
				System.err.println("quest type invalid!");
				break;
			}
		}
		this.title = this.title + this.target;
		if(!rewardName.equals("0")) this.reward = new WeaponItem(rewardName);
		else this.reward = null;
	}
	
	public Item getReward() {
		return reward;
	}
	
	public int getExperience() {
		return exp;
	}
	
	public boolean isComplete() {
		return progress >= amount;
	}
	
	public int getProgress() {
		return progress;
	}
	
	public void setProgress(int progress) {
		this.progress = progress;
	}
	
	public void add(int amount) {
		this.progress += amount;
	}
	
	public int getAmount() {
		return amount;
	}
	
	public boolean rewarded() {
		return rewarded;
	}
	
	public void setRewarded(boolean rewarded) {
		this.rewarded = rewarded;
		switch(getObjective()) {
		case "slay":
			this.title = "slain ";
			break;
		case "gather":
			this.title = "gathered ";
			break;
		case "default":
			System.err.println("quest title is invalid!");
			break;
		}
		this.title = this.title + this.amount + " " + this.target;
	}
	
	public int getType() {
		return type;
	}
	
	public String getTarget() {
		return target;
	}
	
	public String getName() {
		return name;
	}
	
	public String getTitle() {
		return title;
	}
	
	public String getObjective() {
		return objective;
	}
}
