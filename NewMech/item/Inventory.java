package com.AGameStudio.NewMech.item;

import java.util.ArrayList;
import java.util.List;

public class Inventory {
	
	public List <Item> items = new ArrayList<Item>();
	
	public void add(Item item) {
		add(items.size(), item);
	}
	
	public void add(int slot, Item item) {
		Item has = findItem(item);
		if(has == null) items.add(slot, item);
		else has.count += item.count;
	}
	
	private Item findItem(Item item) {
		for(int i = 0; i < items.size(); i++) {
			Item has = items.get(i);
			if(has.getClass() == item.getClass()) return has;
		}
		return null;
	}
	
	public boolean hasItem(Item item, int count) {
		Item i = findItem(item);
		if(i == null) return false;
		return i.count >= item.count;
	}
	
	public boolean removeItem(Item item, int count) {
		Item i = findItem(item);
		if(i == null) return false;
		if(i.count < count) return false;
		i.count -= count;
		if(i.count <= 0) items.remove(i);
		return true;
	}
	
	public int count(Item item) {
		Item has = findItem(item);
		if(has != null) return item.count;
		return 0;
	}
	
	public int findSlot(Item item) {
		for(int i = 0; i < items.size(); i++) {
			Item has = items.get(i);
			if(has == item) return i;
		}
		return -1;
	}
}
