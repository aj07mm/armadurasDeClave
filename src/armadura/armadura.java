package armadura;
import java.applet.AudioClip;

import java.util.*;
import java.lang.Object;
import javax.print.DocFlavor.URL;
import javax.swing.text.html.HTMLDocument.Iterator;

import processing.core.PImage;
import processing.core.PApplet;
import arb.soundcipher.*;


public class armadura extends PApplet {
	
	//instancia ponteiro SoundCipher
	SoundCipher sc = new SoundCipher(this);
	
	//Random Number Generator
	Random randomGenerator = new Random();
	int key_ran = randomGenerator.nextInt(7);//key para tom randomico
    int key_ran_2 = randomGenerator.nextInt(2);//define acd "sus" ou "bemo"

	PImage clave, sustenido, bemol, seminima, seminima_invertida;
	String acd = "";//define o acidente
	int i,j;
	int xs=900;
	int ys=200;
	int value = 0;

	//Rectangle
	int rectX = 133;
	int rectY = 133;
	int rectW = 30;
	int rectH = 30;

	//array com numero de acidentes da armadura
	ArrayList<String> acdArmadura = new ArrayList<String> ();
	
	//contador
	public static int countX = -50;
	public static int countY = 0;
	public static int contador = 0;
	
	//escala
	float[] pitches = {60, 62, 64, 65, 67, 69, 71, 69, 67, 65, 64, 62, 60};//escala de C

	//contadores
	public static int b = 0;
	public String tom;

	public void setup() {
		fill(0, 0, 0);
		textSize(32);
		def_sus_bemo(key_ran_2);
		tom = tom(key_ran,acd);//define o tom na variavel tom que será usada em draw()
		clave = loadImage("sol.gif");
		sustenido = loadImage("sustenido.png");
		bemol = loadImage("bemol.png");
		seminima = loadImage("seminima.png");
		seminima_invertida = loadImage("seminima_invertida.png");
		size(xs, ys);
		background(255);
		copy(clave, 0, 0, 736, 133, 0, 0, xs, ys);
	}
	
	public void draw() {
		text(contador + "/" + tom , 800, 195);//Escreve o Tom no canto da tela
		//System.out.println(tom + "/" + acd);
		verifica_armadura(tom,acd);
		
		for(i=1;i<=6;i++){
			noFill();//mantem quadrados sem preenchimento
			if(this.acd == "bemo")
				bemo(i);//desenha quadros em config bemol
			if(this.acd == "sus")
				sus(i);//desenha quadros em config sus
			//if ( (mouseX > rectX && mouseX < rectX+rectW) && (mouseY > rectY && mouseY < rectY+rectH) ) {
					rect(rectX,rectY,rectW,rectH);
					 if (acd=="sus" && mousePressed && (mouseButton == LEFT)) {
						 if ( (mouseX > rectX && mouseX < rectX+rectW) && (mouseY > rectY && mouseY < rectY+rectH) ) {
						  image (sustenido,rectX,rectY);
						  sus_add_acd(rectX,rectY);
						 }
					  }
					 if (acd=="bemo" && mousePressed && (mouseButton == LEFT)) {
						 if ( (mouseX > rectX && mouseX < rectX+rectW) && (mouseY > rectY && mouseY < rectY+rectH) ) {
						 image (bemol,rectX,rectY);
						 bemo_add_acd(rectX,rectY);
						 }
					  }
			//}

		}
					
	}
	
	public void clear(){
		frameRate(60);
		copy(clave, 0, 0, 736, 133, 0, 0, xs, ys);
		this.acdArmadura.clear();
		
		//Random Number Generator

		this.key_ran = this.randomGenerator.nextInt(7);//key para tom randomico
		this.key_ran_2 = this.randomGenerator.nextInt(2);
		
		PImage clave, sustenido, bemol, seminima;
		def_sus_bemo(key_ran_2);//define o acidente

		
		//contador
		this.countX = -50;
		this.countY = 0;
		
		//contador
		this.b = 0;
		float[] pitches = {60, 62, 64, 65, 67, 69, 71, 69, 67, 65, 64, 62, 60};//escala de C
		this.pitches = pitches;
		this.tom = tom(this.key_ran,this.acd);//define o tom na variavel tom que será usada em draw()
		
		//incrementa contador
		this.contador+=1;

	}
	
