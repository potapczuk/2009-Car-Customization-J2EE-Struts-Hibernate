package br.com.diegoliveira.indiana.form;

import br.com.diegoliveira.indiana.DAO.CarroDAO;
import java.sql.SQLException;
import java.text.ParseException;
import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;

import br.com.diegoliveira.indiana.entity.Modelo;
import java.util.List;

/**
 * Classe Form da entidade Modelo
 * @author Diego Potapczuk
 * @version 0.3
 * @since 0.1
 */
public class ModeloForm extends AbstractForm {
	private String id;
	private String nome;
    private String descricao;
    private String id_carro;
    private String preco;
    private List modelos;

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
        id_carro = "";
        modelos = null;
	}

	public Modelo getModelo() throws SQLException, ParseException {
		Modelo modelo = new Modelo();

		if (id != null && !id.equals(""))
			modelo.setId(Integer.parseInt(id));
		modelo.setNome(nome);
        modelo.setDescricao(descricao);
        modelo.setPreco(Double.parseDouble(converteCurrencyParaDouble(preco)));

        CarroDAO cdao = new CarroDAO();
        modelo.setCarro(cdao.procuraById(Integer.valueOf(id_carro)));

		return modelo;
	}

	public void setModelo(Modelo modelo) {
		id = String.valueOf(modelo.getId());
		nome = modelo.getNome();
        descricao = modelo.getDescricao();
        preco = converteDoubleParaCurrency(String.valueOf(modelo.getPreco()));
        id_carro = Integer.toString(modelo.getCarro().getId());
	}

    public List getModelos() {
        return modelos;
    }

    public void setModelos(List modelos) {
        this.modelos = modelos;
    }

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
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

    public String getId_carro() {
        return id_carro;
    }

    public void setId_carro(String id_carro) {
        this.id_carro = id_carro;
    }

    public String getPreco() {
        return preco;
    }

    public void setPreco(String preco) {
        this.preco = preco;
    }
}