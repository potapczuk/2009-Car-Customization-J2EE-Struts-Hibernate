package br.com.diegoliveira.indiana.form;

import java.sql.SQLException;
import java.text.ParseException;
import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;

import br.com.diegoliveira.indiana.entity.TipoDeAcessorio;
import java.util.List;

/**
 * Classe Form da entidade TipoDeAcessorio
 * @author Diego Potapczuk
 * @version 0.3
 * @since 0.1
 */
public class TipoDeAcessorioForm extends AbstractForm {
	private String id;
	private String nome;
    private List tipoDeAcessorios;

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
		return erros;
	}

    /**
     * Método que limpa o Form
     */
	@Override
	public void reset(ActionMapping mapping, HttpServletRequest request) {
		id = "";
		nome = "";
        tipoDeAcessorios = null;
	}

	public TipoDeAcessorio getTipoDeAcessorio() throws SQLException, ParseException {
		TipoDeAcessorio tipoDeAcessorio = new TipoDeAcessorio();

		if (id != null && !id.equals(""))
			tipoDeAcessorio.setId(Integer.parseInt(id));
		tipoDeAcessorio.setNome(nome);

		return tipoDeAcessorio;
	}

	public void setTipoDeAcessorio(TipoDeAcessorio tipoDeAcessorio) {
		id = String.valueOf(tipoDeAcessorio.getId());
		nome = tipoDeAcessorio.getNome();
	}

    public List getTipoDeAcessorios() {
        return tipoDeAcessorios;
    }

    public void setTipoDeAcessorios(List tipoDeAcessorios) {
        this.tipoDeAcessorios = tipoDeAcessorios;
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
}