	public void escala(String key,int countS){//function draw notes do Soundcipher
		
		float [] dynamics = {100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100};
		float[] durations = {4,4,4,4,4,4,4,4,4,4,4,4,40};

		if(this.b<=12){
			frameRate(1);
			sc.playNote(pitches[this.b], dynamics[this.b], durations[this.b]);
		}
		
        if(this.b<6){
        	//image (seminima,330+countX, (float) (95-countY+0.5+ 1.2) + countS);
        	image (seminima,330+countX, (float) (95-countY+0.5+ 1.2) - countS);//countS aumenta a altura da nota dependendo da escala
    		countX+=40;
    		countY+=16.2232;
    		this.b +=1;
        }else
        	if(this.b>=6 && this.b<=12){
					image (seminima,330+countX, (float) (98-countY+0.5+ 1.2)  - countS);		
					countX+=40;
					countY-=13.7232;
					this.b +=1;
		
			}
        
        if(this.b==13){
	        new java.util.Timer().schedule( 
	                new java.util.TimerTask() {
	                    @Override
	                    public void run() {
	                    	if(armadura.b == 13) 		
	                    		clear();
	                    }
	                }, 
	                1000 
	        );
        }
    }
		
	public String tom(int key, String acd){//Define tom randomico e array da escala
	
		if(acd=="sus"){
			switch (key) {
			case 0:
				tom = "C";
				for(j=0;j<=12;j++){
					this.pitches[j]+=0;
				}
				break;
			case 1:
				tom = "G";
				for(j=0;j<=12;j++){
					this.pitches[j]+=7;
				}
				break;
			case 2:
				tom = "D";
				for(j=0;j<=12;j++){
					this.pitches[j]+=2;
				}
				break;
			case 3:
				tom = "A";
				for(j=0;j<=12;j++){
					this.pitches[j]+=9;
				}
				break;
			case 4:
				tom = "E";
				for(j=0;j<=12;j++){
					this.pitches[j]+=4;
				}
				break;
			case 5:
				tom = "B";
				for(j=0;j<=12;j++){
					this.pitches[j]+=11;
				}
				break;
			case 6:
				tom = "F#";
				for(j=0;j<=12;j++){
					this.pitches[j]+=6;
				}
				break;
			}
		}
		
		if(acd=="bemo"){
			switch (key) {
			case 0:
				tom = "C";
				for(j=0;j<=12;j++){
					this.pitches[j]+=0;
				}
				break;
			case 1:
				tom = "F";
				for(j=0;j<=12;j++){
					this.pitches[j]+=5;
				}
				break;
			case 2:
				tom = "Bb";
				for(j=0;j<=12;j++){
					this.pitches[j]+=10;
				}
				break;
			case 3:
				tom = "Eb";
				for(j=0;j<=12;j++){
					this.pitches[j]+=3;
				}
				break;
			case 4:
				tom = "Ab";
				for(j=0;j<=12;j++){
					this.pitches[j]+=8;
				}
				break;
			case 5:
				tom = "Db";
				for(j=0;j<=12;j++){
					this.pitches[j]+=1;
				}
				break;
			case 6:
				tom = "Gb";
				for(j=0;j<=12;j++){
					this.pitches[j]+=6;
				}
				break;
			}
		}
		
		return tom;

	}
	
	public void sus(int i){//desenha quadrados sus
			switch (i) {//Sustenidos
				case 1://F
					rectX = 100;
					rectY = 15;
					break;
				case 2://E
					rectX = 250;
					rectY = 34;
					break;
				case 3://C
					rectX = 130;
					rectY = 63;
					break;
				case 4://G
					rectX = 160;
					rectY = 04;
					break;
				case 5://D
					rectX = 190;
					rectY = 47;
					break;
				case 6://A
					rectX = 220;
					rectY = 90;
					break;
			}
	}
	
	public void bemo(int i){//desenha quadrados bemo
			switch (i) {//Bemois
				case 1://F
					rectX = 100;
					rectY = 75;
					break;
				case 2://E
					rectX = 250;
					rectY = 63;
					break;
				case 3://C
					rectX = 130;
					rectY = 33;
					break;
				case 4://G
					rectX = 160;
					rectY = 93;
					break;
				case 5://D
					rectX = 190;
					rectY = 47;
					break;
				case 6://A
					rectX = 220;
					rectY = 105;
					break;
			}
	}
	
