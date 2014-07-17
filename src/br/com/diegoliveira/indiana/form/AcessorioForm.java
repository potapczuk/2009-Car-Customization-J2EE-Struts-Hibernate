package br.com.diegoliveira.indiana.form;

import br.com.diegoliveira.indiana.DAO.TipoDeAcessorioDAO;
import java.sql.SQLException;
import java.text.ParseException;
import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;

import br.com.diegoliveira.indiana.entity.Acessorio;
import java.util.List;

/**
 * Classe Form da entidade Acessorio
 * @author Diego Potapczuk
 * @version 0.3
 * @since 0.1
 */
public class AcessorioForm extends AbstractForm {
	private String id;
	private String nome;
    private String descricao;
    private String preco;
    private String tipoDeAcessorio;
    private List acessorios;

    /**
     * Método que valida as informacões do Form
     */
	@Override
	public ActionErrors validate(ActionMapping map, HttpServletRequest req) {
		ActionErrors erros = new ActionErrors();
		// verifica o nome
		if (stringVazia(getNome())) {
			erros.add("Erros", new ActionMessage("erro.campoFaltando",
					"Nome"));
		}
        if (stringVazia(getPreco())) {
			erros.add("Erros", new ActionMessage("erro.campoFaltando",
					"Preço"));
		}
        if (currencyInvalido(getPreco())) {
			erros.add("Erros", new ActionMessage("erro.campoIncorreto",
					"Preço", "25.400,00"));
		}
		return erros;
	}

    /**
     * Método que limpa o Form
     */
	@Override
	public void reset(ActionMapping mapping, HttpServletRequest request) {
		id = "";
		nome = "";
        descricao = "";
        preco = "";
        tipoDeAcessorio = "";
        acessorios = null;
	}

	public Acessorio getAcessorio() throws SQLException, ParseException {
		Acessorio acessorio = new Acessorio();

		if (id != null && !id.equals(""))
			acessorio.setId(Integer.parseInt(id));
		acessorio.setNome(nome);
        acessorio.setDescricao(descricao);
        acessorio.setPreco(Double.parseDouble(converteCurrencyParaDouble(preco)));

        TipoDeAcessorioDAO dao = new TipoDeAcessorioDAO();
        acessorio.setTipoDeAcessorio(dao.procuraById(Integer.valueOf(tipoDeAcessorio)));

		return acessorio;
	}

	public void setAcessorio(Acessorio acessorio) {
		id = String.valueOf(acessorio.getId());
		nome = acessorio.getNome();
        descricao = acessorio.getDescricao();
        preco = converteDoubleParaCurrency(String.valueOf(acessorio.getPreco()));
        tipoDeAcessorio = Integer.toString(acessorio.getTipoDeAcessorio().getId());
	}

    public List getAcessorios() {
        return acessorios;
    }

    public void setAcessorios(List acessorios) {
        this.acessorios = acessorios;
    }

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

    public String getTipoDeAcessorio() {
        return tipoDeAcessorio;
    }

    public void setTipoDeAcessorio(String tipoDeAcessorio) {
        this.tipoDeAcessorio = tipoDeAcessorio;
    }

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getPreco() {
        return preco;
    }

    public void setPreco(String preco) {
        this.preco = preco;
    }
}