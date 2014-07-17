package br.com.diegoliveira.indiana.form;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;

import br.com.diegoliveira.indiana.entity.Marca;
import java.util.List;

/**
 * Classe Form da entidade Marca
 * @author Diego Potapczuk
 * @version 0.3
 * @since 0.1
 */
public class MarcaForm extends AbstractForm {
	private String id;
	private String nome;
    private List marcas;

    /**
     * Método que valida as informacões do Form
     */
	@Override
	public ActionErrors validate(ActionMapping map, HttpServletRequest req) {
		ActionErrors erros = new ActionErrors();
		// verifica o nome
		if (stringVazia(getNome())) {
			erros.add("Erros", new ActionMessage("erro.campoFaltando",
					"Nome da Marca"));
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
        marcas = null;
	}

	public Marca getMarca() {
		Marca marca = new Marca();

		if (id != null && !id.equals(""))
			marca.setId(Integer.parseInt(id));
		marca.setNome(nome);

		return marca;
	}

	public void setMarca(Marca marca) {
		id = String.valueOf(marca.getId());
		nome = marca.getNome();
	}

        public List getMarcas() {
        return marcas;
    }

    public void setMarcas(List marcas) {
        this.marcas = marcas;
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