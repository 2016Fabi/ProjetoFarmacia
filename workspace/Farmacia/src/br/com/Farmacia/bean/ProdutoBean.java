package br.com.Farmacia.bean;

import java.sql.SQLException;
import java.util.ArrayList;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import br.com.Farmacia.DAO.FornecedoresDAO;
import br.com.Farmacia.DAO.ProdutoDAO;
import br.com.Farmacia.domain.Fornecedores;
import br.com.Farmacia.domain.Produtos;
import br.com.Farmacia.util.JSFUtil;

@ManagedBean(name = "MBProduto")
@ViewScoped
public class ProdutoBean {

	private Produtos produtos;
	private ArrayList<Produtos> itens;
	private ArrayList<Produtos> itensFiltrados;

	private ArrayList<Fornecedores> comboFornecedores;

	public Produtos getProdutos() {
		return produtos;
	}

	public void setProdutos(Produtos produtos) {
		this.produtos = produtos;
	}

	public ArrayList<Fornecedores> getComboFornecedores() {
		return comboFornecedores;
	}

	public void setComboFornecedores(ArrayList<Fornecedores> comboFornecedores) {
		this.comboFornecedores = comboFornecedores;
	}

	public ArrayList<Produtos> getItens() {
		return itens;
	}

	public void setItens(ArrayList<Produtos> itens) {
		this.itens = itens;
	}

	public ArrayList<Produtos> getItensFiltrados() {
		return itensFiltrados;
	}

	public void setItensFiltrados(ArrayList<Produtos> itensFiltrados) {
		this.itensFiltrados = itensFiltrados;
	}

	@PostConstruct
	public void prepararPesquisa() {

		try {
			ProdutoDAO fdao = new ProdutoDAO();
			itens = fdao.listar();

		} catch (SQLException e) {
			 JSFUtil.adicionarMensagemErro("ex.getMessage()");
			e.printStackTrace();
		}

	}

	public void prepararNovo() {

		try {
			produtos = new Produtos();
			FornecedoresDAO dao = new FornecedoresDAO();
			comboFornecedores = dao.listar();
		} catch (SQLException e) {
			 JSFUtil.adicionarMensagemErro("ex.getMessage()");
			e.printStackTrace();
		}
	}

	public void novo() {

		try {
			ProdutoDAO fdao = new ProdutoDAO();
			fdao.salvar(produtos);

			itens = fdao.listar();

			 JSFUtil.adicionarMensagemSucesso("Produto salvo com sucesso!");

		} catch (SQLException e) {
			 JSFUtil.adicionarMensagemErro("ex.getMessage()");
			e.printStackTrace();
		}
	}

	public void excluir() {
		try {
			ProdutoDAO fdao = new ProdutoDAO();
			fdao.excluir(produtos);

			itens = fdao.listar();

			 JSFUtil.adicionarMensagemSucesso("Produto excluido com sucesso!");

		} catch (SQLException e) {
			 JSFUtil.adicionarMensagemErro("ex.getMessage()");
			e.printStackTrace();
		}
	}

	public void editar() {
		try {
			ProdutoDAO fdao = new ProdutoDAO();
			fdao.editar(produtos);

			itens = fdao.listar();

			 JSFUtil.adicionarMensagemSucesso("Produto editado com sucesso!");

		} catch (SQLException e) {
			 JSFUtil.adicionarMensagemErro("ex.getMessage()");
			e.printStackTrace();
		}
	}

	public void prepararEditar() {

		try {
			produtos = new Produtos();
			FornecedoresDAO dao = new FornecedoresDAO();
			comboFornecedores = dao.listar();
		} catch (SQLException e) {
			 JSFUtil.adicionarMensagemErro("ex.getMessage()");
			e.printStackTrace();
		}
	}

}
