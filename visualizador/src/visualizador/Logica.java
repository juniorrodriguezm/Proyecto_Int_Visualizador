package visualizador;

import java.io.File;
import java.io.FilenameFilter;

import javax.swing.filechooser.FileNameExtensionFilter;

import processing.core.PApplet;
import processing.core.PImage;

public class Logica {

	PApplet app;
	int correr = 0, numImagenes = 4, posY = 87, var = 0;
	private PImage mas, menos, rotar, interfaz;
	private String[] tipos = { ".png", ".jpg", ".gif" };
	private PImage[] imagenes = new PImage[20];
	private PImage[] imagenesViz = new PImage[20];

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
			imagenesViz[i] = app.loadImage(archivos[i].toString());
			// System.out.println(archivos.length);

		}

		mas = app.loadImage("../interfaz/mas.png");
		menos = app.loadImage("../interfaz/menos.png");
		rotar = app.loadImage("../interfaz/rotar.png");
		interfaz = app.loadImage("../interfaz/interfaz.jpg");

	}

	public void pintar() {

		app.image(interfaz, 0, 0);

		// carga la imagen principal

		imagenesViz[correr].resize(547, 363);

		// carga la lista

		for (int i = var; i < numImagenes; i++) {
			imagenes[i].resize(141, 100);
			app.image(imagenes[i], 134, 120 * i + posY);

		}

	}

	public void clic() {

		System.out.println("numImagenes" + numImagenes);
		System.out.println("var" + var);
		System.out.println(posY);

		// Adelante imagen principal
		if (app.mouseX < 966 && app.mouseX > 860 && app.mouseY < 682 && app.mouseY > 621) {
			correr += 1;
		} else
		// Atras imagen principal
		if (app.mouseX < 700 && app.mouseX > 633 && app.mouseY < 680 && app.mouseY > 619) {
			// correr = 19;
			correr -= 1;
		}

		if (correr > 19) {
			correr = 0;

		}
		if (correr < 0) {
			correr = 19;
		}

		// correr imagenes pequenas
		if (app.mouseX > 177 && app.mouseX < 234 && app.mouseY > 562 && app.mouseY < 600) {
			posY -= 120;
			numImagenes += 1;
			var += 1;
		}

		// indicaciones para recorrer infinitamente las fotos
		if (numImagenes < 0) {
			numImagenes = 19;
		}

		if (var < 0) {
			var = 19;
		}

		if (numImagenes >= 20) {
			numImagenes = 4;
			posY += 1917;
		}

		if (var >= 16) {
			var = 0;
		}

	}

}
