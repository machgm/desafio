package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class AcessoDB {
	
	static final String JDBC_DRIVER = "org.mariadb.jdbc.Driver";
    static final String DB_URL = "jdbc:sqlserver://LOCALHOST\\SQLEXPRESS01;databaseName=desafio";
    //  Database credentials
    static final String USER = "sa";
    static final String PASS = "233549";
    
    Connection conexaoDB = null;
    Statement statement = null;
    PreparedStatement preparedStatement = null;
    

    public AcessoDB() {
    	
	}
	
    
	private Connection conexaoBD() {

		try {
			conexaoDB = DriverManager.getConnection(DB_URL,USER,PASS);
			System.out.println("Conexão Realizada com SUCESSO!");
			return conexaoDB;
			
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Ocorreu uma FALHA ao realizar a conexão");
			return null;
		}
	}
	
	public ResultSet Select (String sql) {
		
		Connection conexao = conexaoBD();
		
		try {
			statement = conexao.createStatement();
			ResultSet result = statement.executeQuery(sql);
			System.out.println("Entrou nos resultados. ");
			return result;
		} catch (SQLException e) {
			System.out.println("Falha ao executar SELECT");
			e.printStackTrace();
			return null;
		}
	}
	
    public void testeConexao() {
    	
    	Connection conexao = conexaoBD();
    	try {
    		
			conexao.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
    }
	
	public Boolean Delete (String sql) {
		
		Connection conexao = conexaoBD();
		
		try {
			statement = conexao.createStatement();
			preparedStatement = conexao.prepareStatement(sql);
			preparedStatement.executeUpdate();
			System.out.println("Instrução Delete Executada");
			return true;
		} catch (SQLException e) {
			System.out.println("Falha ao executar SELECT");
			e.printStackTrace();
			return false;
		}finally {
			try {
				conexao.close();
			} catch (SQLException e) {
				System.out.println("Ocorreu uma FALHA ao fechar a conexão");
				e.printStackTrace();
			}
		}
		
	}
	
	public Boolean Update(String sql) {
		
		Connection conexao = conexaoBD();
		
		try {
			statement = conexao.createStatement();
			preparedStatement = conexao.prepareStatement(sql);
			preparedStatement.executeUpdate();
			System.out.println("Instrução Update Executada");
			return true;
		} catch (SQLException e) {
			System.out.println("Falha ao executar UPDATE");
			e.printStackTrace();
			return false;
		}finally {
			try {
				conexao.close();
			} catch (SQLException e) {
				System.out.println("Ocorreu uma FALHA ao fechar a conexão");
				e.printStackTrace();
			}
		}
	}
	
	public boolean Insert(String sql) {
		System.out.println("Entrou no Insert");
		Connection conexao = conexaoBD();
		
		try {
			statement = conexao.createStatement();
			preparedStatement = conexao.prepareStatement(sql);
			preparedStatement.executeUpdate();
			System.out.println("Instrução Insert Executada");
			return true;
		} catch (SQLException e) {
			System.out.println("Falha ao executar INSERT");
			e.printStackTrace();
			return false;
		}finally {
			try {
				conexao.close();
			} catch (SQLException e) {
				System.out.println("Ocorreu uma FALHA ao fechar a conexão");
				e.printStackTrace();
			}
		}
	}
}


