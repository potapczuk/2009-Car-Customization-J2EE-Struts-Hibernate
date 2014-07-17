package br.com.diegoliveira.indiana.form;

import java.sql.SQLException;
import java.text.ParseException;
import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;

import br.com.diegoliveira.indiana.entity.TipoDePintura;
import java.util.List;

/**
 * Classe Form da entidade TipoDePintura
 * @author Diego Potapczuk
 * @version 0.3
 * @since 0.1
 */
public class TipoDePinturaForm extends AbstractForm {
	private String id;
	private String nome;
    private String preco;
    private List tipoDePinturas;

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
        preco = "";
        tipoDePinturas = null;
	}

	public TipoDePintura getTipoDePintura() throws SQLException, ParseException {
		TipoDePintura tipoDePintura = new TipoDePintura();

		if (id != null && !id.equals(""))
			tipoDePintura.setId(Integer.parseInt(id));
		tipoDePintura.setNome(nome);
        tipoDePintura.setPreco(Double.parseDouble(converteCurrencyParaDouble(preco)));

		return tipoDePintura;
	}

	public void setTipoDePintura(TipoDePintura tipoDePintura) {
		id = String.valueOf(tipoDePintura.getId());
		nome = tipoDePintura.getNome();
        preco = converteDoubleParaCurrency(String.valueOf(tipoDePintura.getPreco()));
	}

    public List getTipoDePinturas() {
        return tipoDePinturas;
    }

    public void setTipoDePinturas(List tipoDePinturas) {
        this.tipoDePinturas = tipoDePinturas;
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

    public String getPreco() {
        return preco;
    }

    public void setPreco(String preco) {
        this.preco = preco;
    }
}