	public void sus_add_acd(int rectX, int recY){//Add acd_de_armadura do click a arraylist - sus
		if(rectX==100 && recY==15){//F
			acdArmadura.add("F");
		}
		
		if(rectX==250 && recY==34){//E
			acdArmadura.add("E");
		}
		
		if(rectX==130 && recY==63){//C
			acdArmadura.add("C");
		}
		
		if(rectX==160 && recY==04){//G
			acdArmadura.add("G");
		}
		
		if(rectX==190 && recY==47){//D
			acdArmadura.add("D");
		}
		
		if(rectX==220 && recY==90){//A
			acdArmadura.add("A");
		}
	}

	public void bemo_add_acd(int rectX, int recY){//Add acd_de_armadura do click a arraylist - bemo
		if(rectX==100 && recY==75){//B
			acdArmadura.add("B");
		}
		
		if(rectX==250 && recY==63){//C
			acdArmadura.add("C");
		}
		
		if(rectX==130 && recY==33){//E
			acdArmadura.add("E");
		}
		
		if(rectX==160 && recY==93){//A
			acdArmadura.add("A");
		}
		
		if(rectX==190 && recY==47){//D
			acdArmadura.add("D");
		}
		
		if(rectX==220 && recY==105){//G
			acdArmadura.add("G");
		}
	}

