package com.AGameStudio.NewMech;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class File {
	
	public static String[] copy() {
		BufferedReader reader;
		String[] temp = new String[50000];
		try {
			reader = new BufferedReader(new FileReader("data.txt"));
			int i = 0;
			while((temp[i] = reader.readLine()) != null) {
				i++;
			}
			reader.close();
		}catch(IOException e) {
			e.printStackTrace();
		}
		return temp;
	}
	
	public static void read() {
		BufferedReader reader;
		String temp;
		try {
			reader = new BufferedReader(new FileReader("data.txt"));
			while((temp = reader.readLine()) != null) {
				System.out.println("Output: " + temp);
			}
			reader.close();
		}catch(IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void readLine(int line) {
		BufferedReader reader;
		String temp = null;
		try {
			reader = new BufferedReader(new FileReader("data.txt"));
			while(line > 0) {
				temp = reader.readLine();
				line--;
			}
			
			System.out.println("file output: " + temp);
			reader.close();
			} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void write(String[] lines) {
		BufferedWriter writer;
		try {
			writer = new BufferedWriter(new FileWriter("data.txt", true));
			for(int i = 0; i < lines.length; i++) {
				if(lines[i] != null) {
					if(!lines[i].isEmpty()) {
						writer.write(lines[i]);
						writer.newLine();
					}					
				}
			}
			writer.close();
		}catch(IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void write() {
		BufferedWriter writer;
		try {
			writer = new BufferedWriter(new FileWriter("data.txt", true));
			writer.write("Hi, do you want some pizza?");
			writer.newLine();
			writer.close();
		}catch(IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void clear() {
		BufferedWriter writer;
		try {
			writer = new BufferedWriter(new FileWriter("data.txt", false));
			writer.write("");
			writer.close();
		}catch(IOException e) {
			e.printStackTrace();
		}
	}
}
