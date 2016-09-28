package visualizador;

import processing.core.PImage;

public class Imagen {
	private int tamano;
	public String nombre, tipo;

	PImage image;

	public Imagen(int tamano, String tipo, String nombre, PImage image) {
		super();
		this.tamano = tamano;
		this.tipo = tipo;
		this.nombre = nombre;
		this.image = image;

		String[] res = nombre.split(".");
		this.nombre = res[0];
		this.tipo = res[1];
		
		
	}

}
