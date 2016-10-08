package com.AGameStudio.NewMech.quests;

import java.util.ArrayList;
import java.util.List;

import com.AGameStudio.NewMech.gfx.Font;
import com.AGameStudio.NewMech.gfx.Screen;

public class QuestLog {
	
	private List<Quest> completed = new ArrayList<Quest>();
	private List<Quest> started = new ArrayList<Quest>();
	private Font font;
	
	private int time = 0;
	
	public QuestLog() {
		this.font = new Font();
	}
	
	public void sort() {
		if(started.size() > 0) {
			for(int i = 0; i < started.size(); i++) {
				Quest quest = started.get(i);
				if(quest == null) {
					started.remove(quest);
					return;
				}
				if(quest.isComplete() && quest.rewarded()) {
					completed.add(quest);
					started.remove(quest);
				}
			}
		}
	}
	
	public void tick() {
		time++;
		if(time % 120 == 0) sort();
	}
	
	public boolean startable(Quest quest) {
		if(find(quest)) return false;
		return true;
	}
	
	public List<Quest> getCompleted() {
		return completed;
	}
	
	public List<Quest> getStarted() {
		return started;
	}
	
	public void add(Quest quest, boolean fin) {
		if(!find(quest)) {
			if(fin) completed.add(quest);
			if(!fin) started.add(quest);
		}
	}
	
	public boolean hasStarted(String name) {
		for(int i = 0; i < started.size(); i++) {
			if(started.get(i) != null && started.get(i).getName().equals(name)) return true;
		}
		return false;
	}
	
	public boolean hasCompleted(String name) {
		for(int i = 0; i < completed.size(); i++) {
			if(completed.get(i).getName().equals(name)) return true;
		}
		return false;
	}
	
	public boolean find(Quest quest) {
		if(quest != null) {
			for(int i = 0; i < started.size(); i++) {
				Quest q = started.get(i);
				if(q != null && q.getName().equals(quest.getName())) return true;
			}
			for(int i = 0; i < completed.size(); i++) {
				Quest q = completed.get(i);
				if(q != null && q.getName().equals(quest.getName())) return true;
			}
		}
		return false;
	}
	
	public Quest get(Quest quest) {
		for(int i = 0; i < started.size(); i++) {
			Quest q = started.get(i);
			if(q != null && q.getName().equals(quest.getName())) return q;
		}
		for(int i = 0; i < completed.size(); i++) {
			Quest q = completed.get(i);
			if(q != null && q.getName().equals(quest.getName())) return q;
		}
		return null;
	}
	
	public void render(Screen screen) {
		int xo = screen.xOffset;
		int yo = screen.yOffset;
		font.render(screen, "Started Quests", xo + 10, yo + 100, true);
		for(int i = 0; i < started.size(); i++) {
			Quest quest = started.get(i);
			if(quest != null) font.render(screen, "" + quest.getTitle() + "	" + quest.getProgress() + "/" + quest.getAmount(), xo + 40, yo + 150 + 16 * i, true);
		}
		font.render(screen, "Completed Quests", xo + 10, yo + 350, true);
		for(int i = 0; i < completed.size(); i++) {
			font.render(screen, "" + completed.get(i).getTitle(), xo + 40, yo + 400 + 16 * i, true);
		}
	}
}
