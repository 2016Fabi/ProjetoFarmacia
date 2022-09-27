package br.com.Farmacia.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import br.com.Farmacia.domain.Fornecedores;
import br.com.Farmacia.domain.Produtos;
import br.com.Farmacia.factory.ConexaoFactory;

public class ProdutoDAO {
	public void salvar(Produtos p) throws SQLException {
		StringBuilder sql = new StringBuilder();
		sql.append("INSERT INTO produtos ");
		sql.append("(descricao, preco, quantidade, fornecedores_codigo) ");
		sql.append("VALUES (?, ?, ?, ?)");

		Connection conexao = ConexaoFactory.conectar();

		PreparedStatement comando = conexao.prepareStatement(sql.toString());

		comando.setString(1, p.getDescricao());
		comando.setDouble(2, p.getPreco());
		comando.setLong(3, p.getQuantidade());
		comando.setLong(4, p.getFornecedores().getCodigo());
		comando.executeUpdate();

	}

	public ArrayList<Produtos> listar() throws SQLException {
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT p.codigo, p.descricao, p.preco, p.quantidade, f.codigo, f.descricao ");
		sql.append("FROM produtos p ");
		sql.append("INNER JOIN fornecedores f ON f.codigo = p.fornecedores_codigo ");

		Connection conexao = ConexaoFactory.conectar();

		PreparedStatement comando = conexao.prepareStatement(sql.toString());

		ResultSet resultado = comando.executeQuery();

		ArrayList<Produtos> lista = new ArrayList<Produtos>();

		while (resultado.next()) {
			Fornecedores f = new Fornecedores();
			f.setCodigo(resultado.getLong("f.codigo"));
			f.setDescricao(resultado.getString("f.descricao"));

			Produtos p = new Produtos();
			p.setCodigo(resultado.getLong("p.codigo"));
			p.setDescricao(resultado.getString("p.descricao"));
			p.setPreco(resultado.getDouble("p.preco"));
			p.setQuantidade(resultado.getLong("p.quantidade"));
			p.setFornecedores(f);

			lista.add(p);
		}

		return lista;
	}

	public void excluir(Produtos p) throws SQLException {
		StringBuilder sql = new StringBuilder();
		sql.append("DELETE FROM produtos ");
		sql.append("WHERE codigo = ? ");

		Connection conexao = ConexaoFactory.conectar();

		PreparedStatement comando = conexao.prepareStatement(sql.toString());
		comando.setLong(1, p.getCodigo());
		comando.executeUpdate();

	}

	public void editar(Produtos p) throws SQLException {
		StringBuilder sql = new StringBuilder();
		sql.append("UPDATE produtos ");
		sql.append("SET descricao = ?, preco = ?, quantidade = ?, fornecedores_codigo = ? ");
		sql.append("WHERE codigo = ? ");

		Connection conexao = ConexaoFactory.conectar();

		PreparedStatement comando = conexao.prepareStatement(sql.toString());

		comando.setString(1, p.getDescricao());
		comando.setDouble(2, p.getPreco());
		comando.setLong(3, p.getQuantidade());
		comando.setLong(4, p.getFornecedores().getCodigo());
		comando.setLong(5, p.getCodigo());

		comando.executeUpdate();

	}
}
