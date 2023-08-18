package br.com.Farmacia.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import br.com.Farmacia.domain.Fornecedores;
import br.com.Farmacia.factory.ConexaoFactory;

public class FornecedoresDAO {
	public void salvar(Fornecedores f) throws SQLException {
		StringBuilder sql = new StringBuilder();
		sql.append("INSERT INTO fornecedores ");
		sql.append("(descricao) ");
		sql.append("VALUES (?)");

		Connection conexao = ConexaoFactory.conectar();

		PreparedStatement comando = conexao.prepareStatement(sql.toString());
		comando.setString(1, f.getDescricao());
		comando.executeUpdate();

	}

	public void excluir(Fornecedores f) throws SQLException {
		StringBuilder sql = new StringBuilder();
		sql.append("DELETE FROM fornecedores ");
		sql.append("WHERE codigo = ? ");

		Connection conexao = ConexaoFactory.conectar();

		PreparedStatement comando = conexao.prepareStatement(sql.toString());
		comando.setLong(1, f.getCodigo());
		comando.executeUpdate();

	}

	public void editar(Fornecedores f) throws SQLException {
		StringBuilder sql = new StringBuilder();
		sql.append("UPDATE fornecedores ");
		sql.append("SET descricao = ? ");
		sql.append("WHERE codigo = ? ");

		Connection conexao = ConexaoFactory.conectar();

		PreparedStatement comando = conexao.prepareStatement(sql.toString());

		comando.setString(1, f.getDescricao());
		comando.setLong(2, f.getCodigo());
		comando.executeUpdate();

	}

	public Fornecedores buscaPorCodigo(Fornecedores f) throws SQLException {
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT codigo, descricao ");
		sql.append("FROM fornecedores ");
		sql.append("WHERE codigo = ? ");

		Connection conexao = ConexaoFactory.conectar();

		PreparedStatement comando = conexao.prepareStatement(sql.toString());

		comando.setLong(1, f.getCodigo());

		ResultSet resultado = comando.executeQuery();
		Fornecedores retorno = null;

		if (resultado.next()) {
			retorno = new Fornecedores();
			retorno.setCodigo(resultado.getLong("codigo"));
			retorno.setDescricao(resultado.getString("descricao"));
		}

		return retorno;
	}

	public ArrayList<Fornecedores> buscarPorDescricao(Fornecedores f) throws SQLException {

		StringBuilder sql = new StringBuilder();
		sql.append("SELECT codigo, descricao ");
		sql.append("FROM fornecedores ");
		sql.append("WHERE descricao LIKE ? ");
		sql.append("ORDER BY descricao ASC ");

		Connection conexao = ConexaoFactory.conectar();

		PreparedStatement comando = conexao.prepareStatement(sql.toString());

		comando.setString(1, "%" + f.getDescricao() + "%");

		ResultSet resultado = comando.executeQuery();

		ArrayList<Fornecedores> lista = new ArrayList<Fornecedores>();

		while (resultado.next()) {
			Fornecedores item = new Fornecedores();
			item.setCodigo(resultado.getLong("codigo"));
			item.setDescricao(resultado.getString("descricao"));

			lista.add(item);
		}

		return lista;
	}

	public ArrayList<Fornecedores> listar() throws SQLException {
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT codigo, descricao ");
		sql.append("FROM fornecedores ");
		sql.append("ORDER BY descricao ASC ");

		Connection conexao = ConexaoFactory.conectar();

		PreparedStatement comando = conexao.prepareStatement(sql.toString());

		ResultSet resultado = comando.executeQuery();

		ArrayList<Fornecedores> lista = new ArrayList<Fornecedores>();

		while (resultado.next()) {
			Fornecedores f = new Fornecedores();
			f.setCodigo(resultado.getLong("codigo"));
			f.setDescricao(resultado.getString("descricao"));

			lista.add(f);
		}

		return lista;
	}

	public static void main(String[] args) {

		// Salvar
//		Fornecedores f1 = new Fornecedores();
//		f1.setDescricao("ROBERTO");
//		
//		Fornecedores f2 = new Fornecedores();
//		f2.setDescricao("VALDIR");
//		
//		FornecedoresDAO fdao = new FornecedoresDAO();
//		
//		try {
//		 fdao.salvar(f1);
//		 fdao.salvar(f2);
//		 System.out.println("Salvo com sucesso!!");
//		
//		} catch (SQLException e) {
//		 System.out.println("Erro ao salvar");
//		 e.printStackTrace();
//		}

		// Excluir
//		Fornecedores f1 = new Fornecedores();
//		f1.setCodigo(3L);
//		
//		FornecedoresDAO fdao = new FornecedoresDAO();
//		
//		try {
//		 fdao.excluir(f1);
//		
//		 System.out.println("Deletado com sucesso!!");
//		
//		} catch (SQLException e) {
//		 System.out.println("Erro ao deletar");
//		 e.printStackTrace();
//		}

		// Editar
//		Fornecedores f1 = new Fornecedores();
//		f1.setCodigo(1L);
//		f1.setDescricao("HUGO VASCONCELOS");
//		
//		FornecedoresDAO fdao = new FornecedoresDAO();
//		
//		try {
//		 fdao.editar(f1);
//		
//		 System.out.println("Editado com sucesso!!");
//		
//		} catch (SQLException e) {
//		 System.out.println("Erro ao Editar");
//		 e.printStackTrace();
//		}

		// buscaPorCodigo
//		Fornecedores f1 = new Fornecedores();
//		f1.setCodigo(1L);
//
//		Fornecedores f2 = new Fornecedores();
//		f2.setCodigo(5L);
//
//		FornecedoresDAO fdao = new FornecedoresDAO();
//
//		try {
//			Fornecedores f3 = fdao.buscaPorCodigo(f1);
//			Fornecedores f4 = fdao.buscaPorCodigo(f2);
//
//			System.out.println("Resultado 1: " + f3);
//			System.out.println("Resultado 2: " + f4);
//
//		} catch (SQLException e) {
//			System.out.println("Erro ao buscar");
//			e.printStackTrace();
//		}

		//buscarPorDescricao
//		FornecedoresDAO fdao = new FornecedoresDAO();
//		 
//		try {
//			ArrayList<Fornecedores>lista = fdao.listar();
//		   for (Fornecedores f : lista){ System.out.println("Resultado " + f); }
//	    } catch (SQLException e) { 
//	    	System.out.println("Erro ao buscar");
//		    e.printStackTrace(); 
//		}
		 

		 //listar
//		 Fornecedores f1 = new Fornecedores(); f1.setDescricao("de");
//		 FornecedoresDAO fdao = new FornecedoresDAO();
//		 try {
//			 ArrayList<Fornecedores>lista = fdao.buscarPorDescricao(f1);
//		     for (Fornecedores f : lista){ System.out.println("Resultado " + f); }
//		} catch (SQLException e) { 
//			 System.out.println("Erro ao buscar");
//		     e.printStackTrace(); 
//		 }
//		 
	 }
}
