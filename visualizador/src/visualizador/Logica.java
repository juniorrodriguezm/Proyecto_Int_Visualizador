package visualizador;

import java.io.File;
import java.io.FilenameFilter;

import javax.swing.filechooser.FileNameExtensionFilter;

import processing.core.PApplet;
import processing.core.PImage;

public class Logica {

	PApplet app;
	private PImage mas, menos, rotar;
	private String[] tipos = { ".png", ".jpg", ".gif" };
	private PImage[] imagenes = new PImage[20];

	public Logica(PApplet app) {

		this.app = app;

		// file que conecta a la carpeta
		File carpeta = new File("../data");

		// arreglo con los tipos de archivos
		String[] tipos = new String[] { "jpg", "png", "bmp" };

		// identifica las extensiones de las imagenes

		FilenameFilter filtroIma = new FilenameFilter() {

			public boolean accept(File carpeta, String nombre) {
				for (final String ext : tipos) {
					if (nombre.endsWith("." + ext)) {
						return true;
					}
				}
				return false;
			}
		};

		// arreglo de tipo File que almacena la direcion de la imagen
		File[] archivos = carpeta.listFiles(filtroIma);


		// carga imagenes
		for (int i = 0; i < archivos.length; i++) {
			imagenes[i] = app.loadImage(archivos[i].toString());
			//System.out.println(archivos.length);

		}

		mas = app.loadImage("../interfaz/mas.png");
		menos = app.loadImage("../interfaz/menos.png");
		rotar = app.loadImage("../interfaz/rotar.png");

	}
	


	public void pintar() {

		app.image(rotar, 1160, 670);
		app.image(menos, 1120, 670);
		app.image(mas, 1080, 670);
		app.imageMode(app.CENTER);

		//carga la imagen principal
		app.image(imagenes[0], 700, 330);
		
		
		//carga la lista
		/*
		for(int i=0; i<imagenes.length; i++){
			imagenes[i].resize(100, 75);
			app.image(imagenes[i], 80, 90 * i+20);
			
		}*/
		
	}

}
