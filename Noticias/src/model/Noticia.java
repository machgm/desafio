package model;

import javafx.scene.control.Button;

public class Noticia {
	
	private int id;
	private String titulo;
	private String data;
	private String hora; 
	private String dataHora;
	private String descricao;
	private Button edit;
	
	public Noticia(int id, String titulo, String dataHora, String descricao) {
		// TODO Auto-generated constructor stub
		
		this.dataHora = dataHora;
		this.descricao = descricao;
		this.id = id;
		this.titulo = titulo;
		this.edit = new Button("Editar");
		
	}

	
	
	public Button getEdit() {
		return edit;
	}

	public void setEdit(Button edit) {
		this.edit = edit;
	}

	public String getDataHora() {
		return dataHora;
	}

	public void setDataHora(String dataHora) {
		this.dataHora = dataHora;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}

	public String getHora() {
		return hora;
	}

	public void setHora(String hora) {
		this.hora = hora;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	
	
}
