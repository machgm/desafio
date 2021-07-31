package controller;

import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import model.AcessoDB;
import model.Loader;
import model.Noticia;

public class PrincipalController implements Initializable {

	Noticia noticia = null;

	AcessoDB acessoDB = new AcessoDB();

	String id = null;

	@FXML
	private ImageView imgViewLogo;

	@FXML
	private TableView<Noticia> tblNoticias;

	@FXML
	private TableColumn<Noticia, Integer> tcCodigo;

	@FXML
	private TableColumn<Noticia, String> tcTitulo;

	@FXML
	private TableColumn<Noticia, String> tcDataHora;

	// @FXML
	// private TableColumn<Noticia, String> tcAcoes;

	@FXML
	private Button btnDeletar;

	@FXML
	void operacaoDeletar(ActionEvent event) {
		if (id != null) {
			acessoDB = new AcessoDB();
			String sql = "DELETE FROM NOTICIAS WHERE CODNOTICIA = " + noticia;
			acessoDB.Delete(sql);

		} else {

		}
	}

	@FXML
	private Button btnAdicionar;

	@FXML
	void adicionarNoticia(ActionEvent event) {
		try {
			Parent parentAdicionar;
			parentAdicionar = FXMLLoader.load(getClass().getResource("../view/AdicionarNoticia.fxml"));
			Scene sceneAdicionar = new Scene(parentAdicionar);
			Stage appStage = (Stage) ((Node) event.getSource()).getScene().getWindow();

			appStage.setScene(sceneAdicionar);
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

		tblNoticias.setRowFactory(tv -> {
			TableRow<Noticia> row = new TableRow<>();
			row.setOnMouseClicked(event -> {
				if (event.getClickCount() == 2 && (!row.isEmpty())) {
					Noticia noticia = row.getItem();
					System.out.println("Double click on: " + noticia.getId());

					FXMLLoader loader = new FXMLLoader();

					try {
						loader.load();
						Parent parentAdicionar;
						parentAdicionar = FXMLLoader.load(getClass().getResource("../view/EditarNoticia.fxml"));
						Scene sceneAdicionar = new Scene(parentAdicionar);
						Stage appStage = (Stage) ((Node) event.getSource()).getScene().getWindow();

						

						appStage.setScene(sceneAdicionar);
						appStage.show();
					} catch (IOException e1) { // TODO Auto-generated catch block e1.printStackTrace(); }

					}
					;

				}
			});
			return row;
		});

		tcCodigo.setCellValueFactory(new PropertyValueFactory<>("id"));
		tcTitulo.setCellValueFactory(new PropertyValueFactory<>("titulo"));
		tcDataHora.setCellValueFactory(new PropertyValueFactory<>("dataHora"));
		/*
		 * // add cell of button edit Callback<TableColumn<Noticia, String>,
		 * TableCell<Noticia, String>> cellFoctory = ( TableColumn<Noticia, String>
		 * param) -> { // make cell containing buttons final TableCell<Noticia, String>
		 * cell = new TableCell<Noticia, String>() { private FXMLLoader loader;
		 * 
		 * @Override public void updateItem(String item, boolean empty) {
		 * super.updateItem(item, empty);
		 * 
		 * if (empty) { setGraphic(null); setText(null);
		 * 
		 * } else {
		 * 
		 * Button deletar = new Button("Deletar"); Button editar = new Button("Editar");
		 * 
		 * deletar.setOnMouseClicked((Event event) -> {
		 * 
		 * try { noticia = tblNoticias.getSelectionModel().getSelectedItem(); String
		 * query = "DELETE FROM `noticias` WHERE codnoticias  =" + noticia.getId();
		 * acessoDB.Delete(query);
		 * 
		 * } catch (Exception ex) { ex.printStackTrace(); }
		 * 
		 * }); editar.setOnMouseClicked((Event event) -> {
		 * 
		 * noticia = tblNoticias.getSelectionModel().getSelectedItem(); FXMLLoader
		 * loader = new FXMLLoader();
		 * loader.setLocation(getClass().getResource("../view/EditarNoticia.fxml")); try
		 * { loader.load(); } catch (IOException ex) { ex.printStackTrace(); }
		 * 
		 * 
		 * Parent parent = loader.getRoot(); Stage stage = new Stage();
		 * stage.setScene(new Scene(parent)); stage.initStyle(StageStyle.UTILITY);
		 * stage.show();
		 * 
		 * try { loader.load(); Parent parentAdicionar; parentAdicionar = FXMLLoader
		 * .load(getClass().getResource("../view/AdicionarNoticia.fxml")); Scene
		 * sceneAdicionar = new Scene(parentAdicionar); Stage appStage = (Stage) ((Node)
		 * event.getSource()).getScene().getWindow();
		 * 
		 * EditarNoticiaController controller = loader.getController();
		 * controller.preencherDados(noticia.getTitulo(), noticia.getDescricao(),
		 * noticia.getData(), noticia.getHora());
		 * 
		 * appStage.setScene(sceneAdicionar); appStage.show(); } catch (IOException e1)
		 * { // TODO Auto-generated catch block e1.printStackTrace(); }
		 * 
		 * });
		 * 
		 * } }
		 * 
		 * };
		 * 
		 * return cell; };
		 * 
		 * tcAcoes.setCellFactory(cellFoctory);
		 */

		atualizaTabela(tblNoticias);

	}

	private void atualizaTabela(TableView<Noticia> tabela) {

		ResultSet result = acessoDB.Select("SELECT * FROM NOTICIAS");

		System.out.println(tabela);

		try {
			tabela.getItems().clear();
			System.out.println("Passou do Clear");
			while (result.next()) {
				tabela.getItems().add(new Noticia(result.getInt("codnoticia"), result.getString("titulo"),
						String.valueOf(result.getString("datacad") + " " + result.getString("horacad")), null));
			}

		} catch (SQLException e) {
			System.out.println("Entrou no Catch do AtualizaTable");
			e.printStackTrace();
		}
	}
}