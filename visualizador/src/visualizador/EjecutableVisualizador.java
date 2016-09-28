package visualizador;

import processing.core.*;

public class EjecutableVisualizador extends PApplet {
	Logica app;

	public void setup() {
		size(1200, 700);

		app = new Logica(this);
	}

	public void draw() {
		background(255);
		app.pintar();
		
	}
	
	public void mousePressed(){
		System.out.println("x"+mouseX);
		System.out.println("y"+mouseY);
		app.clic();
		
	}
}
