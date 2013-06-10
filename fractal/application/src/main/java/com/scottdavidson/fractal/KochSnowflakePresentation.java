package com.scottdavidson.fractal;

import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import processing.core.PApplet;
import processing.core.PFont;
import processing.core.PImage;

public class KochSnowflakePresentation extends PApplet {
	int width = 800, height = 800;
//	PImage scope, background, nightscope;
//	PFont font;
//	ArrayList<Zombie> zombies;
//	ZombieSpawner zspawner;
//	int counter = 1;
//	int playerhealth = 100, score = 0, ammo = 5;
//	boolean scratch = false;
//	int gameOverCount;
//	boolean regScope = true;

	public void setup() {
		size(width, height);

//		// background=loadImage("D:\\_Pictures\\foggy_zombies.jpg");
//		// scope= loadImage("D:\\_Pictures\\scope.png");
//		background = loadImage("thick_fog.jpg");
//		scope = loadImage("scope.png");
//		nightscope = loadImage("scopegood1nightvis.png");
//		font = createFont("Georgia", 20);
//		textFont(font);
//
//		zombies = new ArrayList<Zombie>();
//		zspawner = new ZombieSpawner(this);

	}

	public void draw() {
//		if (gameOver() == false) {
//
//			image(background, 0, 0, 800, 800);
//			
//			//Spawn Zombies
//			counter--;
//			if (counter == 0) {
//				Zombie z = zspawner.zombieSpawn();
//				zombies.add(z);
//
//				counter = 100;
//			}
//
//			for (int i = zombies.size() - 1; i > 0; i--) {
//				Zombie b = zombies.get(i);
//				b.update();
//				b.draw();
//
//				if (b.tooClose()) {
//					playerhealth = playerhealth - 100;
//					scratch = true;
//
//				}
//
//			}
//
//			// sniper scope
//			if (regScope == true) {
//				image(scope, mouseX - 530, mouseY - 275); // scope(original)
//			} else {
//				image(nightscope, mouseX - 530, mouseY - 275); // night vision
//																// scope
//			}
//			fill(0, 0, 0);
//			rect(mouseX - 200, 0, -width - (mouseX - 200), height);
//			rect(mouseX + 200, 0, width - (mouseX + 200), height);
//			rect(0, mouseY - 200, width, -height - (mouseY - 200));
//			rect(0, mouseY + 200, width, height - (mouseY + 200));
//			
//			
//			if (scratch == true) {
//				image(loadImage("scratch.png"), 0, 0, width, height);
//				scratch = false;
//			}
//
//			//On Screen Displays
//			fill(255, 0, 0);
//			text("Score: " + score, width - 100, 20);
//			text("Press ''R'' to reload", 5, 45);
//
//			text(ammo, 25, height - 10);
//			image(loadImage("9mm.png"), 10, height - 35, 15, 30);
//
//			if (ammo == 0) {
//				text("RELOAD", width / 2 - 50, height / 2);
//			}
//
//			if (regScope == true) {
//				fill(255, 255, 255);
//				text("Press ''N'' to change to the night vision scope", 5, 20);
//			} else {
//				fill(0, 255, 0);
//				text("Press ''N'' to change to the regular scope", 5, 20);
//			}
//
//			
//			
//			updateKeys();
//
//		}
//
//		//End Game
//		if (gameOver() == true) {
//			gameOverCount++;
//			// System.out.println(gameOverCount);
//			if (gameOverCount > 60) {
//				image(background, 0, 0, 800, 800);
//
//				for (int i = zombies.size() - 1; i > 0; i--) {
//					Zombie b = zombies.get(i);
//					b.draw();
//				}
//
//				image(loadImage("GameOver.png"), 0, -160, width, height);
//
//			}
//
//			fill(255, 0, 0);
//			text("Score: " + score, width - 100, 20);
//
//		}

	}

//	public boolean gameOver() {
//		if (playerhealth <= 0) {
//			return true;
//		}
//		return false;
//	}
//
//	private void updateKeys() {
//		if (checkKeyReleased(KeyEvent.VK_N)) {
//			if (regScope == true) {
//				regScope = false;
//			} else {
//				regScope = true;
//			}
//
//			removeKeyReleased(KeyEvent.VK_N);
//
//		}
//
//		if (checkKeyReleased(KeyEvent.VK_R)) {
//			ammo = 5;
//			removeKeyReleased(KeyEvent.VK_R);
//			System.out.println("jkfdsa;");
//		}
//	}
//
//	public Set<Integer> keysReleased = new HashSet<Integer>();
//
//	public boolean checkKeyReleased(int keycode) {
//		return this.keysReleased.contains(keycode);
//	}
//
//	public void removeKeyReleased(int keycode) {
//		this.keysReleased.remove(keycode);
//	}
//
//	public void mousePressed() {
//		if (ammo > 0) {
//			ammo--;
//			for (int i = zombies.size() - 1; i > 0; i--) {
//				Zombie b = zombies.get(i);
//
//				if (b.isOver(b, mouseX, mouseY) && mousePressed) {
//					zombies.remove(b);
//					score = score + 10;
//				}
//			}
//		}
//	}
//
//	 @Override
//	 public void keyPressed() {
//	 // add key to the list of keys held down
//	 // with processing, the KeyEvent object is always available as
//	 // "keyEvent",
//	 // the getKeyChar() is already in the variable 'key', and getKeyCode()
//	 // is in the variable 'keyCode'.
////	 this.keysPressed.add(keyCode);
//		 
//	  System.out.println("keys in current held list: ");
//	 }
//
//	public void keyReleased() {
//		// add key from the list of keys released
//		this.keysReleased.add(keyCode);
//		System.out.println("keyreleased");
//	}
//
//	public static void main(String[] args) {
//		// TODO Auto-generated method stub
//
//	}

}