	public void verifica_armadura(String key, String acd){//verifica os clicks, aumenta countY e toca escala
		
		if(acd == "sus"){
			switch (key) {
				case "C"://zoa!
					if(!acdArmadura.contains("C") && !acdArmadura.contains("D") && !acdArmadura.contains("E") && !acdArmadura.contains("F")  && !acdArmadura.contains("G")  && !acdArmadura.contains("A") && !acdArmadura.contains("B")){
						escala(tom,0);
					}else{
						if(acdArmadura.contains("C") || acdArmadura.contains("D") || acdArmadura.contains("E") || acdArmadura.contains("F")  || acdArmadura.contains("G")  || acdArmadura.contains("A") || acdArmadura.contains("B")){
							alert();
						}
					}
				break;
				case "G":
					if(!acdArmadura.contains("C") && !acdArmadura.contains("D") && !acdArmadura.contains("E") && acdArmadura.contains("F")  && !acdArmadura.contains("G")  && !acdArmadura.contains("A") && !acdArmadura.contains("B")){
						escala(tom,60);
					}else{
						if(acdArmadura.contains("C") || acdArmadura.contains("D") || acdArmadura.contains("E") || acdArmadura.contains("G")  || acdArmadura.contains("A") || acdArmadura.contains("B")){
							alert();
						}
					}
					break;
				case "D":
					if(acdArmadura.contains("C") && !acdArmadura.contains("D") && !acdArmadura.contains("E") && acdArmadura.contains("F")  && !acdArmadura.contains("G")  && !acdArmadura.contains("A") && !acdArmadura.contains("B")){
						escala(tom,16);

					}else{	
						if(acdArmadura.contains("D") || acdArmadura.contains("E") || acdArmadura.contains("G")  || acdArmadura.contains("A") || acdArmadura.contains("B")){
							alert();
						}
					}
					break;
				case "A":
					if(acdArmadura.contains("C") && !acdArmadura.contains("D") && !acdArmadura.contains("E") && acdArmadura.contains("F")  && acdArmadura.contains("G")  && !acdArmadura.contains("A") && !acdArmadura.contains("B")){
						escala(tom,74);
					}else{
						if(acdArmadura.contains("D") || acdArmadura.contains("E") || acdArmadura.contains("A") || acdArmadura.contains("B")){
							alert();
						}
					}
					break;
				case "E":
					if(acdArmadura.contains("C") && acdArmadura.contains("D") && !acdArmadura.contains("E") && acdArmadura.contains("F")  && acdArmadura.contains("G")  && !acdArmadura.contains("A") && !acdArmadura.contains("B")){
						escala(tom,29);
					}else{
						if(acdArmadura.contains("E") || acdArmadura.contains("A") || acdArmadura.contains("B")){
							alert();
						}
					}
					break;
				case "B":
					if(acdArmadura.contains("C") && acdArmadura.contains("D") && !acdArmadura.contains("E") && acdArmadura.contains("F")  && acdArmadura.contains("G")  && acdArmadura.contains("A") && !acdArmadura.contains("B")){
						escala(tom,88);
					}else{
						if(acdArmadura.contains("E")  || acdArmadura.contains("B")){
							alert();
						}
					}
					break;
				case "F#":
					if(acdArmadura.contains("C") && acdArmadura.contains("D") && acdArmadura.contains("E") && acdArmadura.contains("F")  && acdArmadura.contains("G")  && acdArmadura.contains("A")){
						escala(tom,46);
					}
					break;
			}
		}
		if(acd == "bemo"){
			switch (key) {
				case "C"://zoa!
					if(!acdArmadura.contains("C") && !acdArmadura.contains("D") && !acdArmadura.contains("E") && !acdArmadura.contains("F")  && !acdArmadura.contains("G")  && !acdArmadura.contains("A") && !acdArmadura.contains("B")){
						escala(tom,0);
					}else{
						if(acdArmadura.contains("C") || acdArmadura.contains("D") || acdArmadura.contains("E") || acdArmadura.contains("F")  || acdArmadura.contains("G")  || acdArmadura.contains("A") || acdArmadura.contains("B")){
							alert();
						}
					}
				case "F":
					if(!acdArmadura.contains("C") && !acdArmadura.contains("D") && !acdArmadura.contains("E") && !acdArmadura.contains("F")  && !acdArmadura.contains("G")  && !acdArmadura.contains("A") && acdArmadura.contains("B")){
						//this.countS=45;
						escala(tom,45);
					}else{
						if(acdArmadura.contains("C") || acdArmadura.contains("D") || acdArmadura.contains("E") || acdArmadura.contains("F")  || acdArmadura.contains("G")  || acdArmadura.contains("A")){
							alert();
						}
					}
					break;
				case "Bb":
					if(!acdArmadura.contains("C") && !acdArmadura.contains("D") && acdArmadura.contains("E") && !acdArmadura.contains("F")  && !acdArmadura.contains("G") && !acdArmadura.contains("A") && acdArmadura.contains("B")){
						escala(tom,88);
					}else{
						if(acdArmadura.contains("C") || acdArmadura.contains("D") || acdArmadura.contains("F")  || acdArmadura.contains("G") || acdArmadura.contains("A")){
							alert();
						}
					}
					break;
				case "Eb":
					if(!acdArmadura.contains("C") && !acdArmadura.contains("D") && acdArmadura.contains("E") && !acdArmadura.contains("F")  && !acdArmadura.contains("G")  && acdArmadura.contains("A") && acdArmadura.contains("B")){
						escala(tom,29);
					}else{
						if(acdArmadura.contains("C") || acdArmadura.contains("D") ||  acdArmadura.contains("F")  || acdArmadura.contains("G")){
							alert();	
						}
					}
					break;
				case "Ab":
					if(!acdArmadura.contains("C") && acdArmadura.contains("D") && acdArmadura.contains("E") && !acdArmadura.contains("F")  && !acdArmadura.contains("G")  && acdArmadura.contains("A") && acdArmadura.contains("B")){
						escala(tom,74);
					}else{
						if(acdArmadura.contains("C") || acdArmadura.contains("F")  || acdArmadura.contains("G")){
							alert();
						}
						
					}
					break;
				case "Db":
					if(!acdArmadura.contains("C") && acdArmadura.contains("D") && acdArmadura.contains("E") && !acdArmadura.contains("F")  && acdArmadura.contains("G") && acdArmadura.contains("A") && acdArmadura.contains("B")){
						escala(tom,16);
					}else{
						if(acdArmadura.contains("C") || acdArmadura.contains("F")){
							alert();
						}
						
					}
					break;
				case "Gb":
					if(acdArmadura.contains("C") && acdArmadura.contains("D") && acdArmadura.contains("E") && !acdArmadura.contains("F")  && acdArmadura.contains("G") && acdArmadura.contains("A") && acdArmadura.contains("B")){
						escala(tom,46);
					}
					break;
			}
		}
		
	}
	
	public void def_sus_bemo(int key_ran_2){
		if(key_ran_2 == 1)
			this.acd = "sus";
		else
			this.acd = "bemo";
	}
	
	public void alert(){
		sc.playMidiFile("alert.mid",30);
		noLoop();
	}	
		
}

	

	
	
