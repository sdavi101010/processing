package com.scottdavidson.emergence.app;

import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import processing.core.PApplet;
import processing.core.PFont;
import processing.core.PImage;


public class App extends PApplet {
	int width = 800, height = 800;
	PImage img, background;
	PFont font;
	ArrayList<Zombie> zombies;
	ZombieSpawner zspawner;
	int counter = 1;
	int playerhealth=100;
	
	public void setup(){
		size(width, height);
		
//		background=loadImage("D:\\_Pictures\\foggy_zombies.jpg");
//		img= loadImage("D:\\_Pictures\\scope.png");
		background=loadImage("thick_fog.jpg");
		img= loadImage("scope.png");
		font= createFont("Georgia", 22);
		textFont(font);
		
		zombies = new ArrayList<Zombie>();
		zspawner = new ZombieSpawner(this);
		
		
		
		
	}
	
	
	
	public void draw(){
		if(gameOver()==false){

			image(background,0,0,800,800);
			counter--;
			if(counter == 0){
				Zombie z = zspawner.zombieSpawn();
				zombies.add(z);
				System.out.println(z);
				counter = 100;
			}



			for(int i=zombies.size()-1; i>0; i--){
				Zombie b = zombies.get(i);
				b.update();
				b.draw();


				if(b.isOver(b, mouseX, mouseY) && mousePressed) {
					zombies.remove(b);
				}

				if(b.tooClose()){
					playerhealth = playerhealth - 100;

				}
			}


		

		
		
		
		
		
		
		
		
		
		
		
		
		
			//			sniper scope
			image(img,mouseX-530,mouseY-275);
			fill(0,0,0);
			rect(mouseX-200,0,-width-(mouseX-200),height);
			rect(mouseX+200,0,width-(mouseX+200),height);					
			rect(0,mouseY-200,width,-height-(mouseY-200));
			rect(0,mouseY+200,width,height-(mouseY+200));
		
		}
		
		
		
		
		
		
	}
	
	public boolean gameOver(){
		if(playerhealth <= 0){
			return true;
		}
		return false;
	}

	private void updateKeys() {
		
	}
	
	public Set<Integer> keysPressed = new HashSet<Integer>();
	
	public boolean checkKeyPressed(int keycode) {
		return this.keysPressed.contains(keycode);
	}
	
	public void keyPressed() {
		// add key to the list of keys held down
		// with processing, the KeyEvent object is always available as
		// "keyEvent",
		// the getKeyChar() is already in the variable 'key', and getKeyCode()
		// is in the variable 'keyCode'.
		this.keysPressed.add(keyCode);
		
		// println("keys in current held list: " + this.keysPressed.toString());
	}
	
	public void keyReleased() {
		// remove key from the list of keys held down
		this.keysPressed.remove(keyCode);
	}
	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
