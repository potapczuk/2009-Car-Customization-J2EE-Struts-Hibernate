package br.com.diegoliveira.indiana.form;

import br.com.diegoliveira.indiana.DAO.TipoDePinturaDAO;
import java.sql.SQLException;
import java.text.ParseException;
import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;

import br.com.diegoliveira.indiana.entity.Pintura;
import java.util.List;

/**
 * Classe Form da entidade Pintura
 * @author Diego Potapczuk
 * @version 0.3
 * @since 0.1
 */
public class PinturaForm extends AbstractForm {
	private String id;
	private String nome;
    private String tipoDePintura;
    private List pinturas;

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
        tipoDePintura = "";
        pinturas = null;
	}

	public Pintura getPintura() throws SQLException, ParseException {
		Pintura pintura = new Pintura();

		if (id != null && !id.equals(""))
			pintura.setId(Integer.parseInt(id));
		pintura.setNome(nome);

        TipoDePinturaDAO dao = new TipoDePinturaDAO();
        pintura.setTipoDePintura(dao.procuraById(Integer.valueOf(tipoDePintura)));

		return pintura;
	}

	public void setPintura(Pintura pintura) {
		id = String.valueOf(pintura.getId());
		nome = pintura.getNome();
        tipoDePintura = Integer.toString(pintura.getTipoDePintura().getId());
	}

    public List getPinturas() {
        return pinturas;
    }

    public void setPinturas(List pinturas) {
        this.pinturas = pinturas;
    }

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

    public String getTipoDePintura() {
        return tipoDePintura;
    }

    public void setTipoDePintura(String tipoDePintura) {
        this.tipoDePintura = tipoDePintura;
    }

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}
}