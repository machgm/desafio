package controller;

import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import model.AcessoDB;
import model.Loader;

public class NovaNoticiaController implements Initializable {


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
    private Button btnVoltar;
    
    private AcessoDB acessoDB;

    @FXML
    void operacaoSalvar(ActionEvent event) {
    	
    	SimpleDateFormat formatterData = new SimpleDateFormat("dd/MM/yyyy");
    	SimpleDateFormat formatterHora = new SimpleDateFormat("HH:mm:ss:SSS");
    	
    	acessoDB = new AcessoDB();
    	
		Date date = new Date();
		Boolean violation = false;
		String listaviolation = "";

		if (tfTitulo.getText().equals("")) {
			violation = true;
			listaviolation += "- Titulo\n";
		}
		if (tfHora.getText().equals("")) {
			violation = true;
			listaviolation += "- Hora\n";
		}
		if (tfDescricao.getText().equals("")) {
			violation = true;
			listaviolation += "- Descricao\n";
		}
		if (tfData.getText().equals("")) {
			violation = true;
			listaviolation += "- Data\n";
		}

		if (violation) {
			Alert alerta = new Alert(AlertType.ERROR,
					"Validação dos dados inconsistente. Verifique os campo:\n\n\n" + listaviolation);
			alerta.showAndWait();

		} else {

			String sql = "insert into noticias (titulo,descricao,datacad,horacad) VALUES " + "('"
					+ tfTitulo.getText() + "','" + tfDescricao.getText() + "','" + tfData.getText() + "','"
					+ tfHora.getText() + "')";

			System.out.println(sql);
			
			if (acessoDB.Insert(sql)) {
				Alert alerta1 = new Alert(AlertType.WARNING, "Registro Inserido com sucesso.");
				alerta1.showAndWait();

			} else {

				Alert alerta1 = new Alert(AlertType.ERROR, "Falha ao Inserir Registro.");
				alerta1.showAndWait();
			}

		}
    	
    }

    @FXML
    void operacaoVoltar(ActionEvent event) {
    	try {
    		System.out.println("Entrou");
			Parent parentHomePage;
			parentHomePage = FXMLLoader.load(getClass().getResource("/view/Principal.fxml"));
			Scene homePage = new Scene(parentHomePage);
			Stage appStage = (Stage) ((Node) event.getSource()).getScene().getWindow();

			appStage.setScene(homePage);
			appStage.show();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
    }
    
    
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		
		Loader.loadLogo(imgViewLogo, "src/resources/logo.jpg");	
		
		
	}

}
