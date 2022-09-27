package br.com.Farmacia.bean;

import java.sql.SQLException;
import java.util.ArrayList;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import br.com.Farmacia.DAO.FornecedoresDAO;
import br.com.Farmacia.domain.Fornecedores;
import br.com.Farmacia.util.JSFUtil;

@ManagedBean(name = "MBFornecedores")
@ViewScoped
public class FornecedoresBean {

	private Fornecedores fornecedores;
	private ArrayList<Fornecedores> itens;
	private ArrayList<Fornecedores> itensFiltrados;

	public Fornecedores getFornecedores() {
		return fornecedores;
	}

	public void setFornecedores(Fornecedores fornecedores) {
		this.fornecedores = fornecedores;
	}

	public ArrayList<Fornecedores> getItens() {
		return itens;
	}

	public void setItens(ArrayList<Fornecedores> itens) {
		this.itens = itens;
	}

	public ArrayList<Fornecedores> getItensFiltrados() {
		return itensFiltrados;
	}

	public void setItensFiltrados(ArrayList<Fornecedores> itensFiltrados) {
		this.itensFiltrados = itensFiltrados;
	}

	@PostConstruct
	public void prepararPesquisa() {

		try {
			FornecedoresDAO fdao = new FornecedoresDAO();
			itens = fdao.listar();

		} catch (SQLException e) {
			JSFUtil.adicionarMensagemErro("ex.getMessage()");
			e.printStackTrace();
		}

	}

	public void prepararNovo() {
		fornecedores = new Fornecedores();
	}

	public void novo() {

		try {
			FornecedoresDAO fdao = new FornecedoresDAO();
			fdao.salvar(fornecedores);

			itens = fdao.listar();

			JSFUtil.adicionarMensagemSucesso("Fornecedor salvo com sucesso!");

		} catch (SQLException e) {
			JSFUtil.adicionarMensagemErro("ex.getMessage()");
			e.printStackTrace();
		}
	}

	public void excluir() {
		try {
			FornecedoresDAO fdao = new FornecedoresDAO();
			fdao.excluir(fornecedores);

			itens = fdao.listar();

			JSFUtil.adicionarMensagemSucesso("Fornecedor excluido com sucesso!");

		} catch (SQLException e) {
			JSFUtil.adicionarMensagemErro("Não é possível excluir um fornecedor que tenha um produto associado!");
			e.printStackTrace();
		}
	}

	public void editar() {
		try {
			FornecedoresDAO fdao = new FornecedoresDAO();
			fdao.editar(fornecedores);

			itens = fdao.listar();

			JSFUtil.adicionarMensagemSucesso("Fornecedor editado com sucesso!");

		} catch (SQLException e) {
			JSFUtil.adicionarMensagemErro("ex.getMessage()");
			e.printStackTrace();
		}
	}
}