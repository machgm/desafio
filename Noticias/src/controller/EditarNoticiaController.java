package controller;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import model.Loader;

public class EditarNoticiaController implements Initializable {

	@FXML
	private ImageView imgViewLogo;

	@FXML
	private Button btnSalvar;

	@FXML
	private TextField tfTitulo;

	@FXML
	private TextField tfData;

	@FXML
	private TextField tfHora;

	@FXML
	private TextArea tfDescricao;

	@FXML
	void operacaoSalvar(ActionEvent event) {

	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub

		Loader.loadLogo(imgViewLogo, "src/resources/logo.jpg");	
	}

	
	public void preencherDados(String titulo, String descricao, String data, String hora) {
		tfData.setText(data);
		tfDescricao.setText(descricao);
		tfHora.setText(hora);
		tfTitulo.setText(titulo);
		// TODO Auto-generated method stub
		
	}
}
