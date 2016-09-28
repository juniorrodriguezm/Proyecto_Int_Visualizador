package visualizador;

import java.io.File;
import java.io.FilenameFilter;

import javax.swing.filechooser.FileNameExtensionFilter;

import processing.core.PApplet;
import processing.core.PImage;

public class Logica {

	PApplet app;
	int correr = 0, numImagenes = 4, rsize = 0, x = 0, y = 0, rota = 0, posY = 87, var = 0;
	boolean zoom = true, rotacion = false, full = false;
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

		imagenesViz[correr].resize(547 + rsize, 363 + rsize);

		// carga la lista

		for (int i = var; i < numImagenes; i++) {
			imagenes[i].resize(141, 100);
			app.image(imagenes[i], 134, 120 * i + posY);

		}

		// rotacion imagen
		if (correr != correr) {
			rotacion = false;
			rota = 0;
		}

		if (rotacion == false) {
			app.image(imagenesViz[correr], 527 + x, 133 + y);
		} else if (rotacion == true) {

			System.out.println(rota);
			switch (rota) {
			case 90:
				app.translate(1110, -450);
				app.rotate(app.radians(rota));
				app.image(imagenesViz[correr], 513 + x, 120 + y);
				break;

			case 180:
				app.translate(1590, 615);
				app.rotate(app.radians(rota));
				app.image(imagenesViz[correr], 513 + x, 120 + y);
				break;

			case 270:
				app.translate(-1590, 615);
				app.rotate(app.radians(rota));
				app.image(imagenesViz[correr], 513 + x, 120 + y);
				break;

			case -90:
				app.translate(50, 50);
				app.rotate(app.radians(rota));
				app.image(imagenesViz[correr], 513 + x, 120 + y);
				break;

			case -180:
				app.translate(1590, 615);
				app.rotate(app.radians(rota));
				app.image(imagenesViz[correr], 513 + x, 120 + y);
				break;

			case -270:
				app.translate(-1590, 615);
				app.rotate(app.radians(rota));
				app.image(imagenesViz[correr], 513 + x, 120 + y);
				break;
			}

		}

		if (full == true) {
			imagenesViz[correr].resize(1200, 754);
			app.image(imagenesViz[correr], 0, 0);
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

	public void zoom() {
		// Resize
		if (rsize >= 100) {
			zoom = false;
		} else if (rsize <= -180) {
			zoom = false;
		}

		if (zoom == true) {
			if (app.mouseX < 1140 && app.mouseX > 1080 && app.mouseY < 682 && app.mouseY > 619) {
				rsize += 10;
				x -= 5;
				y -= 5;

				System.out.println(rsize);
			}

			if (app.mouseX < 1052 && app.mouseX > 993 && app.mouseY < 682 && app.mouseY > 619) {
				rsize -= 10;
				System.out.println(rsize);
				x += 5;
				y += 5;
			}
		}

	}

	public void rotar() {
		if (app.mouseX < 520 && app.mouseX > 455 && app.mouseY < 682 && app.mouseY > 619) {
			rotacion = true;
			rota -= 90;
			if (rota < -270) {
				rotacion = false;
				rota = 0;
			}

		}

		if (app.mouseX < 624 && app.mouseX > 540 && app.mouseY < 682 && app.mouseY > 619) {
			rotacion = true;

			rota += 90;
			if (rota > 270) {
				rotacion = false;
				rota = 0;
			}

		}
	}

	public void fullScreen() {
		if (app.mouseX < 860 && app.mouseX > 743 && app.mouseY < 685 && app.mouseY > 619) {
			full = true;

		}
	}

}
