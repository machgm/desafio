package model;

import java.io.File;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Loader {

	
	public static void loadLogo (ImageView viewer, String caminhoImagem) {
		
		File file = new File(caminhoImagem);
        Image image = new Image(file.toURI().toString());
        viewer.setImage(image);
	}
}
